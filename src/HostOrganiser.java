import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */
public class HostOrganiser {


    protected static ArrayList<String> playList;
    protected static HashMap<String, File> localLibraryMap;


    public HostOrganiser() throws IOException, ClassNotFoundException {


    }


    public void addToPlayList(String songTitle, File songPath) {

        // if playlist doesn't alreayd contain the song, add to end
        if(!playList.contains(songTitle))
            playList.add(songTitle);

        //TODO add in responce to app, 'this is already on playlist!'



    }

    public static void importLibrary(String libraryPath) {

        ArrayList<File> files = new ArrayList<>();
        getFiles(libraryPath, files);

        for(File aFile : files) {
            String name = aFile.getName().substring(0,aFile.getName().length()-4);
            if (!localLibraryMap.containsKey(name)) {
                localLibraryMap.put(name, aFile);
            }
        }
    }


    private static void getFiles(String libraryPath, ArrayList<File> files) {
        File directory = new File(libraryPath);
        // Get all the files from a directory.
        File[] fList = directory.listFiles();

        if(fList != null){
            for (File file : fList) {
                if (file.isFile()) {
                    if(file.getName().substring(file.getName().length()-4).equals(".mp3"))
                        files.add(file);
                } else if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                }
            }
        }
    }

}
