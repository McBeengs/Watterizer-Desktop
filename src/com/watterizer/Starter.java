/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer;

import com.watterizer.modals.FirstUseSetupJFrame;
import com.watterizer.modals.SplashScreen;
import com.watterizer.panels.GenericErrorJPanel;
import com.watterizer.panels.LoginJPanel;
import com.watterizer.util.OpaqueScreen;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ProtocolException;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.Painter;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.tools.jconsole.LocalVirtualMachine;

/**
 *
 * @author 15153769
 */
public class Starter {

    private static int min = 10;
    private static JDialog dialog;
    private static JOptionPane pane;

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(new Locale("pt", "BR"));
        String separator = File.separator;

        String path = Starter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
        decodedPath = decodedPath.substring(1).replace("\\", File.separator).replace("/", File.separator);
        if (decodedPath.contains("watt_exec.jar")) {
            FileOutputStream stream = new FileOutputStream(new File(UsefulMethods.getClassPath(Starter.class) + "log.txt"));
            System.setErr(new PrintStream(stream));
        }

//        ZoneInfoProvider provider = new ZoneInfoProvider("org/joda/time/tz/data");
//        DateTimeZone.setProvider(provider);
        UIManager.put("ProgressBarUI", "javax.swing.plaf.metal.MetalProgressBarUI");
        UIManager.put("ProgressBar.cellLength", Integer.MAX_VALUE);
        UIManager.put("ProgressBar.foreground", new Color(51, 153, 255));
        UIManager.put("SeparatorUI", "javax.swing.plaf.metal.MetalSeparatorUI");
        UIManager.put("Separator.foreground", new Color(160, 160, 160));
        UIManager.put("SeparatorUI.shadow", new Color(160, 160, 160));
        UIManager.put("TreeUI", "javax.swing.plaf.metal.MetalTreeUI");
        UIManager.put("SeparatorUI", "javax.swing.plaf.metal.MetalSeparatorUI");
        UIManager.put("Separator.foreground", new Color(255, 200, 20));
        UIManager.put("Separator.background", new Color(255, 200, 20));
        UIManager.put("TreeUI", "javax.swing.plaf.metal.MetalTreeUI");
        UIManager.put("Button.background", new Color(255, 200, 20));
        UIManager.put("Button.select", new Color(255, 220, 20));
        //UIManager.put("ComboBox.background", new Color(255, 200, 20));
        //UIManager.put("ComboBox.buttonBackground", new Color(255, 220, 20));
        UIManager.put("RadioButton.foreground", Color.white);
        UIManager.put("RadioButton.focus", Color.yellow);
        UIManager.put("RadioButton.background", Color.black);
        UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 11));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.background", Color.black);
        UIManager.put("OptionPane.opaque", false);
        UIManager.put("OptionPane.sameSizeButtons", true);
        UIManager.put("Panel.background", Color.black);

        UIManager.put("TabbedPaneUI", "javax.swing.plaf.metal.MetalTabbedPaneUI");
        UIManager.put("TabbedPane.selected", new Color(234, 213, 25));
        UIManager.put("TabbedPane.unselectedBackground", new Color(150, 150, 150));
        UIManager.put("TabbedPane.borderHightlightColor", Color.black);
        UIManager.put("TabbedPane.darkShadow", Color.black);
        UIManager.put("TabbedPane.light", Color.black);
        UIManager.put("TabbedPane.darkShadow", Color.black);
        UIManager.put("TabbedPane.focus", Color.black);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.selectHighlight", Color.black);
        UIManager.put("TabbedPane.font", UsefulMethods.getHeaderFont());
        UIManager.put("TabbedPane.tabsOpaque", true);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (UsefulMethods.isShutdownTrue()) {
                        UsefulMethods.getArduinoInstance().close();
                        System.err.println("exited");
                    } else {

                    }
                } catch (IOException ex) {
                    Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        File getConfig = new File(UsefulMethods.getClassPath(SplashScreen.class) + separator + "config");
        if (!getConfig.exists()) {
            getConfig.mkdir();
        }

        File getOptions = new File(UsefulMethods.getClassPath(SplashScreen.class) + "config" + separator + "options.xml");
        if (!getOptions.exists()) {
            final XmlManager style = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
            //make first run setup screen
            new FirstUseSetupJFrame(style).setVisible(true);
            return;
        }

        XmlManager style;
        try {
            style = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        } catch (java.lang.NullPointerException ex) {
            OpaqueScreen screen = new OpaqueScreen();
            GenericErrorJPanel error = new GenericErrorJPanel(screen, "Falha com as configurações", GenericErrorJPanel.ALERT_MESSAGE,
                    "", GenericErrorJPanel.OK);
            error.setRightButtonAction((ActionEvent e) -> {
                new Thread() {
                    @Override
                    public void run() {

                    }
                }.start();
                error.disposeWindow();
            });

            screen.setRootFrame(error);
            screen.setVisible(true);
            return;
        }

        String set = style.getContentByName("style", 0);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (info.getName().contains(set)) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        if (UIManager.getLookAndFeel().getName().contains("Nimbus")) {
            UIManager.put("MenuBar[Enabled].backgroundPainter", (Painter<javax.swing.JMenuBar>) (Graphics2D gd, JMenuBar t, int i, int i1) -> {
                t.setBackground(Color.red);
            });
            UIManager.put("control", Color.black);
            UIManager.put("nimbusBase", new Color(178, 136, 0));
        } else if (UIManager.getLookAndFeel().getName().contains("Windows")) {
            UIManager.put("ButtonUI", "javax.swing.plaf.metal.MetalButtonUI");
            UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 11));
        }

        String s = "{"
                + "\"valor\":" + style.getContentByName("kwh", 0)
                + "}";
        UsefulMethods.getWebServiceResponse("http://" + style.getContentByName("webServiceHost", 0) + ":" + style.getContentByName("webServicePort", 0)
                + "/kilowatt", "POST", s);

        new Thread("Shutdown Hook") {
            @Override
            public void run() {
                while (true) {
                    try {
                        XmlManager xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
                        String json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0)
                                + "/desligapc", "GET", null);
                        JSONArray macs = new JSONArray(json);
                        for (int i = 0; i < macs.length(); i++) {
                            JSONObject obj = macs.getJSONObject(i);
                            if (obj.getString("mac").equals(UsefulMethods.getPcModel().getMac())) {
                                String message = "O computador será desligado automaticamente em &num minutos. Gostaria de permanecer utilizando-o?";

                                Timer t = new Timer(60000, (ActionEvent ae) -> {
                                    min--;
                                    if (min <= 0) {
                                        try {
                                            Runtime.getRuntime().exec("shutdown -s -t 0");
                                        } catch (Exception ex) {
                                            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    pane.setMessage(message.replace("&num", "" + min));
                                    pane.repaint();
                                    pane.revalidate();
                                    dialog.repaint();
                                    dialog.revalidate();
                                });
                                t.setRepeats(true);
                                t.start();

                                JButton[] buttons = new JButton[2];
                                JButton yes = new JButton(new AbstractAction() {
                                    @Override
                                    public void actionPerformed(ActionEvent ae) {
                                        try {
                                            t.stop();
                                            String s = "{"
                                                    + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + " \","
                                                    + "\"option\":\"sim\""
                                                    + "}";
                                            UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0)
                                                    + "/desligaconf", "POST", s);
                                            dialog.dispose();
                                        } catch (ProtocolException ex) {
                                            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                yes.setText("Sim");

                                JButton no = new JButton(new AbstractAction() {
                                    @Override
                                    public void actionPerformed(ActionEvent ae) {
                                        try {
                                            String s = "{"
                                                    + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + " \","
                                                    + "\"option\":\"nao\""
                                                    + "}";
                                            UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0)
                                                    + "/desligaconf", "POST", s);

                                            Runtime.getRuntime().exec("shutdown -s -t 0");
                                        } catch (Exception ex) {
                                            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                no.setText("Não");

                                buttons[0] = yes;
                                buttons[1] = no;

                                pane = new JOptionPane(message.replace("&num", "" + min), JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null, buttons);
                                dialog = pane.createDialog("Alerta");
                                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

                                dialog.setVisible(true);
                            }
                        }
                        sleep(1000);
                    } catch (IOException | JSONException | HeadlessException | InterruptedException ex) {
                        Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();

        java.awt.EventQueue.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        });
    }
}
