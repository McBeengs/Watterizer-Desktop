/* **********   MainJFrame.java   **********
 *
 * This piece of garbage was brought to you by nothing less than the almighty lord
 * of programming, the Java God and ruler of all the non living things, McBeengs, 
 * A.K.A. myself. I don't mind anyone steal or using my codes at their own business,
 * but at least, and I meant VERY least, give me the proper credit for it. I really
 * don't know what the code below does at this point in time while I write this stuff, 
 * but if you took all this time to sit, rip the .java files and read all this 
 * unnecessary bullshit, you know for what you came, doesn't ?
 * 
 * Copyright(c) {YEAR!!!} Mc's brilliant mind. All Rights (kinda) Reserved.
 */

 /*
 * {Insert class description here}
 */
package com.watterizer.modals;

import com.watterizer.panels.MeasurerPanel;
import com.watterizer.panels.RightClickUser;
import com.watterizer.panels.options.OptionsJFrame;
import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class MainJFrame extends javax.swing.JFrame {

    //private boolean isIconfied = false;
    private final MeasurerPanel measurerPanel;
    private XmlManager xml;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainJFrame() throws IOException {
        initComponents();
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        });

        if (SystemTray.isSupported()) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    SystemTray tray = SystemTray.getSystemTray();
                    PopupMenu popup = new PopupMenu();
                    MenuItem defaultItem = new MenuItem("Abrir");
                    setVisible(false);
                    defaultItem.addActionListener((ActionEvent e1) -> {
                        setVisible(true);
                        setState(Frame.NORMAL);
                    });
                    popup.add(defaultItem);
                    TrayIcon trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/ic_logo_16.png")).getImage(), "Watterizer", popup);
                    trayIcon.addActionListener((ActionEvent e1) -> {
                        setVisible(true);
                        setState(Frame.NORMAL);
                    });

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

        getContentPane().setBackground(Color.BLACK);
        mainPanel.setLayout(new GridLayout(0, 1));

        measurerPanel = new MeasurerPanel();
        measurerPanel.setVisible(true);
        mainPanel.add(measurerPanel);

        try {
            iconDisplayer.setIcon(new ImageIcon(ImageIO.read(UsefulMethods.downloadFile("fotoid1.png"))));
        } catch (Exception ex) {
            iconDisplayer.setIcon(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        iconDisplayer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        optionsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 550));

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        jSeparator2.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator2.setForeground(new java.awt.Color(255, 200, 20));

        jPanel2.setBackground(new java.awt.Color(255, 200, 20));

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.BOLD, 45);
        jLabel2.setFont(header);
        jLabel2.setText("Medidor");

        iconDisplayer.setBackground(new java.awt.Color(255, 255, 255));
        iconDisplayer.setForeground(new java.awt.Color(255, 255, 255));
        iconDisplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconDisplayer.setText(" ");
        iconDisplayer.setBorder(new RoundedCornerBorder(255, 255, new java.awt.Color(255, 200, 20)));
        iconDisplayer.setOpaque(true);
        iconDisplayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconDisplayerMouseClicked(evt);
            }
        });

        header = header.deriveFont(Font.BOLD, 22);
        jLabel1.setFont(header);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(UsefulMethods.getCurrentUserModel().getUsername());

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Administrador");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2))))
                .addContainerGap())
        );

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configurações");

        optionsItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/settings.png"))); // NOI18N
        optionsItem.setText("Opções");
        optionsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsItemActionPerformed(evt);
            }
        });
        jMenu2.add(optionsItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optionsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsItemActionPerformed
        new OptionsJFrame().setVisible(true);
    }//GEN-LAST:event_optionsItemActionPerformed

    private void iconDisplayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconDisplayerMouseClicked
        if (evt.getButton() == 1) { // left

        } else if (evt.getButton() == 3) { //right
            JPopupMenu menu = new JPopupMenu();
            RightClickUser user = new RightClickUser();
            user.setTopButtonAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("detalhes usuário");
                }
            });
            user.setBottomButtonAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (JOptionPane.showConfirmDialog(null, "Deseja fazer o logout?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        try {
                            String s = "{\n"
                                    + "\"token\":\"" + UsefulMethods.getCurrentUserModel().getToken() + "\"\n"
                                    + "}";
                            
                            UsefulMethods.getWebServiceResponse(xml.getContentByName("webServiceHost", 0) + "/logout", "GET", s);
                            
                            System.exit(0);
                        } catch (ProtocolException ex) {
                            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            menu.add(user);
            menu.show(iconDisplayer, evt.getX() - 400, evt.getY());
        }
    }//GEN-LAST:event_iconDisplayerMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconDisplayer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem optionsItem;
    // End of variables declaration//GEN-END:variables

}
