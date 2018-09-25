import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


//TODO check if video is already downloaded or convertd...
public class HostGetter {


    private static String URL;
    private static String title;
    private static String songPath;
    public static String realTitle;
    //TODO consider moving this outa here!



    public HostGetter() throws IOException, ClassNotFoundException {
    }


    public void getAudio(String URL) throws IOException, InterruptedException {
        this.URL = URL;
        //TODO rename this to downloadVideo()
        getVideo();
        convertVideo();
    }

    public String convertVideo() throws IOException, InterruptedException {

        for(int i = 0; i < title.length(); i++) {
            char lastChar = title.charAt(i);
            if (lastChar == '"' || lastChar == '\'' )
                title = title.substring(0, i) + title.substring(i+1, title.length());
        }


        String goFrom =  songPath + "/"+ title + ".mp4";
        String goTo = songPath + "/" + realTitle + ".mp3";


        System.out.println(goFrom);
        System.out.println(goTo);

        System.out.println("converting audio");
        Process p = new ProcessBuilder("ffmpeg",
                "-i",
                goFrom,
                goTo).start();

        p.waitFor();

        //TODO remove, debug

        String line = "";
        BufferedReader in = new BufferedReader(
                new InputStreamReader(p.getErrorStream()));
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();


        // add song to downloaded songs array
        HostOrganiser.localLibraryMap.put(realTitle, new File(goTo));

        System.out.println("Conversion compete");
        File toDelete = new File(goFrom);
        toDelete.delete();

        return "";
    }

    public static String getVideo() throws IOException, InterruptedException {


        // PYTHON SCRIPT LOCATION
        String cmd =
                "python3 /home/deaglan/workspace/Party_Player/src/TubeDownloader.py" + " ";
//                          "python3 /home/user/code/party_player/Party_Player/src/TubeDownloader.py" + " ";

        String pathToDownloadTo = HostInitialize.songPath;

        cmd = cmd + URL + " " + pathToDownloadTo;

        Process p = Runtime.getRuntime().exec(cmd);

        System.out.println("Waiting for download");
        p.waitFor();

        BufferedReader stdInput= new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s;
        while((s = stdInput.readLine()) != null) {
            title = s;
        }

        System.out.println(title);
        realTitle = title;

        songPath = pathToDownloadTo;

        System.out.println("Download Complete ");


        stdInput.close();

        return "";
    }


}
