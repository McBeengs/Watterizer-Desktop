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

import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
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

public class UsefulMethods {

    public static final int OPTIONS = 0;
    public static final int LANGUAGE = 1;
    private static XmlManager options;
    private static XmlManager language;
    private static Connection conn = null;

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
                + "  <gui>\n"
                + "    <language attr=\"0\">English</language>\n"
                + "    <style attr=\"0\">Metal</style>\n"
                + "  </gui>\n"
                + "</root>\n"
                + "";
    }

    public static String getClassPath(Class<?> cls) {
        try {
            String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
            if (decodedPath.contains("/")) {
                decodedPath = decodedPath.substring(0, decodedPath.lastIndexOf("/")) + "/";
            } else {
                decodedPath = decodedPath.substring(0, decodedPath.lastIndexOf("\\")) + "\\";
            }

            return decodedPath;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static XmlManager getManagerInstance(int manager) {
        if (manager == OPTIONS && options != null) {
            return options;
        }

        if (manager == LANGUAGE && language != null) {
            return language;
        }

        UsefulMethods get = new UsefulMethods();
        options = new XmlManager();
        String separator = System.getProperty("file.separator");
        boolean checkOS = false;

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
            } else {
                checkOS = true;
            }
        }

        options.loadFile(UsefulMethods.getClassPath(get.getClass()) + "config" + separator + "options.xml");
        switch (manager) {
            case OPTIONS:
                return options;
            case LANGUAGE:
                if (!checkOS) {
                    language = new XmlManager();
                    String temp = options.getContentByName("language", 0);
                    temp = temp.substring(0, temp.indexOf(","));
                    language.loadFile(UsefulMethods.getClassPath(get.getClass()) + separator + "language" + separator + temp.toLowerCase() + ".xml");

                    return language;
                }
        }

        return null;
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

    public static Component makeComponent(String str) {
        return null;
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

    public static void makeBalloon(final JComponent component, final String text, final Color color) {
        new Thread("Showing ballon \"" + text + "\"") {
            @Override
            public void run() {
                BalloonTip balloonTip = new BalloonTip(component, new JLabel(text), new MinimalBalloonStyle(color, 10),
                        BalloonTip.Orientation.LEFT_BELOW, BalloonTip.AttachLocation.SOUTH, 25, 10, false);

                FadingUtils.fadeInBalloon(balloonTip, null, 200, 24);
                try {
                    java.lang.Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
                FadingUtils.fadeOutBalloon(balloonTip, null, 200, 24);
                TimingUtils.showTimedBalloon(balloonTip, 200);
            }
        }.start();
    }

    public static Connection getDBInstance() {
        if (conn != null) {
            return conn;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/watterizer", "watterizer", "senairianos115");
            } catch (ClassNotFoundException | SQLException ex) {
                return null;
            }

            return conn;
        }
    }
}
