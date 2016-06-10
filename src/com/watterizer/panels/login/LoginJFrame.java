package com.watterizer.panels.login;

import com.watterizer.crypto.Encrypter;
import com.watterizer.panels.main.MainJFrame;
import com.watterizer.util.UsefulMethods;
import com.watterizer.util.UserModel;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginJFrame extends javax.swing.JFrame {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public LoginJFrame() {
        initComponents();
        setTitle("Login | Watterizer");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passInput = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        errorLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Watterizer :)");

        userInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userInputKeyPressed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Username");

        passInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passInputKeyPressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Password");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/lilCog.png"))); // NOI18N
        jLabel4.setText("Configurações Administrativas");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Permanecer logado");

        errorLog.setForeground(new java.awt.Color(255, 51, 51));
        errorLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLog.setText("    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passInput)
                            .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(errorLog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passInput, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(12, 12, 12)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorLog, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        userInput.setEnabled(false);
        passInput.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jButton1.setEnabled(false);

        new Thread() {
            @Override
            public void run() {
                try {
                    if (checkInput()) {
                        checkDB();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void userInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userInputKeyPressed
        paintInput(0, 2);
        if (evt.getKeyCode() == 10) {
            userInput.setEnabled(false);
            passInput.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jButton1.setEnabled(false);
            checkInput();
        }
    }//GEN-LAST:event_userInputKeyPressed

    private void passInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passInputKeyPressed
        paintInput(1, 2);
        if (evt.getKeyCode() == 10) {
            userInput.setEnabled(false);
            passInput.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jButton1.setEnabled(false);
            checkInput();
        }
    }//GEN-LAST:event_passInputKeyPressed

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        JOptionPane.showMessageDialog(null, "Essa opção ainda não esta pronta", "Ops", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jLabel4MouseClicked

    private boolean checkInput() {
        if (userInput.getText().length() == 0 || passInput.getPassword().length == 0) {
            if (userInput.getText().length() <= 0 && passInput.getPassword().length <= 0) {
                errorLog.setText("Username e Password vazios");
                paintInput(0, 1);
                paintInput(1, 1);
                userInput.setEnabled(true);
                passInput.setEnabled(true);
                jCheckBox1.setEnabled(true);
                jButton1.setEnabled(true);
                return false;
            } else if (userInput.getText().length() <= 0) {
                errorLog.setText("Username vazio");
                paintInput(0, 1);
                userInput.setEnabled(true);
                passInput.setEnabled(true);
                jCheckBox1.setEnabled(true);
                jButton1.setEnabled(true);
                return false;
            } else if (passInput.getPassword().length <= 0) {
                errorLog.setText("Password vazio");
                paintInput(1, 1);
                userInput.setEnabled(true);
                passInput.setEnabled(true);
                jCheckBox1.setEnabled(true);
                jButton1.setEnabled(true);
                return false;
            }
        } else {
            new Thread() {
                @Override
                public void run() {
                    try {
                        checkDB();
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
            return true;
        }

        return false;
    }

    private void paintInput(int input, int color) {
        switch (color) {
            case 0:
                if (input == 0) {
                    userInput.setBackground(new Color(50, 205, 50));
                } else {
                    passInput.setBackground(new Color(50, 205, 50));
                }
                break;
            case 1:
                if (input == 0) {
                    userInput.setBackground(new Color(238, 44, 44));
                } else {
                    passInput.setBackground(new Color(238, 44, 44));
                }
                break;
            case 2:
                passInput.setBackground(Color.WHITE);
                userInput.setBackground(Color.WHITE);
                errorLog.setText("    ");
                break;
        }
    }

    private void checkDB() throws SQLException {
        Connection conn = UsefulMethods.getDBInstance();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuario WHERE username = ?");
        statement.setString(1, userInput.getText());

        ResultSet result = statement.executeQuery();
        if (result.next()) { // o usuário existe no banco, mas a senha não necessariamente está correta
            statement = conn.prepareStatement("SELECT * FROM usuario WHERE username = ? AND senha = ?");
            statement.setString(1, userInput.getText());

            String getPass = "";
            char[] array = passInput.getPassword();
            for (int i = 0; i < array.length; i++) {
                getPass += array[i];
            }
            statement.setString(2, Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, getPass));

            result = statement.executeQuery();
            if (result.next()) { // a senha está correta
                UserModel model = new UserModel();
                model.setId(result.getInt("id"));
                model.setNome(result.getString("nome"));
                model.setUsername(result.getString("username"));
                model.setEmail(result.getString("email"));
                model.setSenha(result.getString("senha"));
                model.setTelefone(result.getString("telefone"));
                model.setDataCadastro(result.getDate("data_cadastro"));
                model.setHoraInicioExpediente(result.getTime("hora_inicio_expediente"));
                model.setHoraFimExpediente(result.getTime("hora_fim_expediente"));
                model.setHoraAlmoco(result.getTime("hora_almoco"));
                model.setIdPergunta(result.getInt("id_pergunta"));
                model.setRespostaPergunta(result.getString("resposta_pergunta"));
                model.setIdSetor(result.getInt("id_setor"));
                model.setIdPerfil(result.getInt("id_perfil"));
                UsefulMethods.setCurrentUserModel(model);

                new MainJFrame().setVisible(true);
                dispose();
            } else { // a senha não está correta
                errorLog.setText("A senha para o usuário \"" + userInput.getText() + "\" esta incorreta");
                paintInput(1, 1);
            }
        } else { // o usuário não existe no banco
            errorLog.setText("Não foi encontrado nenhum usuário com o nome \"" + userInput.getText() + "\"");
            paintInput(0, 1);
            paintInput(1, 1);
        }

        userInput.setEnabled(true);
        passInput.setEnabled(true);
        jCheckBox1.setEnabled(true);
        jButton1.setEnabled(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLog;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField passInput;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables
}
