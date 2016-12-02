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
    private boolean isSocketConnected = false;

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
        isSocketConnected = true;
    }

    /**
     * Escreve e recebe mensagem full no socket
     *
     * @param message
     * @return
     * @throws java.io.IOException
     */
    public String echo(String message) throws IOException {
        // out & in 
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // escreve str no socket e lêr
        out.println(message);
        String retorno = in.readLine();
        return retorno;
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

    public void close() {
        try {
            socket.close();
            isSocketConnected = false;
        } catch (IOException ex) {
            Logger.getLogger(SocketNodeJS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isSocketConnected() {
        return isSocketConnected;
    }
}
