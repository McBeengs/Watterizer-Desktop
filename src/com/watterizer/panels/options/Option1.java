package com.watterizer.panels.options;

import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.io.File;
import javax.swing.UIManager;

public class Option1 extends javax.swing.JPanel {

    private String[] languages;
    private String[] styles;
    private int languageActive;
    private int styleSelected;
    private XmlManager xml;
    private XmlManager language;

    public Option1(XmlManager xml) {
        this.xml = xml;
        language = UsefulMethods.getManagerInstance(UsefulMethods.LANGUAGE);

        File file = new File(UsefulMethods.getClassPath(Option1.class) + File.separator + "language");
        languages = new String[file.listFiles().length];
        
        for (int i = 0; i < languages.length; i++) {
            String s = file.listFiles()[i].getName();
            s = s.substring(0, s.indexOf("."));
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
            languages[i] = s;
        }

        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        styles = new String[plafs.length];

        for (int i = 0; i < plafs.length; i++) {
            styles[i] = plafs[i].getName();
        }

        initComponents();

        languageActive = Integer.parseInt(xml.getContentByAttribute("language", 0, "attr"));
        languageSelector.setSelectedIndex(languageActive);

        styleSelected = Integer.parseInt(xml.getContentByAttribute("style", 0, "attr"));
        styleSelector.setSelectedIndex(styleSelected);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainLabel = new javax.swing.JLabel();
        topSeparator = new javax.swing.JSeparator();
        languageLabel = new javax.swing.JLabel();
        languageSelector = new javax.swing.JComboBox<>();
        styleLabel = new javax.swing.JLabel();
        styleSelector = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(516, 434));

        mainLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        mainLabel.setForeground(new java.awt.Color(255, 255, 255));
        mainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLabel.setText("Opções Gerais");

        topSeparator.setBackground(new java.awt.Color(255, 200, 20));
        topSeparator.setForeground(new java.awt.Color(255, 200, 20));

        languageLabel.setForeground(new java.awt.Color(255, 255, 255));
        languageLabel.setText(language.getContentById("language"));

        languageSelector.setModel(new javax.swing.DefaultComboBoxModel<>(languages));
        languageSelector.setFocusable(false);
        languageSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageSelectorActionPerformed(evt);
            }
        });

        styleLabel.setForeground(new java.awt.Color(255, 255, 255));
        styleLabel.setText(language.getContentById("style"));

        styleSelector.setModel(new javax.swing.DefaultComboBoxModel<>(styles));
        styleSelector.setFocusable(false);
        styleSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleSelectorActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(languageLabel)
                            .addComponent(styleLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(languageSelector, 0, 100, Short.MAX_VALUE)
                            .addComponent(styleSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(381, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(topSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageLabel)
                    .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(styleLabel)
                    .addComponent(styleSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(288, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void languageSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageSelectorActionPerformed
        if (languageSelector.getSelectedIndex() != languageActive) {
            UsefulMethods.makeBalloon(styleSelector, "The program must be restarted", Color.yellow);
        }

        xml.setContentByAttribute("language", "attr", 0, "" + languageSelector.getSelectedIndex());
        xml.setContentByName("language", 0, languageSelector.getItemAt(languageSelector.getSelectedIndex()));
    }//GEN-LAST:event_languageSelectorActionPerformed

    private void styleSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleSelectorActionPerformed
        if (styleSelector.getSelectedIndex() != styleSelected) {
            UsefulMethods.makeBalloon(styleSelector, "The program must be restarted", Color.yellow);
        }

        xml.setContentByAttribute("style", "attr", 0, "" + styleSelector.getSelectedIndex());
        xml.setContentByName("style", 0, styleSelector.getItemAt(styleSelector.getSelectedIndex()));
    }//GEN-LAST:event_styleSelectorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JComboBox<String> languageSelector;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JLabel styleLabel;
    private javax.swing.JComboBox<String> styleSelector;
    private javax.swing.JSeparator topSeparator;
    // End of variables declaration//GEN-END:variables
}
