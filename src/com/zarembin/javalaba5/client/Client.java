package com.zarembin.javalaba5.client;

import com.zarembin.javalaba5.util.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        String hostname ="127.0.0.1";
        try (
                Socket socket = new Socket(hostname, Constant.PORT_NUMBER);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream()
        ) {
            ClientGame clientGame = new ClientGame(socket, in, out);
            clientGame.startGame();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("в main Client");
            System.exit(1);
        }
    }
}
