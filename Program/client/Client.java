package client;

import common.Message;
import server.ConnectedClient;
import server.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    String adress;
    int port;
    Socket socket;

    ObjectInputStream in;
    ObjectOutputStream out;

    public Client(String serverIP, int serverPort ) throws IOException {
        adress = serverIP;
        port = serverPort;

        socket = new Socket(adress, port);

        out = new ObjectOutputStream(socket.getOutputStream());

//        Thread threadSend = new Thread(new ClientSend(this));
//        threadSend.start();
//
//        Thread threadReceive = new Thread(new ClientReceive(this));
//        threadReceive.start();
    }

    public void disconnectedServer() throws IOException {
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            socket.close();
        }
        if (in != null) {
            in.close();
        }
    }

    public void messageReceived(Message mess) {
        System.out.println(mess);
    }
}
