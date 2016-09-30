/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.modals;

import com.watterizer.models.PCModel;
import com.watterizer.net.SocketNodeJS;
import com.watterizer.panels.SelectConfig;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 15153769
 */
public class FirstUseSetupJFrame extends javax.swing.JFrame {

    private static XmlManager xml;
    private PCModel model;
    private final GridBagConstraints c = new GridBagConstraints();
    private JButton previous = new JButton();
    private JButton next = new JButton();
    private int currentPanel = 0;

    public FirstUseSetupJFrame(XmlManager xml) {
        initComponents();
        FirstUseSetupJFrame.xml = xml;
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(getWelcomePanel());

        c.gridx = 0;
        c.gridy = 0;
        previous.setEnabled(false);
        previous.addActionListener((ActionEvent ae) -> {
            previousPanel();
        });

        next.addActionListener((ActionEvent ae) -> {
            nextPanel();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(null, "Se você parar agora, as configurações serão perdidas. Tem certeza?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    eraseConfig();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        contentPane = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 200, 20));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/images/logoMedium.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void previousPanel() {
        currentPanel--;
        contentPane.removeAll();

        switch (currentPanel) {
            case 1:
                EventQueue.invokeLater(() -> {
                    contentPane.add(getWebServiceConnPanel(), c);
                    contentPane.revalidate();
                    contentPane.repaint();
                });
                previous.setEnabled(false);
                break;
            case 2:
                
                break;
        }
    }

    private void nextPanel() {
        currentPanel++;
        next.setEnabled(false);
        contentPane.removeAll();
        switch (currentPanel) {
            case 1:
                EventQueue.invokeLater(() -> {
                    contentPane.add(getWebServiceConnPanel(), c);
                    contentPane.revalidate();
                    contentPane.repaint();
                });
                break;
            case 2:
                EventQueue.invokeLater(() -> {
                    contentPane.add(getTerminalPanel(), c);
                    contentPane.revalidate();
                    contentPane.repaint();
                });
                previous.setEnabled(true);
                break;
        }
    }

    private void eraseConfig() {
        File getConfig = new File(UsefulMethods.getClassPath(UsefulMethods.class) + "config" + File.separator + "options.xml");
        if (getConfig.exists()) {
            FirstUseSetupJFrame.xml = null;
            System.gc();
            getConfig.deleteOnExit();
            System.exit(0);
        }
    }

    private JPanel getSelectConfigPanel() {
        JPanel pane = new JPanel();
        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JLabel errorText = new javax.swing.JLabel();
        javax.swing.JRadioButton jRadioButton1 = new javax.swing.JRadioButton();
        javax.swing.JRadioButton jRadioButton2 = new javax.swing.JRadioButton();
        javax.swing.JLabel errorTitle = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();

        errorText.setForeground(new java.awt.Color(255, 255, 255));
        errorText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        errorText.setText("De qual modo este terminal deve ser configurado?");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jRadioButton1.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Seeder");

        jRadioButton2.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Leecher");

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        errorTitle.setFont(header);
        errorTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        errorTitle.setText("Configuração Básica");

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                UsefulMethods.makeBalloon(jLabel1, "<html><body>Computadores onde a unidade Arduino será instalada<br>recebem o nome de \"Seeders\". Já aqueles"
                        + " que serão<br>usados sem realizar a medição, recebem o nome de \"Leechers\".</html></body>", 5000, Color.yellow);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/question.png"))); // NOI18N

        next.setText("Próximo");

        previous.setText("Anterior");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)
                        .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(errorText)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(errorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(previous)
                                        .addGap(428, 428, 428)
                                        .addComponent(next)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(errorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton1)
                                .addComponent(jRadioButton2))
                        .addGap(170, 170, 170)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    private JPanel getWelcomePanel() {
        JPanel pane = new JPanel();
        javax.swing.JLabel errorText = new javax.swing.JLabel();
        javax.swing.JLabel errorTitle = new javax.swing.JLabel();

        errorText.setForeground(new java.awt.Color(255, 255, 255));
        errorText.setText("<html><body style=\"text-align: justify;\">    Seja bem vindo ao auxiliar de configuração para a aplicação Watterizer. Clique em \"Próximo\" para continuar</body></html>");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        errorTitle.setFont(header);
        errorTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        errorTitle.setText("Bem Vindo");

        next.setText("Próximo");

        previous.setText("Anterior");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(errorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(previous)
                                        .addGap(428, 428, 428)
                                        .addComponent(next))
                                .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(errorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    private JPanel getWebServiceConnPanel() {
        JPanel pane = new JPanel();

        javax.swing.JLabel errorText = new javax.swing.JLabel();
        javax.swing.JLabel errorTitle = new javax.swing.JLabel();
        javax.swing.JLabel errorText1 = new javax.swing.JLabel();
        javax.swing.JTextField jTextField1 = new javax.swing.JTextField();
        javax.swing.JLabel errorText2 = new javax.swing.JLabel();
        javax.swing.JTextField jTextField2 = new javax.swing.JTextField();
        javax.swing.JLabel errorText3 = new javax.swing.JLabel();
        javax.swing.JTextField jTextField3 = new javax.swing.JTextField();
        JButton jButton1 = new javax.swing.JButton();
        javax.swing.JLabel display = new javax.swing.JLabel();

        errorText.setForeground(new java.awt.Color(255, 255, 255));
        errorText.setText("<html><body style=\"text-align: justify;\">    Antes de mais nada, precisamos estabelecer uma conexão entre o servidor para que as etapas seguintes "
                + "sejam configuradas. Caso não saiba os campos abaixo, entre em contato com o responsável pela arquitetura do sistema.</body></html>");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        errorTitle.setFont(header);
        errorTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        errorTitle.setText("Conectar-se ao Servidor");

        errorText1.setForeground(new java.awt.Color(255, 255, 255));
        errorText1.setText("Url:");

        jTextField1.setText("10.0.4.70");

        errorText2.setForeground(new java.awt.Color(255, 255, 255));
        errorText2.setText("Porta Socket:");

        jTextField2.setText("3000");
        jTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int[] keys = new int[]{97, 98, 99, 100, 101, 102, 103, 104, 105, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48};

            }
        });

        errorText3.setForeground(new java.awt.Color(255, 255, 255));
        errorText3.setText("Porta WebService:");

        jTextField3.setText("1515");

        jButton1.setText("Testar");
        jButton1.addActionListener((ActionEvent ae) -> {
            new Thread() {
                @Override
                public void run() {
                    jTextField1.setEnabled(false);
                    jTextField2.setEnabled(false);
                    jTextField3.setEnabled(false);
                    jButton1.setEnabled(false);
                    next.setEnabled(false);
                    display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/spinner.gif")));
                    display.setForeground(Color.white);
                    display.setText("Testando...");
                    try {
                        SocketNodeJS socket = new SocketNodeJS();
                        socket.socketConnect(jTextField1.getText(), Integer.parseInt(jTextField2.getText()));

                        if (!socket.echo("test").isEmpty() && UsefulMethods.getWebServiceResponse("http://" + jTextField1.getText() + ":" + jTextField3.getText()
                                + "/test", "GET", null).equals("OK")) {
                            display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/ok.png")));
                            display.setForeground(new java.awt.Color(50, 205, 50));
                            display.setText("Conexão estabelecida.");
                            jTextField1.setEnabled(true);
                            jTextField2.setEnabled(true);
                            jTextField3.setEnabled(true);
                            jButton1.setEnabled(true);

                            xml.setContentByName("webServiceHost", 0, jTextField1.getText());
                            xml.setContentByName("socketPort", 0, jTextField2.getText());
                            xml.setContentByName("webServicePort", 0, jTextField3.getText());

                            next.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/error2.png")));
                        display.setForeground(new java.awt.Color(238, 44, 44));
                        display.setText("Falha com a conexão.");
                        jTextField1.setEnabled(true);
                        jTextField2.setEnabled(true);
                        jTextField3.setEnabled(true);
                        jButton1.setEnabled(true);
                    }
                }
            }.start();
        });

        display.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        display.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(errorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(errorText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(previous)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(next)))
                                        .addGap(0, 10, Short.MAX_VALUE))
                                .addComponent(display, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(errorText1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(errorText2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(errorText3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(errorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(errorText1)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorText2)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorText3)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(display)
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    private JPanel getTerminalPanel() {
        String pcName = "";
        ArrayList<String> macs = new ArrayList<>();
        Map<Integer, String> setores = new HashMap<>();

        try {
            String json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + Integer
                    .parseInt(xml.getContentByName("webServicePort", 0)) + "/setorcheck", "GET", null);

            if (json.equals("false")) {
                JOptionPane.showMessageDialog(this, "Não existe nenhum setor cadastrado no servidor. Por favor entre no módulo web e cadastre ao menos um para continuar.",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                eraseConfig();
                System.exit(0);
            } else {
                json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + Integer
                    .parseInt(xml.getContentByName("webServicePort", 0)) + "/setor", "GET", null);
                
                json = json.substring(1, json.length() - 1);
                setores.put(0, "- Selecione um Setor -");
                int i = 1;
                for (String s : json.split("},")) {
                    if (!s.endsWith("}")) {
                        s += "}";
                    }
                    JSONObject obj = new JSONObject(s);
                    setores.put(obj.getInt("id"), obj.getString("setor"));
                    i++;
                }
            }

            boolean isSaved = false;
            InetAddress ip = InetAddress.getLocalHost();
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();

            macs.add("- Selecione um MAC -");
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();

                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }

                    macs.add(sb.toString());
                    String s = "{"
                            + "\"mac\" : \"" + sb.toString() + "\","
                            + "\"command\" : \"check\""
                            + "}";
                    json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + Integer
                            .parseInt(xml.getContentByName("webServicePort", 0)) + "/pccheck", "POST", s);
                    json = json.substring(1, json.length() - 1);
                    if (!json.isEmpty()) {
                        isSaved = true;
                        break;
                    }
                }
            }

            if (isSaved) {

            }

            pcName = ip.getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        JPanel pane = new JPanel();
        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JLabel top = new javax.swing.JLabel();
        javax.swing.JLabel text = new javax.swing.JLabel();
        javax.swing.JLabel label1 = new javax.swing.JLabel();
        javax.swing.JLabel info1 = new javax.swing.JLabel();
        javax.swing.JComboBox jComboBox1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel label2 = new javax.swing.JLabel();
        javax.swing.JLabel info2 = new javax.swing.JLabel();
        javax.swing.JTextField jTextField1 = new javax.swing.JTextField();
        javax.swing.JLabel label3 = new javax.swing.JLabel();
        javax.swing.JComboBox jComboBox2 = new javax.swing.JComboBox<>();
        javax.swing.JLabel label4 = new javax.swing.JLabel();
        javax.swing.JRadioButton jRadioButton1 = new javax.swing.JRadioButton();
        javax.swing.JRadioButton jRadioButton2 = new javax.swing.JRadioButton();
        javax.swing.JLabel info3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top.setFont(header);
        top.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top.setText("Identicficar terminal");

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("[text]");

        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("MAC:");

        info1.setForeground(new java.awt.Color(255, 255, 255));
        info1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/question.png"))); // NOI18N
        info1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                UsefulMethods.makeBalloon(info1, "<html>  Para que o computador possa ser desligado<br>remotamente, é necessário que"
                        + " o endereço<br>físico seja informado. Caso exista mais de um<br> endereço neste terminal, selecione aquele que<br>"
                        + "diz respeito a placa de rede em uso.</html>", 8000, Color.yellow);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(macs.toArray()));

        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText("Nome:");

        info2.setForeground(new java.awt.Color(255, 255, 255));
        info2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/question.png"))); // NOI18N
        info2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                UsefulMethods.makeBalloon(info2, "<html>  Quando necesário se referir a este terminal,<br>será usado o nome informado neste campo.</html>", 8000, Color.yellow);
            }
        });

        jTextField1.setText(pcName);

        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setText("Setor:");
        
        String[] setoresDisplay = new String[setores.size()];
        for (int i = 0; i < setoresDisplay.length; i++) {
            setoresDisplay[i] = setores.get(i);
        }

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(setoresDisplay));
        jComboBox2.addActionListener((ActionEvent ae) -> {
            for(Object i : setores.keySet().toArray()){
                if (setores.get(i).equals(jComboBox2.getSelectedItem().toString())) {
                    System.err.println("Id: " + i);
                    System.out.println("Setor: " + jComboBox2.getSelectedItem().toString());
                    break;
                }
            }
            //jComboBox2.getSelectedItem().toString()
        });

        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setText("Terminal:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Seeder");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Leecher");

        info3.setForeground(new java.awt.Color(255, 255, 255));
        info3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/question.png"))); // NOI18N
        info3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                UsefulMethods.makeBalloon(info3, "<html>  Configuração de uso para este terminal.<h3><em>Seeder:</em></h3>  O terminal que ficará responsável por "
                        + " medir e enviar<br>dados de consumo ao servidor. Não será utilizado pelo<br>usuário comum e necessariamente terá uma unidade<br>Arduino."
                        + "<h3><em>Leecher:</em></h3>  O terminal que a maioria dos usuários irá utilizar. Não<br>envia medições ao servidor e apenas atua como um<br>intermediário"
                        + " para a função de desligamento remoto.</html>", 8000, Color.yellow);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(previous)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(next)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(info1)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(info2))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jRadioButton1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jRadioButton2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(info3)))
                                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1)
                                        .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(info3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton2)))
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
