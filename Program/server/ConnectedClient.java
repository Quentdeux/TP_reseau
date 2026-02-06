package server;

import common.Message;

import java.io.*;
import java.net.Socket;

public class ConnectedClient implements Runnable {
    static int idCounter = 0;
    private int id;

    Server server;
    Socket socket;

    ObjectOutputStream out;
    ObjectInputStream in;

    public ConnectedClient (Server Server, Socket Socket) throws IOException {
        server = Server;
        socket = Socket;
        id = idCounter++;
        out = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Nouvelle connexion, id = " + id);
    }

    public void setId(int Id) {
        id = Id;
    }

    @Override
    public void run() {
        try {
            System.out.println(socket);

            // Crash ici, cherché mais pas trouvé pourquoi
            in = new ObjectInputStream(socket.getInputStream());

            boolean isActive = true;
            while (isActive) {
                Message mess = (Message) in.readObject();
                mess.setSender(String.valueOf(id));
                server.broadcastMessage(mess, id);
                server.disconnectedClient(this);
                isActive = false;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    int getId() {
        return id;
    }
}
