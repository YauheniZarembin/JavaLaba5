package com.zarembin.javalaba5.server;

import com.zarembin.javalaba5.util.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args)
    {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1000);
        try (ServerSocket serverSocket = new ServerSocket(Constant.PORT_NUMBER)) {
            while (true) {
                Socket client1 = serverSocket.accept();
                Socket client2 = serverSocket.accept();
                ServerGame serverGame = new ServerGame(client1, client2);
                fixedThreadPool.execute(serverGame);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("in main Server");
            System.exit(1);
        }
    }
}
