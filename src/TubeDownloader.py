from pytube import YouTube
import sys

def download(URL, path):
	YouTube("%s" % URL).streams.filter(only_audio=True).first().download("%s" % path)


if __name__ == "__main__":
	download(sys.argv[1], sys.argv[2])

