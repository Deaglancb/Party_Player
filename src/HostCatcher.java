/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */


import javazoom.jl.decoder.JavaLayerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// This class will hopefully interact directly with the app
public class HostCatcher {


    public HostCatcher() {

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



        /* ------------------ Receiving from Client -------------- */
        try {

            while (true) {
                inputLine = in.readLine();
                System.out.println("FROM SOCKET: " + inputLine);

//TODO FIX THIS STUFF
                HostPlayer.setMusic(HostGetter.getAudio(HostParser.parseSongInput(inputLine)));
//


                if (inputLine.equals("quit") || inputLine.equals("")) {
                    break;
                }
            }

            System.out.println("Connection closed");
            serverSocket.close();

        } catch (NullPointerException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

        /* ------------------- End of Receiving from Client ------- */

    }

    public void getUserInput(String userInput /*somehow get user message*/) throws IOException, InterruptedException {



        HostParser.parseSongInput(userInput);

    }
}
