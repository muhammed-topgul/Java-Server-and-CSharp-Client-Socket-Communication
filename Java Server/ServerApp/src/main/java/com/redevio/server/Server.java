package com.redevio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 00:32
 */
public class Server {
    public static void main(String[] args) throws IOException {

        try {
            connect();
        } catch (Exception e) {
        }

    }

    public static void connect() throws Exception {
        System.out.println("Server started...");
        ServerSocket serverSocket = new ServerSocket(5060, 10);
        while (true) {
            Socket socket = serverSocket.accept();
            Echoer echoer = new Echoer(socket);
            echoer.start();
        }
    }
}