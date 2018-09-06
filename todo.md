Host Client Player___ (Option 1)
1. Play dynamic list of mp3s


Host Client Sorter__ (Option 1)
1. Sort playlist dynamically, based on meta info


Host Client Catcher___ (Option 1)        **Major Changed could improve overhead here**
1. Take in Youtube URL

~~2. Stream using VLC, find stream URL from codec information (labeled location)   <-- alternatively figure out how to get this URL manually~~
~~3. Download video~~
2.1 Pass URL to python script, download with pytube
4. Convert video to mp3 using ffmpeg, delete video
5. Save & Index audio


Host Client Parser___ (Option 1)
1. Take info from User Clients
2. If URL, pass on to Host Client Catcher, skip 3
3. If just name, search for youtube, select top relevant, Go to step 2.
4. Pass meta info about user to Host Client Sorter




User Clients___ (option 1)
1. Basic interface, either type in song name or input youtube URL
    At some point a UI to decide a particular video is preferred, youtube in a frame in the app or something to this effect.
2. Depending on user permissions ('power level' ?), allow to:
      add to playlist
      play next
      delete song from playlist - at the very least the songs they've added
      skip song
      enable/disable others view of full playlist
      
