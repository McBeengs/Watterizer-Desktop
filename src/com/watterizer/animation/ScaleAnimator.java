package com.watterizer.animation;

/* **********   ScaleAnimator.java   **********
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

import static java.lang.Thread.sleep;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * The class {@code ScaleAnimator} is designed for apply a simple zooming
 * animation on any component, as long it implements
 * {@link javax.swing.JComponent}. The animation consists in gradually reveal
 * the component with an scalling effect, with a speed that is also gradually
 * rising.
 *
 * @author McBeengs
 */
public class ScaleAnimator implements AnimatorCore<JComponent> {

    private static int NB_STEPS;
    private final int finalWidth;
    private final int finalHeight;
    private final JComponent component;
    private final ArrayList<AnimationListener<JComponent>> listeners;

    /**
     * The animation consists in gradually reveal the component with an scalling
     * effect, with a speed slowly decreasing.
     *
     * @param component The component who will be animated.
     * @param finalWidth The width that the component will have at the end of
     * the animation.
     * @param finalHeight The height that the component will have at the end of
     * the animation.
     * @param steps The number of steps required to go from the first to the
     * last frame of animation. A number lower than 100 means a quicker
     * animation with less processing demand, but the quality of said animation
     * is sacrified.
     */
    public ScaleAnimator(JLabel component, int finalWidth, int finalHeight, int steps) {
        this.component = component;
        this.finalWidth = finalWidth;
        this.finalHeight = finalHeight;
        NB_STEPS = steps;
        listeners = new ArrayList<>();
    }

    @Override
    public void beginAnimation() {
        new Thread() {
            @Override
            public void run() {
                listeners.stream().forEach((listener) -> {
                    listener.onAnimationStart(component);
                });

                for (int i = 0; i < NB_STEPS; i++) {
                    try {
                        animateComponent(i, NB_STEPS, component);
                        sleep(i % 100 / 5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScaleAnimator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                listeners.stream().forEach((listener) -> {
                    listener.onAnimationEnd(component);
                });
            }
        }.start();
    }

    @Override
    public void animateComponent(final int i, final int nbSteps, final JComponent component) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                int w = (i + 1) * finalWidth / nbSteps;
                int h = (i + 1) * finalHeight / nbSteps;

                component.setSize(w, h);
                //panelZooming.setScale((double) (i + 1) / nbSteps);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ScaleAnimator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * An custom-made implementation of {@link javax.swing.JPanel} that allows whatever content inside the 
     * panel be scalable to {@link ScaleAnimator} animations. All the contents inside the panel must be resizable
     * and / or organized so the new scale doesn't make them conflict with each other, otherwise it can generate 
     * strange behaviors.
     */
    @Override
    public void addAnimationListener(AnimationListener listener) {
        listeners.add(listener);
    }
}
