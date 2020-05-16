package client;

import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        try {
            ChatClient cc = new ChatClient("127.0.0.1", 8030);
            cc.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        DatagramReceiver receiver = new DatagramReceiver(InetAddress.getByName("localhost"), 5002);
//        receiver.Receive();
    }
}
