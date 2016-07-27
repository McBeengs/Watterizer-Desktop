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
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsefulMethods {

    public static final int OPTIONS = 0;
    public static final int LANGUAGE = 1;
    public static double CURRENT_KWH_CHARGE;
    private static final long TIME_OF_OPENING = System.currentTimeMillis();
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
                + "    <exec>watt_app.jar</exec>"
                + "</root>\n"
                + "";
    }

    public static String getClassPath(Class<?> cls) {
        try {
            String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
            return decodedPath.substring(1).replace("\\", File.separator).replace("/", File.separator);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsefulMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static long getTimeOfOpening() {
        return TIME_OF_OPENING;
    }

    public static Connection getDBInstance() {
        if (conn != null) {
            return conn;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //conn = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/watterizer", "watterizer", "senairianos115");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/watterizer", "root", "");
                //conn = DriverManager.getConnection("jdbc:mysql://10.0.3.230/watterizer", "watterizer", "senairianos115");
            } catch (ClassNotFoundException | SQLException ex) {
                return null;
            }

            return conn;
        }
    }
}
