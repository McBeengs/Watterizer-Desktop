/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.panels;

import com.watterizer.Starter;
import com.watterizer.modals.MainSeederJFrame;
import com.watterizer.crypto.Encrypter;
import com.watterizer.modals.MainLeecherJFrame;
import com.watterizer.util.OpaqueScreen;
import com.watterizer.util.UsefulMethods;
import com.watterizer.models.UserModel;
import com.watterizer.xml.XmlManager;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 15153769
 */
public class LoginJPanel extends javax.swing.JPanel {

    private final XmlManager xml;
    private final XmlManager language;
    private final OpaqueScreen rootScreen;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public LoginJPanel(OpaqueScreen rootScreen) throws IOException, InterruptedException {
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        language = UsefulMethods.getManagerInstance(UsefulMethods.LANGUAGE);
        initComponents();
        setVisible(false);

        if (Boolean.parseBoolean(xml.getContentByName("autoLogin", 0))) {
            userInput.setText(Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, xml.getContentByName("user", 0)));
            passInput.setText(Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, xml.getContentByName("pass", 0)));

            OpaqueScreen screen = new OpaqueScreen();
            screen.setVisible(true);

            if (JOptionPane.showConfirmDialog(screen.getRootFrame(), "Logar-se como \"" + userInput.getText() + "\"?") == JOptionPane.YES_OPTION) {
                if (checkInput()) {
                    screen.close();
                    checkDB();
                } else {
                    setVisible(true);
                }
            } else {
                screen.close();
                setVisible(true);
            }
        } else {
            setVisible(true);
        }

        this.rootScreen = rootScreen;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        passInput = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        errorLog = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jSeparator2.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator2.setForeground(new java.awt.Color(255, 200, 20));
        jSeparator2.setPreferredSize(new java.awt.Dimension(0, 3));

        jPanel1.setBackground(new java.awt.Color(255, 200, 20));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/logoMedium.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(UsefulMethods.getHeaderFont());
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Login");

        userInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userInputKeyPressed(evt);
            }
        });

        passInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passInputKeyPressed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText(language.getContentById("keepLogin"));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

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

        errorLog.setBackground(new java.awt.Color(0, 0, 0));
        errorLog.setForeground(new java.awt.Color(255, 51, 51));
        errorLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLog.setText("    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passInput)
                            .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(174, Short.MAX_VALUE))
            .addComponent(errorLog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passInput, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorLog, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jCheckBox1.setSelected(Boolean.parseBoolean(xml.getContentByName("autoLogin", 0)));
    }// </editor-fold>//GEN-END:initComponents

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
                        xml.setContentByName("user", 0, "null");
                        xml.setContentByName("pass", 0, "null");
                    }
                    checkDB();
                }
            }

        }.start();
    }//GEN-LAST:event_jButton1ActionPerformed

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
                    System.out.println(xml);
                    xml.saveXml();

                    String s = "{\n"
                            + "\"login\":\"" + Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, userInput.getText()) + "\",\n"
                            + "\"senha\":\"" + Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, new String(passInput.getPassword())) + "\"\n"
                            + "}";
                    String result = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":"
                            + xml.getContentByName("webServicePort", 0) + "/login", "POST", s);
                    String json = Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, result);
                    json = json.substring(1, json.length() - 1);
                    try {
                        JSONObject obj = new JSONObject(json);
                        UserModel model = new UserModel();
                        model.setId(obj.getInt("id"));
                        model.setUsername(obj.getString("username"));
                        model.setSenha(obj.getString("senha"));
                        model.setEmail(obj.getString("email"));
                        model.setNome(obj.getString("nome"));
                        model.setTelefone(obj.getString("telefone"));
                        model.setPerfil(obj.getString("perfil"));
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        model.setHoraEntrada(new Time(sdf.parse(obj.getString("hora_entrada")).getTime()));
                        model.setHoraIntervalo(new Time(sdf.parse(obj.getString("hora_intervalo")).getTime()));
                        model.setHoraSaida(new Time(sdf.parse(obj.getString("hora_saida")).getTime()));
                        model.setIdPergunta(obj.getInt("id_pergunta"));
                        model.setRespostaPergunta(obj.getString("resposta_pergunta"));
                        model.setIdPerfil(obj.getInt("id_perfil"));
                        model.setTokenDesktop(obj.getString("token_desktop"));
                        UsefulMethods.setCurrentUserModel(model);

                        if (xml.getContentByName("terminalType", 0).equals("0") && model.getPerfil().toLowerCase().equals("administrador")) {
                            new MainSeederJFrame().setVisible(true);
                            System.err.println("painel seeder iniciado");
                        } else {
                            new MainLeecherJFrame().setVisible(true);
                            System.err.println("painel leecher iniciado");
                        }

                        if (xml.getContentByName("lastEnd", 0).equals("false")) {
                            System.err.println("última saída inválida");
                            String path = LoginJPanel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                            String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
                            decodedPath = decodedPath.substring(1).replace("\\", File.separator).replace("/", File.separator);
                            if (decodedPath.contains("watt_exec.jar")) {
                                try {
                                    ProcessBuilder pb = new ProcessBuilder("java", "-jar", UsefulMethods.getClassPath(Starter.class) + File.separator + "watt_runn.jar",
                                            UsefulMethods.getUserModel().getNome(), "" + UsefulMethods.getUserModel().getId());
                                    pb.directory(new File(UsefulMethods.getClassPath(Starter.class)));
                                    pb.start();
                                    System.err.println("watt_runn.jar iniciado");
                                } catch (Exception ex) {
                                    Logger.getLogger(LoginJPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                        xml.setContentByName("lastEnd", 0, "false");
                        xml.saveXml();

                        rootScreen.close();

                    } catch (JSONException | ParseException ex) {
                        errorLog.setText("Login inválido");
                        paintInput(0, 1);
                        paintInput(1, 1);
                        Logger.getLogger(LoginJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    userInput.setEnabled(true);
                    passInput.setEnabled(true);
                    jCheckBox1.setEnabled(true);
                    jButton1.setEnabled(true);
                } catch (Exception ex) {
                    Logger.getLogger(LoginJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(0);
                }
            }
        }.start();
    }

    private JPanel getPanel() {
        return this;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLog;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField passInput;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables

    public static class ContentPane extends JPanel {

        public ContentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Allow super to paint
            super.paintComponent(g);
            // Apply our own painting effect
            Graphics2D g2d = (Graphics2D) g.create();
            // 50% transparent Alpha
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setColor(getBackground());
            g2d.fill(getBounds());
            g2d.dispose();
        }
    }
}
