package com.watterizer.panels;

import com.watterizer.panels.login.LoginJFrame;
import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.util.UsefulMethods;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SplashScreen extends javax.swing.JFrame {

    private String errorDetail;

    @SuppressWarnings("")
    public SplashScreen() {
        if (!checkAmbient()) {
            System.err.println("deu ruim");
        } else {
            setUndecorated(true);
            initComponents();
            getContentPane().setVisible(false);
            
        }

        fadeInSplash(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checar arquivo de configurações para identificar servidor local ou remoto.
                // Caso seja remoto:
                new Thread() {
                    @Override
                    public void run() {
                        executeTests();
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

            fadeOutSplash(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new LoginJFrame().setVisible(true);
                    dispose();
                }
            });

        } catch (Exception ex) {
            switch (ex.getMessage()) {
                case "Internet":
                    GenericErrorJFrame internet = new GenericErrorJFrame("Falha com a Internet", GenericErrorJFrame.ALERT_MESSAGE, errorDetail, GenericErrorJFrame.OK_RETRY);

                    internet.setRightButtonAction(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            internet.disposeWindow();
                            dispose();
                        }
                    });

                    internet.setRetryButtonAction(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Thread() {
                                @Override
                                public void run() {
                                    executeTests();
                                }
                            }.start();
                            internet.disposeWindow();
                        }
                    });

                    internet.setVisible(true);
                    break;
                case "DB":
                    GenericErrorJFrame db = new GenericErrorJFrame("Falha com a Internet", GenericErrorJFrame.ALERT_MESSAGE, errorDetail, GenericErrorJFrame.OK_RETRY);

                    db.setRightButtonAction(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            db.disposeWindow();
                            dispose();
                        }
                    });

                    db.setRetryButtonAction(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Thread() {
                                @Override
                                public void run() {
                                    executeTests();
                                }
                            }.start();
                            db.disposeWindow();
                        }
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
        infoDisplayer.setText("Testando a conexão com a rede");
        for (int i = 0; i < 10; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        try {
            URL url = new URL("https://www.google.com");
            url.openStream();
        } catch (Exception ex) {
            errorDetail = "Não foi possível conectar-se a internet. Isso pode ter sido causado por alguma configuração errada no Sistema Operacional, uma queda no provedor ou "
                    + "um problema no cabeamento. Aguarde alguns instantes e tente novamente. Caso o problema persista, entre em contato com um administrador.";

            throw new Exception("Internet");
        }

        for (int i = 10; i < 30; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        infoDisplayer.setText("Conectando ao servidor principal");
        Connection conn = UsefulMethods.getDBInstance();
        for (int i = 30; i < 50; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(10);
        }

        if (conn == null) {
            errorDetail = "Não foi possível se conectar ao banco de dados \"" + "Watterizer" /* obter nome pelo arquivo de configuração */ + "\". Isso pode ter sido causado "
                    + "por alguma configuração errada nas opções ou por uma queda no provedor. Aguarde alguns instantes e tente novamente. Caso o problema persista, entre em contato com um administrador.";

            throw new Exception("DB");
        }
        for (int i = 50; i < 60; i++) {
            jProgressBar1.setValue(i);
            Thread.sleep(5);
        }
//        try {
//            Statement teste = conn.createStatement();
//            ResultSet resultado = teste.executeQuery("SELECT * FROM usuario");
//            
//            while (resultado.next()) {
//                System.out.println(resultado.getString(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return true;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
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
