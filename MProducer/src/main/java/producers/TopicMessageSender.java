package producers;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class TopicMessageSender {
    public static void main(String[] args) {
        ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination Topic = context.createTopic("MyTopic");
            JMSProducer producer = context.createProducer();
            TextMessage outMsg = context.createTextMessage(); outMsg.setText("Message that only consumer1 will receive");
            outMsg.setStringProperty("symbol", "BSTU");
            producer.setPriority(9).send(Topic, "This is priority 9 package");
            producer.setPriority(0).send(Topic, "This is priority 0 package");
            producer.send(Topic, outMsg);
            System.out.println("Delivered messages to MyTopic");
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
