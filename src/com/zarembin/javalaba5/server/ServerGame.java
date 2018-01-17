package com.zarembin.javalaba5.server;

import com.zarembin.javalaba5.util.Message;
import com.zarembin.javalaba5.util.Constant;

import java.io.InputStream;
import java.net.Socket;

public class ServerGame implements Runnable {
    private Socket client1;
    private Socket client2;

    public ServerGame(Socket client1, Socket client2) {
        this.client1 = client1;
        this.client2 = client2;
    }

    @Override
    public void run() {
        try (InputStream tin1 = client1.getInputStream();
             InputStream tin2 = client2.getInputStream();) {
            InputStream in1 = tin1, in2 = tin2;

            while (!Message.receive(in1).equals(Constant.I_AM_READY));
            while (!Message.receive(in2).equals(Constant.I_AM_READY));

            new Message(Constant.GAME_STARTED).send(client1);
            new Message(Constant.GAME_STARTED).send(client2);

            String moveResult = "";
            while (!moveResult.equals(Constant.YOU_WIN)) {
                new Message(Constant.YOUR_TURN).send(client1);
                do {
                    String move = Message.receive(in1);
                    new Message(move).send(client2);
                    moveResult = Message.receive(in2);
                    new Message(moveResult).send(client1);
                } while (moveResult.equals(Constant.HIT)
                        || moveResult.equals(Constant.SUNK));

                Socket tmp = client1;
                client1 = client2;
                client2 = tmp;
                InputStream in = in1;
                in1 = in2;
                in2 = in;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

