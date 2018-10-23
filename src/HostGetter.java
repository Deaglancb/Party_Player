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

//TODO Consider having another thread checking the size of downloading mp4s,if the size hasn't increaed for 20-30secods, download has stalled, which seems to happen sometimes
    // if this is the case, we want to stop and restart the download

    public HostGetter() throws IOException, ClassNotFoundException {
    }


    public static String getAudio(String songURL) throws IOException, InterruptedException {
        URL = songURL;
        //TODO rename this to downloadVideo()
        getVideo();
        String toReturn = convertVideo();

        return toReturn;
    }

    public static String convertVideo() throws IOException, InterruptedException {

        for(int i = 0; i < title.length(); i++) {
            char lastChar = title.charAt(i);
            if (lastChar == '"' || lastChar == ',' || lastChar == ':' || lastChar == '\'' || lastChar == '.' || lastChar == '/' || lastChar == ',' || lastChar == '/' || lastChar == '%')
                title = title.substring(0, i) + title.substring(i+1, title.length());
        }


        String goFrom =  songPath + "/"+ title + ".mp4";
        String goTo = songPath + "/" + realTitle + ".mp3";


        System.out.println(goFrom);
        System.out.println(goTo);

        File videoFile = new File(goFrom);
        File audioFile = new File(goTo);

//        videoFile.renameTo(audioFile);

        System.out.println("converting audio");
        Process p = new ProcessBuilder("ffmpeg",
                "-i",
                goFrom,
                goTo).start();

        p.waitFor();
//
//        //TODO remove, debug
//
//        String line = "";
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(p.getErrorStream()));
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }
//        in.close();


        // add song to downloaded songs array
        HostOrganiser.localLibraryMap.put(realTitle, new File(goTo));

        System.out.println("Conversion compete");
        File toDelete = new File(goFrom);
        toDelete.delete();

        return goTo;
    }

    public static String getVideo() throws IOException, InterruptedException {


        // PYTHON SCRIPT LOCATION
        String cmd =
                "ipython3 /home/deaglan/workspace/Party_Player/src/TubeDownloader.py" + " ";
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
