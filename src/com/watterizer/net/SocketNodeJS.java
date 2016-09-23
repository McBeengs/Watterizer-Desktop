/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15153769
 */
public class SocketNodeJS {

    private Socket socket = null;

    /**
     * Realiza a conexão com o socket
     *
     * @param ip
     * @param port
     * @throws UnknownHostException
     * @throws IOException
     */
    public void socketConnect(String ip, int port) throws UnknownHostException, IOException {
        this.socket = new Socket(ip, port);
    }

    /**
     * Escreve e recebe mensagem full no socket
     *
     * @param message
     * @return
     */
    public String echo(String message) {
        try {
            // out & in 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // escreve str no socket e lêr
            out.println(message);
            String retorno = in.readLine();
            return retorno;

        } catch (IOException ex) {
            Logger.getLogger(SocketNodeJS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public String readLine() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketNodeJS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
