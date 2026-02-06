package server;

import common.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int port;
    List<ConnectedClient> clients;

    public Server(int Port) throws IOException {
        port = Port;
        this.clients = new ArrayList<ConnectedClient>();

        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }

    public int getNumClients() {
        return clients.size();
    }

    public void addClient(ConnectedClient newClient) throws IOException {
        this.clients.add(newClient);
        broadcastMessage(new Message(String.valueOf(newClient.getId()), "Vient de se connecter"), newClient.getId());
    }

    void broadcastMessage(Message mess, int id) throws IOException {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage(mess);
            }
        }
    }

    void disconnectedClient(ConnectedClient discClient) throws IOException {
        discClient.closeClient();
        this.clients.remove(discClient);
        broadcastMessage(new Message(String.valueOf(discClient.getId()), "Vient de se déconnecter"), discClient.getId());
    }

    public int getPort() {
        return port;
    }
}
