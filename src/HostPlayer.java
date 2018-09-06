/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */

import javax.swing.*;

import sun.audio.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HostPlayer {

    AudioStream stream;
    JButton play = new JButton("Play");
    JButton pause = new JButton("Pause");
    JButton next = new JButton("Next");


    JFrame frame = new JFrame("Party Player");

    HostPlayer() {
        buildGUI();
    }

    private void setButton() {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                playButtonPressed();
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
    }

    private void playButtonPressed() {
        frame.remove(play);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        gbc.gridx = 0;
        frame.add(pause,gbc);

    }
    private void pauseButtonPressed() {

        frame.remove(pause);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        gbc.gridx = 0;
        frame.add(play,gbc);
    }
    private void nextButtonPressed() {

    }
}
