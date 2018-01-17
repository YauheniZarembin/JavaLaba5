package com.zarembin.javalaba5.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Message {
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public static String receive(InputStream in) throws IOException {
        Message msg = new Message(new DataInputStream(in).readUTF());
        return msg.text;
    }

    public void send(Socket socket) throws IOException {
        (new DataOutputStream(socket.getOutputStream())).writeUTF(this.text);
    }
}
