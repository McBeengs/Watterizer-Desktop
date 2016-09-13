
import com.watterizer.net.SocketNodeJS;
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
import javax.script.ScriptException;


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

    public static void main(String[] args) throws IOException, InterruptedException, ScriptException, NoSuchMethodException {
        SocketNodeJS conn = new SocketNodeJS();
        conn.socketConnect("10.0.4.70", 3000);
        conn.echo("oi");
    }
}
