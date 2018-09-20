//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import javazoom.jl.decoder.JavaLayerException;

import javax.naming.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException {

        String songPath = "";

        final int PORT = 6666;
        final int DISCOVERY_PORT = 7777;

        /* -------------- Server Code  -------------------------*/

        // server socket
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;

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

                if (inputLine.equals("quit") || inputLine.equals("")) {
                    break;
                }

                System.out.println("FROM SOCKET: " + inputLine);
            }

            System.out.println("Connection closed");
            serverSocket.close();

        } catch (NullPointerException ex) {
            System.out.println(ex);
        }

        /* ------------------- End of Receiving from Client ------- */

        songPath = tmp.getAudio(inputLine);

//        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=-sUXMzkh-jI")


//        String songPath = "/home/user/Desktop/tempMusic/ITS A LONG WAY TO THE TOP (IF YOU WANNA ROCK N ROLL) - AC DC.mp3";
//        anotherTmp.setMusic(songPath);

//        HostParser tmp =  new HostParser();
//        String nearestURL = tmp.getNearestResult("so long marianne");
//        System.out.println(nearestURL);
    }


}