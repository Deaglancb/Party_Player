import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


//TODO check if video is already downloaded or convertd...
/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */
public class HostGetter {


    private String URL;
    private String title = "";
    private String path;


    public HostGetter() {
        path = System.getProperty("user.home") + "/Desktop";
        if (!(new File(path + "/tempMusic").exists()))
            new File(path + "/tempMusic").mkdir();

        path = path + "/tempMusic";
    };


    public String getAudio(String URL) throws IOException, InterruptedException {
        this.URL = URL;
        getVideo();
        path = convertVideo();
        return "path";

    }

    public String convertVideo() throws IOException, InterruptedException {

        char lastChar = title.toCharArray()[title.length()-1];
        String tmpPath;
        if (lastChar < 'A' || lastChar > 'Z' && lastChar < 'a' || lastChar > 'z')
            tmpPath = path + "/" + title.substring(0,title.length()-1);
        else
            tmpPath = path + "/" + title;



        String correctedPath = tmpPath;


        String goFrom =  correctedPath + ".mp4";
        String goTo = correctedPath + ".mp3";


        System.out.println(goFrom);
        System.out.println(goTo);

        System.out.println("converting audio");
        Process p = new ProcessBuilder("ffmpeg",
                "-i",
                goFrom,
                goTo).start();

        p.waitFor();

        //TODO remove, degbug

//        String line = "";
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(p.getErrorStream()));
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }
//        in.close();

        System.out.println("Conversion compete");
        File toDelete = new File(goFrom);
        toDelete.delete();

        return goTo;
    }

    public String getVideo() throws IOException, InterruptedException {



        String cmd =
                "python3 /home/deaglan/workspace/Party_Player/src/TubeDownloader.py" + " " + URL + " " + path;


        Process p = Runtime.getRuntime().exec(cmd);

        System.out.println("Waiting for download");
        p.waitFor();

        BufferedReader stdInput= new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s;
        while((s = stdInput.readLine()) != null) {
            title = s;
        }

        System.out.println(title);
        System.out.println("Download Complete ");


        stdInput.close();

        return "";
    }


}
