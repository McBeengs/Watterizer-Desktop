/* **********   AnimatorCore.java   **********
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

package com.watterizer.animation;

import java.awt.event.ActionListener;

public interface AnimatorCore<T> {
    
    /**
     * Starts a new thread and kicks in the animation
     */
    void beginAnimation();
    
    void animateComponent(final int i, final int nbSteps, final T component);
    
    /**
     * Adds a listener that will be notified after the animation starts and ends.
     * @param listener The action to be executed
     */
    void addAnimationListener(AnimationListener listener);
}