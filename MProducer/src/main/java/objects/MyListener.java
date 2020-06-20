package objects;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {
    public void onMessage(Message m) {
        if (m instanceof TextMessage) {
            try {
                System.out.println("following message is received: " + m.getBody(String.class));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}