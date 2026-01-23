package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int port;
    List<ConnectedClient> clients;

    public Server(int Port) {
        port = Port;
        this.clients = new ArrayList<ConnectedClient>();

        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }

    public int getNumClients() {
        return clients.size();
    }

    public void addClient(ConnectedClient newClient) {
        this.clients.add(newClient);
        broadcastMessage(newClient.getId()+" vient de se connecter", newClient.getId());
    }

    void broadcastMessage() {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage(mess);
            }
        }
    }

    void disconnectedClient(ConnectedClient discClient) throws IOException {
        discClient.closeClient();
        this.clients.remove(discClient);
        broadcastMessage(discClient.getId()+" vient de se connecter", newClient.getId());

    }
}
