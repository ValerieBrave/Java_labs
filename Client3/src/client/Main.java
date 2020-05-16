package client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            ChatClient cc = new ChatClient("127.0.0.1", 8030);
            cc.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
