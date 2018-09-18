import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


//TODO check if video is already downloaded or convertd...
public class HostGetter {


    private String URL;
    private String title = "";
    private String path;
    private String homeDir;
    private String indexDir;
    public String realTitle;
    protected String indexFile = "";
    //TODO consider moving this outa here!
    protected static HashSet<String> downloadedSongs;

    // PYTHON SCRIPT LOCATION
    private String cmd =
                            "python3 /home/deaglan/workspace/Party_Player/src/TubeDownloader.py" + " ";
//                            "python3 /home/user/code/party_player/Party_Player/src/TubeDownloader.py" + " ";


    public HostGetter() throws IOException, ClassNotFoundException {

        // going to store music in the folder on user's desktop, if folder doesn't exist, create it
        homeDir = System.getProperty("user.home");
        path = homeDir + "/Desktop/tempMusic";


        indexDir = path + "/songIndex";

        if(!(new File(indexDir).exists())) {
            new File(indexDir).createNewFile();
            //TODO In case user deletes index file, check all other files in the directoy for mp3s and add them again
            downloadedSongs = new HashSet<>();

            FileOutputStream fos = new FileOutputStream(indexDir);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(downloadedSongs);
            oos.close();


        } else {
            FileInputStream fis = new FileInputStream(indexDir);
            ObjectInputStream ois = new ObjectInputStream(fis);
            downloadedSongs = (HashSet<String>) ois.readObject();
            ois.close();
        }




        if (!(new File(path).exists()))
            new File(path).mkdir();

    }


    public String getAudio(String URL) throws IOException, InterruptedException {
        this.URL = URL;

        //TODO rename this to downloadVideo()
        getVideo();

        path = convertVideo();

        return path;

    }

    public String convertVideo() throws IOException, InterruptedException {

        char lastChar = title.toCharArray()[title.length()-1];
        String tmpPath;

        String tmpTitle = "";

        for(int i = 0; i < title.length(); i++) {
            lastChar = title.charAt(i);
            if (lastChar == '"' || lastChar == '\'' )
                title = title.substring(0, i) + title.substring(i+1, title.length());
        }



        tmpPath = path + "/" + title;
        System.out.println( "           " + title);



        String correctedPath = tmpPath;




        String goFrom =  correctedPath + ".mp4";
        String goTo = path + "/" + realTitle + ".mp3";


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

        System.out.println("Conversion compete");
        File toDelete = new File(goFrom);
        toDelete.delete();

        path = goTo;
        return "";
    }

    public String getVideo() throws IOException, InterruptedException {



        String cmd = this.cmd + URL + " " + path;

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

        // add song to downloaded songs array
        downloadedSongs.add(title);


        //TODO Don't do this for every song, only when program is closing, but for now since we can kill it I'll write it every time

        FileOutputStream fos = new FileOutputStream(indexDir);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(downloadedSongs);
        oos.close();

        System.out.println("Download Complete ");


        stdInput.close();

        return "";
    }


}
