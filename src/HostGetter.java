import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


//TODO check if video is already downloaded or convertd...
public class HostGetter {


    private String URL;
    private String title = "";
    private String path;
    private String homeDir;
    public String realTitle;
    // PYTHON SCRIPT LOCATION
    private String cmd =
                            "python3 /home/deaglan/workspace/Party_Player/src/TubeDownloader.py" + " ";
//                            "python3 /home/user/code/party_player/Party_Player/src/TubeDownloader.py" + " ";


    public HostGetter() {
        homeDir = System.getProperty("user.home");
        path = homeDir + "/Desktop";
        if (!(new File(path + "/tempMusic").exists()))
            new File(path + "/tempMusic").mkdir();

        path = path + "/tempMusic";
    }


    public String getAudio(String URL) throws IOException, InterruptedException {
        this.URL = URL;
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
        System.out.println("Download Complete ");


        stdInput.close();

        return "";
    }


}
