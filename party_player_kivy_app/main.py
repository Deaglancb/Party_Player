
import kivy
from kivy.app import App
from kivy.uix.widget import Widget
from kivy.properties import ObjectProperty

import socket
import ipaddress


HOST = "localhost"
PORT = 6666


class RootWidget(Widget):

    url_input = ObjectProperty(None)
    list_label = ObjectProperty(None)

    def __init__(self):
        super(RootWidget, self).__init__()

        self.bcast_addr = self.get_broadcast_addr()

        if self.bcast_addr:
            try:
                sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
                sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
                sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
                print("Broadcast socket created...")

                sock.bind((HOST, PORT))
                print("Socket bound...")

            except socket.error as e:
                print(e)

        self.client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        try:
            self.client_socket.connect((HOST, PORT))
        except socket.error as e:
            print("[!!] Error: ", e)

    def add_song(self):
        self.list_label.text += '\n'+self.url_input.text
        msg = self.url_input.text + '\n'
        binary = msg.encode()
        try:
            self.client_socket.send(binary)
        except socket.error as e:
            print(e)

    def get_broadcast_addr(self):
        local_ip = self.get_local_ip()
        addr = ipaddress.ip_interface(local_ip + "\24")
        return addr.network.broadcast_address

    def get_local_ip(self):
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # udp
        s.connect(("8.8.8.8", 80))  # connects to Google
        ip = s.getsockname()[0]
        # print("IP ", ip)
        s.close()
        return ip

    # def find_server(self):



class PartyApp(App):

    def build(self):
        return RootWidget()


if __name__ == '__main__':
    PartyApp().run()
