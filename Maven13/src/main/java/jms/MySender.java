package jms;

import com.sun.messaging.QueueConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MySender {
    QueueConnection con;
    public void Send(String user) throws JMSException {
        try {
            InitialContext ctx=new InitialContext();
            QueueConnectionFactory f= (QueueConnectionFactory)ctx.lookup("java:comp/DefaultJMSConnectionFactory ");
            con=f.createQueueConnection(); con.start(); //2) создать queue session
            QueueSession session= con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE); //3) получить Queue object
            Queue queue=(Queue)ctx.lookup("UsersQueue");
            QueueSender sender=session.createSender(queue);
            TextMessage outMsg = session.createTextMessage();
            outMsg.setText(user + "logged in");
            sender.send(outMsg);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
    }
}
