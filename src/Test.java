//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;



//        String songPath = "";
//
//        final int PORT = 6666;
//        final int DISCOVERY_PORT = 7777;
//
//        /* -------------- Server Code  -------------------------*/
//
//        // server socket
//        ServerSocket serverSocket = null;
//        Socket clientSocket = null;
//        BufferedReader in = null;
//
//        try {
//            serverSocket = new ServerSocket(6666);
//            clientSocket = serverSocket.accept();
//
//            System.out.println("Connection received...");
//            System.out.println(clientSocket.getRemoteSocketAddress());
//
//            in = new BufferedReader(
//                    new InputStreamReader(clientSocket.getInputStream())
//            );

/*

PROCESS GOES ->->

    Inputted to server from user app
        run user input through HostParser,
        That will pass it to Organiser, which checks if it's already on computer
            if not, pass to hostGetter to download
        Back to organiser to add to playlist
        HostPlayer plays all the stuff in the background anyways


*/
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException, ClassNotFoundException {
//        HostPlayer anotherTmp = new HostPlayer();
//        HostGetter tmp = new HostGetter();


        /* ------------------ Receiving from Client -------------- */
//        try {
//
//            while (true) {
//                inputLine = in.readLine();
//
//                if (inputLine.equals("quit") || inputLine.equals("")) {
//                    break;
//                }
//
//                System.out.println("FROM SOCKET: " + inputLine);
//            }

        new HostInitialize();
        new HostCatcher();

        /* -------------- Server Code  -------------------------*/


//        HostGetter tmp = new HostGetter();
//        HostPlayer anotherTmp = new HostPlayer();
//        HostOrganiser firstTmp = new HostOrganiser();





//        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=-sUXMzkh-jI");
//        songPath = tmp.getAudio(inputLine);

//        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=-sUXMzkh-jI")



//        HostParser tmp =  new HostParser();
//        String nearestURL = tmp.getNearestResult("so long marianne");
//        System.out.println(nearestURL);
    }


}