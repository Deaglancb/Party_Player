import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private int BUFFER_SIZE = 100;
    private int BACKLOG = 10;
    private int PORT = 6666;
    private int MCAST_PORT = 10000;
    private String MCAST_ADDR = "224.0.0.1";
    private ArrayList<InetAddress> clientAddresses;

    private ServerSocket serverSocket;

    // new ServerSocket(port, BACKLOG)
    private byte[] bytes;
    private DatagramPacket dataGram;
    private DatagramSocket datagramSocket;
    private MulticastSocket multicastSocket;

    public Server() {
        bytes = new byte[BUFFER_SIZE];
        clientAddresses = new ArrayList<>();
        try {
            datagramSocket = new DatagramSocket();
            multicastSocket = new MulticastSocket(MCAST_PORT);
            multicastSocket.joinGroup(InetAddress.getByName(MCAST_ADDR));
            dataGram = new DatagramPacket(bytes, bytes.length,
                    InetAddress.getByName(MCAST_ADDR), MCAST_PORT);
            listenForClients();
        } catch (IOException ex) {
            System.out.println("Error: multicast socket");
            ex.printStackTrace();
        }
    }

    private void listenForClients() {
        try {
            while (true) {
                multicastSocket.receive(dataGram);
                sendAddressToClient();
            }
        } catch (IOException ex) {
            System.out.println("Error: multicast receive");
            ex.printStackTrace();
        }
    }

    private void sendAddressToClient() {
        try {
            datagramSocket.send(dataGram);
        } catch (IOException ex) {
            System.out.println("Error: sending dataGram");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }


}
