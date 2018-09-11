
import socket

# test server socket

HOST = 'localhost'
PORT = 6666

server_socket = socket.socket()
server_socket.bind((HOST, PORT))
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

server_socket.listen(5)

client_sock, addr = server_socket.accept()

while True:
    msg = client_sock.recv(1024).decode()
    if msg:
        print(msg)
    else:
        print("No msg received... quitting")
        break

