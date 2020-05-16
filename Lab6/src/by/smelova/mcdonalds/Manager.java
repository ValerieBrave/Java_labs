package by.smelova.mcdonalds;

import java.util.*;

public class Manager {
    public ArrayList<SalesDesk> alldesks = new ArrayList<SalesDesk>();

    public  void Spread(Visitor v) {
        int desk_ind = v.desk.desk_id;
        SalesDesk optional = Collections.min(alldesks, new DesksComparator());
        desk_ind = optional.desk_id;
        if(desk_ind != v.desk.desk_id) {
            Collections.min(alldesks, new DesksComparator()).queue_length++;
            v.desk.queue_length--;
            v.desk = optional;
        }

    }
}
