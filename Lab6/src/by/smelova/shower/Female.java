package by.smelova.shower;


import java.util.Random;

public class Female extends Thread  implements IGuest {
    public int id;
    public Bathroom room;
    public Female(int id, Bathroom b) {
        this.id = id;
        this.room = b;
    }
    public String Gender() {
        return "f";
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            if(!room.checkRoom(this.Gender())) {
                do {
                    this.sleep(3000);
                } while (!room.checkRoom(this.Gender()));

            }
            room.male_allowed = false;
                room.acquire((IGuest) this);
                room.release();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
