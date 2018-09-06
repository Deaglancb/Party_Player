/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */


// This should interact with user apps/ interfaces
//


import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HostParser {

    public void HostParser() {

    }


    // in case user simply enters name of a song
    public String getNearestResult(String songName) throws IOException, InterruptedException {

        String[] searchWords = songName.split(" ");
        String toAddToSearch = "";

        int i = 0;
        for(String str : searchWords) {
            toAddToSearch += str;
            i++;
            if (!(i == searchWords.length))
                toAddToSearch += "+";
        }


        String URLToGet = "https://www.youtube.com/results?search_query=" + toAddToSearch;

        Process p = new ProcessBuilder("GET",
                                                URLToGet).start();

        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s;
        while((s = in.readLine()) != null) {
            if (s.contains("href=\"/watch"))
                break;
        }


        String[] tokens = s.split(" ");

        String videoURL = "";
        for(String str : tokens) {
            if (str.contains("/watch?v=")) {
                videoURL = str;
                break;
            }
        }


        int firstQuote = -1;
        int secondQuote = -1;

        char[] tmpURL = videoURL.toCharArray();
        for(i = 0; i < videoURL.length(); i++){
            if (firstQuote == -1 && tmpURL[i] == '"') {
                firstQuote = i;
            } else {
                secondQuote = i;
            }

        }

        videoURL = "youtube.com" + videoURL.substring(firstQuote+1, secondQuote);

        return videoURL;
    }

}
