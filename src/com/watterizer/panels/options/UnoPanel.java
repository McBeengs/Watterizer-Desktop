/* **********   temp.java   **********
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
package com.watterizer.panels.options;

import javax.swing.JComboBox;

public class UnoPanel extends javax.swing.JPanel {

    public UnoPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        a0 = new javax.swing.JComboBox<>();
        a1 = new javax.swing.JComboBox<>();
        a2 = new javax.swing.JComboBox<>();
        a3 = new javax.swing.JComboBox<>();
        a4 = new javax.swing.JComboBox<>();
        a5 = new javax.swing.JComboBox<>();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/uno.png"))); // NOI18N

        jLabel2.setText("A0:");

        jLabel3.setText("A1:");

        jLabel4.setText("A2:");

        jLabel5.setText("A3:");

        jLabel6.setText("A4:");

        jLabel7.setText("A5:");

        a0.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nulo (Vazio)", "Computador", "Monitor", "Impressora", "Ocupado (Outro)" }));
        a0.setFocusable(false);
        a0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a0ActionPerformed(evt);
            }
        });

        a1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nulo (Vazio)", "Computador", "Monitor", "Impressora", "Ocupado (Outro)" }));
        a1.setFocusable(false);
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });

        a2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nulo (Vazio)", "Computador", "Monitor", "Impressora", "Ocupado (Outro)" }));
        a2.setFocusable(false);
        a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a2ActionPerformed(evt);
            }
        });

        a3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nulo (Vazio)", "Computador", "Monitor", "Impressora", "Ocupado (Outro)" }));
        a3.setFocusable(false);
        a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a3ActionPerformed(evt);
            }
        });

        a4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nulo (Vazio)", "Computador", "Monitor", "Impressora", "Ocupado (Outro)" }));
        a4.setFocusable(false);
        a4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a4ActionPerformed(evt);
            }
        });

        a5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nulo (Vazio)", "Computador", "Monitor", "Impressora", "Ocupado (Outro)" }));
        a5.setFocusable(false);
        a5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(a0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        comboAction();
    }//GEN-LAST:event_a1ActionPerformed

    private void a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a2ActionPerformed
        comboAction();
    }//GEN-LAST:event_a2ActionPerformed

    private void a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a3ActionPerformed
        comboAction();
    }//GEN-LAST:event_a3ActionPerformed

    private void a4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a4ActionPerformed
        comboAction();
    }//GEN-LAST:event_a4ActionPerformed

    private void a5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a5ActionPerformed
        comboAction();
    }//GEN-LAST:event_a5ActionPerformed

    private void a0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a0ActionPerformed
        comboAction();
    }//GEN-LAST:event_a0ActionPerformed

    private void comboAction() {
        JComboBox[] combos = new JComboBox[]{a0, a1, a2, a3, a4, a5};
        int s = 0;
        for (JComboBox combo : combos) {
            if (combo.getSelectedIndex() != 0) {
                s++;
            }
        }

        if (s > 2) { // num of measures per terminal
            for (JComboBox combo : combos) {
                if (combo.getSelectedIndex() == 0) {
                    combo.setSelectedIndex(4);
                    combo.setEnabled(false);
                }
            }
        } else {
            for (JComboBox combo : combos) {
                combo.setEnabled(true);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> a0;
    private javax.swing.JComboBox<String> a1;
    private javax.swing.JComboBox<String> a2;
    private javax.swing.JComboBox<String> a3;
    private javax.swing.JComboBox<String> a4;
    private javax.swing.JComboBox<String> a5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables

}
