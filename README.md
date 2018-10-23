# Party_Player


Beginnings of distributed playlist & music player

Very much so a work in progress in an effort to make a proof of concept

Intention:
           
           users using an app on their phone can link to a host computer the program is running on, 
           input a song title, or youtube URL, the program will download the song & convert to mp3,
           and add to playlist. In future preferably users can have power levels to remove others' 
           songs or add a song to play next rather than putting it at the end of playlist.
           
Thus far:  
                  
           Youtube downloader works using python script with pytube (via HostGetter), this downloads the video from a given URL, and converts it to an mp3, and stores it in the locallibrary database.
 
           HostPlayerGUI will be the GUI for the host, users can currently input a specific path and any mp3s in that path will be added to the librarylibrary.dat (initialize verifies this playlist (or creates it if non existant), on startup).
 
           HostParser allows you to input a song title/ artist and will get the top result from a respective youtube search, this can be passed along to HostGetter.
 
           HostPlayer largely deals with playing mp3s with jzoom right now, nothing here is automated, you can player.setMusic(somePath) and the song will play, pausable & playable with the GUI.



Reliant on 

JZoom, Java Layer 1.0.1
http://www.javazoom.net/javalayer/javalayer.html


Python3
https://www.python.org/

Pytube
https://python-pytube.readthedocs.io/en/latest/#
