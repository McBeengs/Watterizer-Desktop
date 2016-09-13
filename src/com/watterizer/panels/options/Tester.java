/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.panels.options;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 15153769
 */
public class Tester {

    public static void main(String[] args) {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        
        new OptionsJFrame().setVisible(true);
    }
}
