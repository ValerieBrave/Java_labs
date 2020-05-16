package by.smelova.mcdonalds;

import java.util.Comparator;

public class DesksComparator implements Comparator<SalesDesk> {

    @Override
    public int compare(SalesDesk o1, SalesDesk o2) {
        if(o1.queue_length > o2.queue_length) return 1;
        if(o1.queue_length < o2.queue_length) return -1;
        return 0;
    }
}
