/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author 15153769
 */
public class StylizedJButton extends JButton {

    public StylizedJButton() {
        this(null);
    }

    public StylizedJButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 5, 5);

        if (getModel().isPressed()) {
            g2.setColor(new Color(255, 255, 100));
        } else if (getModel().isRollover()) {
            g2.setColor(Color.yellow);
        } else {
            g2.setColor(getBackground());
        }

        Area corner = new Area(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
        //corner.subtract(new Area(round));
        g2.fill(corner);
        super.paintComponent(g2);
    }

}
