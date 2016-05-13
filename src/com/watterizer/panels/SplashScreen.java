package com.watterizer.panels;

import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.util.UsefulMethods;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
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
                infoDisplayer.setText("cabô");

                // Checar arquivo de configurações para identificar servidor local ou remoto.
                // Caso seja remoto:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            checkInternetConn();
                        } catch (Exception ex) {

                            switch (ex.getMessage()) {
                                case "Internet":
                                    GenericErrorJFrame internet = new GenericErrorJFrame("Falha com a Internet.", errorDetail, GenericErrorJFrame.OK_RETRY);

                                    internet.setRightButtonAction(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            internet.disposeWindow();
                                        }
                                    });

                                    internet.setRetryButtonAction(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            
                                        }
                                    });
                                    
                                    internet.setVisible(true);
                                    break;
                                case "DB":
                                    GenericErrorJFrame db = new GenericErrorJFrame("Falha com a Internet.", errorDetail, GenericErrorJFrame.OK_RETRY);

                                    db.setRightButtonAction(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            db.disposeWindow();
                                        }
                                    });

                                    db.setRetryButtonAction(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            
                                        }
                                    });
                                    
                                    db.setVisible(true);
                                    break;
                            }
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

        splashLogo = new javax.swing.JLabel();
        infoDisplayer = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.0f));
        setResizable(false);

        splashLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/splash.png"))); // NOI18N

        infoDisplayer.setBackground(new java.awt.Color(204, 0, 51));
        infoDisplayer.setOpaque(true);

        jProgressBar1.setValue(70);
        jProgressBar1.setBorder(new RoundedCornerBorder(35, 35));
        jProgressBar1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(splashLogo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(infoDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(splashLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoDisplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

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

    private boolean isOk = false;

    private boolean checkInternetConn() throws Exception {
        infoDisplayer.setText("Conectando ao servidor principal");

        try {
            URL url = new URL("https://www.google.com");
            url.openStream();
        } catch (Exception ex) {
            errorDetail = "Não foi possível conectar-se a internet. Isso pode ter sido causado por alguma configuração errada no Sistema Operacional, uma queda no provedor ou "
                    + "um problema no cabeamento. Aguarde alguns instantes e tente novamente. Caso o problema persista, entre em contato com um administrador.";

            throw new Exception("Internet");
        }

        Connection conn = UsefulMethods.getDBInstance();
        if (conn == null) {
            errorDetail = "Não foi possível se conectar ao banco de dados \"" + "Batatinha" /* obter nome pelo arquivo de configuração */ + "\". Isso pode ter sido causado "
                    + "por alguma configuração errada nas opções ou por uma queda no provedor. Aguarde alguns instantes e tente novamente. Caso o problema persista, entre em contato com um administrador.";

            throw new Exception("DB");
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
        System.out.println("yes");
        return true;
    }

    public static void main(String args[]) {
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
