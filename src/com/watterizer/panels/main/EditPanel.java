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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class EditPanel extends javax.swing.JPanel {

    private ArrayList<String> perguntas;
    private String perfil;

    public EditPanel() {
        Connection db = UsefulMethods.getDBInstance();
        ResultSet rs;

        perguntas = new ArrayList<>();
        String[] big = new String[]{"--- Selecione uma pergunta ---"};
        try {
            rs = db.createStatement().executeQuery("SELECT * FROM perguntasecreta");

            while (rs.next()) {
                String found = "[" + rs.getInt("id") + "]" + rs.getString("pergunta");
                perguntas.add(found);
            }

            big = new String[perguntas.size() + 1];

            big[0] = "--- Selecione uma pergunta ---";
            for (int i = 0; i < perguntas.size(); i++) {
                big[i + 1] = perguntas.get(i).substring(3, perguntas.get(i).length());
            }
            
            rs = db.createStatement().executeQuery("SELECT perfil FROM perfil WHERE id = " + UsefulMethods.getCurrentUserModel().getIdPerfil());
            rs.next();
            
            perfil = rs.getString("perfil");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        questionDropdown.setModel(new DefaultComboBoxModel(big));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameDisplay = new javax.swing.JLabel();
        middleSeparator = new javax.swing.JSeparator();
        emailDisplay = new javax.swing.JLabel();
        usernameDisplay = new javax.swing.JLabel();
        telephoneDisplay = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        passwordDisplay = new javax.swing.JLabel();
        profileDisplay = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();
        questionDisplay = new javax.swing.JLabel();
        awnserDisplay = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        emailInput = new javax.swing.JTextField();
        telephoneInput = new javax.swing.JTextField();
        usernameInput = new javax.swing.JTextField();
        questionDropdown = new javax.swing.JComboBox<>();
        passwordInput = new javax.swing.JPasswordField();
        awnserInput = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 0, 0));

        nameDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        nameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        nameDisplay.setText("Nome:");

        middleSeparator.setBackground(new java.awt.Color(255, 200, 20));
        middleSeparator.setForeground(new java.awt.Color(255, 200, 20));
        middleSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        emailDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        emailDisplay.setForeground(new java.awt.Color(255, 255, 255));
        emailDisplay.setText("E-Mail:");

        usernameDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        usernameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        usernameDisplay.setText("Username:");

        telephoneDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        telephoneDisplay.setForeground(new java.awt.Color(255, 255, 255));
        telephoneDisplay.setText("Telefone:");

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

        questionDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        questionDisplay.setForeground(new java.awt.Color(255, 255, 255));
        questionDisplay.setText("Perg. Sec.:");

        awnserDisplay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        awnserDisplay.setForeground(new java.awt.Color(255, 255, 255));
        awnserDisplay.setText("Resposta:");

        nameInput.setText(UsefulMethods.getCurrentUserModel().getNome());
        nameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameInputKeyPressed(evt);
            }
        });

        emailInput.setText(UsefulMethods.getCurrentUserModel().getEmail());
        emailInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailInputKeyPressed(evt);
            }
        });

        telephoneInput.setText(UsefulMethods.getCurrentUserModel().getTelefone());
        telephoneInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telephoneInputKeyPressed(evt);
            }
        });

        usernameInput.setText(UsefulMethods.getCurrentUserModel().getUsername());
        usernameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameInputKeyPressed(evt);
            }
        });

        questionDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        passwordInput.setText(UsefulMethods.getCurrentUserModel().getSenha());
        passwordInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordInputKeyPressed(evt);
            }
        });

        awnserInput.setText(UsefulMethods.getCurrentUserModel().getRespostaPergunta());
        awnserInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                awnserInputKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(telephoneDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telephoneInput))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usernameInput))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameInput))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emailDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(middleSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(questionDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(questionDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(profileDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passwordInput))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(awnserDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(awnserInput))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(questionDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(questionDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(awnserDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(awnserInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameInput)
                            .addComponent(nameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telephoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephoneDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))))
            .addComponent(middleSeparator)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameInputKeyPressed
        if (nameInput.getText().length() >= 50) {
            nameInput.setText(nameInput.getText().substring(0, 49));
        }
    }//GEN-LAST:event_nameInputKeyPressed

    private void emailInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailInputKeyPressed
        if (emailInput.getText().length() >= 50) {
            emailInput.setText(emailInput.getText().substring(0, 49));
        }
    }//GEN-LAST:event_emailInputKeyPressed

    private void telephoneInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephoneInputKeyPressed
        if (telephoneInput.getText().length() >= 14) {
            telephoneInput.setText(telephoneInput.getText().substring(0, 13));
        }
    }//GEN-LAST:event_telephoneInputKeyPressed

    private void usernameInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameInputKeyPressed
        if (usernameInput.getText().length() >= 255) {
            usernameInput.setText(usernameInput.getText().substring(0, 254));
        }
    }//GEN-LAST:event_usernameInputKeyPressed

    private void awnserInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_awnserInputKeyPressed
        if (awnserInput.getText().length() >= 100) {
            awnserInput.setText(awnserInput.getText().substring(0, 99));
        }
    }//GEN-LAST:event_awnserInputKeyPressed

    private void passwordInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordInputKeyPressed
        String senha = "";
        for (char c : passwordInput.getPassword()) {
            senha += c;
        }
        if (senha.length() >= 45) {
            passwordInput.setText(senha.substring(0, 44));
        }
    }//GEN-LAST:event_passwordInputKeyPressed

    public ArrayList<String> getPerguntas() {
        return perguntas;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel awnserDisplay;
    public javax.swing.JTextField awnserInput;
    private javax.swing.JLabel emailDisplay;
    public javax.swing.JTextField emailInput;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator middleSeparator;
    private javax.swing.JLabel nameDisplay;
    public javax.swing.JTextField nameInput;
    private javax.swing.JLabel passwordDisplay;
    public javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel profileDisplay;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel questionDisplay;
    public javax.swing.JComboBox<String> questionDropdown;
    private javax.swing.JLabel telephoneDisplay;
    public javax.swing.JTextField telephoneInput;
    private javax.swing.JLabel usernameDisplay;
    public javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables

}
