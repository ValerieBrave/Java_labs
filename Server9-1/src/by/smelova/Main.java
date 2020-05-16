package by.smelova;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        //---------------№1
	    //PageContent getter = new PageContent("https://www.tut.by/");
        //getter.GetContent("tutby.txt");
        //---------------№2
        try {
            ChatServerSocket server = new ChatServerSocket(8030);
            server.Start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //---------------№3
//        DatagramSender sender = new DatagramSender(InetAddress.getByName("localhost"), 5002);
//        sender.Send();
    }
}
