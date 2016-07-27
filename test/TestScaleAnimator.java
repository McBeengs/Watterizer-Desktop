/* **********   TestScaleAnimator.java   **********
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
import javax.swing.JFrame;

public class TestScaleAnimator {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScaleAnimator.JPanelZooming panel = new NovoJPanel();
        panel.setSize(100, 100);
        frame.add(panel);
        ScaleAnimator anim = new ScaleAnimator(null, panel, 100, 100, 100);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    frame.repaint();
                }
            }
        }.start();

        frame.setVisible(true);
        anim.beginAnimation();
    }
}
