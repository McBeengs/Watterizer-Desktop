
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
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
public class TestConnectionNodeJS {
    
    private Socket socket = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        TestConnectionNodeJS client = new TestConnectionNodeJS();
        client.socketConnect("10.0.4.70", 3000);
        Random rgn = new Random();
                    
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            double currentSpent = rgn.nextInt(100) / 100.0;
            System.out.println(client.echo("" + (currentSpent / 60.0)));
            sleep(1000);
        }
    }

    // realiza a conexão com o socket
    private void socketConnect(String ip, int port) throws UnknownHostException, IOException {
        System.out.println("[Conectando socket...]");
        this.socket = new Socket(ip, port);
    }

    // escreve e recebe mensagem full no socket (String)
    public String echo(String message) {
        try {
            // out & in 
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));

            // escreve str no socket e lêr
            out.println(message);
            String retorno = in.readLine();
            return retorno;

        } catch (IOException ex) {
            Logger.getLogger(TestConnectionNodeJS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    // obtem instância do socket
    private Socket getSocket() {
        return socket;
    }
}
