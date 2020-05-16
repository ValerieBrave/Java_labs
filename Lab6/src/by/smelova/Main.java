package by.smelova;
import by.smelova.mcdonalds.Manager;
import by.smelova.mcdonalds.SalesDesk;
import by.smelova.mcdonalds.Visitor;
import by.smelova.shower.Bathroom;
import by.smelova.shower.Female;
import by.smelova.shower.Male;

public class Main {

    public static void main(String[] args) {
            Bathroom room = new Bathroom(2);
            Male m1 = new Male(1, room);
            Male m2 = new Male(2, room);
            Male m3 = new Male(3, room);
            Male m4 = new Male(4, room);
            Female f1 = new Female(1, room);
            Female f2 = new Female(2, room);
            Female f3 = new Female(3, room);
            Female f4 = new Female(4, room);
            m1.start();
            f1.start();
            f2.start();
            //m2.start();
            //m3.start();
            f3.start();
            m4.start();
            f4.start();
            //-------------------------------------------------------
        /*Manager manager = new Manager();
        SalesDesk desk1 = new SalesDesk(1,4, manager);
        SalesDesk desk2 = new SalesDesk(2,0, manager);

        manager.alldesks.add(desk1);
        manager.alldesks.add(desk2);

        Visitor vis1 = new Visitor(1, desk1);
        Visitor vis2 = new Visitor(2, desk1);
        Visitor vis3 = new Visitor(3, desk1);
        Visitor vis4 = new Visitor(4, desk1);

        vis4.start();
        vis1.start();
        vis2.start();
        vis3.start();*/
    }
}
