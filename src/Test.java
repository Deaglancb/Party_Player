import com.sun.jndi.toolkit.url.Uri;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        HostGetter tmp = new HostGetter();
        tmp.getAudio("https://www.youtube.com/watch?v=UtF6Jej8yb4");


    }
}