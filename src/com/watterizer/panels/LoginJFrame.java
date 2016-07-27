package com.watterizer.panels;

import com.watterizer.crypto.Encrypter;
import com.watterizer.panels.main.JFrame;
import com.watterizer.util.UsefulMethods;
import com.watterizer.util.UserModel;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginJFrame extends javax.swing.JFrame {

    private final XmlManager xml;
    private final XmlManager language;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public LoginJFrame() {
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        language = UsefulMethods.getManagerInstance(UsefulMethods.LANGUAGE);
        initComponents();
        setVisible(false);
        setTitle("Login | Watterizer");
        getContentPane().setBackground(Color.BLACK);

        if (Boolean.parseBoolean(xml.getContentByName("autoLogin", 0))) {
            userInput.setText(Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, xml.getContentByName("user", 0)));
            passInput.setText(Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, xml.getContentByName("pass", 0)));
            if (JOptionPane.showConfirmDialog(rootPane, "Logar-se como \"" +userInput.getText() + "\"?") == JOptionPane.YES_OPTION) {
                if (checkInput()) {
                    checkDB();
                } else {
                    setVisible(true);
                }
            } else {
                setVisible(true);
            }
        } else {
            setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passInput = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        errorLog = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        userInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userInputKeyPressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(language.getContentById("username"));

        passInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passInputKeyPressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText(language.getContentById("password"));

        jCheckBox1.setBackground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText(language.getContentById("keepLogin"));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        errorLog.setBackground(new java.awt.Color(0, 0, 0));
        errorLog.setForeground(new java.awt.Color(255, 51, 51));
        errorLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLog.setText("    ");

        jPanel1.setBackground(new java.awt.Color(255, 200, 20));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/logoMedium.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator2.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator2.setForeground(new java.awt.Color(255, 200, 20));
        jSeparator2.setPreferredSize(new java.awt.Dimension(0, 3));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Login");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 117, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passInput)
                            .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(126, Short.MAX_VALUE))
            .addComponent(errorLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passInput, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(errorLog, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jCheckBox1.setSelected(Boolean.parseBoolean(xml.getContentByName("autoLogin", 0)));

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
                if (checkInput()) {
                    if (Boolean.parseBoolean(xml.getContentByName("autoLogin", 0))) {
                        xml.setContentByName("user", 0, Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, userInput.getText()));
                        xml.setContentByName("pass", 0, Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, new String(passInput.getPassword())));
                    } else {
                        xml.setContentByName("user", 0, "");
                        xml.setContentByName("pass", 0, "");
                    }
                    checkDB();
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
            if (checkInput()) {
                checkDB();
            }
        }
    }//GEN-LAST:event_userInputKeyPressed

    private void passInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passInputKeyPressed
        paintInput(1, 2);
        if (evt.getKeyCode() == 10) {
            userInput.setEnabled(false);
            passInput.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jButton1.setEnabled(false);
            if (checkInput()) {
                checkDB();
            }
        }
    }//GEN-LAST:event_passInputKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            xml.setContentByName("autoLogin", 0, "true");
        } else {
            xml.setContentByName("autoLogin", 0, "false");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            } else if (userInput.getText().length() <= 0) {
                errorLog.setText("Username vazio");
                paintInput(0, 1);
                userInput.setEnabled(true);
                passInput.setEnabled(true);
                jCheckBox1.setEnabled(true);
                jButton1.setEnabled(true);
            } else if (passInput.getPassword().length <= 0) {
                errorLog.setText("Password vazio");
                paintInput(1, 1);
                userInput.setEnabled(true);
                passInput.setEnabled(true);
                jCheckBox1.setEnabled(true);
                jButton1.setEnabled(true);
            }
            return false;
        }
        return true;
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

    private void checkDB() {
        new Thread() {
            @Override
            public void run() {
                try {
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
                            xml.saveXml();

                            new JFrame().setVisible(true);
                            dispose();
                        } else { // a senha não está correta
                            errorLog.setText("A senha para o usuário \"" + userInput.getText() + "\" está incorreta");
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
                } catch (SQLException | IOException ex) {

                }
            }
        }.start();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLog;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField passInput;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables
}
