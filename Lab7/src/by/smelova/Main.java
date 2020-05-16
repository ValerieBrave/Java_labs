package by.smelova;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO();
        //ArrayList<String> test = dao.Weather.RainyColdRegion("Wales", 0);
        //dao.AddResident("Swiss", "Swiss-german");
        ArrayList<String> test = dao.Residents.getAll();
        for (String l: test
             ) {
            System.out.println(l);
        }
    }

}
