package com.watterizer.runtime;

import com.watterizer.crypto.Encrypter;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/* **********   runtime.java   **********
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

 /* **********   Description of the Class   **********
 * This is the class where all the magic happens, and while she is as boring and 
 * redundant as an Ctrl+C / Ctrl+V, the methods inside surely aren't. I could keep 
 * all day long typing just how much I love "Hello World" begginers, but we are better
 * than this. Now go on and build your calculator.
 */
public class Runner {

    private static JDialog dialog;
    private static JFrame frame;
    private static boolean adminOk = false;

    public static void main(String[] args) throws IOException, SQLException {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                String os = System.getProperty("os.name").toLowerCase();
                String openUser = null;
                String openId = null;
                String openIdSetor = null;
                String openTimeOfOpening = null;
                String openTimeOfClose = Long.toString(System.currentTimeMillis());
                String dbLink = null;
                String dbUser = null;
                String dbPass = null;

                try {
//            openUser = args[0];
//            openId = args[1];
//            openIdSetor = args[2];
//            openTimeOfOpening = args[3];
//            dbLink = args[4];
//            dbUser = args[5];
//            dbPass = args[6];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Execução inválida. Inicie a partir do executável Java \"watt_exec.jar\"", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection conn = null;
                XmlManager xml = new XmlManager();
                File getOptions = new File(UsefulMethods.getClassPath(Runner.class) + "params.xml");

                if (!getOptions.exists()) {
                    String content = UsefulMethods.getOptions();

                    if (content != null) {
                        getOptions.createNewFile();
                        try (PrintWriter writer = new PrintWriter(getOptions)) {
                            writer.print(content);
                        }
                    }
                }

                xml.loadFile(getOptions);
                XmlManager config = new XmlManager();
                config.loadFile(UsefulMethods.getClassPath(Runner.class) + "config" + File.separator + "options.xml");

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(config.getContentByName("databaseUrl", 0), config.getContentByName("databaseUser", 0),
                            Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, config.getContentByName("databasePass", 0)));
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao localizar a biblioteca \"mysql-connector-java-5.1.18.jar\" na pasta \"libs\".", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar-se ao banco de dados \"" + config.getContentByName("databaseName", 0)
                            + "\". Clique \"Ok\" para reiniciar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    try {
                        ProcessBuilder pb = new ProcessBuilder("java", "-jar", UsefulMethods.getClassPath(Runner.class) + "watt_runn.jar", openUser, openId,
                                openTimeOfOpening, openTimeOfClose, dbLink, dbUser, dbPass);
                        pb.directory(new File(UsefulMethods.getClassPath(Runner.class)));
                        pb.start();
                        System.exit(0);
                    } catch (IOException ex2) {
                        Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex2);
                    }
                }
                final Connection finalConn = conn;
                ResultSet rs = conn.createStatement().executeQuery("SELECT id FROM perfil WHERE perfil = 'Administrador'");
                rs.next();
                int perfil = rs.getInt("id");

                if (os.contains("win")) {

                } else if (os.contains("uni") || os.contains("nux") || os.contains("aix")) {

                } else if (os.contains("mac")) {

                } else {
                    JOptionPane.showMessageDialog(null, "Seu sistema operacional não suporta o Java JRE 8 ou acima.");
                    return;
                }

                ArrayList<String> lines;
                String exec = xml.getContentByName("exec", 0);

                while (true) {
                    lines = getAllRunningProcesses();

                    boolean wasFound = false;
                    for (String s : lines) {
                        if (s.contains(exec)) {
                            wasFound = true;
                            break;
                        }
                    }

                    if (!wasFound) {
                        String message = "Foi detectada uma falha de execução na aplicação \"Watterizer\". Reinicie a aplicação ou\n"
                                + "entre em contato com um administrador para resolver o problema em até &num segundos \n"
                                + "(10 minutos), caso contrário, uma advertência será gerada.";
                        frame = new JFrame();
                        frame.setSize(1, 1);
                        frame.setUndecorated(true);
                        frame.setVisible(true);

                        JButton restart = new JButton(new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    //System.out.println(UsefulMethods.getClassPath(Runner.class) + "watt_exec.jar");
                                    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "C:\\Users\\Mc\\Desktop\\dist\\Alpha_13.jar", "teste");
                                    pb.directory(new File(UsefulMethods.getClassPath(Runner.class)));
                                    pb.start();
                                    ArrayList<String> check = getAllRunningProcesses();
                                    check.stream().filter((s) -> (s.contains(exec))).forEach((_item) -> {
                                        System.exit(0);
                                    });
                                    JOptionPane.showMessageDialog(null, "Erro ao tentar reiniciar o programa.", "Erro", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException ex) {
                                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        restart.setText("Reiniciar aplicação");
                        JButton enterAdmin = new JButton(new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    @SuppressWarnings("UseOfObsoleteCollectionType")
                                    Hashtable<String, String> info = loginJOptionPane(null);
                                    PreparedStatement statement = finalConn.prepareStatement("SELECT * FROM usuario WHERE username = ? AND senha = ? AND id_perfil = ?");
                                    statement.setString(1, info.get("user"));
                                    statement.setString(2, Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, info.get("pass")));
                                    statement.setInt(3, perfil);

                                    if (statement.executeQuery().next()) {
                                        dialog.dispose();
                                        frame.dispose();
                                        adminOk = true;
                                        new ArduinoTester().setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Login incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        enterAdmin.setText("Entrar como administrador");
                        JButton[] buttons = new JButton[]{restart, enterAdmin};
                        JOptionPane pane = new JOptionPane(message.replace("&num", Integer.toString(600)), JOptionPane.INFORMATION_MESSAGE, 2, null, buttons);
                        dialog = new JDialog(frame, "Aviso", true);
                        dialog.setContentPane(pane);
                        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);

                        new Thread() {
                            @Override
                            public void run() {
                                for (int i = 600; i >= 0; i--) {
                                    try {
                                        pane.setMessage(message.replace("&num", Integer.toString(i)));
                                        pane.repaint();
                                        dialog.repaint();
                                        sleep(1000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                if (!adminOk) {
                                    try {
                                        dialog.dispose();
                                        finalConn.createStatement().execute("INSERT INTO ocorrencia (`id`, `id_usuario`, `titulo`, `id_setor`, `mensagem`) VALUES (NULL, '" + /*openId*/ "6" + "', '"
                                                + "Mensagem do sistema - Falha com um dos Arduinos', '" + /*openIdSetor*/ "1" + "', 'Esta é uma mensagem automática do sistema, sendo altamente "
                                                + "recomendável que a situação relatada aqui seja conferida para que os fatos relatados aqui tenham total veemência. \n\n"
                                                + "Hoje (dia &date), as &date, houve um incidente com a unidade Arduino relacionada ao usuário &string. Essa mensagem diz respeito a um aviso enviado a tal usuário, "
                                                + "e que o mesmo o ignorou, não relatou a nenhum administrador do sistema a já mencionada falha de hardware.\n\n"
                                                + "É necessário que a unidade seja reparada o mais rápido possível, e que as medidas necessárias sejam tomadas para que a omissão não venha acontecer de novo.')");
                                        JOptionPane.showMessageDialog(null, "Não foi detectada a entrada de um administrador nem de uma tentativa de reinício\n"
                                                + " do aplicativo, logo uma advertência foi gerada no nome do usuário \"" + "fulano" + "\".");
                                        System.exit(0);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }.start();

                        dialog.setVisible(true);
                        break;
                    }

                    lines.clear();
                }
            } catch (HeadlessException | SQLException | IOException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private static ArrayList<String> getAllRunningProcesses() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        String line;
        String home = System.getProperty("java.home");
        home = home.substring(0, home.length() - 3) + "bin\\";
        Process p = Runtime.getRuntime().exec(home + "jps -lv");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    private static Hashtable<String, String> loginJOptionPane(JFrame frame) {
        Hashtable<String, String> logininformation = new Hashtable<>();

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username:", SwingConstants.RIGHT));
        label.add(new JLabel("Password:", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showConfirmDialog(frame, panel, "Login", JOptionPane.OK_CANCEL_OPTION);

        logininformation.put("user", username.getText());
        logininformation.put("pass", new String(password.getPassword()));
        return logininformation;
    }
}
