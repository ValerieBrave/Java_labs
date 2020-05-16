package by.smelova.mcdonalds;

public class Visitor extends Thread {
    public int visitor_id;
    public SalesDesk desk;

    public Visitor(int id, SalesDesk desk) {
        this.visitor_id = id;
        this.desk = desk;
    }

    @Override
    public void run() {

        try {
                if(!desk.is_free) {
                    do {
                        this.sleep(3000);
                        this.desk.manager.Spread(this);
                    } while(!desk.is_free);
                }
                desk.GiveService(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
