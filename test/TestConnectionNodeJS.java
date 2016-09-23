
import com.watterizer.crypto.Encrypter;
import com.watterizer.net.SocketNodeJS;
import com.watterizer.util.UsefulMethods;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import javax.script.ScriptException;
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
public class TestConnectionNodeJS {

    private final Socket socket = null;

    public static void main(String[] args) throws IOException, InterruptedException, ScriptException, NoSuchMethodException {
        while (true) {
            URL url = new URL("http://10.0.4.70:1515/desliga");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("GET");
            http.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            System.out.println(sb.toString());
            if (sb.toString().contains("admin")) {
                if (JOptionPane.showConfirmDialog(null, "Desligar", "seila", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    url = new URL("http://10.0.4.70:1515/desligaconf");
                    con = url.openConnection();
                    http = (HttpURLConnection) con;
                    http.setRequestMethod("POST");
                    http.setDoOutput(true);

                    String s = "{\n"
                            + "\"confirm\":true,\n"
                            + "\"user\":\"" + "admin" + "\"\n"
                            + "}";
                    byte[] out = s.getBytes(StandardCharsets.UTF_8);
                    int length = out.length;

                    http.setFixedLengthStreamingMode(length);
                    http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    http.connect();
                    try (OutputStream os = http.getOutputStream()) {
                        os.write(out);
                    }
                    
                    System.err.println("desligar");
                    break;
                } else {
                    url = new URL("http://10.0.4.70:1515/desligaconf");
                    con = url.openConnection();
                    http = (HttpURLConnection) con;
                    http.setRequestMethod("POST");
                    http.setDoOutput(true);

                    String s = "{\n"
                            + "\"confirm\":false,\n"
                            + "\"user\":\"" + "admin" + "\"\n"
                            + "}";
                    byte[] out = s.getBytes(StandardCharsets.UTF_8);
                    int length = out.length;

                    http.setFixedLengthStreamingMode(length);
                    http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    http.connect();
                    try (OutputStream os = http.getOutputStream()) {
                        os.write(out);
                    }
                    
                    System.err.println("continua");
                }
            }
            sleep(1000);
        }
    }
}
