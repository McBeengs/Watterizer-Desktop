package com.watterizer.panels.options;

import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Option1 extends javax.swing.JPanel {

    private String[] languages;
    private String[] styles;
    private int languageActive;
    private String lookNFeel;
    private int styleSelected;
    private XmlManager xml;
    private XmlManager language;

    public Option1() {
//        this.xml = xml;
//        language = UsefulMethods.loadManager(UsefulMethods.LANGUAGE);
//
//        languages = new String[]{"English"};
//
//        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
//        styles = new String[plafs.length];
//
//        for (int i = 0; i < plafs.length; i++) {
//            styles[i] = plafs[i].getName();
//        }
//
//        initComponents();
//
//        String temp2 = xml.getContentByName("language", 0);
//        languageActive = Integer.parseInt(temp2.substring(temp2.indexOf(",") + 2));
//        lookNFeel = xml.getContentByName("style", 0);
//
//        languageSelector.setSelectedIndex(languageActive);
//
//        styleSelected = Integer.parseInt(lookNFeel.substring(lookNFeel.indexOf(",") + 2));
//        styleSelector.setSelectedIndex(styleSelected);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainLabel = new javax.swing.JLabel();
        topSeparator = new javax.swing.JSeparator();
        languageLabel = new javax.swing.JLabel();
        languageSelector = new javax.swing.JComboBox<>();
        languageAlert = new javax.swing.JLabel();
        styleLabel = new javax.swing.JLabel();
        styleSelector = new javax.swing.JComboBox<>();
        styleAlert = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setMinimumSize(new java.awt.Dimension(516, 434));

        mainLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        mainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLabel.setText("Opções Gerais");

        languageLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        languageLabel.setText("Língua");

        languageSelector.setModel(new javax.swing.DefaultComboBoxModel<>(languages));
        languageSelector.setFocusable(false);
        languageSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageSelectorActionPerformed(evt);
            }
        });

        languageAlert.setVisible(false);
        languageAlert.setIcon(new ImageIcon(getClass().getResource("/com/style/icons/error.png")));
        languageAlert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        languageAlert.setForeground(new java.awt.Color(255, 204, 0));
        languageAlert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/error.png"))); // NOI18N
        languageAlert.setText("Um reinício será necessário");

        styleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        styleLabel.setText("Estilo");

        styleSelector.setModel(new javax.swing.DefaultComboBoxModel<>(styles));
        styleSelector.setFocusable(false);
        styleSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleSelectorActionPerformed(evt);
            }
        });

        styleAlert.setVisible(false);
        styleAlert.setIcon(new ImageIcon(getClass().getResource("/com/style/icons/error.png")));
        styleAlert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        styleAlert.setForeground(new java.awt.Color(255, 204, 0));
        styleAlert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/error.png"))); // NOI18N
        styleAlert.setText("Um reinício será necessário");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(languageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(styleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(languageSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(styleSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(languageAlert, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(styleAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(247, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(topSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(languageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(styleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(languageAlert))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(styleSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(styleAlert))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void languageSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageSelectorActionPerformed
        if (languageSelector.getSelectedIndex() != languageActive) {
            languageAlert.setVisible(true);
        } else {
            languageAlert.setVisible(false);
        }

//        xml.setContentByName("language", 0, languageSelector.getItemAt(languageSelector.getSelectedIndex())
//            + ", " + languageSelector.getSelectedIndex());
    }//GEN-LAST:event_languageSelectorActionPerformed

    private void styleSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleSelectorActionPerformed
        if (styleSelector.getSelectedIndex() != styleSelected) {
            styleAlert.setVisible(true);
        } else {
            styleAlert.setVisible(false);
        }

//        xml.setContentByName("style", 0, styleSelector.getItemAt(styleSelector.getSelectedIndex())
//            + ", " + styleSelector.getSelectedIndex());
    }//GEN-LAST:event_styleSelectorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel languageAlert;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JComboBox<String> languageSelector;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JLabel styleAlert;
    private javax.swing.JLabel styleLabel;
    private javax.swing.JComboBox<String> styleSelector;
    private javax.swing.JSeparator topSeparator;
    // End of variables declaration//GEN-END:variables
}
