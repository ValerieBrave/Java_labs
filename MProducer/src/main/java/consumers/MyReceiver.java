package consumers;

import com.sun.messaging.QueueConnectionFactory;
import objects.MyListener;

import javax.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import java.util.Properties;

public class MyReceiver {
    public static void main(String[] args) {

        try {
            Context ctx=new InitialContext();

            QueueConnectionFactory f= (QueueConnectionFactory)ctx.lookup("java:comp/DefaultJMSConnectionFactory");
            QueueConnection con=f.createQueueConnection();
            con.start();
            QueueSession session=con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue=(Queue)ctx.lookup("UsersQueue");
            QueueReceiver receiver=session.createReceiver(queue);
            MyListener listener =new MyListener();
            receiver.setMessageListener(listener);
            System.out.println("Waiting for messages...");
            while(true){
                Thread.sleep(1000);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
