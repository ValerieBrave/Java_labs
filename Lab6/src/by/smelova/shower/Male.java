package by.smelova.shower;

import by.smelova.shower.Bathroom;
import by.smelova.shower.IGuest;

import java.util.Random;

public class Male extends Thread implements IGuest {
    public int id;
    public Bathroom room;
    public Male(int id, Bathroom b) {
        this.id = id;
        this.room = b;
    }
    public String Gender() {
        return "m";
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
                room.male_allowed = true;
                room.acquire((IGuest) this);
                room.release();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
