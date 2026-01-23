package server;

import common.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectedClient implements Runnable {
    static int idCounter = 0;
    int id;

    Server server;
    Socket socket;

    ObjectOutputStream out;
    ObjectInputStream in;

    public void ConnectedClient (Server Server, Socket Socket) {
        server = Server;
        socket = Socket;
        id = idCounter++;

        out = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Nouvelle connexion, id = " + id);
    }

    @Override
    public void run() {
        in = new ObjectInputStream(socket.getInputStream());
        boolean isActive = true;
        while (isActive) {
            try {
                Message mess = (Message) in.readObject();
                mess.setSender(String.valueOf(id));
                server.broadcastMessage(mess, id);
                server.disconnectedClient(this);
                isActive = false;
            }
            catch (Exception ex) {
            }
            catch (EOFException ex) {
            }
        }
    }

    void sendMessage(Message mess) throws IOException {
        this.out.writeObject(mess);
        this.out.flush();
    }

    void closeClient() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}
