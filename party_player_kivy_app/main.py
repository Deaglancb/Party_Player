
import kivy
from kivy.app import App
from kivy.uix.widget import Widget
from kivy.properties import ObjectProperty

import socket
import sys

HOST = "localhost"
PORT = 6666


class RootWidget(Widget):

    url_input = ObjectProperty(None)
    list_label = ObjectProperty(None)

    def __init__(self):
        super(RootWidget, self).__init__()
        self.client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        try:
            self.client_socket.connect((HOST, PORT))
        except socket.error as e:
            print("[!!] Error: ", e)

    def add_song(self):
        self.list_label.text += '\n'+self.url_input.text
        binary = self.url_input.text.encode()
        try:
            self.client_socket.send(binary)
        except socket.error as e:
            print(e)


class PartyApp(App):

    def build(self):
        return RootWidget()


if __name__ == '__main__':
    PartyApp().run()
