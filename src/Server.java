import java.net.ServerSocket;

public class Server {

    private int PORT;
    private ServerSocket serverSocket;
    private static int BACKLOG = 5;
    // new ServerSocket(port, BACKLOG)

    public Server(int port) {
        PORT = port;
    }

    private void bindServer() {

    }


}
