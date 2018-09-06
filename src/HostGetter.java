import java.io.IOException;

/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */
public class HostGetter {



    public HostGetter(String URL) throws IOException, InterruptedException {

        String videoURL = "https://www.youtube.com/watch?v=PUlQNsl4Qvk";
        String path = "/home/deaglan/Desktop";
        String cmd =
                "python3 /home/deaglan/workspace/Party_Player/src/TubeDownloader.py" + " " + videoURL + " " + path;


        Process p = Runtime.getRuntime().exec(cmd);

        System.out.println("Waiting for download");
        p.waitFor();
        System.out.println("Download Complete");

    }


}
