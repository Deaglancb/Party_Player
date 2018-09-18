/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */


import java.io.IOException;

// This class will hopefully interact directly with the app
public class HostCatcher {



    public void getUserInput(String userInput /*somehow get user message*/) throws IOException, InterruptedException {



        HostParser.parseSongInput(userInput);

    }
}
