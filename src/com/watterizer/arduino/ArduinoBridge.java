/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArduinoBridge {

    private ArrayList<ConsoleHandler> consoleHandlers = new ArrayList<>();
    private ArrayList<DisconnectHandler> disconnectHandlers = new ArrayList<>();
    private boolean isBeingChecked = false;
    private boolean isDisconnected;
    private CommPortIdentifier portId = null;
    private SerialPort serialPort = null;
    private BufferedReader input;
    private static final String PORT_NAMES[] = {
        "/dev/usbdev", // Linux
        "/dev/tty", // Linux
        "/dev/serial", // Linux
        "COM3", "COM8" // Windows
    };

    public ArduinoBridge() {
        consoleHandlers = new ArrayList<>();
        disconnectHandlers = new ArrayList<>();
    }

    public boolean checkConnection() {
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }

        if (portId == null) {
            isDisconnected = true;

            disconnectHandlers.stream().forEach((handler) -> {
                handler.onDisconnect(portId);
            });

            return false;
        } else {
            isDisconnected = false;
            return true;
        }
    }

    public boolean openConnection() throws PortInUseException, UnsupportedCommOperationException, IOException, TooManyListenersException {
        if (isDisconnected) {
            if (!checkConnection()) {
                return false;
            }
        } else {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    2000);

            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            serialPort.notifyOnDataAvailable(true);
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

            serialPort.addEventListener((SerialPortEvent spe) -> {
                if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    try {
                        if (checkConnection()) {
                            for (ConsoleHandler handler : consoleHandlers) {
                                handler.onConsoleUpdated(new ConsoleEvent(input.readLine(), serialPort, spe.getEventType(),
                                        spe.getNewValue(), spe.getOldValue()));
                            }
                        } else {
                            disconnectHandlers.stream().forEach((handler) -> {
                                handler.onDisconnect(portId);
                            });
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ArduinoBridge.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //do something
                }
            });
        }

        return true;
    }

    public void startCheckIn() {
        if (!isBeingChecked) {
            isBeingChecked = true;
            new Thread("Connection Check-In") {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if (!checkConnection()) {
                                break;
                            }
                            sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ArduinoBridge.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();
        }
    }

    public boolean waitForConnection(long time) {
        long start = System.currentTimeMillis();
        long end = start + time;

        while (System.currentTimeMillis() < end) {
            if (checkConnection()) {
                try {
                    openConnection();
                } catch (PortInUseException | UnsupportedCommOperationException | IOException | TooManyListenersException ex) {
                    Logger.getLogger(ArduinoBridge.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        }

        return false;
    }

    public void addDisconnectHandler(DisconnectHandler handler) {
        disconnectHandlers.add(handler);
    }

    public void removeDisconnecHandler(DisconnectHandler handler) {
        disconnectHandlers.remove(handler);
    }

    public void removeDisconnecHandler(int pos) {
        disconnectHandlers.remove(pos);
    }

    public void removeAllDisconnecHandlers() {
        disconnectHandlers.clear();
    }

    public void addConsoleHandler(ConsoleHandler handler) {
        consoleHandlers.add(handler);
    }

    public void removeConsoleHandler(ConsoleHandler handler) {
        consoleHandlers.remove(handler);
    }

    public void removeConsoleHandler(int pos) {
        consoleHandlers.remove(pos);
    }

    public void removeAllConsoleHandlers() {
        consoleHandlers.clear();
    }

    public interface DisconnectHandler {

        void onDisconnect(CommPortIdentifier id);
    }

    public interface ConsoleHandler {

        void onConsoleUpdated(ConsoleEvent evt);
    }

    public class ConsoleEvent extends SerialPortEvent {

        private final String read;

        public ConsoleEvent(String read, SerialPort sp, int i, boolean bln, boolean bln1) {
            super(sp, i, bln, bln1);
            this.read = read;
        }

        public String getConsoleOutput() {
            return read;
        }

    }
}
