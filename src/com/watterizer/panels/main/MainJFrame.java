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
package com.watterizer.panels.main;

import aurelienribon.slidinglayout.SLPanel;
import com.watterizer.panels.options.OptionsJFrame;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.ImageIcon;

public class MainJFrame extends javax.swing.JFrame {

    //private boolean isIconfied = false;
    private int currentPanel;
    private final UserPanel userPanel;
    private final MeasurerPanel measurerPanel;
    private final RankingPanel rankingPanel;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainJFrame() {
        initComponents();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 39) {
                    if (currentPanel == 0) {
                        changeMainPanel(1);
                    } else if (currentPanel == 1) {
                        changeMainPanel(2);
                    }
                } else if (e.getKeyCode() == 37) {
                    if (currentPanel == 2) {
                        changeMainPanel(1);
                    } else if (currentPanel == 1) {
                        changeMainPanel(0);
                    }
                }
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

        mainPanel.setLayout(new GridLayout(0, 1));
        
        userPanel = new UserPanel();
        measurerPanel = new MeasurerPanel();
        rankingPanel = new RankingPanel();
        userPanel.setVisible(false);
        measurerPanel.setVisible(true);
        rankingPanel.setVisible(false);

        //mainPanel.add(userPanel);
        mainPanel.add(measurerPanel);
        //mainPanel.add(rankingPanel);

        changeMainPanel(1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftBigButton = new SLPanel();
        textLeftBigButton = new javax.swing.JLabel();
        iconLeftBigButton = new javax.swing.JLabel();
        rightBigButton = new SLPanel();
        textRightBigButton = new javax.swing.JLabel();
        iconRightBigButton = new javax.swing.JLabel();
        mainPanel = new SLPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        optionsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 550));

        leftBigButton.setBackground(new java.awt.Color(255, 102, 0));
        leftBigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leftBigButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                leftBigButtonMouseExited(evt);
            }
        });

        textLeftBigButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textLeftBigButton.setText("Usuário");
        textLeftBigButton.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        textLeftBigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLeftBigButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textLeftBigButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textLeftBigButtonMouseExited(evt);
            }
        });

        iconLeftBigButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLeftBigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/user.png"))); // NOI18N
        iconLeftBigButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        iconLeftBigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconLeftBigButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconLeftBigButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconLeftBigButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout leftBigButtonLayout = new javax.swing.GroupLayout(leftBigButton);
        leftBigButton.setLayout(leftBigButtonLayout);
        leftBigButtonLayout.setHorizontalGroup(
            leftBigButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textLeftBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addComponent(iconLeftBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftBigButtonLayout.setVerticalGroup(
            leftBigButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftBigButtonLayout.createSequentialGroup()
                .addComponent(iconLeftBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(textLeftBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );

        rightBigButton.setBackground(new java.awt.Color(51, 51, 255));
        rightBigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rightBigButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rightBigButtonMouseExited(evt);
            }
        });

        textRightBigButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textRightBigButton.setText("Ranking");
        textRightBigButton.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        textRightBigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textRightBigButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textRightBigButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textRightBigButtonMouseExited(evt);
            }
        });

        iconRightBigButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconRightBigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/ranking.png"))); // NOI18N
        iconRightBigButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        iconRightBigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconRightBigButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconRightBigButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconRightBigButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout rightBigButtonLayout = new javax.swing.GroupLayout(rightBigButton);
        rightBigButton.setLayout(rightBigButtonLayout);
        rightBigButtonLayout.setHorizontalGroup(
            rightBigButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textRightBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addComponent(iconRightBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightBigButtonLayout.setVerticalGroup(
            rightBigButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightBigButtonLayout.createSequentialGroup()
                .addComponent(iconRightBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(textRightBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftBigButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(rightBigButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightBigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textRightBigButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textRightBigButtonMouseClicked
        if (currentPanel == 0) {
            changeMainPanel(1);
        } else if (currentPanel == 1) {
            changeMainPanel(2);
        }
    }//GEN-LAST:event_textRightBigButtonMouseClicked

    private void textLeftBigButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textLeftBigButtonMouseClicked
        if (currentPanel == 2) {
            changeMainPanel(1);
        } else if (currentPanel == 1) {
            changeMainPanel(0);
        }
    }//GEN-LAST:event_textLeftBigButtonMouseClicked

    private void optionsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsItemActionPerformed
        new OptionsJFrame().setVisible(true);
    }//GEN-LAST:event_optionsItemActionPerformed

    private void iconLeftBigButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLeftBigButtonMouseClicked
        if (currentPanel == 2) {
            changeMainPanel(1);
        } else if (currentPanel == 1) {
            changeMainPanel(0);
        }
    }//GEN-LAST:event_iconLeftBigButtonMouseClicked

    private void iconRightBigButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconRightBigButtonMouseClicked
        if (currentPanel == 0) {
            changeMainPanel(1);
        } else if (currentPanel == 1) {
            changeMainPanel(2);
        }
    }//GEN-LAST:event_iconRightBigButtonMouseClicked

    private void leftBigButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftBigButtonMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_leftBigButtonMouseEntered

    private void leftBigButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftBigButtonMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_leftBigButtonMouseExited

    private void rightBigButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightBigButtonMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_rightBigButtonMouseEntered

    private void rightBigButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightBigButtonMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_rightBigButtonMouseExited

    private void iconLeftBigButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLeftBigButtonMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_iconLeftBigButtonMouseEntered

    private void iconLeftBigButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLeftBigButtonMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_iconLeftBigButtonMouseExited

    private void textLeftBigButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textLeftBigButtonMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_textLeftBigButtonMouseEntered

    private void textLeftBigButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textLeftBigButtonMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_textLeftBigButtonMouseExited

    private void iconRightBigButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconRightBigButtonMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_iconRightBigButtonMouseEntered

    private void iconRightBigButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconRightBigButtonMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_iconRightBigButtonMouseExited

    private void textRightBigButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textRightBigButtonMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_textRightBigButtonMouseEntered

    private void textRightBigButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textRightBigButtonMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_textRightBigButtonMouseExited

    private void changeMainPanel(int position) {
        if (position == currentPanel || position < 0 || position > 2) {
            return;
        }

        currentPanel = position;

        switch (position) {
            case 0:
                mainPanel.removeAll();
                mainPanel.add(userPanel);
                userPanel.setVisible(true);
                measurerPanel.setVisible(false);
                rankingPanel.setVisible(false);
                leftBigButton.setVisible(false);
                rightBigButton.setVisible(true);
                rightBigButton.setBackground(Color.green);
                iconRightBigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/chart.png")));
                textRightBigButton.setText("Medidor");
                setTitle("Usuário | Watterizer");
                break;
            case 1:
                mainPanel.removeAll();
                mainPanel.add(measurerPanel);
                userPanel.setVisible(false);
                measurerPanel.setVisible(true);
                rankingPanel.setVisible(false);
                leftBigButton.setVisible(true);
                leftBigButton.setBackground(new Color(255, 102, 0));
                iconLeftBigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/user.png")));
                textLeftBigButton.setText("Usuário");
                rightBigButton.setVisible(true);
                rightBigButton.setBackground(new Color(51, 51, 255));
                iconRightBigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/ranking.png")));
                textRightBigButton.setText("Ranking");
                setTitle("Medidor | Watterizer");
                break;

            case 2:
                mainPanel.removeAll();
                mainPanel.add(rankingPanel);
                userPanel.setVisible(false);
                measurerPanel.setVisible(false);
                rankingPanel.setVisible(true);
                leftBigButton.setVisible(true);
                leftBigButton.setBackground(Color.green);
                iconLeftBigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/chart.png")));
                textLeftBigButton.setText("Medidor");
                rightBigButton.setVisible(false);
                setTitle("Ranking | Watterizer");
                break;
        }

        mainPanel.revalidate();
    }

    /*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconLeftBigButton;
    private javax.swing.JLabel iconRightBigButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel leftBigButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem optionsItem;
    private javax.swing.JPanel rightBigButton;
    private javax.swing.JLabel textLeftBigButton;
    private javax.swing.JLabel textRightBigButton;
    // End of variables declaration//GEN-END:variables
    */
    private javax.swing.JLabel iconLeftBigButton;
    private javax.swing.JLabel iconRightBigButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private SLPanel leftBigButton;
    private SLPanel mainPanel;
    private javax.swing.JMenuItem optionsItem;
    private SLPanel rightBigButton;
    private javax.swing.JLabel textLeftBigButton;
    private javax.swing.JLabel textRightBigButton;
    
}
