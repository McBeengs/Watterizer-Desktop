package com.watterizer.panels;

import com.watterizer.panels.main.JFrame;
import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Locale;
import javax.swing.Action;
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
            // Checar arquivo de configurações para identificar servidor local ou remoto.
            // Caso seja remoto:
            new Thread() {
                @Override
                public void run() {
                    executeTests();
                }
            }.start();
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
        infoDisplayer.setBounds(10, 190, 280, 30);

        jProgressBar1.setBorder(new RoundedCornerBorder(25, 25, Color.BLACK));
        jProgressBar1.setOpaque(true);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(310, 190, 540, 30);

        splashLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/splash.png"))); // NOI18N
        getContentPane().add(splashLogo);
        splashLogo.setBounds(-30, -10, 960, 250);

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

    private void executeTests() {
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(60);
        jProgressBar1.setValue(0);

        try {
            checkInternetConn();

            fadeOutSplash(1000, (ActionEvent e) -> {
                new LoginJFrame();
                dispose();
            });

        } catch (Exception ex) {
            switch (ex.getMessage()) {
                case "Arduino":
                    GenericErrorJFrame arduino = new GenericErrorJFrame(language.getContentById("failure").replace("&string", "o Arduino"),
                            GenericErrorJFrame.ERROR_MESSAGE, errorDetail, GenericErrorJFrame.OK_RETRY);

                    arduino.setRightButtonAction((ActionEvent e) -> {
                        arduino.disposeWindow();
                        dispose();
                    });

                    arduino.setRetryButtonAction((ActionEvent e) -> {
                        new Thread() {
                            @Override
                            public void run() {
                                executeTests();
                            }
                        }.start();
                        arduino.disposeWindow();
                    });

                    arduino.setVisible(true);
                    break;
                case "Internet":
                    GenericErrorJFrame internet = new GenericErrorJFrame(language.getContentById("failure").replace("&string", "a Internet"),
                            GenericErrorJFrame.ALERT_MESSAGE, errorDetail, GenericErrorJFrame.OK_RETRY);

                    internet.setRightButtonAction((ActionEvent e) -> {
                        internet.disposeWindow();
                        dispose();
                    });

                    internet.setRetryButtonAction((ActionEvent e) -> {
                        new Thread() {
                            @Override
                            public void run() {
                                executeTests();
                            }
                        }.start();
                        internet.disposeWindow();
                    });

                    internet.setVisible(true);
                    break;
                case "DB":
                    GenericErrorJFrame db = new GenericErrorJFrame(language.getContentById("failure").replace("&string", "o Banco de Dados"),
                            GenericErrorJFrame.ALERT_MESSAGE, errorDetail, GenericErrorJFrame.OK_RETRY);

                    db.setRightButtonAction((ActionEvent e) -> {
                        db.disposeWindow();
                        dispose();
                    });

                    db.setRetryButtonAction((ActionEvent e) -> {
                        new Thread() {
                            @Override
                            public void run() {
                                executeTests();
                            }
                        }.start();
                        db.disposeWindow();
                    });

                    db.setVisible(true);
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

        return true;
    }

    private boolean checkInternetConn() throws Exception {
        infoDisplayer.setIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/spinner.gif")));
        infoDisplayer.setText(language.getContentById("testArduino"));
//        try {
//            UsefulMethods.getArduinoInstance();
//        } catch (IOException ex) {
//            errorDetail = language.getContentById("arduinoFailure");
//            throw new Exception("Arduino");
//        }
        for (int i = 0; i < 10; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        infoDisplayer.setText(language.getContentById("testInternet"));
        try {
            URL url = new URL("https://www.google.com");
            url.openStream();
        } catch (Exception ex) {
            errorDetail = language.getContentById("internetFailure");
            throw new Exception("Internet");
        }

        for (int i = 10; i < 30; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        infoDisplayer.setText(language.getContentById("testDb"));
        Connection conn = UsefulMethods.getDBInstance();
        for (int i = 30; i < 50; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        if (conn == null) {
            errorDetail = language.getContentById("dbFailure").replace("&string", xml.getContentByName("databaseName", 0));
            throw new Exception("DB");
        }
        for (int i = 50; i < 60; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        return true;
    }

    public static void main(String args[]) {
        Locale.setDefault(new Locale("pt", "BR"));
        String separator = File.separator;

        File getConfig = new File(UsefulMethods.getClassPath(SplashScreen.class) + separator + "config");
        if (!getConfig.exists()) {
            getConfig.mkdir();
        }

        File getOptions = new File(UsefulMethods.getClassPath(SplashScreen.class) + "config" + separator + "options.xml");
        if (!getOptions.exists()) {
            final XmlManager style = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
            //make first run setup screen
            //new SetupContainer(style).setVisible(true);
            //return;
        }

        XmlManager style;
        try {
            style = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        } catch (java.lang.NullPointerException ex) {
            GenericErrorJFrame error = new GenericErrorJFrame("Falha com as configurações", GenericErrorJFrame.ALERT_MESSAGE,
                    "", GenericErrorJFrame.OK);
            error.setRightButtonAction((ActionEvent e) -> {
                new Thread() {
                    @Override
                    public void run() {
                        
                    }
                }.start();
                error.disposeWindow();
            });
            error.setVisible(true);
            return;
        }
        String set = style.getContentByName("style", 0);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (set.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoDisplayer;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel splashLogo;
    // End of variables declaration//GEN-END:variables

}
