package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class DatagramReceiver {
    public DatagramSocket socket;
    public byte[] message;
    public DatagramPacket to_receive;
    public InetAddress address;
    public int port;
    public DatagramReceiver(InetAddress a, int p) throws SocketException {
        socket = new DatagramSocket(p, a);
        message = new byte[2];
        to_receive = new DatagramPacket(message, 2);
        address = a;
        port = p;
    }
    public void Receive() throws IOException {
        socket.receive(to_receive);
        System.out.println(Arrays.toString(to_receive.getData()));
    }
}
