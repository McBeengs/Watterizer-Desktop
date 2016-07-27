/* **********   NovoClass1.java   **********
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The class {@code ScaleAnimator} is designed for apply a simple zooming
 * animation on any component, as long it is inside a {@code JPanelZooming}. The
 * animation consists in gradually reveal the component with an scalling effect,
 * with a speed that is also gradually rising.
 *
 * @author McBeengs
 */
public class ScaleAnimator {

    private static int NB_STEPS;
    private final int finalWidth;
    private final int finalHeight;
    private final JFrame owner;
    private final JPanelZooming component;
    private JDialog dialog;
    private JPanelZooming panelZooming;

    /**
     * The animation consists in gradually reveal the component with an scalling
     * effect, with a speed that is also gradually rising.
     *
     * @param owner The root of the component, usually a {@link javax.swing.JFrame} or {@link javax.swing.JPanel}.
     *  It is used as an anchor while the animation is running to lock the component at the center. Can be nullable, 
     *  however it can generate strange behaviors.
     * @param component The component who will be animated.
     * @param finalWidth The width that the component will have at the end of the animation.
     * @param finalHeight The height that the component will have at the end of the animation.
     * @param steps The number of steps required to go from the first to the last frame of animation. A number lower  
     * than 100 means a quicker animation with less processing demand, but the quality of said animation is sacrified.
     */
    public ScaleAnimator(JFrame owner, JPanelZooming component, int finalWidth, int finalHeight, int steps) {
        this.owner = owner;
        this.component = component;
        this.finalWidth = finalWidth;
        this.finalHeight = finalHeight;
        NB_STEPS = steps;
    }

    /**
     * Starts a new thread and kicks in the animation
     */
    public void beginAnimation() {
        new Thread() {
            @Override
            public void run() {
                panelZooming = component;

                for (int i = 0; i < NB_STEPS; i++) {
                    try {
                        scaleComponent(i, NB_STEPS);
                        sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScaleAnimator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    private void scaleComponent(final int i, final int nbSteps) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                if (dialog == null) {
                    dialog = new JDialog(owner);
                    dialog.setUndecorated(true);
                    dialog.setModal(false);
                    dialog.setContentPane(panelZooming);
                    dialog.setSize(0, 0);
                    dialog.setLocationRelativeTo(owner);
                    dialog.setVisible(true);
                }
                int w = (i + 1) * finalWidth / nbSteps;
                int h = (i + 1) * finalHeight / nbSteps;

                panelZooming.setScale((double) (i + 1) / nbSteps);
                dialog.setSize(w, h);
                dialog.setLocationRelativeTo(null);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ScaleAnimator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * An custom-made implementation of {@link javax.swing.JPanel} that allows whatever content inside the 
     * panel be scalable to {@link ScaleAnimator} animations. All the contents inside the panel must be resizable
     * and / or organized so the new scale doesn't make them conflict with each other, otherwise it can generate 
     * strange behaviors.
     */
    public static class JPanelZooming extends JPanel {

        private double scale = 1.0d;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform at = g2.getTransform();
            AffineTransform oldTransform = (AffineTransform) at.clone();
            at.scale(scale, scale);
            g2.setTransform(at);
            g2.setTransform(oldTransform);
        }

        /**
         * Sets the new scale for all the components inside the panel. This method is automatically set when using 
         * {@link ScaleAnimator} and is highly recommended to leave it untouched.
         * 
         * @param scale The new scale of the panel
         */
        public void setScale(double scale) {
            this.scale = scale;
        }
    }
}
