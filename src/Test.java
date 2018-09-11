//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException {


        HostPlayer anotherTmp = new HostPlayer();
//
        HostGetter tmp = new HostGetter();
        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=-sUXMzkh-jI");


//        String songPath = "/home/user/Desktop/tempMusic/ITS A LONG WAY TO THE TOP (IF YOU WANNA ROCK N ROLL) - AC DC.mp3";
//        anotherTmp.setMusic(songPath);

//        HostParser tmp =  new HostParser();
//        String nearestURL = tmp.getNearestResult("so long marianne");
//        System.out.println(nearestURL);
    }
}