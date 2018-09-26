import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
 * Deaglan Connolly Bree, 15511107
 * deaglan.connolly-bree@ucdconnect.ie
 */
public class HostPlayerGUI {

    static JFrame frame = new JFrame("Party Player");
    static JButton play = new JButton("Play");
    static JButton pause = new JButton("Pause");
    static JButton next = new JButton("Next");
    static JLabel songTitle = new JLabel("");
    static JTextField libraryPath = new JTextField();
    static JButton setPath = new JButton("Set");

    public HostPlayerGUI() {
        buildGUI();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    HostInitialize.closeProgram();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void setTitle(String title) {
        songTitle.setText(title);
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


        gbc.gridx = 0;
        gbc.gridy = 6;
        libraryPath.setVisible(true);
//        libraryPath.setBounds(50,150,200,30);
        frame.add(libraryPath,gbc);
        gbc.gridx = 3;
        setPath.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        setPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setLibraryPath();
            }
        });
        frame.add(setPath,gbc);


    }

    private void setLibraryPath() { HostOrganiser.importLibrary(libraryPath.getText()); }

    private void playButtonPressed() throws JavaLayerException {

        HostPlayer.shouldBePlaying = true;

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
        HostPlayer.shouldBePlaying = false;


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
