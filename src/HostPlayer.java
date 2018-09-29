/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */

import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class HostPlayer{

    protected static volatile boolean shouldBePlaying = false;
    static Thread currentSong;


    private static class Song extends Thread {

        private AdvancedPlayer player = null;
        boolean running = false;
        private int pausedOnFrame = 0;
        FileInputStream test;
        String filePath;

        Song(String src) throws JavaLayerException {
            filePath = src;
            System.out.println(src);
            FactoryRegistry.systemRegistry().createAudioDevice();
        }

        public void play() throws JavaLayerException, IOException {

            test = new FileInputStream(new File(filePath));

            if (player != null)
                test.skip(test.available() - pausedOnFrame);

            player = new AdvancedPlayer(test);
            running = true;
            new Thread() {
                @Override
                public void run(){
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }

        public void pause() throws IOException {
            running = false;
            pausedOnFrame = test.available();
            player.close();
        }

        @Override
        public void run() {
            while(true) {
                while(shouldBePlaying)
                    if (!running) {
                        try {
                            this.play();
                        } catch (JavaLayerException | IOException e) {
                            e.printStackTrace();
                        }
                    }

                while(!shouldBePlaying)
                    if (running){
                        try {
                            this.pause();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
    }

    HostPlayer() { }

    public static void setMusic(String src) throws JavaLayerException {

        System.out.println(src);
        currentSong = new Thread(new Song(src));
        HostPlayerGUI.setTitle("Leonard Cohen");
        currentSong.start();

    }



}
