/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.arduino;

import gnu.io.CommPortIdentifier;

/**
 *
 * @author 15153769
 */
public class Watterizer {

    /**
     * @param args the command line arguments
     */
    
    static long time = System.currentTimeMillis();
    
    public static void main(String[] args) {
        ArduinoBridge arduinoBridge = new ArduinoBridge("COM8");

        System.out.println("Waiting connection...");

        if (arduinoBridge.waitForConnection(5000)) {
            System.out.println("Sucsess");
            System.err.println("Start reading lines from console...");
            System.err.println("-------------");

            arduinoBridge.addDisconnectHandler((CommPortIdentifier id) -> {
                System.out.println("ops");
            });

            arduinoBridge.addConsoleHandler((ArduinoBridge.ConsoleEvent evt) -> {
                System.err.print("Read line: ");
                System.out.print(evt.getConsoleOutput());
                long now = System.currentTimeMillis();
                System.out.println(" | " + (time - now));
                time = now;
            });
        } else {
            System.err.println("Failed");
        }
    }

}
