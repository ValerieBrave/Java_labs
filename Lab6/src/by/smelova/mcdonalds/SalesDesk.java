package by.smelova.mcdonalds;

import java.util.Comparator;
import java.util.Random;

public class SalesDesk {
    public int desk_id;
    public boolean is_free = true;
    public int queue_length;
    public Manager manager;

    public SalesDesk(int desk_id, int q, Manager m) {
        this.desk_id = desk_id;
        this.queue_length = q;
        this.manager = m;
    }

    public void GiveService(Visitor visitor) throws InterruptedException {
        this.is_free = false;
        Object key = new Object();
        Random r = new Random();
        synchronized (key) {System.out.println(String.format("desk%d services visitor%d", this.desk_id, visitor.visitor_id));}
        visitor.sleep(r.nextInt(2000)+5000);
        synchronized (key) {System.out.println(String.format("visitor%d leaves desk%d", visitor.visitor_id, this.desk_id));}
        this.is_free = true;
        this.queue_length--;
    }


}
