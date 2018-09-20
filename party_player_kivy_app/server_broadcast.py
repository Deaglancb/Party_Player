import socket
import ipaddress


def get_local_ip():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # udp
    s.connect(("8.8.8.8", 80))  # connects to Google
    ip = s.getsockname()[0]
    print("IP ", ip)
    s.close()
    return ip


if __name__ == "__main__":
    local_ip = get_local_ip()
    addr = ipaddress.ip_interface(local_ip + "/24")
    bcast_addr = addr.network.broadcast_address

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind((bcast_addr, 6666))
    server_socket.listen(5)

    client_sock, addr = server_socket.accept()

    server_socket.sendto(local_ip.encode(), addr)

    server_socket.close()
