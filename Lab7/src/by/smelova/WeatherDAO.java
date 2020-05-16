package by.smelova;

import java.sql.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WeatherDAO {
    protected  Connection connection;

    public ArrayList<String> getAll() {
        ArrayList<String> res = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from WEATHER");
            while(rs.next()) {
                String line = rs.getString(1) + " " +
                        rs.getDate(2) + " temp: "+
                        rs.getInt(3) + "C prec: "+
                        rs.getInt(4) + "mm";
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

    public ArrayList<String> WeatherInRegion(String region) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            String sql = String.format("select Region, Date, Temperature, Precipitation from WEATHER where Region like '%s'", region);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                String line = rs.getString(1) + " " +
                        rs.getDate(2) + " temp: "+
                        rs.getInt(3) + "C prec: "+
                        rs.getInt(4) + "mm";
                res.add(line);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return res;
        }
    }

    public ArrayList<String> RainyColdRegion(String region, int temp) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            String sql = String.format("select Region, Date, Temperature, Precipitation from WEATHER where Region like '%s' and Temperature < %d", region, temp);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                String line = rs.getString(1) + " " +
                        rs.getDate(2) + " temp: "+
                        rs.getInt(3) + "C";
                res.add(line);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return  res;
        }
    }

    public WeatherDAO(Connection con) {
        this.connection = con;
    }
}
