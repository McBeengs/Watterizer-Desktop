
import com.watterizer.Starter;
import com.watterizer.panels.LoginJPanel;
import com.watterizer.util.UsefulMethods;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 15153769
 */
public class TestOpenWattRunn {

    public static void main(String[] args) {
        System.out.println(UsefulMethods.getClassPath(TestOpenWattRunn.class) + "watt_runn.jar");
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", UsefulMethods.getClassPath(TestOpenWattRunn.class) + "watt_runn.jar", "Batata Frita é mt bom", "" + "2");
        pb.redirectErrorStream();
        //pb.directory(new File(UsefulMethods.getClassPath(Starter.class)));
        try {
            Process proc = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(TestOpenWattRunn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
