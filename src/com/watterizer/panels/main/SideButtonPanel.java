/* **********   SideButtonJPanel.java   **********
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
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class SideButtonPanel extends SLPanel {

    @SuppressWarnings("")
    public SideButtonPanel(ImageIcon icon, String text, Color color) {
        initComponents();
        this.setIcon(icon);
        this.setText(text);
        setBackground(color);
        setOpaque(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/user.png"))); // NOI18N
        icon.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setText("Usuário");
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setIcon(ImageIcon icon) {
        this.icon.setIcon(icon);
    }
    
    public void setText(String text) {
        this.text.setText(text);
    }
    
    public void transitionColors (Color c1, Color c2) {
        
    }
    
    public void setListeners(MouseListener listener) {
        icon.addMouseListener(listener);
        text.addMouseListener(listener);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables

}
