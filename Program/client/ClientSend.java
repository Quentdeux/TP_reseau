package client;

import common.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable {
    Socket socket;
    ObjectOutputStream out;

    @Override
    public void run() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Votre message >> ");
                    String m = sc.nextLine();

                    Message mess = new Message("client", m);
                    out.writeObject(mess);
                    out.flush();
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}
