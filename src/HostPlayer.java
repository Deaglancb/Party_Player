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

    private volatile boolean shouldBePlaying = false;


    private class Song extends Thread {

        private AdvancedPlayer player = null;
        boolean running = false;
        private int pausedOnFrame = 0;
        FileInputStream test;
        String filePath;

        Song(String src) throws JavaLayerException {
            filePath = src;
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


                while(shouldBePlaying) {
                    if (!running) {
                        try {
                            this.play();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                while(!shouldBePlaying) {
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
    }

    Thread currentSong;
    JButton play = new JButton("Play");
    JButton pause = new JButton("Pause");
    JButton next = new JButton("Next");
    JLabel songTitle = new JLabel("");


    JFrame frame = new JFrame("Party Player");

    HostPlayer() {
        buildGUI();
    }

    public void setMusic(String src) throws IOException, JavaLayerException {

        currentSong = new Song(src);//new Thread(new Song(src));
        songTitle.setText("Leonard Cohen");
        currentSong.start();

    }

    private void setButton() {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    playButtonPressed();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
                frame.revalidate();
                frame.repaint();
            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pauseButtonPressed();
                frame.repaint();
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextButtonPressed();

                frame.repaint();
            }
        });

    }
    public void buildGUI() {
        setButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        play.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        pause.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        next.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        frame.setMinimumSize(new Dimension(1500,800));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        gbc.gridx = 0;
        frame.add(play, gbc);
        gbc.gridx = 1;
        frame.add(next,gbc);

        frame.pack();
        frame.setVisible(true);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        songTitle.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        frame.add(songTitle,gbc);


    }

    private void playButtonPressed() throws JavaLayerException {

        shouldBePlaying = true;

        //GUI Stuff
        pause.setMaximumSize(play.getSize());
        frame.remove(play);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(pause,gbc);

    }

    private void pauseButtonPressed() {
        shouldBePlaying = false;


        //GUI Stuff
        play.setMaximumSize(pause.getSize());
        frame.remove(pause);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(play,gbc);
    }


    private void nextButtonPressed() {

    }
}
