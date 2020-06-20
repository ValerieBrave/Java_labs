package classes;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormService {
    Connection cnn;
    public static ArrayList<User> users = new ArrayList<>();
    public ArrayList<User> GetAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> rc = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        cnn = DriverManager.getConnection("jdbc:sqlite:D://JavaLabs/Servlets10/users.sqlite3");
        Statement st = cnn.createStatement();
        ResultSet rs = st.executeQuery("SELECT username, password, role FROM USERS");
        while(rs.next()) {
            User us = new User();
            us.username = rs.getString(1);
            us.role = rs.getString(3);
            us.setPassword(rs.getInt(2));
            rc.add(us);
        }
        st.close();
        cnn.close();
        return rc;
    }
    public FormService() throws SQLException, ClassNotFoundException {
        this.users = this.GetAllUsers();
    }
    public boolean isRegistrated(User us) {
        boolean rc = false;
        for (User u: this.users
             ) {
            if(us.username.equals(u.username) && us.getPassword()==u.getPassword()) rc = true;
        }
        return rc;
    }
    public boolean isNameTaken(String username) {
        boolean rc= false;
        for (User u: this.users
             ) {
            if(username.equals(u.username)) rc = true;
        }
        return rc;
    }
    public void Register(User u) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        cnn = DriverManager.getConnection("jdbc:sqlite:D://JavaLabs/Servlets10/users.sqlite3");
        Statement st = cnn.createStatement();
        st.execute("insert into USERS (username, password, role) values ('"+u.username +"', "+u.getPassword()+", "+"'user')");
        st.close();
        cnn.close();
        u.role = "user";
        users.add(u);
    }
    public int HashPassword(String pass) {
        int hash = 2139062143;
        char[] pass_chars = pass.toCharArray();
        for (char c: pass_chars
             ) {
            hash = 37*hash + c;
        }
        return hash;
    }
}
