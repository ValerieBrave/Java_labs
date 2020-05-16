package client;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            ChatClient cc = new ChatClient("127.0.0.1", 8030);
            cc.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
