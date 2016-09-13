/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.modals;

import com.watterizer.panels.LoginJPanel;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 15153769
 */
public class LoginJFrame {

    public LoginJFrame() throws IOException, InterruptedException {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 255, 0, 0));

        frame.setLayout(new BorderLayout());
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(0);

        Runtime.getRuntime().exec("taskkill /F /IM " + "explorer.exe").waitFor();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Runtime.getRuntime().exec("taskkill /F /IM " + "taskmgr.exe").waitFor();
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(LoginJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();

        LoginJPanel panel = new LoginJPanel((ActionEvent ae) -> {
            try {
                Runtime.getRuntime().exec("explorer.exe");
                frame.dispose();
            } catch (IOException ex) {
                Logger.getLogger(LoginJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        panel.setPreferredSize(new Dimension(745, 450));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        ContentPane pane = new ContentPane();
        pane.setLayout(new GridBagLayout());
        pane.add(panel);

        frame.setContentPane(pane);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    public static class ContentPane extends JPanel {

        public ContentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Allow super to paint
            super.paintComponent(g);
            // Apply our own painting effect
            Graphics2D g2d = (Graphics2D) g.create();
            // 50% transparent Alpha
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setColor(getBackground());
            g2d.fill(getBounds());
            g2d.dispose();
        }
    }
}
