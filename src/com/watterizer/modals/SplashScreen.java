package com.watterizer.modals;

import com.watterizer.panels.GenericErrorJPanel;
import com.watterizer.panels.LoginJPanel;
import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.util.OpaqueScreen;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SplashScreen extends javax.swing.JFrame {

    private String errorDetail;
    private final XmlManager xml;
    private final XmlManager language;

    @SuppressWarnings("")
    public SplashScreen() {
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        language = UsefulMethods.getManagerInstance(UsefulMethods.LANGUAGE);

        if (!checkAmbient()) {
            System.err.println("deu ruim");
        } else {
            setUndecorated(true);
            initComponents();
            getContentPane().setVisible(false);

        }

        fadeInSplash(2000, (ActionEvent e) -> {
            // Checar arquivo de configurações para identificar terminal "Seeder" ou "Leecher"
            // Caso seja Seeder:
            if (xml.getContentByName("terminalType", 0).equals("0")) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            executeTests(true);
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();
            } else {
                //caso seja leecher
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            executeTests(false);
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();
            }
        });
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoDisplayer = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        splashLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Watterizer");
        setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.0f));
        setMinimumSize(new java.awt.Dimension(1005, 240));
        setResizable(false);
        getContentPane().setLayout(null);

        infoDisplayer.setBackground(new java.awt.Color(0, 0, 0));
        infoDisplayer.setForeground(new java.awt.Color(255, 255, 255));
        infoDisplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoDisplayer.setOpaque(true);
        getContentPane().add(infoDisplayer);
        infoDisplayer.setBounds(20, 160, 280, 30);

        jProgressBar1.setBorder(new RoundedCornerBorder(25, 25, Color.BLACK));
        jProgressBar1.setOpaque(true);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(320, 160, 490, 30);

        splashLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/splash.png"))); // NOI18N
        getContentPane().add(splashLogo);
        splashLogo.setBounds(0, -50, 960, 310);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fadeInSplash(int millis, ActionListener callback) {
        long start = System.currentTimeMillis();
        final Timer t = new Timer(0, null);

        t.addActionListener((ActionEvent e) -> {
            getContentPane().setVisible(true);
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > millis) {
                t.stop();
                if (callback != null) {
                    callback.actionPerformed(e);
                }
            } else {
                setOpacity(0.0f + (float) elapsed / millis);
            }
        });

        t.start();
    }

    private void fadeOutSplash(int millis, ActionListener callback) {
        long start = System.currentTimeMillis();
        final Timer t = new Timer(0, null);

        t.addActionListener((ActionEvent e) -> {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > millis) {
                t.stop();
                if (callback != null) {
                    callback.actionPerformed(e);
                }
            } else {
                setOpacity(1.0f - (float) elapsed / millis);
            }
        });

        t.start();
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void executeTests(boolean bol) throws IOException, InterruptedException {
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(60);
        jProgressBar1.setValue(0);

        try {
            checkInternetConn(bol);

            fadeOutSplash(1000, (ActionEvent e) -> {
                try {
                    OpaqueScreen screen = new OpaqueScreen();
                    screen.setRootFrame(new LoginJPanel(screen));
                    screen.setVisible(true);
                    dispose();
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (Exception ex) {
            OpaqueScreen screen = new OpaqueScreen();

            switch (ex.getMessage()) {
                case "Arduino":
                    GenericErrorJPanel arduino = new GenericErrorJPanel(screen, language.getContentById("failure").replace("&string", "o Arduino"),
                            GenericErrorJPanel.ERROR_MESSAGE, errorDetail, GenericErrorJPanel.OK_RETRY);

                    arduino.setRightButtonAction((ActionEvent e) -> {
                        arduino.disposeWindow();
                        screen.close();
                        File getOptions = new File(UsefulMethods.getClassPath(UsefulMethods.class) + "config" + File.separator + "options.xml");
                        getOptions.delete();
                        dispose();
                        System.exit(0);
                    });

                    arduino.setRetryButtonAction((ActionEvent e) -> {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    executeTests(bol);
                                } catch (IOException | InterruptedException ex1) {
                                    Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }.start();
                        screen.close();
                    });

                    screen.setRootFrame(arduino);
                    screen.setVisible(true);
                    break;
                case "Internet":
                    GenericErrorJPanel internet = new GenericErrorJPanel(screen, language.getContentById("failure").replace("&string", "a Internet"),
                            GenericErrorJPanel.ALERT_MESSAGE, errorDetail, GenericErrorJPanel.OK_RETRY);

                    internet.setRightButtonAction((ActionEvent e) -> {
                        internet.disposeWindow();
                        dispose();
                        System.exit(0);
                    });

                    internet.setRetryButtonAction((ActionEvent e) -> {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    executeTests(bol);
                                } catch (IOException | InterruptedException ex1) {
                                    Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }.start();
                        screen.close();
                    });

                    screen.setRootFrame(internet);
                    screen.setVisible(true);
                    break;
                case "DB":
                    GenericErrorJPanel db = new GenericErrorJPanel(screen, language.getContentById("failure").replace("&string", "o Banco de Dados"),
                            GenericErrorJPanel.ALERT_MESSAGE, errorDetail, GenericErrorJPanel.OK_RETRY);

                    db.setRightButtonAction((ActionEvent e) -> {
                        db.disposeWindow();
                        dispose();
                        System.exit(0);
                    });

                    db.setRetryButtonAction((ActionEvent e) -> {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    executeTests(bol);
                                } catch (IOException | InterruptedException ex1) {
                                    Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }.start();
                        screen.close();
                    });

                    screen.setRootFrame(db);
                    screen.setVisible(true);
                    break;
            }
        }
    }

    private boolean checkAmbient() {
        try {
            ClassLoader.getSystemClassLoader().loadClass("gnu.io.CommPortIdentifier");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"RXTXcomm\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("net.java.balloontip.BalloonTip");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"balloontip-1.2.4.1\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.jfree.chart.ChartColor");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"jfreechart-1.0.19\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.apache.commons.codec.CharEncoding");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"commons-codec-1.10\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.apache.commons.io.ByteOrderMark");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"commons-io-2.5\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.jfree.base.log.DefaultLog");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"jcommon-1.0-23\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.apache.commons.net.bsd.RCommandClient");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"commons-net-3.5\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.json.Cookie");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"json-org\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("sun.tools.jconsole.AboutDialog");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"jconsole\"");
            return false;
        }

        try {
            ClassLoader.getSystemClassLoader().loadClass("org.joda.time.DateTime");
        } catch (ClassNotFoundException e) {
            System.err.println("Não achou \"joda-time-2.1\"");
            return false;
        }

        return true;
    }

    private boolean checkInternetConn(boolean bol) throws Exception {
        if (Boolean.parseBoolean(xml.getContentById("isDebugActive")) && Boolean.parseBoolean(xml.getContentById("offlineMode"))) {
            return true;
        } else {
            if (bol) {
                infoDisplayer.setIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/spinner.gif")));
                infoDisplayer.setText(language.getContentById("testArduino"));
                try {
                    UsefulMethods.getArduinoInstance();
                } catch (IOException ex) {
                    errorDetail = language.getContentById("arduinoFailure");
                    Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                    throw new Exception("Arduino");
                }
            }
            for (int i = 0; i < 30; i++) {
                jProgressBar1.setValue(i);
                Thread.sleep(10);
            }

            infoDisplayer.setText(language.getContentById("testInternet"));
            try {
                URL url = new URL("https://www.google.com");
                url.openStream();
            } catch (Exception ex) {
                errorDetail = language.getContentById("internetFailure");
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception("Internet");
            }

            for (int i = 30; i < 60; i++) {
                jProgressBar1.setValue(i);
                Thread.sleep(10);
            }

            try {
                System.out.println(UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":"
                        + xml.getContentByName("webServicePort", 0) + "/test", "GET", null));

                String s = "{"
                        + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + "\""
                        + "}";
                System.err.println(s);

                UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":"
                        + xml.getContentByName("webServicePort", 0) + "/pcligado", "POST", s);
            } catch (Exception ex) {
                errorDetail = language.getContentById("dbFailure");
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception("DB");
            }

            for (int i = 60; i <= 100; i++) {
                jProgressBar1.setValue(i);
                Thread.sleep(10);
            }

            return true;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoDisplayer;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel splashLogo;
    // End of variables declaration//GEN-END:variables

}
