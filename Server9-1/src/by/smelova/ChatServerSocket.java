package by.smelova;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
//extends ServerSocket
public class ChatServerSocket  {
    ServerSocketChannel serverChannel;
    ServerSocket server;
    ArrayList<Socket> clients_sockets;
    ArrayList<String> clients_names;
    ArrayList<DataInputStream> clients_messages;
    ArrayList<DataOutputStream> server_messages;
    public ChatServerSocket(int port) throws IOException {
        //super(port);
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress("127.0.0.1", port));
        server = serverChannel.socket();

        this.clients_sockets = new ArrayList<>();
        this.clients_names = new ArrayList<>();
        this.clients_messages = new ArrayList<>();
        this.server_messages = new ArrayList<>();
    }

    public void Start() throws IOException {

        try {
            OutputStream outstream = null;
            InputStream instream = null;
            DataInputStream dis = null;
            DataOutputStream dos = null;
            while (true) {

                //Socket got_socket = server.accept();      // пришел клиент
                SocketChannel got_socket = serverChannel.accept();
                if(got_socket != null) {
                    instream = got_socket.socket().getInputStream();
                    outstream = got_socket.socket().getOutputStream();
                    dis = new DataInputStream(instream);
                    dos = new DataOutputStream(outstream);

                    String client_name = dis.readUTF(); // узнали его имя



                    this.clients_sockets.add(got_socket.socket());   //зарегистрировали его сокет
                    this.clients_names.add(client_name);    //имя
                    this.clients_messages.add(dis);         //поток для чтения его сообщений
                    this.server_messages.add(dos);          //поток для отправки ему сообщений

                    for (DataOutputStream ou: this.server_messages      // сказали всем, что в чате новенький
                    ) {
                        ou.writeUTF(String.format("%s entered chat", client_name));
                    }

                    System.out.println(client_name);
                    dos.writeUTF("hello from chat server - you've just entered chat");
                }

                String left = "";
                int cl_mes = this.clients_messages.size();
                boolean spam = false;
                for (int i = 0; i < this.clients_messages.size(); i++) {
                    String an = this.clients_messages.get(i).readUTF();
                    System.out.println(an);
                    if(an.equals("exit")) {
                        System.out.println(this.clients_names.get(i) +" "+an);
                        left = this.clients_names.get(i);
                        spam = true;
                        this.server_messages.get(i).writeUTF("free");
                        this.clients_messages.remove(i);
                        this.server_messages.remove(i);
                        this.clients_sockets.get(i).close();
                        this.clients_sockets.remove(i);
                        this.clients_names.remove(i);
                    }
                }
                if(spam) {
                    for (DataOutputStream ou: this.server_messages      // сказали всем, кто ушел
                    ) {
                        ou.writeUTF(String.format("%s left chat", left));
                    }
                }

//
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
