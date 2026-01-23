package server;

import java.net.ServerSocket;

public class Connection implements Runnable {
    Server server;
    ServerSocket serverSocket;

    public Connection(Server Server) {
        server = Server;
        this.serverSocket = new ServerSocket(server.getPort());
    }

    @Override
    public void run() {
        while(true) {
            Socket socketNewClient = serverSocket.accept();
            ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
            newClient.setId(server.getNumClients());
            server.addClient(newClient);

            try {
                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            }
            catch (Exception ex) {
            }


        }
    }
}
