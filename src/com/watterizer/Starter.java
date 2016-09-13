/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer;

import com.watterizer.modals.GenericErrorJFrame;
import com.watterizer.modals.SplashScreen;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Locale;
import javax.swing.UIManager;

/**
 *
 * @author 15153769
 */
public class Starter {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        String separator = File.separator;

        File getConfig = new File(UsefulMethods.getClassPath(SplashScreen.class) + separator + "config");
        if (!getConfig.exists()) {
            getConfig.mkdir();
        }

        File getOptions = new File(UsefulMethods.getClassPath(SplashScreen.class) + "config" + separator + "options.xml");
        if (!getOptions.exists()) {
            final XmlManager style = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
            //make first run setup screen
            //new SetupContainer(style).setVisible(true);
            //return;
        }

        XmlManager style;
        try {
            style = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        } catch (java.lang.NullPointerException ex) {
            GenericErrorJFrame error = new GenericErrorJFrame("Falha com as configurações", GenericErrorJFrame.ALERT_MESSAGE,
                    "", GenericErrorJFrame.OK);
            error.setRightButtonAction((ActionEvent e) -> {
                new Thread() {
                    @Override
                    public void run() {

                    }
                }.start();
                error.disposeWindow();
            });
            error.setVisible(true);
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
        UIManager.put("ComboBox.background", new Color(255, 200, 20));
        UIManager.put("Button.background", new Color(255, 200, 20));
        UIManager.put("Button.select", new Color(255, 220, 20));
        UIManager.put("ComboBox.buttonBackground", new Color(255, 220, 20));
        UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 11));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.background", Color.black);
        UIManager.put("OptionPane.opaque", false);
        UIManager.put("OptionPane.sameSizeButtons", true);
        UIManager.put("Panel.background", Color.black);

        java.awt.EventQueue.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        });
    }
}
