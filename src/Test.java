//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import javazoom.jl.decoder.JavaLayerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException {

        ServerSocket serverSocket;
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String songPath = "";

        try {
            serverSocket = new ServerSocket(6666);
            clientSocket = serverSocket.accept();

            System.out.println("Connection received...");
            System.out.println(clientSocket.getRemoteSocketAddress());

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        String inputLine = "", outputLine;



        HostPlayer anotherTmp = new HostPlayer();
//
        HostGetter tmp = new HostGetter();

        try {
            inputLine = in.readLine();
            System.out.println("FROM SOCKET: " + inputLine);
            System.out.println("Loop broke...");
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }


//        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=-sUXMzkh-jI");
        songPath = tmp.getAudio(inputLine);


//        String songPath = "/home/user/Desktop/tempMusic/ITS A LONG WAY TO THE TOP (IF YOU WANNA ROCK N ROLL) - AC DC.mp3";
//        anotherTmp.setMusic(songPath);

//        HostParser tmp =  new HostParser();
//        String nearestURL = tmp.getNearestResult("so long marianne");
//        System.out.println(nearestURL);
    }
}