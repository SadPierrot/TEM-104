package com.company;

import java.io.*;
import java.net.Socket;

public class ConnectionChannel {
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public ConnectionChannel(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public byte[] interactionResultOnRequest(byte[] data, int size) {
        byte[] a = new byte[size];
        try {
            dataOutputStream.write(data);
            dataOutputStream.flush();
            dataInputStream.readFully(a);
        } catch (IOException e) {
            System.out.println("Ex" + e);
        }
        return a;
    }
}
