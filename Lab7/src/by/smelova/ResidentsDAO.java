package by.smelova;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ResidentsDAO {
    protected Connection connection;

    public ArrayList<String> getAll() {
        ArrayList<String> res = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from RESIDENTS");
            while(rs.next()) {
                String line = rs.getString(1) + " speak " + rs.getString(2);
                res.add(line);
            }
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return res;
        }
    }

    public ResidentsDAO(Connection con) {
        this.connection = con;
    }
}
