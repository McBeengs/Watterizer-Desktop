/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.modals;

import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Minutes;
import org.joda.time.Period;

/**
 *
 * @author 15153769
 */
public class MainLeecherJFrame extends JFrame {

    private final XmlManager xml;

    public MainLeecherJFrame() {
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);

        if (SystemTray.isSupported()) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    SystemTray tray = SystemTray.getSystemTray();
                    PopupMenu popup = new PopupMenu();
                    MenuItem defaultItem = new MenuItem("Logout");
                    setVisible(false);
                    defaultItem.addActionListener((ActionEvent e1) -> {
                        System.out.println(UsefulMethods.getUserModel().getPerfil().toLowerCase());
                        if (UsefulMethods.getUserModel().getPerfil().toLowerCase().equals("administrador")) {
                            adminLogout();
                        } else {
                            regularLogout();
                        }
                    });
                    popup.add(defaultItem);
                    TrayIcon trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/ic_logo_16.png")).getImage(), "Watterizer", popup);

                    try {
                        tray.add(trayIcon);
                    } catch (AWTException ex) {

                    }
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                    if (SystemTray.isSupported()) {
                        SystemTray tray = SystemTray.getSystemTray();

                        for (TrayIcon icon : tray.getTrayIcons()) {
                            tray.remove(icon);
                        }

                    }
                }
            });
        }

        setSize(1, 1);
        setUndecorated(true);
        setVisible(true);
        setState(JFrame.ICONIFIED);
    }

    private void adminLogout() {
        DateTimeZone brazil = DateTimeZone.forID("America/Sao_Paulo");
        DateTime dt = new DateTime(UsefulMethods.getUserModel().getHoraIntervalo());
        DateTime horaIntervalo = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(), new DateTime().getDayOfMonth(),
                dt.hourOfDay().get(), dt.minuteOfHour().get(), brazil);
        dt = new DateTime(UsefulMethods.getUserModel().getHoraSaida());
        DateTime horaSaida = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(), new DateTime().getDayOfMonth(),
                dt.hourOfDay().get(), dt.minuteOfHour().get(), brazil);
        dt = new DateTime();
        DateTime now = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.hourOfDay().get(), dt.minuteOfHour().get(), brazil);
        String s;

        if (Minutes.minutesBetween(now, horaIntervalo).getMinutes() <= 0) {
            //make condition of letting the program to be closed within 10 min

            if (Minutes.minutesBetween(now, horaSaida).getMinutes() <= 0) {

            } else {
                String time = "";
                s = "a saida";
                String text = "<html><body>Ainda falta &time para " + s + ". Tem certeza que"
                        + " deseja sair?</html></body>";

                Period period = new Period(now, horaSaida);
                if (period.getHours() > 0) {
                    if (period.getHours() > 1) {
                        time += period.getHours() + " horas";
                    } else {
                        time += period.getHours() + " hora";
                    }
                } else if (period.getMinutes() > 0) {
                    if (period.getMinutes() > 1) {
                        time += period.getMinutes() + " minutos";
                    } else {
                        time += period.getMinutes() + " minuto";
                    }
                } else if (period.getSeconds() > 0) {
                    if (period.getSeconds() > 1) {
                        time += period.getSeconds() + " segundos";
                    } else {
                        time += period.getSeconds() + " segundo";
                    }
                }

                JLabel label = new JLabel(text.replace("&time", time), JLabel.CENTER);
                label.setForeground(Color.white);

                if (JOptionPane.showConfirmDialog(null, label, "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        s = "{\n"
                                + "\"token\":\"" + UsefulMethods.getUserModel().getTokenDesktop() + "\",\n"
                                + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + "\"\n"
                                + "}";
                        UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0) + "/logout", "POST", s);
                    } catch (ProtocolException ex) {
                        Logger.getLogger(MainSeederJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainSeederJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    UsefulMethods.setIsShutdownTrue(true);
                    System.exit(0);
                }
            }
        } else {
            String time = "";
            s = "o intervalo";
            String text = "<html><body>Ainda falta &time para " + s + ". Tem certeza que"
                    + " deseja sair?</html></body>";

            Period period = new Period(now, horaIntervalo);
            if (period.getHours() > 0) {
                if (period.getHours() > 1) {
                    time += period.getHours() + " horas";
                } else {
                    time += period.getHours() + " hora";
                }
            } else if (period.getMinutes() > 0) {
                if (period.getMinutes() > 1) {
                    time += period.getMinutes() + " minutos";
                } else {
                    time += period.getMinutes() + " minuto";
                }
            } else if (period.getSeconds() > 0) {
                if (period.getSeconds() > 1) {
                    time += period.getSeconds() + " segundos";
                } else {
                    time += period.getSeconds() + " segundo";
                }
            }

            JLabel label = new JLabel(text.replace("&time", time), JLabel.CENTER);
            label.setForeground(Color.white);

            if (JOptionPane.showConfirmDialog(null, label, "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                s = "{\n"
                        + "\"token\":\"" + UsefulMethods.getUserModel().getTokenDesktop() + "\",\n"
                        + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + "\"\n"
                        + "}";

                try {
                    UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0) + "/logout", "POST", s);
                } catch (ProtocolException ex) {
                    Logger.getLogger(MainSeederJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainSeederJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                UsefulMethods.setIsShutdownTrue(true);
                System.exit(0);
            }
        }
    }

    private void regularLogout() {
        DateTimeZone brazil = DateTimeZone.forID("America/Sao_Paulo");
        DateTime dt = new DateTime(UsefulMethods.getUserModel().getHoraSaida());
        DateTime horaSaida = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(), new DateTime().getDayOfMonth(),
                dt.hourOfDay().get(), dt.minuteOfHour().get(), brazil);
        dt = new DateTime();
        DateTime now = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.hourOfDay().get(), dt.minuteOfHour().get(), brazil);
        String s;

        if (Minutes.minutesBetween(now, horaSaida).getMinutes() <= 0) {
            try {
                s = "{\n"
                        + "\"token\":\"" + UsefulMethods.getUserModel().getTokenDesktop() + "\",\n"
                        + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + "\"\n"
                        + "}";
                UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0) + "/logout", "POST", s);
            } catch (ProtocolException ex) {
                Logger.getLogger(MainSeederJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainSeederJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            UsefulMethods.setIsShutdownTrue(true);
            System.exit(0);
        } else {
            String time = "";
            s = "a saida";
            String text = "<html><body>Ainda falta &time para " + s + ".</html></body>";

            Period period = new Period(now, horaSaida);
            if (period.getHours() > 0) {
                if (period.getHours() > 1) {
                    time += period.getHours() + " horas";
                } else {
                    time += period.getHours() + " hora";
                }
            } else if (period.getMinutes() > 0) {
                if (period.getMinutes() > 1) {
                    time += period.getMinutes() + " minutos";
                } else {
                    time += period.getMinutes() + " minuto";
                }
            } else if (period.getSeconds() > 0) {
                if (period.getSeconds() > 1) {
                    time += period.getSeconds() + " segundos";
                } else {
                    time += period.getSeconds() + " segundo";
                }
            }

            JLabel label = new JLabel(text.replace("&time", time), JLabel.CENTER);
            label.setForeground(Color.white);

            JOptionPane.showMessageDialog(null, label, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
