package by.smelova.shower;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bathroom extends Semaphore {

    volatile int counter; // - сколько уже зашло
    int capacity;
    volatile boolean male_allowed = false; // - кому можно заходить
    public Bathroom(int permits) {
        super(permits);
        this.capacity = permits;
    }

    public boolean checkRoom(String gen) {
        if(this.counter == 0) return true;
        if(gen.equals("f") && !this.male_allowed)  return true;
        else if(gen.equals("f") && this.male_allowed && this.counter != 0) return false;
        else if(gen.equals("m") && this.male_allowed) return  true;
        else  return false;
    }

    public  void acquire(Male guest) throws InterruptedException {
        Object key1 = new Object();
        Object key2 = new Object();
        Random r = new Random();
        synchronized (key1) {System.out.println(String.format("%s guest %d goes in", guest.Gender(), guest.id)); }
        guest.sleep(r.nextInt(1000)+7000);
        synchronized (key2) {System.out.println(String.format("m guest %d goes out", guest.id));}
    }

    public  void acquire(Female guest) throws InterruptedException {
        Random r = new Random();
            Object key1 = new Object();
        Object key2 = new Object();
            synchronized (key1) {System.out.println(String.format("%s guest %d goes in", guest.Gender(), guest.id)); }
            guest.sleep(r.nextInt(1000)+7000);
            synchronized (key2) {System.out.println(String.format("f guest %d goes out", guest.id));}
    }

    public  void acquire(IGuest guest) throws InterruptedException {
            super.acquire();
        this.counter++;
            if(guest.Gender().equals("f")) {
                this.acquire((Female)guest);
            }
            else {
                this.acquire((Male)guest);
            }
        this.counter--;
    }
}
