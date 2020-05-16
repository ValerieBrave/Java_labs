package by.smelova;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DatagramSender {
    public DatagramSocket socket;
    public byte[] message;
    public DatagramPacket to_send;
    public InetAddress address;
    public int port;
    public DatagramSender(InetAddress a, int p) throws SocketException {
        address = a;
        port = p;
        message = new byte[] {0x12,0x13};
        socket = new DatagramSocket();
    }
    public void Send() throws IOException {
        to_send = new DatagramPacket(message, 2, address, port);
        socket.send(to_send);
    }
}
