package com.watterizer.runtime;

import com.watterizer.crypto.Encrypter;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.json.JSONException;
import org.json.JSONObject;

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

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> {
            try {
                String path = Runner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
                decodedPath = decodedPath.substring(1).replace("\\", File.separator).replace("/", File.separator);
                if (decodedPath.contains("watt_runn.jar")) {
                    FileOutputStream stream = new FileOutputStream(new File(UsefulMethods.getClassPath(Runner.class) + File.separator + "runn_log.txt"));
                    System.setErr(new PrintStream(stream));
                }
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                String openUser;
                String openId;

                try {
                    openUser = args[0];
                    openId = args[1];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Execução inválida. Inicie a partir do executável Java \"watt_exec.jar\"", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                XmlManager config = new XmlManager();
                config.loadFile(UsefulMethods.getClassPath(Runner.class) + File.separator + "config" + File.separator + "options.xml");

                try {
                    URL url = new URL("http://" + config.getContentByName("webServiceHost", 0) + ":" + config.getContentByName("webServicePort", 0) + "/test");
                    URLConnection conn = url.openConnection();
                    conn.setConnectTimeout(1000);
                    conn.setReadTimeout(1000);
                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    StringBuilder sb = new StringBuilder();
                    String output;
                    while ((output = br.readLine()) != null) {
                        sb.append(output);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Falha ao conectar-se ao banco de dados. Clique \"Ok\" para reiniciar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    try {
                        ProcessBuilder pb = new ProcessBuilder("java", "-jar", UsefulMethods.getClassPath(Runner.class) + File.separator + "watt_runn.jar", openUser, openId);
                        pb.directory(new File(UsefulMethods.getClassPath(Runner.class)));
                        pb.start();
                        System.exit(0);
                    } catch (IOException ex2) {
                        Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex2);
                    }
                }

                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Runtime.getRuntime().exec("taskkill /F /IM " + "taskmgr.exe").waitFor();
                            } catch (IOException | InterruptedException ex) {
                                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }.start();

                String message = "Foi detectada uma falha de execução na aplicação \"Watterizer\". Reinicie a aplicação ou\n"
                        + "entre em contato com um administrador para resolver o problema em até &num segundos \n"
                        + "(10 minutos), caso contrário, uma advertência será gerada.";
                frame = new JFrame();
                frame.setSize(1, 1);
                frame.setUndecorated(true);
                frame.setVisible(true);

                JButton enterAdmin = new JButton(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            @SuppressWarnings("UseOfObsoleteCollectionType")
                            Hashtable<String, String> info = loginJOptionPane(null);

                            if (!info.get("user").isEmpty() && !info.get("pass").isEmpty()) {
                                URL url = new URL("http://" + config.getContentByName("webServiceHost", 0) + ":" + config.getContentByName("webServicePort", 0) + "/login");
                                URLConnection con = url.openConnection();
                                HttpURLConnection http = (HttpURLConnection) con;
                                http.setRequestMethod("POST");
                                http.setDoOutput(true);

                                String s = "{\n"
                                        + "\"login\":\"" + Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, info.get("user")) + "\",\n"
                                        + "\"senha\":\"" + Encrypter.encrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, info.get("pass")) + "\"\n"
                                        + "}";

                                byte[] out = s.getBytes(StandardCharsets.UTF_8);
                                int length = out.length;

                                http.setFixedLengthStreamingMode(length);
                                http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                http.connect();
                                try (OutputStream os = http.getOutputStream()) {
                                    os.write(out);
                                }

                                BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
                                StringBuilder sb = new StringBuilder();
                                String output;
                                while ((output = br.readLine()) != null) {
                                    sb.append(output);
                                }

                                String json = sb.toString();
                                json = Encrypter.decrypt(Encrypter.KEY, Encrypter.INIT_VECTOR, json);
                                json = json.substring(1, json.length() - 1);
                                System.out.println(json);
                                JSONObject obj = new JSONObject(json);

                                if (obj.getString("perfil").toLowerCase().trim().equals("administrador")) {
                                    dialog.dispose();
                                    frame.dispose();
                                    adminOk = true;
                                    JOptionPane.showMessageDialog(null, "O assistente foi finalizado e a advertência cancelada. Por favor verifique as instalações\n"
                                            + "e volte a executar o programa.");
                                    System.exit(0);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Login incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (IOException | HeadlessException ex) {

                        } catch (JSONException ex) {
                            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                enterAdmin.setText("Anular (Administrador)");
                JButton[] buttons = new JButton[]{enterAdmin};
                JOptionPane pane = new JOptionPane(message.replace("&num", Integer.toString(600)), JOptionPane.INFORMATION_MESSAGE, 2, null, buttons);
                dialog = new JDialog(frame, "Aviso", true);
                dialog.setContentPane(pane);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                dialog.setLocationRelativeTo(null);

                final String finalUser = openUser;
                final String finalIdUser = openId;

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
                                URL url = new URL("http://" + config.getContentByName("webServiceHost", 0) + ":" + config.getContentByName("webServicePort", 0) + "/advertencia");
                                URLConnection con = url.openConnection();
                                HttpURLConnection http = (HttpURLConnection) con;
                                http.setRequestMethod("POST");
                                http.setDoOutput(true);
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                String dataFormatada = format.format(new Date());
                                format = new SimpleDateFormat("HH:mm");
                                String hora = format.format(new Date());
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(new Date());
                                if (cal.get(Calendar.AM_PM) == Calendar.PM) {
                                    hora += " da tarde";
                                } else {
                                    hora += " da manhã";
                                }

                                String s = "{\n"
                                        + "\"id_usuario\" : \"" + finalIdUser + "\",\n"
                                        + "\"titulo\" : \"Mensagem do sistema - Falha com um dos Terminais\",\n"
                                        + "\"mensagem\" : \"Esta é uma mensagem automática do sistema, sendo altamente recomendável que a situação relatada aqui "
                                        + "seja conferida para que os fatos relatados aqui tenham total veemência. Hoje (dia " + dataFormatada + "), as " + hora + ", "
                                        + "houve um incidente com o terminal relacionado ao usuário " + finalUser + ". Essa mensagem diz respeito a um "
                                        + "aviso enviado a tal usuário, e que o mesmo o ignorou, não relatou a nenhum administrador do sistema a já mencionada falha de hardware. "
                                        + "É necessário que a unidade seja reparada o mais rápido possível, e que as medidas necessárias sejam tomadas para que a omissão "
                                        + "não venha acontecer de novo.\"\n"
                                        + "}";

                                byte[] out = s.getBytes(StandardCharsets.UTF_8);
                                int length = out.length;

                                http.setFixedLengthStreamingMode(length);
                                http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                http.connect();
                                try (OutputStream os = http.getOutputStream()) {
                                    os.write(out);
                                }

                                BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
                                StringBuilder sb = new StringBuilder();
                                String output;
                                while ((output = br.readLine()) != null) {
                                    sb.append(output);
                                }

                                System.out.println(sb.toString());
                                if (sb.toString().equals("CREATED")) {
                                    JOptionPane.showMessageDialog(null, "Não foi detectada a entrada de um administrador nem de uma tentativa de reinício\n"
                                            + " do aplicativo, logo uma advertência foi gerada no nome do usuário \"" + finalUser + "\".");
                                    System.exit(0);
                                }
                            } catch (IOException | HeadlessException ex) {
                                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }.start();

                dialog.setVisible(true);
            } catch (HeadlessException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
