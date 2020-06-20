package producers;

import javax.jms.*;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import objects.User;

public class DirectMessageSender {
    public static void main(String[] args) {
        ConnectionFactory factory;
        factory = new ConnectionFactory();
        try {
            JMSContext context = factory.createContext("admin","admin");
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination usersQueue = context.createQueue("UsersQueue");
            JMSProducer producer = context.createProducer();
            User Vasya = new User("Vasya", "guest", 123);
            ObjectMessage om = context.createObjectMessage(Vasya);
            producer.send(usersQueue, om);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Delivered message to OpenMQ server");
    }
}
