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

import com.watterizer.crypto.Encrypter;
import com.watterizer.style.RoundedCornerBorder;
import com.watterizer.util.UsefulMethods;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserPanel extends javax.swing.JPanel {

    private Connection db;
    private GridBagConstraints gbc;
    private ArrayList<String> perguntas;
    private ImageIcon icon;
    private String pergunta;
    private boolean isEditing = false;

    public UserPanel() {
        initComponents();
        db = UsefulMethods.getDBInstance();
        try {
            ResultSet rs = db.createStatement().executeQuery("SELECT * FROM perguntaseguranca WHERE id = "
                    + UsefulMethods.getCurrentUserModel().getIdPergunta());
            rs.next();
            pergunta = rs.getString("pergunta");
        } catch (SQLException ex) {
            pergunta = "[Pergunta alterada, excluída ou indisponível]";
        }

        container.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        container.add(new ShowPanel(), gbc);

        try {
            URL url = new URL("http://10.0.3.230:8080/Watterizer/img/imagensPerfil/fotoid" + UsefulMethods.getCurrentUserModel().getId() + ".png".trim());
            //Image image = new ImageIcon(url).getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
            icon = new ImageIcon(url);
        } catch (Exception ex) {
            icon = new ImageIcon(getClass().getResource("/com/watterizer/style/images/errorScreen.png"));
        }

        iconDisplayer.setIcon(icon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconDisplayer = new javax.swing.JLabel();
        editButton = new javax.swing.JLabel();
        container = new javax.swing.JPanel();

        iconDisplayer.setBackground(new java.awt.Color(0, 0, 0));
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

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/notepad.png"))); // NOI18N
        editButton.setText("Editar");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 238, Short.MAX_VALUE)
                        .addComponent(iconDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iconDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton))
                .addGap(18, 18, 18)
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        if (isEditing) {
            EditPanel panel = (EditPanel) container.getComponent(0);
            boolean hasChanged = false;

            if (!UsefulMethods.getCurrentUserModel().getNome().equals(panel.nameLabel.getText())) {
                UsefulMethods.getCurrentUserModel().setNome(panel.nameLabel.getText());
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getEmail().equals(panel.emailLabel.getText())) {
                UsefulMethods.getCurrentUserModel().setEmail(panel.emailLabel.getText());
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getTelefone().equals(panel.telefoneLabel.getText())) {
                UsefulMethods.getCurrentUserModel().setTelefone(panel.telefoneLabel.getText());
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getUsername().equals(panel.usernameLabel.getText())) {
                UsefulMethods.getCurrentUserModel().setUsername(panel.usernameLabel.getText());
                hasChanged = true;
            }

            String senha = "";
            for (char c : panel.passwordLabel.getPassword()) {
                senha += c;
            }
            if (!UsefulMethods.getCurrentUserModel().getSenha().equals(senha)) {
                UsefulMethods.getCurrentUserModel().setSenha(Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, senha));
                hasChanged = true;
            }

            int idNova;
            for (idNova = 0; idNova < perguntas.size(); idNova++) {
                if (panel.perguntaLabel.getSelectedItem().toString().equals(perguntas.get(idNova))) {
                    break;
                }
            }

            idNova = Integer.parseInt(perguntas.get(idNova - 1).substring(1, 2));
            if (UsefulMethods.getCurrentUserModel().getIdPergunta() != idNova) {
                UsefulMethods.getCurrentUserModel().setIdPergunta(idNova);
                hasChanged = true;
            }
            if (!UsefulMethods.getCurrentUserModel().getResposta().equals(panel.respostaLabel.getText())) {
                UsefulMethods.getCurrentUserModel().setResposta(panel.respostaLabel.getText());
                hasChanged = true;
            }

            if (hasChanged) {
                UsefulMethods.saveCurrentUserModel();
            }

            container.removeAll();
            editButton.setText("Editar");
            iconDisplayer.setIcon(icon);
            container.add(new ShowPanel());
            isEditing = false;
            container.revalidate();
        } else {
            editButton.setText("Carregando...");
            editButton.setEnabled(false);
            iconDisplayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/notepad.png")));
            ShowPanel panel = (ShowPanel) container.getComponent(0);
            panel.deactivateAll();

            new Thread() {
                @Override
                public void run() {
                    EditPanel set = new EditPanel();
                    container.removeAll();
                    editButton.setText("Salvar");
                    editButton.setEnabled(true);
                    container.add(set);
                    isEditing = true;
                    container.revalidate();
                }
            }.start();
        }
    }//GEN-LAST:event_editButtonMouseClicked

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
    private javax.swing.JPanel container;
    private javax.swing.JLabel editButton;
    private javax.swing.JLabel iconDisplayer;
    // End of variables declaration//GEN-END:variables

    private class ShowPanel extends JPanel {

        public ShowPanel() {
            initComponents();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            t1 = new javax.swing.JLabel();
            nameLabel = new javax.swing.JLabel();
            t2 = new javax.swing.JLabel();
            emailLabel = new javax.swing.JLabel();
            t3 = new javax.swing.JLabel();
            telefoneLabel = new javax.swing.JLabel();
            t4 = new javax.swing.JLabel();
            usernameLabel = new javax.swing.JLabel();
            t5 = new javax.swing.JLabel();
            passwordLabel = new javax.swing.JLabel();
            t6 = new javax.swing.JLabel();
            perguntaLabel = new javax.swing.JLabel();
            t7 = new javax.swing.JLabel();
            respostaLabel = new javax.swing.JLabel();

            iconDisplayer.setEnabled(true);

            //setPreferredSize(new java.awt.Dimension(620, 203));
            t1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t1.setText("Nome:");

            nameLabel.setText(UsefulMethods.getCurrentUserModel().getNome());

            t2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t2.setText("E-Mail:");

            emailLabel.setText(UsefulMethods.getCurrentUserModel().getEmail());

            t3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t3.setText("Telefone:");

            telefoneLabel.setText(UsefulMethods.getCurrentUserModel().getTelefone());

            t4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t4.setText("Username:");

            usernameLabel.setText(UsefulMethods.getCurrentUserModel().getUsername());

            t5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t5.setText("Senha:");

            String show = "";
            for (int i = 0; i < UsefulMethods.getCurrentUserModel().getSenha().length(); i++) {
                show += "*";
            }
            passwordLabel.setText(show);

            t6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t6.setText("Pergunta Secreta:");

            perguntaLabel.setText(pergunta);

            t7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t7.setText("Resposta:");

            respostaLabel.setText(UsefulMethods.getCurrentUserModel().getResposta());

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(204, 204, 204)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(t6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameLabel)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(telefoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(passwordLabel)
                                    .addComponent(perguntaLabel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(respostaLabel))
                            .addGap(100, 100, 100))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telefoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(perguntaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(respostaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16))
            );
        }// </editor-fold>                        

        public void deactivateAll() {
            emailLabel.setEnabled(false);
            nameLabel.setEnabled(false);
            passwordLabel.setEnabled(false);
            perguntaLabel.setEnabled(false);
            respostaLabel.setEnabled(false);
            telefoneLabel.setEnabled(false);
            usernameLabel.setEnabled(false);
            t1.setEnabled(false);
            t2.setEnabled(false);
            t3.setEnabled(false);
            t4.setEnabled(false);
            t5.setEnabled(false);
            t6.setEnabled(false);
            t7.setEnabled(false);
        }

        private javax.swing.JLabel emailLabel;
        private javax.swing.JLabel nameLabel;
        private javax.swing.JLabel passwordLabel;
        private javax.swing.JLabel perguntaLabel;
        private javax.swing.JLabel respostaLabel;
        private javax.swing.JLabel t1;
        private javax.swing.JLabel t2;
        private javax.swing.JLabel t3;
        private javax.swing.JLabel t4;
        private javax.swing.JLabel t5;
        private javax.swing.JLabel t6;
        private javax.swing.JLabel t7;
        private javax.swing.JLabel telefoneLabel;
        private javax.swing.JLabel usernameLabel;
    }

    private class EditPanel extends JPanel {

        public EditPanel() {
            initComponents();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            t1 = new javax.swing.JLabel();
            nameLabel = new javax.swing.JTextField();
            t2 = new javax.swing.JLabel();
            emailLabel = new javax.swing.JTextField();
            t3 = new javax.swing.JLabel();
            telefoneLabel = new javax.swing.JTextField();
            t4 = new javax.swing.JLabel();
            usernameLabel = new javax.swing.JTextField();
            t5 = new javax.swing.JLabel();
            passwordLabel = new javax.swing.JPasswordField();
            t6 = new javax.swing.JLabel();

            iconDisplayer.setEnabled(false);

            perguntaLabel = new javax.swing.JComboBox();
            String[] big = new String[]{"--- Selecione uma pergunta ---"};
            perguntas = new ArrayList<>();
            try {
                ResultSet rs = db.createStatement().executeQuery("SELECT * FROM perguntaseguranca");

                while (rs.next()) {
                    String found = "[" + rs.getInt("id") + "]" + rs.getString("pergunta");
                    perguntas.add(found);
                }

                big = new String[perguntas.size() + 1];

                big[0] = "--- Selecione uma pergunta ---";
                for (int i = 0; i < perguntas.size(); i++) {
                    big[i + 1] = perguntas.get(i).substring(3, perguntas.get(i).length());
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            perguntaLabel.setModel(new DefaultComboBoxModel(big));

            for (int i = 0; i < perguntaLabel.getItemCount(); i++) {
                if (perguntaLabel.getItemAt(i).equals(pergunta)) {
                    perguntaLabel.setSelectedIndex(i);
                    break;
                }
            }

            t7 = new javax.swing.JLabel();
            respostaLabel = new javax.swing.JTextField();

            setPreferredSize(new java.awt.Dimension(620, 250));

            t1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t1.setText("Nome:");

            nameLabel.setText(UsefulMethods.getCurrentUserModel().getNome());

            t2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t2.setText("E-Mail:");

            emailLabel.setText(UsefulMethods.getCurrentUserModel().getEmail());

            t3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t3.setText("Telefone:");

            telefoneLabel.setText(UsefulMethods.getCurrentUserModel().getTelefone());

            t4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t4.setText("Username:");

            usernameLabel.setText(UsefulMethods.getCurrentUserModel().getUsername());

            t5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t5.setText("Senha:");

            passwordLabel.setText(UsefulMethods.getCurrentUserModel().getSenha());

            t6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t6.setText("Pergunta Secreta:");

            t7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            t7.setText("Resposta:");

            respostaLabel.setText(UsefulMethods.getCurrentUserModel().getResposta());

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(204, 204, 204)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(t6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameLabel)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(telefoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(passwordLabel)
                                    .addComponent(perguntaLabel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(respostaLabel))
                            .addGap(100, 100, 100))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telefoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(perguntaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(respostaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16))
            );
        }// </editor-fold>

        public javax.swing.JTextField emailLabel;
        public javax.swing.JTextField nameLabel;
        public javax.swing.JPasswordField passwordLabel;
        public javax.swing.JComboBox perguntaLabel;
        public javax.swing.JTextField respostaLabel;
        public javax.swing.JLabel t1;
        public javax.swing.JLabel t2;
        public javax.swing.JLabel t3;
        public javax.swing.JLabel t4;
        public javax.swing.JLabel t5;
        public javax.swing.JLabel t6;
        public javax.swing.JLabel t7;
        public javax.swing.JTextField telefoneLabel;
        public javax.swing.JTextField usernameLabel;
    }
}
