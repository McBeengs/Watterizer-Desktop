
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
public class NovoClass1 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                new URL("http://produto.mercadolivre.com.br/MLB-788621449-placa-me-dell-inspiron-14z-5423-intel-i5-c-defeito-_JM").openStream();
            } catch (MalformedURLException ex) {
                Logger.getLogger(NovoClass1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NovoClass1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
