
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;
import javax.swing.JLabel;

/* **********   NovoJFrame.java   **********
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
public class NovoJFrame extends javax.swing.JFrame {

    public NovoJFrame() {
        initComponents();
        Tween.registerAccessor(JLabel.class, new JLabelAccessor());
        TweenManager manager = new TweenManager();
        Tween.to(label, 1, 3f).target(10f, 20f).start(manager);

        new Thread() {
            long time = System.currentTimeMillis();

            @Override
            public void run() {
                while (true) {
                    manager.update((System.currentTimeMillis() - time));
                    repaint();
                    time = System.currentTimeMillis();
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label)
                .addContainerGap(346, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(label)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new NovoJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables

    public class JLabelAccessor implements TweenAccessor<JLabel> {

        public static final int POSITION_X = 1;
        public static final int POSITION_Y = 2;
        public static final int POSITION_XY = 3;

        @Override
        public int getValues(JLabel target, int tweenType, float[] returnValues) {
            switch (tweenType) {
                case POSITION_X:
                    returnValues[0] = target.getX();
                    return 1;
                case POSITION_Y:
                    returnValues[0] = target.getY();
                    return 1;
                case POSITION_XY:
                    returnValues[0] = target.getX();
                    returnValues[1] = target.getY();
                    return 2;
                default:
                    assert false;
                    return -1;
            }
        }

        @Override
        public void setValues(JLabel target, int tweenType, float[] newValues) {
            System.out.println(newValues[0]);
            switch (tweenType) {
                case POSITION_X:
                    target.setAlignmentX(newValues[0]);
                    break;
                case POSITION_Y:
                    target.setAlignmentY(newValues[0]);
                    break;
                case POSITION_XY:
                    target.setAlignmentX(newValues[0]);
                    target.setAlignmentY(newValues[1]);
                    break;
                default:
                    assert false;
                    break;
            }
        }

    }
}
