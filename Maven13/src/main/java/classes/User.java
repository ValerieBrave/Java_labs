package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    public String username;
    public String role;
    private int password;

    public User(String username, String role, int password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public User() {
        username = "";
        role = "";
        password = 0;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


}
