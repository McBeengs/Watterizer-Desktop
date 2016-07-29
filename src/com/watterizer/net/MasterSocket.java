/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

/**
 *
 * @author 15153769
 */
public class MasterSocket {

    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        System.out.println("Possible addresses:");
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if (i.getHostAddress().matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
                    System.out.println(i.getHostAddress());
                }
            }
        }

        System.err.println("---------------------");
        System.err.println("Opening connection on port \"12345\"");
        ServerSocket servidor = new ServerSocket(12345);

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
            switch (cliente.getInetAddress().getHostName()) {
                case "LAB10-12":
                    ObjectOutputStream stream = new ObjectOutputStream(cliente.getOutputStream());
                    stream.flush();
                    Random rgn = new Random();
                    stream.writeObject("Rodrigo");
                    break;
                case "LAB10-10":
                    ObjectOutputStream stream2 = new ObjectOutputStream(cliente.getOutputStream());
                    stream2.flush();
                    Random rgn2 = new Random();
                    stream2.writeObject("Kinsley");
            }
        }
    }
}
