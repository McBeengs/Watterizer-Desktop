package com.watterizer.panels;

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
    public static final int ERROR_MESSAGE = 11;
    public static final int ALERT_MESSAGE = 12;

    @SuppressWarnings("")
    public GenericErrorJFrame(String title, int messageType, String message, int type) throws ArrayIndexOutOfBoundsException {
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

        switch (messageType) {
            case ERROR_MESSAGE:
                iconDisplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/errorBig.png")));
                break;
            case ALERT_MESSAGE:
                iconDisplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/alertBig.png")));
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errorTitle = new javax.swing.JLabel();
        iconDisplay = new javax.swing.JLabel();
        errorText = new javax.swing.JLabel();
        retryButton = new javax.swing.JButton();
        leftButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        backgroundPOG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(570, 520));
        setMinimumSize(new java.awt.Dimension(570, 520));
        setPreferredSize(new java.awt.Dimension(570, 520));
        setResizable(false);
        getContentPane().setLayout(null);

        errorTitle.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        errorTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorTitle.setText("[error-title]");
        getContentPane().add(errorTitle);
        errorTitle.setBounds(30, 30, 540, 44);

        iconDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconDisplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/alertBig.png"))); // NOI18N
        getContentPane().add(iconDisplay);
        iconDisplay.setBounds(30, 70, 130, 120);

        errorText.setForeground(new java.awt.Color(255, 255, 255));
        errorText.setText("jLabel2");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(errorText);
        errorText.setBounds(40, 210, 490, 190);

        retryButton.setText("jButton2");
        retryButton.setFocusable(false);
        getContentPane().add(retryButton);
        retryButton.setBounds(352, 410, 180, 23);

        leftButton.setText("jButton1");
        leftButton.setFocusable(false);
        getContentPane().add(leftButton);
        leftButton.setBounds(350, 450, 85, 23);

        rightButton.setText("jButton2");
        rightButton.setFocusable(false);
        getContentPane().add(rightButton);
        rightButton.setBounds(450, 450, 85, 23);

        backgroundPOG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/errorScreen.png"))); // NOI18N
        getContentPane().add(backgroundPOG);
        backgroundPOG.setBounds(0, -6, 570, 520);

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
        super.dispose();
    }

    @Override
    public void dispose() {
        //Logger.getLogger(GenericErrorJFrame.class.getName()).log(Level.SEVERE, "\"dispose\" is overriten. Use \"disposeWindow\" instead");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundPOG;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel errorTitle;
    private javax.swing.JLabel iconDisplay;
    private javax.swing.JButton leftButton;
    private javax.swing.JButton retryButton;
    private javax.swing.JButton rightButton;
    // End of variables declaration//GEN-END:variables
}
