package com.watterizer.panels;

import java.awt.Component;
import java.awt.event.ActionListener;

public class GenericErrorJFrame extends javax.swing.JFrame {

    public static final int YES = 1;
    public static final int NO = 2;
    public static final int YES_NO = 3;
    public static final int CANCEL = 4;
    public static final int OK = 5;
    public static final int CANCEL_OK = 6;
    public static final int YES_RETRY = 7;
    public static final int OK_RETRY = 8;
    public static final int YES_NO_RETRY = 9;
    public static final int CANCEL_OK_RETRY = 10;

    @SuppressWarnings("")
    public GenericErrorJFrame(String title, String message, int type) throws ArrayIndexOutOfBoundsException {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(title + " | Watterizer");
        retryButton.setText("Tentar Novamente");
        retryButton.setVisible(false);
        leftButton.setVisible(false);
        rightButton.setVisible(false);

        errorTitle.setText(title);
        errorText.setText("<html><body style=\"text-align: justify;\">    " + message + "</body></html>");

        switch (type) {
            case YES:
                rightButton.setVisible(true);
                rightButton.setText("Sim");

                break;
            case NO:
                rightButton.setVisible(true);
                rightButton.setText("Não");

                break;
            case YES_NO:
                leftButton.setVisible(true);
                leftButton.setText("Não");

                rightButton.setVisible(true);
                rightButton.setText("Sim");
            case CANCEL:
                rightButton.setVisible(true);
                rightButton.setText("Cancelar");

                break;
            case OK:
                rightButton.setVisible(true);
                rightButton.setText("Ok");

                break;
            case CANCEL_OK:
                leftButton.setVisible(true);
                leftButton.setText("Cancelar");
                rightButton.setVisible(true);
                rightButton.setText("Ok");
                break;
            case YES_RETRY:
                retryButton.setVisible(true);
                rightButton.setVisible(true);
                rightButton.setText("Sim");
                break;
            case OK_RETRY:
                retryButton.setVisible(true);
                rightButton.setVisible(true);
                rightButton.setText("Ok");
                break;
            case YES_NO_RETRY:
                retryButton.setVisible(true);
                leftButton.setVisible(true);
                leftButton.setText("Não");
                rightButton.setVisible(true);
                rightButton.setText("Sim");
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        errorTitle = new javax.swing.JLabel();
        errorText = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        leftButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        retryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        errorTitle.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        errorTitle.setText("[error-title]");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(errorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(errorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        errorText.setText("jLabel2");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        leftButton.setText("jButton1");

        rightButton.setText("jButton2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftButton)
                    .addComponent(rightButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        retryButton.setText("jButton2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(retryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(retryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setRetryButtonAction(ActionListener listener) {
        retryButton.addActionListener(listener);
    }

    public void setLeftButtonAction(ActionListener listener) {
        leftButton.addActionListener(listener);
    }

    public void setRightButtonAction(ActionListener listener) {
        rightButton.addActionListener(listener);
    }

    public void disposeWindow() {
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel errorTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton leftButton;
    private javax.swing.JButton retryButton;
    private javax.swing.JButton rightButton;
    // End of variables declaration//GEN-END:variables
}
