/* **********   GetOptions.java   **********
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
package com.watterizer.util;

import com.watterizer.models.UserModel;
import com.watterizer.arduino.ArduinoBridge;
import com.watterizer.models.PCModel;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.utils.FadingUtils;
import net.java.balloontip.utils.TimingUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.json.JSONException;
import org.json.JSONObject;

public class UsefulMethods {

    public static final int OPTIONS = 0;
    public static final int LANGUAGE = 1;
    public static double CURRENT_KWH_CHARGE;
    private static boolean isShutdownTrue = false;
    private static XmlManager options;
    private static XmlManager language;
    private static ArduinoBridge bridge;
    private static FTPClient ftp;
    private static Font headerFont;
    private static UserModel model;
    private static PCModel pc;

    public static String getOptions() {
        //get OS
        String os = System.getProperty("os.name").toLowerCase();
        String path;

        //get system path separator
        String separator = System.getProperty("file.separator");

        if (os.contains("win")) {
            path = System.getProperty("user.home") + separator + "Documents" + separator + "Repository" + separator;
        } else if (os.contains("uni") || os.contains("nux") || os.contains("aix")) {
            path = System.getProperty("user.home") + separator + "Repository" + separator;
        } else if (os.contains("mac")) {
            path = System.getProperty("user.home") + "";
        } else {
            JOptionPane.showMessageDialog(null, "Seu sistema operacional não suporta o Java JRE 7 ou acima.");
            return null;
        }

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<root>\n"
                + "    <gui>\n"
                + "        <language attr=\"0\">Português</language>\n"
                + "        <style attr=\"0\">Metal</style>\n"
                + "        <webServiceHost>localhost</webServiceHost>\n"
                + "        <webServicePort>12345</webServicePort>\n"
                + "        <socketPort>12345</socketPort>\n"
                + "        <terminalType>0</terminalType>\n"
                + "        <autoLogin>false</autoLogin>\n"
                + "        <arduinoPort>COM1</arduinoPort>\n"
                + "        <kwh>0.0</kwh>\n"
                + "        <user>null</user>\n"
                + "        <pass>null</pass>\n"
                + "        <lastEnd>true</lastEnd>\n"
                + "    </gui>\n"
                + "    <debug>\n"
                + "        <boolean id=\"isDebugActive\">false</boolean>\n"
                + "        <boolean id=\"saveLog\">true</boolean>\n"
                + "        <boolean id=\"offlineMode\">false</boolean>\n"
                + "    </debug>\n"
                + "</root>\n"
                + "";
    }

    public static String getClassPath(Class<?> cls) {
        try {
            String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath.substring(1).replace("\\", File.separator).replace("/", File.separator);
            if (decodedPath.contains("watt_exec.jar")) {
                decodedPath = decodedPath.substring(0, decodedPath.indexOf("watt_exec.jar"));
            }
            return decodedPath;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static UserModel getUserModel() {
        return model;
    }

    public static void setCurrentUserModel(UserModel model) {
        UsefulMethods.model = model;
    }

    public static PCModel getPcModel() {
        if (pc != null) {
            return pc;
        } else {
            try {
                pc = new PCModel();
                Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
                while (networks.hasMoreElements()) {
                    NetworkInterface network = networks.nextElement();
                    byte[] mac = network.getHardwareAddress();

                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }

                        String json;
                        if (sb.toString().length() == 17) {
                            System.out.println(sb.toString());
                            String s = "{"
                                    + "\"mac\" : \"" + sb.toString() + "\","
                                    + "\"command\" : \"check\""
                                    + "}";
                            json = getWebServiceResponse("http://" + getManagerInstance(OPTIONS).getContentByName("webServiceHost", 0) + ":" + Integer
                                    .parseInt(getManagerInstance(OPTIONS).getContentByName("webServicePort", 0)) + "/equipamentocheck", "POST", s);
                            json = json.substring(1, json.length() - 1);
                            JSONObject obj = new JSONObject(json);

                            try {
                                pc.setArduinoId(obj.getInt("id_arduino"));
                            } catch (Exception ex) {
                                pc.setArduinoId(-1);
                            }
                            
                            pc.setType(Integer.parseInt(getManagerInstance(OPTIONS).getContentByName("terminalType", 0)));
                            if (!json.isEmpty()) {
                                pc.setMac(sb.toString());
                                break;
                            }
                        }
                    }
                }
                return pc;
            } catch (NumberFormatException | IOException | JSONException ex) {
                return null;
            }
        }
    }

    public static void saveCurrentUserModel() {

    }

    public static String getWebServiceResponse(String url, String method, String json) throws MalformedURLException, ProtocolException, IOException {
        return getWebServiceResponse(url, method, null, null, json);
    }

    public static String getWebServiceResponse(String url, String method, String[] requestKeys,
            String[] requestValues, String json) throws MalformedURLException, ProtocolException, IOException {
        URL u = new URL(url);
        URLConnection con = u.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod(method);
        http.setDoOutput(true);
        String output;
        StringBuilder sb;

        if (json != null && requestKeys == null && requestValues == null) {
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            return sb.toString();
        } else if (requestKeys != null && requestValues != null && requestKeys.length == requestValues.length) {
            if (json != null) {
                byte[] out = json.getBytes(StandardCharsets.UTF_8);
                int length = out.length;

                http.setFixedLengthStreamingMode(length);
                for (int i = 0; i < requestKeys.length; i++) {
                    http.setRequestProperty(requestKeys[i], requestValues[i]);
                }

                http.connect();
                try (OutputStream os = http.getOutputStream()) {
                    os.write(out);
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
                sb = new StringBuilder();
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }

                return sb.toString();
            } else {
                for (int i = 0; i < requestKeys.length; i++) {
                    http.setRequestProperty(requestKeys[i], requestValues[i]);
                }
                http.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
                sb = new StringBuilder();
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }

                return sb.toString();
            }
        } else {
            http.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            return sb.toString();
        }
    }

    public static XmlManager getManagerInstance(int manager) {
        if (manager == OPTIONS && options != null) {
            return options;
        } else if (manager == LANGUAGE && language != null) {
            return language;
        }

        options = new XmlManager();
        boolean checkOS = false;

        File getConfig = new File(UsefulMethods.getClassPath(UsefulMethods.class) + File.separator + "config");
        if (!getConfig.exists()) {
            getConfig.mkdir();
        }

        File getOptions = new File(UsefulMethods.getClassPath(UsefulMethods.class) + "config" + File.separator + "options.xml");
        if (!getOptions.exists()) {
            String content = UsefulMethods.getOptions();

            if (content != null) {
                try {
                    getOptions.createNewFile();
                    try (PrintWriter writer = new PrintWriter(getOptions)) {
                        writer.print(content);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                checkOS = true;
            }
        }

        options.loadFile(UsefulMethods.getClassPath(UsefulMethods.class) + "config" + File.separator + "options.xml");
        switch (manager) {
            case OPTIONS:
                return options;
            case LANGUAGE:
                if (!checkOS) {
                    language = new XmlManager();
                    String temp = options.getContentByName("language", 0);
                    language.loadFile(UsefulMethods.getClassPath(UsefulMethods.class) + File.separator + "language"
                            + File.separator + temp.toLowerCase() + ".xml");

                    return language;
                }
        }

        return null;
    }

    public static ArduinoBridge getArduinoInstance() throws IOException {
        if (bridge != null) {
            return bridge;
        } else {
            bridge = new ArduinoBridge(getManagerInstance(OPTIONS).getContentByName("arduinoPort", 0));
            if (bridge.waitForConnection(5000)) {
                return bridge;
            } else {
                bridge = null;
                throw new IOException("Somehow the Arduino connection has fallen.");
            }
        }
    }

    public static void updateManagersInstances() {
        UsefulMethods get = new UsefulMethods();
        options = new XmlManager();
        String separator = System.getProperty("file.separator");

        File getConfig = new File(UsefulMethods.getClassPath(get.getClass()) + separator + "config");
        if (!getConfig.exists()) {
            getConfig.mkdir();
        }

        File getOptions = new File(UsefulMethods.getClassPath(get.getClass()) + "config" + separator + "options.xml");
        if (!getOptions.exists()) {
            String content = UsefulMethods.getOptions();

            if (content != null) {
                try {
                    getOptions.createNewFile();
                    PrintWriter writer = new PrintWriter(getOptions);
                    writer.print(content);
                } catch (IOException ex) {
                    Logger.getLogger(get.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        options.loadFile(UsefulMethods.getClassPath(get.getClass()) + "config" + separator + "options.xml");
        language = new XmlManager();
        String temp = options.getContentByName("language", 0);
        temp = temp.substring(0, temp.indexOf(","));
        language.loadFile(UsefulMethods.getClassPath(get.getClass()) + separator + "language" + separator + temp.toLowerCase() + ".xml");
    }

    public static boolean isShutdownTrue() {
        return isShutdownTrue;
    }

    public static void setIsShutdownTrue(boolean bl) {
        isShutdownTrue = bl;
    }

    public static Font getHeaderFont() {
        if (headerFont != null) {
            return headerFont;
        } else {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream stream = classLoader.getResourceAsStream("com/watterizer/style/featuredItem.ttf");
                headerFont = Font.createFont(Font.TRUETYPE_FONT, stream);
                ge.registerFont(headerFont);
                headerFont = headerFont.deriveFont(Font.PLAIN, 30);
                return headerFont;
            } catch (FontFormatException | IOException ex) {
                Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }

    public static InputStream downloadFile(String source) throws IOException {
        if (ftp == null) {
            ftp = new FTPClient();
            ftp.connect(getManagerInstance(OPTIONS).getContentByName("webServiceHost", 0), 21);

            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                System.err.println("FTP connection failed");
                return null;
            }

            ftp.login("watterizer", "senairianos115");
        }

        return ftp.retrieveFileStream(source);
    }

    public static boolean uploadFile(String localFilePath, String fileName, String hostDir) throws IOException {
        try {
            InputStream input = new FileInputStream(new File(localFilePath));
            if (!hostDir.endsWith("/")) {
                hostDir += "/";
            }
            ftp.storeFile(hostDir + fileName, input);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void makeHyperlinkOptionPane(String[] message, String link, int linkIndex, int messageType, String messageTitle) {
        JOptionPane pane = new JOptionPane(null, messageType);

        StringBuilder style = new StringBuilder("font-family:" + pane.getFont().getFamily() + ";");
        style.append("font-weight:").append(pane.getFont().isBold() ? "bold" : "normal").append(";");
        style.append("font-size:").append(pane.getFont().getSize()).append("pt;");
        style.append("background-color: rgb(").append(pane.getBackground().getRed()).append(", ")
                .append(pane.getBackground().getGreen()).append(", ").append(pane.getBackground().getBlue()).append(");");

        JEditorPane ep = new JEditorPane();
        ep.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        ep.setEditable(false);
        ep.setBorder(null);

        String show = "";
        for (int i = 0; i < message.length; i++) {
            if (i != linkIndex) {
                show += message[i];
            } else {
                show += " <a href=\"" + link + "\">" + message[i] + "</a>";
            }
        }

        ep.setText("<html><body style=\"" + style + "\">" + show + "</body></html>");

        ep.addHyperlinkListener((HyperlinkEvent e) -> {
            if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {

                }
            }
        });

        JOptionPane.showMessageDialog(null, ep, messageTitle, messageType);
    }

    public static String getSimpleDateFormat() {
        Calendar date = Calendar.getInstance();

        return date.getDisplayName(2, 2, Locale.US) + " " + date.get(Calendar.DATE) + ", " + date.get(Calendar.YEAR);
    }

    public static void makeBalloon(final JComponent component, final String text, final long time, final Color color) {
        makeBalloon(component, text, time, 0, color);
    }

    public static void makeBalloon(final JComponent component, final String text, final long time, final long delay, final Color color) {
        new Thread("Showing ballon \"" + text + "\"") {
            @Override
            public void run() {
                try {
                    java.lang.Thread.sleep(delay);
                    BalloonTip balloonTip = new BalloonTip(component, new JLabel(text), new MinimalBalloonStyle(color, 10),
                            BalloonTip.Orientation.LEFT_BELOW, BalloonTip.AttachLocation.SOUTH, 25, 10, false);

                    FadingUtils.fadeInBalloon(balloonTip, null, 200, 24);

                    java.lang.Thread.sleep(time);

                    FadingUtils.fadeOutBalloon(balloonTip, null, 200, 24);
                    TimingUtils.showTimedBalloon(balloonTip, 200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
}
