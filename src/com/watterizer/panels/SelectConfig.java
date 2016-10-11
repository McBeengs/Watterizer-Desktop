/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.panels;

import com.watterizer.util.UsefulMethods;
import java.awt.Font;

/**
 *
 * @author 15153769
 */
public class SelectConfig extends javax.swing.JPanel {

    /**
     * Creates new form SelectConfig
     */
    public SelectConfig() {
        initComponents();
        text.setText("<html><body style=\"text-align: justify;\">    Antes de mais nada, precisamos estabelecer uma conexão entre o servidor para que as etapas seguintes "
                + "sejam configuradas. Caso não saiba os campos abaixo, entre em contato com o responsável pela arquitetura do sistema.</body></html>");
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        top = new javax.swing.JLabel();
        text = new javax.swing.JLabel();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setVerifyInputWhenFocusTarget(false);

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top.setFont(header);
        top.setForeground(new java.awt.Color(255, 255, 255));
        top.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top.setText("Identicficar terminal");

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("[text]");
        text.setToolTipText("");

        previous.setText("Anterior");

        next.setText("Próximo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(previous))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    private javax.swing.JLabel text;
    private javax.swing.JLabel top;
    // End of variables declaration//GEN-END:variables
}
