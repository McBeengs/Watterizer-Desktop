/* **********   Temp.java   **********
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

import com.watterizer.util.UsefulMethods;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowPanel extends javax.swing.JPanel {

    private String pergunta;
    private String perfil;

    public ShowPanel() {
        Connection db = UsefulMethods.getDBInstance();
        ResultSet rs;
        try {
            rs = db.createStatement().executeQuery("SELECT * FROM perguntasecreta WHERE id = "
                    + UsefulMethods.getCurrentUserModel().getIdPergunta());
            rs.next();
            pergunta = rs.getString("pergunta");
        } catch (SQLException ex) {
            pergunta = "[Pergunta alterada, excluída ou indisponível]";
        }
        
        try {
            rs = db.createStatement().executeQuery("SELECT perfil FROM perfil WHERE id = " + UsefulMethods.getCurrentUserModel().getIdPerfil());
            rs.next();
            
            perfil = rs.getString("perfil");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShowPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameDisplay = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        middleSeparator = new javax.swing.JSeparator();
        emailDisplay = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        usernameDisplay = new javax.swing.JLabel();
        telephoneDisplay = new javax.swing.JLabel();
        telephoneLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        passwordDisplay = new javax.swing.JLabel();
        profileDisplay = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        questionDisplay = new javax.swing.JLabel();
        questionLabel = new javax.swing.JLabel();
        awnserDisplay = new javax.swing.JLabel();
        awnserLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        nameDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        nameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        nameDisplay.setText("Nome:");

        nameLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText(UsefulMethods.getCurrentUserModel().getNome());

        middleSeparator.setBackground(new java.awt.Color(255, 200, 20));
        middleSeparator.setForeground(new java.awt.Color(255, 200, 20));
        middleSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        emailDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        emailDisplay.setForeground(new java.awt.Color(255, 255, 255));
        emailDisplay.setText("E-Mail:");

        emailLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel.setText(UsefulMethods.getCurrentUserModel().getEmail());

        usernameDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        usernameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        usernameDisplay.setText("Username:");

        telephoneDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        telephoneDisplay.setForeground(new java.awt.Color(255, 255, 255));
        telephoneDisplay.setText("Telefone:");

        telephoneLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        telephoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        telephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        telephoneLabel.setText(UsefulMethods.getCurrentUserModel().getTelefone());

        usernameLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText(UsefulMethods.getCurrentUserModel().getUsername());

        passwordDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        passwordDisplay.setForeground(new java.awt.Color(255, 255, 255));
        passwordDisplay.setText("Senha:");

        profileDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        profileDisplay.setForeground(new java.awt.Color(255, 255, 255));
        profileDisplay.setText("Perfil:");

        profileLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        profileLabel.setForeground(new java.awt.Color(255, 255, 255));
        profileLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profileLabel.setText(perfil);

        passwordLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        String show = "";
        for (int i = 0; i < UsefulMethods.getCurrentUserModel().getSenha().length(); i++) {
            show += "*";
        }
        passwordLabel.setText(show);

        questionDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        questionDisplay.setForeground(new java.awt.Color(255, 255, 255));
        questionDisplay.setText("Perg. Sec.:");

        questionLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        questionLabel.setForeground(new java.awt.Color(255, 255, 255));
        questionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel.setText(pergunta);

        awnserDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        awnserDisplay.setForeground(new java.awt.Color(255, 255, 255));
        awnserDisplay.setText("Resposta:");

        awnserLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        awnserLabel.setForeground(new java.awt.Color(255, 255, 255));
        awnserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        awnserLabel.setText(UsefulMethods.getCurrentUserModel().getRespostaPergunta());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emailDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(telephoneDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telephoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(middleSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(questionDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(awnserDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(awnserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(profileDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(telephoneDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(telephoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(questionDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(awnserDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(awnserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(profileDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(middleSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void deactivateAll() {
        for (Component c : getComponents()) {
            c.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel awnserDisplay;
    private javax.swing.JLabel awnserLabel;
    private javax.swing.JLabel emailDisplay;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator middleSeparator;
    private javax.swing.JLabel nameDisplay;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel passwordDisplay;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel profileDisplay;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel questionDisplay;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel telephoneDisplay;
    private javax.swing.JLabel telephoneLabel;
    private javax.swing.JLabel usernameDisplay;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

}
