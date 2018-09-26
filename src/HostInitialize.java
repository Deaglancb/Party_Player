import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */
public class HostInitialize {


    static File playList;
    static File localLibrary;
    protected static String songPath;

    public HostInitialize() throws IOException, ClassNotFoundException {



        loadFiles();
        verifyLocalLibrary();
        verifyPlaylist();



        HostOrganiser organiser = new HostOrganiser();
        HostGetter getter = new HostGetter();
        HostParser parser = new HostParser();
        HostPlayerGUI GUI = new HostPlayerGUI();
        HostPlayer player = new HostPlayer();

        File file = new File("../playlist.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> searchDis = new ArrayList<>();


        String st;
        while ((st = br.readLine()) != null) {
            String hmm = st;
            hmm = hmm.replace("&amp;", "&");
            hmm = hmm.replace("live", "");
            hmm = hmm.replace("Live", "");
            if (!hmm.equals(""))
                searchDis.add(hmm);
        }


        for(String str:searchDis) {
            try {
                String song = parser.parseSongInput(str + " audio");
//                System.out.println(str);
                getter.getAudio(song);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private void loadFiles() throws IOException, ClassNotFoundException {
        // going to store music in the folder on user's desktop, if folder doesn't exist, create it
        String folderPath = System.getProperty("user.home") + "/Desktop/tempMusic";
        songPath = folderPath;

        // CREATE A FOLDER STRUCTURE
//   /user/Desktop
//            |___tempMusic
//                   |___songA.mp3
//                   |___songB.mp3
//                   |___songC.mp3
//                   |___index
//                         |___localLibrary.dat
//                         |___playlist.dat


        if (!(new File(folderPath).exists()))
            new File(folderPath).mkdir();

        String index = folderPath + "/index";

        if (!(new File(index).exists()))
            new File(index).mkdir();


        localLibrary = new File(index + "/localLibrary.dat");
        playList = new File(index + "/playList.dat");


        //TODO VERIFY LOCAL LIBRARY
        //CREATE||READ locallibrary.dat
        if(!localLibrary.exists()) {
            localLibrary.createNewFile();
            HostOrganiser.localLibraryMap = new HashMap<>();

            FileOutputStream fos = new FileOutputStream(localLibrary);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(HostOrganiser.localLibraryMap);
            oos.close();

        } else {
            // if it already exists, pull the hashmap from it
            FileInputStream fis = new FileInputStream(localLibrary);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HostOrganiser.localLibraryMap = (HashMap<String,File>) ois.readObject();
            ois.close();

        }


        if (!playList.exists()) {
            playList.createNewFile();
            //TODO In case user deletes index file, check all other files in the directoy for mp3s and add them again
            HostOrganiser.playList = new ArrayList<>();

            FileOutputStream fos = new FileOutputStream(playList);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(HostOrganiser.playList);
            oos.close();

        } else {
            FileInputStream fis = new FileInputStream(playList);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HostOrganiser.playList = (ArrayList<String>) ois.readObject();
            ois.close();
        }

    }


    public static void verifyPlaylist() {
        ArrayList<String> songsToRemove = new ArrayList<>();



        for(String title : HostOrganiser.playList) {
            if(!HostOrganiser.localLibraryMap.containsKey(title))
                songsToRemove.add(title);
        }

        for(String song: songsToRemove)
            HostOrganiser.playList.remove(song);
    }

    public static void verifyLocalLibrary() {
        ArrayList<String> songsToRemove = new ArrayList<>();

        for (HashMap.Entry<String, File> entry : HostOrganiser.localLibraryMap.entrySet()) {
            String title = entry.getKey();
            File path = entry.getValue();

            if(!path.exists())
                songsToRemove.add(title);

        }

        for(String song: songsToRemove)
            HostOrganiser.localLibraryMap.remove(song);
    }

    public static void closeProgram() throws IOException {

        // write the local library to the file for future use
        FileOutputStream fos = new FileOutputStream(localLibrary);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(HostOrganiser.localLibraryMap);
        oos.close();


        // write playlist to file just in case accidental close
        fos = new FileOutputStream(playList);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(HostOrganiser.playList);
        oos.close();

    }

}
