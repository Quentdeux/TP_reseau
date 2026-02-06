package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable {
    Server server;
    ServerSocket serverSocket;

    public Connection(Server Server) throws IOException {
        server = Server;
        this.serverSocket = new ServerSocket(server.getPort());
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket socketNewClient = serverSocket.accept();
                ConnectedClient newClient = new ConnectedClient(server, socketNewClient);
                newClient.setId(server.getNumClients());
                server.addClient(newClient);
                try {
                    Thread threadNewClient = new Thread(newClient);
                    threadNewClient.start();
                }
                catch (Exception ex) {
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
