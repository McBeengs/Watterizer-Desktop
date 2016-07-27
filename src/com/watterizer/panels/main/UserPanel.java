/* **********   MeasurerPanel.java   **********
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
import com.watterizer.crypto.Encrypter;
import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.animation.ScaleAnimator;
import com.watterizer.animation.ScallableComponent;
import com.watterizer.util.UsefulMethods;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserPanel extends SLPanel {

    private Connection db;
    private ArrayList<String> perguntas;
    private ScaleAnimator iconAnimator;
    private ImageIcon icon;
    @SuppressWarnings("FieldMayBeFinal")
    private String pergunta;
    private boolean isEditing = false;

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public UserPanel() {
        initComponents();
        
        iconAnimator = new ScaleAnimator(iconDisplayer, 185, 185, 100);
        db = UsefulMethods.getDBInstance();
        try {
            ResultSet rs = db.createStatement().executeQuery("SELECT * FROM perguntasecreta WHERE id = "
                    + UsefulMethods.getCurrentUserModel().getIdPergunta());
            rs.next();
            pergunta = rs.getString("pergunta");
        } catch (SQLException ex) {
            pergunta = "[Pergunta alterada, excluída ou indisponível]";
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    //URL url = new URL("http://10.0.3.230:8080/Watterizer/img/imagensPerfil/fotoid" + UsefulMethods.getCurrentUserModel().getId() + ".png".trim());
                    //Image image = new ImageIcon(url).getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
                    //icon = new ImageIcon(url);
                    icon = new ImageIcon(new URL("https://66.media.tumblr.com/1108e527b5ed8cc70879517343fae063/tumblr_inline_o60l4rkLr71rqzcqm_500.gif"));
                } catch (Exception ex) {
                    icon = new ImageIcon(getClass().getResource("/com/watterizer/style/images/errorScreen.png"));
                }

                iconDisplayer.setIcon(icon);
            }
        }.start();

        canvas.setLayout(new GridLayout(0, 1));
        canvas.add(new ShowPanel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        iconDisplayer = new javax.swing.JLabel();
        canvas = new javax.swing.JPanel();
        editButton = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 200, 20));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 36)); // NOI18N
        jLabel2.setText("Usuário");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(461, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jSeparator2.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator2.setForeground(new java.awt.Color(255, 200, 20));

        iconDisplayer.setBackground(new java.awt.Color(255, 255, 255));
        iconDisplayer.setForeground(new java.awt.Color(255, 255, 255));
        iconDisplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconDisplayer.setText(" ");
        iconDisplayer.setBorder(new RoundedCornerBorder(255, 255, getBackground()));
        iconDisplayer.setOpaque(true);
        iconDisplayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconDisplayerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconDisplayerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconDisplayerMouseExited(evt);
            }
        });

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/editSmall.png"))); // NOI18N
        editButton.setText("Editar informações");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(iconDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(iconDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconDisplayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconDisplayerMouseClicked
        if (isEditing) {
            if (evt.getButton() == 1) {
                rightClickEvent();
            } else if (evt.getButton() == 3) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem change = new JMenuItem(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rightClickEvent();
                    }
                });
                change.setText("Alterar imagem");
                menu.add(change);

                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_iconDisplayerMouseClicked

    private void iconDisplayerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconDisplayerMouseEntered
        if (isEditing) {
            iconDisplayer.setEnabled(true);
        }
    }//GEN-LAST:event_iconDisplayerMouseEntered

    private void iconDisplayerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconDisplayerMouseExited
        if (isEditing) {
            iconDisplayer.setEnabled(false);
        }
    }//GEN-LAST:event_iconDisplayerMouseExited

    private void editButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_editButtonMouseEntered

    private void editButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_editButtonMouseExited

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        if (isEditing) {
            EditPanel panel = (EditPanel) canvas.getComponent(0);
            boolean hasChanged = false;

            if (!UsefulMethods.getCurrentUserModel().getNome().equals(panel.nameInput.getText())) {
                UsefulMethods.getCurrentUserModel().setNome(panel.nameInput.getText());
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getEmail().equals(panel.emailInput.getText())) {
                UsefulMethods.getCurrentUserModel().setEmail(panel.emailInput.getText());
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getTelefone().equals(panel.telephoneInput.getText())) {
                UsefulMethods.getCurrentUserModel().setTelefone(panel.telephoneInput.getText());
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getUsername().equals(panel.usernameInput.getText())) {
                UsefulMethods.getCurrentUserModel().setUsername(panel.usernameInput.getText());
                hasChanged = true;
            }

            String senha = "";
            for (char c : panel.passwordInput.getPassword()) {
                senha += c;
            }
            if (!UsefulMethods.getCurrentUserModel().getSenha().equals(senha)) {
                UsefulMethods.getCurrentUserModel().setSenha(Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, senha));
                hasChanged = true;
            }

            int idNova;
            for (idNova = 0; idNova < perguntas.size(); idNova++) {
                if (panel.questionDropdown.getSelectedItem().toString().equals(perguntas.get(idNova))) {
                    break;
                }
            }

            idNova = Integer.parseInt(perguntas.get(idNova - 1).substring(1, 2));
            if (UsefulMethods.getCurrentUserModel().getIdPergunta() != idNova) {
                UsefulMethods.getCurrentUserModel().setIdPergunta(idNova);
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getRespostaPergunta().equals(panel.awnserInput.getText())) {
                UsefulMethods.getCurrentUserModel().setRespostaPergunta(panel.awnserInput.getText());
                hasChanged = true;
            }

            if (hasChanged) {
                UsefulMethods.saveCurrentUserModel();
            }

            canvas.removeAll();
            editButton.setText("Editar informações");
            editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/editSmall.png")));
            iconDisplayer.setIcon(icon);
            canvas.add(new ShowPanel());
            isEditing = false;
            canvas.revalidate();
        } else {
            editButton.setText("Carregando...");
            editButton.setEnabled(false);
            editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/saveSmall.png")));
            iconDisplayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/notepad.png")));
            ShowPanel panel = (ShowPanel) canvas.getComponent(0);
            panel.deactivateAll();

            new Thread() {
                @Override
                public void run() {
                    EditPanel set = new EditPanel();
                    perguntas = set.getPerguntas();
                    canvas.removeAll();
                    editButton.setText("Salvar informações");
                    editButton.setEnabled(true);
                    canvas.add(set);
                    isEditing = true;
                    canvas.revalidate();
                }
            }.start();
        }
    }//GEN-LAST:event_editButtonMouseClicked

    public void beginAnimations() {
        iconDisplayer.setVisible(true);
        iconAnimator.beginAnimation();
    }
    
    public void resetAnimations() {
        iconDisplayer.setOpaque(false);
    }
    
    private void rightClickEvent() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Todos ", "gif", "png"));

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                String ftpUrl = String.format("ftp://%s:%s@%s/%s;type=i", "bruno", "bruno", "10.0.3.230:21", "fotoid" + UsefulMethods.getCurrentUserModel().getId() + ".png");
                FileInputStream inputStream;
                try (OutputStream outputStream = new URL(ftpUrl).openConnection().getOutputStream()) {
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                inputStream.close();

                try {
                    URL url = new URL("http://10.0.3.230:8080/Watterizer/img/imagensPerfil/fotoid" + UsefulMethods.getCurrentUserModel().getId() + ".png".trim());
                    //Image image = new ImageIcon(url).getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(url);
                } catch (Exception ex) {
                    System.out.println("erro atualizando a imagem após upload");
                    icon = new ImageIcon(getClass().getResource("/com/watterizer/style/images/errorScreen.png"));
                }

                JOptionPane.showMessageDialog(null, "A imagem foi alterada. Poderá ser preciso reiniciar o programa.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

            } catch (MalformedURLException ex) {
                Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel canvas;
    private javax.swing.JLabel editButton;
    private javax.swing.JLabel iconDisplayer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    private class ScallableJLabel extends JLabel implements ScallableComponent<JLabel> {

        private double scale = 1.0d;
        
        @Override
        public void setScale(double scale) {
            this.scale = scale;
        }
        
    }
}
