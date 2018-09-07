//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException {


        HostPlayer anotherTmp = new HostPlayer();

//        HostGetter tmp = new HostGetter();
//        String songPath = tmp.getAudio("https://www.youtube.com/watch?v=8xQOb51qZ-c");


        String songPath = "/home/deaglan/Desktop/tempMusic/So Long Marianne by Leonard Cohen.mp3";
        anotherTmp.setMusic(songPath);

//        HostParser tmp =  new HostParser();
//        String nearestURL = tmp.getNearestResult("so long marianne");
//        System.out.println(nearestURL);
    }
}