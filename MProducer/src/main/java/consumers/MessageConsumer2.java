package consumers;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class MessageConsumer2 implements MessageListener {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;
    MessageConsumer2()  {
        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.CLIENT_ACKNOWLEDGE)) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            // for pub-sub
            Destination mytopic = context.createTopic("MyTopic");
            String selector = "symbol=BSTU";
            consumer = context.createConsumer(mytopic, selector);
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
            //for pub-sub
            System.out.println("Got the object message from MyTopic: " + message.getBody(String.class));
            message.acknowledge();
        } catch (JMSException e) {
            System.err.println("JMSException: " + e.toString());
        }
    }

    public static void main(String[] args) {
        new MessageConsumer2();
    }
}
