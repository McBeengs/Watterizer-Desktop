
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 15153769
 */
public class NovoClass {

    public static TweenManager manager = new TweenManager();
    public static JFrame frame = new JFrame();
    public static GridBagConstraints c = new GridBagConstraints();

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        

        Tween.registerAccessor(JLabel.class, new JLabelAccessor());

        JLabel label = new JLabel("teste");
        label.setBackground(Color.red);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.PAGE_START;
        frame.add(label, c);
        Tween.to(label, 1, 3f).target(5f).start(manager);
        
        frame.setVisible(true);
    }

    public static class JLabelAccessor implements TweenAccessor<JLabel> {

        public static final int POSITION_X = 1;
        public static final int POSITION_Y = 2;
        public static final int POSITION_XY = 3;

        @Override
        public int getValues(JLabel target, int tweenType, float[] returnValues) {
            switch (tweenType) {
                case POSITION_X:
                    
                    return 1;
                case POSITION_Y:
                    
                    return 1;
                case POSITION_XY:
                    
                    return 2;
                default:
                    assert false;
                    return -1;
            }
        }

        @Override
        public void setValues(JLabel target, int tweenType, float[] newValues) {
            switch (tweenType) {
                case POSITION_X:
                    
                    break;
                case POSITION_Y:
                    
                    break;
                case POSITION_XY:
                    
                    break;
                default:
                    assert false;
                    break;
            }
        }

    }
}
