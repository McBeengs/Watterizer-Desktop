/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.modals;

import com.watterizer.arduino.ArduinoBridge;
import com.watterizer.models.PCModel;
import com.watterizer.net.SocketNodeJS;
import com.watterizer.panels.Milagre;
import com.watterizer.panels.NewEquipmentJFrame;
import com.watterizer.panels.SelectConfig;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import gnu.io.CommPortIdentifier;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 15153769
 */
public class FirstUseSetupJFrame extends javax.swing.JFrame {

    private static XmlManager xml;
    private static PCModel model;
    private final GridBagConstraints c = new GridBagConstraints();
    private final JButton previous = new JButton();
    private final JButton next = new JButton();
    private int currentPanel = 0;
    private String wsHostTemp;
    private String wsPortTemp;
    private JFrame terminalFrame;
    private int terminalSelected = 0;
    boolean isMacSelected = false;
    boolean isSetorSelected = false;
    boolean isLastScreen = false;
    public static ArrayList<Equipment> insertEquipments = new ArrayList<>();
    private static JTable equipmentsTable;
    private static DefaultTableModel tableModel;

    public static class Equipment {

        public String mac;
        public String nome;
        public String descricao;
        public int posicionado;
        public int idArduino;
        public int numeroPorta;

        @Override
        public String toString() {
            return "MAC: " + mac + " | Nome: " + nome + " | Descrição: " + descricao + " | Posicionado: " + posicionado + " | idArduino: " + idArduino + " | numeroPorta" + numeroPorta + " | ";
        }
    }

    public static void addEquipment(Equipment equip) {
        for (Equipment e : insertEquipments) {
            if (e.nome.equals(equip.nome)) {
                return;
            }
        }
        insertEquipments.add(equip);
        tableModel.addRow(new Object[]{equip.nome, equip.nome.equals(model.getNome()) ? "Medidor" : "Equipamento",
            equip.descricao == null ? "Sem descrição" : equip.descricao.equals("null") ? "Sem descrição" : equip.descricao});
    }

    public FirstUseSetupJFrame(XmlManager xml) {
        initComponents();
        FirstUseSetupJFrame.xml = xml;
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(getWelcomePanel());
        setTitle("Watterizer - Auxiliar de configuração inicial");

        c.gridx = 0;
        c.gridy = 0;
        previous.setEnabled(false);
        previous.addActionListener((ActionEvent ae) -> {
            previousPanel();
        });

        next.addActionListener((ActionEvent ae) -> {
            nextPanel();
            if (currentPanel > 1) {
                try {
                    UsefulMethods.getWebServiceResponse("http://" + wsHostTemp + ":" + wsPortTemp + "/test", "GET", null);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "A conexão com o banco de dados caiu inesperadamente. Infelizmente o auxiliar terá que ser encerrado", wsHostTemp, WIDTH);
                    System.exit(0);
                }
            }

            if (isLastScreen) {
                try {
                    xml.saveXml();
                    System.out.println(xml.toString());
                    System.out.println("salvo xml");
                    if (model != null) {
                        String arduinoId = "";
                        if (model.getType() == 0) {
                            String s = "{"
                                    + "   \"id_setor\":\"" + model.getSetorId() + "\", "
                                    + "   \"localizacao\":\"" + "null\""
                                    + "}";
                            System.out.println(s);
                            arduinoId = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0) + "/arduino", "POST", s);
                            System.out.println("arduino cadastrado");
                        }

                        String s = "{"
                                + "   \"mac\":\"" + model.getMac() + "\", "
                                + "   \"nome\":\"" + model.getNome() + "\", ";
                        if (model.getType() == 0) {
                            s += "   \"id_arduino\":\"" + arduinoId + "\", ";
                        }

                        s += "   \"descricao\":\"" + "" + "\", "
                                + "   \"posicionado\":\"0\", "
                                + "   \"command\":\"create\""
                                + "}";
                        UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0) + "/equipamentocheck", "POST", s);

                        for (Equipment equip : insertEquipments) {
                            s = "{"
                                    + "   \"mac\":\"" + equip.mac + "\", "
                                    + "   \"nome\":\"" + equip.nome + "\", "
                                    + "   \"id_arduino\":\"" + arduinoId + "\", "
                                    + "   \"descricao\":\"" + equip.descricao + "\", "
                                    + "   \"posicionado\":\"" + equip.posicionado + "\", "
                                    + "   \"numero_porta\":" + equip.numeroPorta + ", "
                                    + "   \"command\":\"create\""
                                    + "}";
                            UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0) + "/equipamentocheck", "POST", s);
                        }
                        System.out.println("salvo no banco");
                    } else {
                        System.err.println("pulado salvar banco");
                    }
                    System.err.println("abrir watt_exec.jar");
                    System.exit(0);
                } catch (IOException ex) {
                    Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        isLastScreen = false;

        if (currentPanel == 1) {
            EventQueue.invokeLater(() -> {
                contentPane.add(getWebServiceConnPanel(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
            previous.setEnabled(false);
        } else if (currentPanel == 2) {
            EventQueue.invokeLater(() -> {
                JPanel pane = getTerminalPanel();
                if (pane != null) {
                    next.setEnabled(false);
                    contentPane.add(pane, c);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else {
                    previous.setEnabled(false);
                    isLastScreen = true;
                    contentPane.add(getEndPanel(), c);
                    contentPane.revalidate();
                    contentPane.repaint();
                }
            });
        } else if (!isLastScreen && currentPanel == 3) {
            EventQueue.invokeLater(() -> {
                contentPane.add(getCheckArduinoPane(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
        } else if (currentPanel == 4) {
            EventQueue.invokeLater(() -> {
                contentPane.add(getTerminalPanel(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
        }
    }

    private String pcName;

    private void nextPanel() {
        currentPanel++;
        next.setEnabled(false);
        next.setText("Próximo");
        contentPane.removeAll();
        ArrayList<String> macs = new ArrayList<>();

        if (currentPanel == 1) {
            EventQueue.invokeLater(() -> {
                contentPane.add(getWebServiceConnPanel(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
        } else if (currentPanel == 2) {
            EventQueue.invokeLater(() -> {
                String json = "";
                try {
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

                            if (sb.toString().length() == 17) {
                                macs.add(sb.toString());
                                if (!isSaved) {
                                    String s = "{"
                                            + "\"mac\" : \"" + sb.toString() + "\","
                                            + "\"command\" : \"check\""
                                            + "}";
                                    json = UsefulMethods.getWebServiceResponse("http://" + wsHostTemp + ":" + wsPortTemp + "/equipamentocheck", "POST", s);
                                    json = json.substring(1, json.length() - 1);
                                    if (!json.isEmpty()) {
                                        isSaved = true;
                                    }
                                }
                            }
                        }
                    }

                    pcName = ip.getHostName();
                    if (isSaved) {
                        System.out.println(json);
                        if (JOptionPane.showConfirmDialog(null, getConfiguredTerminalPane(json), "Aviso",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                            next.setEnabled(true);
                            isLastScreen = true;
                            next.setText("Sair");
                            contentPane.add(getEndPanel(), c);
                            contentPane.revalidate();
                            contentPane.repaint();
                            return;
                        }
                    }

                    terminalFrame = new JFrame("Watterizer - Escolher tipo do terminal");
                    JPanel terminal = getTerminalSelectionPane();
                    terminalFrame.setContentPane(terminal);
                    terminalFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    terminalFrame.setSize(new Dimension(terminal.getWidth(), terminal.getHeight()));
                    terminalFrame.pack();
                    terminalFrame.setLocationRelativeTo(null);
                    terminalFrame.setVisible(true);
//                    String json = UsefulMethods.getWebServiceResponse("http://" + wsHostTemp + ":" + wsPortTemp + "/arduino", "GET", null);
//                    JSONArray array = new JSONArray(json);
//                    if (array.length() <= 0) {
//                        contentPane.add(getCheckArduinoPane(), c);
//                        contentPane.revalidate();
//                        contentPane.repaint();
//                        return;
//                    } else {
//
//                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "A conexão com o banco de dados caiu inesperadamente. Infelizmente o auxiliar terá que ser encerrado", wsHostTemp, WIDTH);
                    System.exit(0);
                } catch (JSONException ex) {
                    Logger.getLogger(FirstUseSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

//                JPanel pane = getTerminalPanel();
//                if (pane == null) {
//                    contentPane.removeAll();
//                    EventQueue.invokeLater(() -> {
//                        previous.setEnabled(false);
//                        next.setEnabled(true);
//                        isLastScreen = true;
//                        next.setText("Sair");
//                        contentPane.add(getEndPanel(), c);
//                        contentPane.revalidate();
//                        contentPane.repaint();
//                    });
//                } else {
//                    contentPane.add(pane, c);
//                    contentPane.revalidate();
//                    contentPane.repaint();
//                }
            });
            previous.setEnabled(true);
        } else if (!isLastScreen && currentPanel == 3) {
            if (terminalSelected == 0) {
                next.setEnabled(true);
                isLastScreen = true;
                next.setText("Sair");
                contentPane.add(new Milagre(), c);
                contentPane.revalidate();
                contentPane.repaint();
            }
        } else if (!isLastScreen && currentPanel == 3 && model.getType() == 1) {
            EventQueue.invokeLater(() -> {
                next.setEnabled(true);
                isLastScreen = true;
                next.setText("Sair");
                contentPane.add(getEndPanel(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
        } else if (!isLastScreen && currentPanel == 3 && model.getType() == 0) {
            EventQueue.invokeLater(() -> {
                next.setEnabled(true);
                contentPane.add(getCheckArduinoPane(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
        } else if (!isLastScreen && currentPanel == 4 && model.getType() == 0) {
            EventQueue.invokeLater(() -> {
                next.setEnabled(true);
                contentPane.add(getEquipmentsPane(), c);
                contentPane.revalidate();
                contentPane.repaint();
            });
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

    private JPanel getWelcomePanel() {
        JPanel pane = new JPanel();
        javax.swing.JLabel text = new javax.swing.JLabel();
        javax.swing.JLabel top = new javax.swing.JLabel();

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("<html><body style=\"text-align: justify;\">    Seja bem vindo ao auxiliar de configuração para a aplicação Watterizer. Clique em \"Próximo\" para continuar</body></html>");
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top.setFont(header);
        top.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top.setText("Bem Vindo");

        next.setText("Próximo");

        previous.setText("Anterior");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
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
                        .addGap(177, 177, 177)
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
        errorText.setText("<html><body style=\"text-align: justify;\">   Antes de mais nada, precisamos estabelecer uma conexão entre o servidor para que as etapas seguintes "
                + "sejam configuradas. Caso não saiba os campos abaixo, entre em contato com o responsável pela arquitetura do sistema.</body></html>");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        setTitle("Watterizer - Conexão com o WebService");

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        errorTitle.setFont(header);
        errorTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        errorTitle.setText("Conectar-se ao Servidor");

        errorText1.setForeground(new java.awt.Color(255, 255, 255));
        errorText1.setText("Url:");

        jTextField1.setText("10.0.3.28");
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == ' ') {
                    getToolkit().beep();
                    e.consume();
                }
            }

        });
//        jTextField1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                int dots = 0;
//                for (char c : jTextField1.getText().toCharArray()) {
//                    if (c == '.') {
//                        dots++;
//                        if (dots == 4) {
//                            getToolkit().beep();
//                            e.consume();
//                        }
//                    }
//                }
//                char c = e.getKeyChar();
//                if (!((c >= '0') && (c <= '9')
//                        || (c == KeyEvent.VK_BACK_SPACE)
//                        || (c == KeyEvent.VK_DELETE)
//                        || (c == '.'))) {
//                    getToolkit().beep();
//                    e.consume();
//                }
//
//                if (jTextField1.getText().length() >= 14) {
//                    jTextField1.setText(jTextField1.getText().substring(0, 14));
//                }
//            }
//
//        });

        errorText2.setForeground(new java.awt.Color(255, 255, 255));
        errorText2.setText("Porta Socket:");

        jTextField2.setText("3000");
        jTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }

                if (jTextField2.getText().length() >= 5) {
                    jTextField2.setText(jTextField2.getText().substring(0, 4));
                }
            }

        });

        errorText3.setForeground(new java.awt.Color(255, 255, 255));
        errorText3.setText("Porta WebService:");

        jTextField3.setText("1515");
        jTextField3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }

                if (jTextField3.getText().length() >= 5) {
                    jTextField3.setText(jTextField3.getText().substring(0, 4));
                }
            }

        });

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
                        String host = jTextField1.getText().trim();
                        if (host.contains("http://")) {
                            host = host.substring(7);
                        }

                        wsHostTemp = host;
                        wsPortTemp = jTextField3.getText();

                        if (!socket.echo("test").isEmpty() && UsefulMethods.getWebServiceResponse("http://" + host + ":" + jTextField3.getText()
                                + "/test", "GET", null).equals("OK")) {
                            display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/ok.png")));
                            display.setForeground(new java.awt.Color(50, 205, 50));
                            display.setText("Conexão estabelecida.");
                            jTextField1.setEnabled(true);
                            jTextField2.setEnabled(true);
                            jTextField3.setEnabled(true);
                            jButton1.setEnabled(true);

                            xml.setContentByName("webServiceHost", 0, host);
                            xml.setContentByName("socketPort", 0, jTextField2.getText());
                            xml.setContentByName("webServicePort", 0, jTextField3.getText());

                            next.setEnabled(true);
                        }
                    } catch (NumberFormatException | IOException ex) {
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
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    @SuppressWarnings("element-type-mismatch")
    private JPanel getTerminalPanel(String pcName, ArrayList<String> macs) {

        ArrayList<String> setoresDisplay = new ArrayList<>();
        Map<Integer, String> setores = new HashMap<>();
        setTitle("Watterizer - Configurar Terminal");

        try {
            String json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + Integer
                    .parseInt(xml.getContentByName("webServicePort", 0)) + "/setorcheck", "GET", null);

            if (json.equals("false")) {
                JOptionPane.showMessageDialog(this, "Não existe nenhum setor cadastrado no servidor. Por favor entre no módulo web e cadastre ao menos um para continuar.",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                Desktop.getDesktop().browse(new URI("http://" + xml.getContentByName("webServiceHost", 0) + ":1515"));
                eraseConfig();
                System.exit(0);
            } else {
                json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + Integer
                        .parseInt(xml.getContentByName("webServicePort", 0)) + "/setor", "GET", null);

                json = json.substring(1, json.length() - 1);
                setoresDisplay.add("- Selecione um Setor -");
                for (String s : json.split("},")) {
                    if (!s.endsWith("}")) {
                        s += "}";
                    }
                    JSONObject obj = new JSONObject(s);
                    setores.put(obj.getInt("id"), obj.getString("setor"));
                    setoresDisplay.add(obj.getString("setor"));
                }
            }
        } catch (NumberFormatException | IOException | HeadlessException | JSONException | URISyntaxException ex) {
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

        model = new PCModel();

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top.setFont(header);
        top.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top.setText("Identicficar terminal");

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("<html><body style=\"text-align: justify;\">   Para que as funções remotas possam ser utilizadas no módulo web, este terminal deve ser "
                + "configurado com os requisitos abaixo.</html></body>");

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
        jComboBox1.addActionListener((ActionEvent ae) -> {
            if (jComboBox1.getSelectedItem().toString().equals("- Selecione um MAC -")) {
                isMacSelected = false;
                next.setEnabled(false);
            } else {
                model.setMac(jComboBox1.getSelectedItem().toString());
                isMacSelected = true;
                if (isSetorSelected && isMacSelected && !jTextField1.getText().isEmpty()) {
                    next.setEnabled(true);
                    xml.setContentByName("terminalType", 0, jRadioButton1.isSelected() ? "0" : "1");
                } else {
                    next.setEnabled(false);
                }
            }
        });

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
        model.setNome(jTextField1.getText());
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jTextField1.getText().length() >= 50) {
                    jTextField1.setText(jTextField1.getText().substring(0, 49));
                }
            }

        });
        jTextField1.addCaretListener((CaretEvent ce) -> {
            if (jTextField1.getText().isEmpty()) {
                next.setEnabled(false);
            } else {
                model.setNome(jTextField1.getText());
                if (isMacSelected && isSetorSelected) {
                    next.setEnabled(true);
                    xml.setContentByName("terminalType", 0, jRadioButton1.isSelected() ? "0" : "1");
                }
            }
        });

        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setText("Setor:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(setoresDisplay.toArray()));
        jComboBox2.addActionListener((ActionEvent ae) -> {
            if (jComboBox2.getSelectedItem().toString().equals("- Selecione um Setor -")) {
                isSetorSelected = false;
                next.setEnabled(false);
                return;
            }
            for (Object i : setores.keySet().toArray()) {
                if (setores.get(i).equals(jComboBox2.getSelectedItem().toString())) {
                    model.setSetor(jComboBox2.getSelectedItem().toString());
                    model.setSetorId(Integer.parseInt(i.toString()));
                    isSetorSelected = true;
                    break;
                }
            }

            if (isSetorSelected && isMacSelected && !jTextField1.getText().isEmpty()) {
                next.setEnabled(true);
                xml.setContentByName("terminalType", 0, jRadioButton1.isSelected() ? "0" : "1");
            } else {
                next.setEnabled(false);
            }
            //jComboBox2.getSelectedItem().toString()
        });

        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setText("Terminal:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Seeder");
        jRadioButton1.addActionListener((ActionEvent ae) -> {
            model.setType(0);
            xml.setContentByName("terminalType", 0, "0");
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Leecher");
        jRadioButton2.addActionListener((ActionEvent ae) -> {
            model.setType(1);
            xml.setContentByName("terminalType", 0, "1");
        });

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
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    private boolean response = false;

    private JPanel getCheckArduinoPane() {
        JPanel pane = new JPanel();
        javax.swing.JLabel text = new javax.swing.JLabel();
        javax.swing.JLabel top1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JComboBox jComboBox1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel updateButton = new javax.swing.JLabel();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JTextArea descricaoText = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        setTitle("Watterizer - Conexão com o Arduino");

        next.setEnabled(false);

        setBackground(new java.awt.Color(0, 0, 0));

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("<html><body style=\"text-align: justify;\">    Ao configurar o terminal como Seeder, é necessário que o computador tenha uma unidade Arduino "
                + "conectada a ele, e este Arduino também precisa de ao menos um medidor para consumir os gastos de uma fonte elétrica. Primeiramente, cheque a "
                + "comunicação com este terminal para com o Arduino com os controles abaixo:</body></html>");
        text.setToolTipText("");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Porta:");

        descricaoText.setColumns(20);
        descricaoText.setRows(5);
        jScrollPane1.setViewportView(descricaoText);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descrição:");

        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/lilCog.png"))); // NOI18N
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(updateList()));
                jComboBox1.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                UsefulMethods.makeBalloon(updateButton, "<html>  Atualizar lista</html>", 3000, 1000, Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                updateButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top1.setFont(header);
        top1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top1.setText("Testar conexão com Arduino");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(updateList()));

        jButton1.setText("Testar");
        jButton1.addActionListener((ActionEvent ae) -> {
            if (!descricaoText.getText().isEmpty()) {
                response = false;
                jLabel3.setVisible(true);
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/spinner.gif")));
                jLabel3.setForeground(Color.white);
                jLabel3.setText("Testando...");
                ArduinoBridge b = new ArduinoBridge(jComboBox1.getSelectedItem().toString().trim());
                if (b.waitForConnection(100)) {
                    b.addConsoleHandler((ArduinoBridge.ConsoleEvent evt) -> {
                        response = true;
                    });

                    final long end = System.currentTimeMillis() + 5000;
                    new Thread() {
                        @Override
                        public void run() {
                            while (System.currentTimeMillis() < end) {
                                //não apague essa linha
                                System.out.print(response);
                                if (response) {
                                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/ok.png")));
                                    jLabel3.setForeground(new java.awt.Color(50, 205, 50));
                                    jLabel3.setText("Conexão estabelecida.");
                                    System.out.println("yes");
                                    next.setEnabled(true);
                                    b.close();

                                    insertArduino = "{"
                                            + "\"id_setor\":\"" + model.getSetorId() + "\","
                                            + "\"localizacao\":\"" + descricaoText.getText() + "\","
                                            + "\"id_computador_responsavel\":\"0\""
                                            + "}";
                                    break;
                                }
                            }
                            if (!response) {
                                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/error2.png")));
                                jLabel3.setForeground(new java.awt.Color(238, 44, 44));
                                jLabel3.setText("Falha com a conexão.");
                                b.close();
                            }
                        }
                    }.start();

                } else {
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/error2.png")));
                    jLabel3.setForeground(new java.awt.Color(238, 44, 44));
                    jLabel3.setText("Falha com a conexão.");
                    b.close();
                }
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(previous)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(next))
                                                .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateButton)
                                        .addGap(39, 39, 39)
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(top1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton1)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    private Object[] updateList() {
        ArrayList<String> ports = new ArrayList<>();
        Enumeration pList = CommPortIdentifier.getPortIdentifiers();

        while (pList.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
            switch (cpi.getPortType()) {
                case CommPortIdentifier.PORT_SERIAL:
                    ports.add(cpi.getName());
                    break;
            }
        }

        return ports.toArray();
    }

    private JPanel getEquipmentsPane() {
        JPanel pane = new JPanel();
        isLastScreen = true;

        javax.swing.JLabel text = new javax.swing.JLabel();
        javax.swing.JLabel top1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        equipmentsTable = new JTable();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        setTitle("Watterizer - Adicionar Equipamentos");

        setBackground(new java.awt.Color(0, 0, 0));

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("<html><body style=\"text-align: justify;\">    O medidor conectado na porta \"A0\" será automáticamente reservado para a medição do ambiente"
                + " como um todo. Caso tenha mais de um medidor conectado a outras portas, configure-os abaixo.</html></body>");
        text.setToolTipText("");

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top1.setFont(header);
        top1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top1.setText("Configurar terminais");

        equipmentsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Equipamento",
                    "Tipo",
                    "Descrição"
                }
        ));
        jScrollPane1.setViewportView(equipmentsTable);

        tableModel = (DefaultTableModel) equipmentsTable.getModel();

        jButton1.setText("Adicionar Equipamento");
        jButton1.addActionListener((ActionEvent ae) -> {
            new NewEquipmentJFrame().setVisible(true);
        });

        equipmentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getButton() == 3) {
                    int r = equipmentsTable.rowAtPoint(me.getPoint());
                    if (r >= 0 && r < equipmentsTable.getRowCount()) {
                        equipmentsTable.setRowSelectionInterval(r, r);
                    } else {
                        equipmentsTable.clearSelection();
                    }

                    int rowindex = equipmentsTable.getSelectedRow();
                    if (rowindex > 0) {
                        JPopupMenu popup = new JPopupMenu();
                        JMenuItem item = new JMenuItem("seila");
                        item.addMouseListener(new MouseAdapter() {

                            @Override
                            public void mouseClicked(MouseEvent me) {
                                tableModel.removeRow(r);
                            }
                        });

                        popup.add(item);
                        popup.show(equipmentsTable, me.getX(), me.getY());
                    }
                }
            }
        });

        FirstUseSetupJFrame.Equipment equip = new FirstUseSetupJFrame.Equipment();
        equip.mac = model.getMac();
        equip.nome = model.getNome();
        equip.posicionado = 0;
        equip.numeroPorta = NewEquipmentJFrame.portNum;
        FirstUseSetupJFrame.addEquipment(equip);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(previous)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(next))
                                        .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))
                                .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(top1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(next)
                                .addComponent(previous))
                        .addContainerGap())
        );

        return pane;
    }

    private JPanel getTerminalSelectionPane() {
        JPanel pane = new JPanel();
        javax.swing.ButtonGroup buttonGroup1;
        javax.swing.JLabel jLabel1;
        javax.swing.JToggleButton leecherButton;
        javax.swing.JLabel leecherQuestion;
        javax.swing.JToggleButton seederButton;
        javax.swing.JLabel seederQuestion;
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        leecherButton = new javax.swing.JToggleButton();
        seederButton = new javax.swing.JToggleButton();
        seederQuestion = new javax.swing.JLabel();
        leecherQuestion = new javax.swing.JLabel();
        JButton okButton = new javax.swing.JButton();

        jLabel1.setText("Como deseja cadastrar este terminal?");
        jLabel1.setForeground(Color.WHITE);

        seederQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                UsefulMethods.makeBalloon(seederQuestion, "<html><h3><em>Seeder:</em></h3><p>  O terminal que ficará responsável por "
                        + " medir e enviar<br>dados de consumo ao servidor. Não será utilizado pelo<br>usuário comum e necessariamente terá uma unidade<br>Arduino.</p></html>", 5000, Color.yellow);
            }
        });

        leecherQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                UsefulMethods.makeBalloon(leecherQuestion, "<html><h3><em>Leecher:</em></h3><p>  O terminal que a maioria dos usuários irá utilizar."
                        + " Não<br>envia medições ao servidor e apenas atua como um<br>intermediário para a função de desligamento remoto.</p></html>", 5000, Color.yellow);
            }
        });

        if (terminalSelected == 0) {
            seederButton.setSelected(true);
        } else {
            leecherButton.setSelected(true);
        }

        buttonGroup1.add(leecherButton);
        leecherButton.setText("leecher");
        leecherButton.addActionListener((ActionEvent ae) -> {
            terminalSelected = 1;
            System.out.println(terminalSelected);
        });

        buttonGroup1.add(seederButton);
        seederButton.setText("seeder");
        seederButton.addActionListener((ActionEvent ae) -> {
            terminalSelected = 0;
            System.out.println(terminalSelected);
        });

        seederQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/question.png"))); // NOI18N

        leecherQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/question.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);

        okButton.setText("Ok");
        okButton.addActionListener((ActionEvent ae) -> {
            terminalFrame.dispose();
            nextPanel();
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 67, Short.MAX_VALUE)
                                        .addComponent(seederQuestion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(seederButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(leecherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap())
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(leecherQuestion)
                                                        .addGap(71, 71, 71))))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(leecherQuestion)
                                        .addGap(40, 40, 40))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(leecherButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(seederQuestion)
                                                .addGap(40, 40, 40))
                                        .addComponent(seederButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addContainerGap())
        );

        return pane;
    }

    private JPanel getConfiguredTerminalPane(String json) throws JSONException {
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel top = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JLabel mac = new javax.swing.JLabel();
        javax.swing.JLabel setor = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel arduino = new javax.swing.JLabel();
        JPanel pane = new JPanel();
        setTitle("Watterizer - Terminal já cadastrado");

        JSONObject obj = new JSONObject(json);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/terminal.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Este terminal já foi cadastrado no banco de dados. Você deseja recadastrá-lo?");

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 20);
        top.setFont(header);
        top.setForeground(new java.awt.Color(255, 255, 255));
        top.setText(obj.getString("nome"));

        mac.setForeground(new java.awt.Color(255, 255, 255));
        mac.setText(obj.getString("mac"));

        setor.setForeground(new java.awt.Color(255, 255, 255));
        try {
            setor.setText(obj.getString("setor"));
        } catch (JSONException ex) {
            setor.setText("...");
        }

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Setor:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("MAC:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Arduino:");

        arduino.setForeground(new java.awt.Color(255, 255, 255));
        arduino.setText(obj.getString("mac").contains("-") ? "Possui unidade configurada" : "Não configurado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel2)
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel6)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(mac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel8)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(arduino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel7)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(setor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(mac)
                                                .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel7)
                                                .addComponent(setor))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel8)
                                                .addComponent(arduino))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        return pane;
    }

    private JPanel getEndPanel() {
        JPanel pane = new JPanel();
        javax.swing.JLabel text = new javax.swing.JLabel();
        javax.swing.JLabel top = new javax.swing.JLabel();
        setTitle("Watterizer - Finalizar auxiliar");

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 40);
        top.setFont(header);
        top.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top.setText("Configuração concluida");

        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setText("<html><body style=\"text-align: justify;\">    A configuração foi concluida. Pressione \"Sair\" para finalizar o auxiliar e iniciar o programa.</html></body>");
        text.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(layout);
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
                        .addGap(177, 177, 177)
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
