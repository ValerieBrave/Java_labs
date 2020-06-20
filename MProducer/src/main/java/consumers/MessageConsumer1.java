package consumers;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import objects.User;


import javax.jms.*;
import javax.jms.MessageListener;

public class MessageConsumer1 implements MessageListener {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    MessageConsumer1()  {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");

            // for p2p
//            Destination usersQueue = context.createQueue("UsersQueue");
//            consumer = context.createConsumer(usersQueue);
//            consumer.setMessageListener(this);
//            System.out.println("Listening to Queue...");

            // for pub-sub
            Destination mytopic = context.createTopic("MyTopic");

            consumer = context.createConsumer(mytopic);
            consumer.setMessageListener(this);
            System.out.println("Listening to Topic...");

            Thread.sleep(100000);
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onMessage(Message message) {
        try {
            //for p2p
//            System.out.println("Got the object message from the UsersQueue: " + message.getBody(User.class));
//            System.out.println("\n = Here's what toString() on the message prints \n" + message);
            //for pub-sub
            System.out.println("Got the object message from MyTopic: " + message.getBody(String.class));
        } catch (JMSException e) {
            System.err.println("JMSException: " + e.toString());
        }
    }

    public static void main(String[] args) {
        new MessageConsumer1();
    }
}
