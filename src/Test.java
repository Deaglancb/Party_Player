//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import javazoom.jl.decoder.JavaLayerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException {

        /* -------------- Server Code  -------------------------*/
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;
        String songPath = "";

        try {
            serverSocket = new ServerSocket(6666);
            clientSocket = serverSocket.accept();

            System.out.println("Connection received...");
            System.out.println(clientSocket.getRemoteSocketAddress());

            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        String inputLine = "", outputLine;
        /* ---------------- End of Server Setup Code ---------------------- */



        HostPlayer anotherTmp = new HostPlayer();
        HostGetter tmp = new HostGetter();


        /* ------------------ Receiving from Client -------------- */
        try {

            while (true) {
                inputLine = in.readLine();
                System.out.println("FROM SOCKET: " + inputLine);
                if (inputLine.equals("quit") || inputLine.equals("")) {
                    break;
                }
            }
            
            System.out.println("Connection closed");
            serverSocket.close();

        } catch (NullPointerException ex) {
            System.out.println(ex);
        }

        /* ------------------- End of Receiving from Client ------- */


//        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=-sUXMzkh-jI");
        songPath = tmp.getAudio(inputLine);


//        String songPath = "/home/user/Desktop/tempMusic/ITS A LONG WAY TO THE TOP (IF YOU WANNA ROCK N ROLL) - AC DC.mp3";
//        anotherTmp.setMusic(songPath);

//        HostParser tmp =  new HostParser();
//        String nearestURL = tmp.getNearestResult("so long marianne");
//        System.out.println(nearestURL);
    }
}