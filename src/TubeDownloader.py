#!/usr/bin/python3 

from pytube import YouTube
import sys

def download(URL, path):
	yt = YouTube("%s" % URL)
	yt.streams.filter(only_audio=True).first().download("%s" % path, yt.title)
	print(yt.title)


if __name__ == "__main__":
	download(sys.argv[1], sys.argv[2])

