package by.smelova;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegionsDAO {
    protected Connection connection;

    public ArrayList<String> getAll() {
        ArrayList<String> res = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from REGIONS");
                    while(rs.next()) {
                String line = rs.getString(1) + ", " +
                                rs.getInt(2) + "thousand km^2, " +
                                rs.getString(3) + " people";
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

    public RegionsDAO(Connection con) {
        this.connection = con;
    }
}
