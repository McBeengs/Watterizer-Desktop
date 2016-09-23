
import com.watterizer.util.OpaqueScreen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 15153769
 */
public class TestOpaqueScreen {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        OpaqueScreen s = new OpaqueScreen(null);
        
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestOpaqueScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.close();
            }
        }.start();
        
        s.setVisible(true);
        if (JOptionPane.showConfirmDialog(s.getRootFrame(), "Sair?", "teste", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            s.close();
        } else {
            System.err.println("não");
            s.close();
        }
    }
}
