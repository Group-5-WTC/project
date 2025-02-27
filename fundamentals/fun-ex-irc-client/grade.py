import sys
import os
import subprocess
import time
import random
import socket
import threading

path_to_submission = os.path.normpath(sys.argv[1]) + "/"

listener_username = f"lmsl{random.randint(1, 100000)}"
sender_username = f"lmss{random.randint(1, 100000)}"
channel = f"#testing-{random.randint(1, 100000)}"
message = f"Hello, world! Here's a random number: {random.randint(1, 1000)}"

# simple mock server that listens for messages

class MockServer:
    def __init__(self, host, port, channel):
        self.host = host
        self.port = port
        self.channel = channel
        self.messages = []
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.bind((self.host, self.port))
        self.sock.listen(1)

    def listen(self):
        conn, addr = self.sock.accept()
        with conn:
            # print("Connected by", addr)
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                self.messages.append(data.decode("utf-8"))
                # conn.sendall(data)
        # print("Disconnected by", addr)

    def start_listen_async(self):
        self.thread = threading.Thread(target=self.listen)
        self.thread.start()

    def terminate_listen_async(self):
        self.sock.close()
        self.thread.join()

    def get_messages(self):
        return self.messages

mock_server = MockServer(
    "localhost",
    6667,
    channel
)


def start_listener(stdout):
    process = subprocess.Popen(
        ["python3", "irc.py",
            "listen", "localhost", "6667",
            channel, listener_username],
        cwd=path_to_submission,
        stdout=stdout,
        # stderr=subprocess.PIPE,
    )
    time.sleep(2)  # allow some time for the connection

    return process


def kill_listener(process):
    process.kill()
    process.wait()


def send_message(message):
    # python3 irc.py send 161.35.35.171 6667 '#programming' 'username'
    process = subprocess.Popen(
        ["python3", "irc.py",
            "send", "localhost", "6667",
            channel, sender_username],
        cwd=path_to_submission,
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    time.sleep(2)  # allow some time for the connection
    process.stdin.write(message.encode("utf-8"))
    process.stdin.write("\n".encode("utf-8"))
    process.stdin.flush()
    time.sleep(2)
    process.kill()
    process.wait()

mock_server.start_listen_async()
time.sleep(2) # allow some time for the connection

listener = start_listener(subprocess.PIPE)
send_message(message)
time.sleep(2)  # time allowed to receive the message
kill_listener(listener)

output = mock_server.get_messages()
mock_server.terminate_listen_async()

output = "\n".join(output)

if listener_username in output and channel in output:
    print("You were able to send and receive a message! Well done.")
    exit(0)
else:
    print("We weren't able to send and receive a message. Try again.")
    exit(1)
