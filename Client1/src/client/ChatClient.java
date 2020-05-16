package client;

import java.io.*;
import java.net.Socket;

public class ChatClient extends Thread {
    public Socket socketClient;
    OutputStream outstream = null;
    InputStream instream = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    public ChatClient(String host, int port) throws IOException {
        socketClient = new Socket(host, port);  //8030
        outstream = socketClient.getOutputStream();
        dos = new DataOutputStream(outstream);
        instream = socketClient.getInputStream();
        dis = new DataInputStream(instream);
    }

    public void run() {
        try {
            dos.writeUTF("client1");
            String answ = dis.readUTF();
            System.out.println(answ);
            this.sleep(10000);
            dos.writeUTF("client1 writes to public chat");
            dos.writeUTF("exit");
            String answ2 = "";
            while(!answ2.equals("free")) {
                answ2 = dis.readUTF();
                System.out.println(answ2);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
