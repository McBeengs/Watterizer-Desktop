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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.Painter;
import javax.swing.UIManager;
import sun.tools.jconsole.LocalVirtualMachine;

/**
 *
 * @author 15153769
 */
public class Starter {

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(new Locale("pt", "BR"));
        String separator = File.separator;

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Runtime.getRuntime().exec("taskkill /F /IM " + "taskmgr.exe").waitFor();
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(LoginJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();

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

        UIManager.put("Panel.background", Color.black);
        UIManager.put("Panel.background", Color.black);
        UIManager.put("Panel.background", Color.black);
        UIManager.put("Panel.background", Color.black);
        UIManager.put("Panel.background", Color.black);
        UIManager.put("TabbedPane.borderHightlightColor", Color.black);
        UIManager.put("TabbedPane.darkShadow", Color.black);
        UIManager.put("TabbedPane.light", Color.black);
        UIManager.put("TabbedPane.darkShadow", Color.black);
        UIManager.put("TabbedPane.focus", Color.black);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.selectHighlight", Color.black);
        UIManager.put("TabbedPane.font", UsefulMethods.getHeaderFont());
        UIManager.put("TabbedPane.tabsOpaque", true);

        final Map<Integer, LocalVirtualMachine> virtualMachines = LocalVirtualMachine.getAllVirtualMachines();
        int isRunning = 0;
        for (final Entry<Integer, LocalVirtualMachine> entry : virtualMachines.entrySet()) {
            if (!entry.getValue().displayName().isEmpty()) {
                if (entry.getValue().displayName().equals("com.watterizer.Starter") && isRunning < 1) {
                    isRunning++;
                } else {
                    JOptionPane.showMessageDialog(null, "O aplicativo \"Watterizer\" já está em execução.", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }

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
            style = null;
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
                if (set.equals(info.getName())) {
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

        java.awt.EventQueue.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        });
    }
}
