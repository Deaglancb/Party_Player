//TODO CONSIDER checking length of song before we add it to the playlist...at least without confirming
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

//        HostGetter tmp = new HostGetter();
//        tmp.getAudio("https://www.youtube.com/watch?v=UtF6Jej8yb4");

        HostParser tmp =  new HostParser();
        String nearestURL = tmp.getNearestResult("lcd soundsystem");
        System.out.println(nearestURL);
    }
}