package classes;

public class Wine {
    public String Wine;
    public int Year;
    public String Type;

    public Wine(String wine, int year, String type) {
        Wine = wine;
        Year = year;
        Type = type;
    }

    public Wine() {
        Wine = "";
        Year = 2020;
        Type ="";
    }
}
