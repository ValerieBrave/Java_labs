package by.smelova;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    Connection connection;
    public WeatherDAO Weather;
    public RegionsDAO Regions;
    public ResidentsDAO Residents;
    public DAO()  {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection ("jdbc:sqlserver://DESKTOP-K6FD3O4\\SQLEXPRESS;databaseName=Weather;", "svv", "svv");
            Weather = new WeatherDAO(connection);
            Regions = new RegionsDAO(connection);
            Residents = new ResidentsDAO(connection);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("DAO constructor failure");
            e.printStackTrace();
        }

    }

    public ArrayList<String> WeatherByLanguage(String lang) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            String prepSQL = "select w.Region, Language, Date, Temperature, Precipitation " +
                    "from REGIONS inner join RESIDENTS " +
                    "on REGIONS.Residents like RESIDENTS.Name " +
                    "inner join WEATHER w " +
                    "on w.Region like REGIONS.Region " +
                    "where Language like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(prepSQL);
            preparedStatement.setString(1, lang);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String line = rs.getString(1) + ", " + rs.getString(2) + " language, " +
                        rs.getDate(3) + " temp: "+
                        rs.getInt(4) + "C prec: "+
                        rs.getInt(5) + "mm";
                res.add(line);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return res;
        }

    }

    public ArrayList<String> AvgTempBySquare(int sq) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            String prepSQL = "select w.Region, Square, avg(w.Temperature) " +
                    "from WEATHER w inner join REGIONS " +
                    "on w.Region like REGIONS.Region " +
                    "where REGIONS.Square > ? " +
                    "group by w.Region, Square ";
            PreparedStatement preparedStatement = connection.prepareStatement(prepSQL);
            preparedStatement.setInt(1, sq);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                String line = rs.getString(1) + ", " +
                        rs.getInt(2) + " km^2, " +
                        rs.getInt(3) + "C";
                res.add(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }

    public void AddResident(String nam, String lang) throws SQLException {
        Savepoint savepoint = null;
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("svpnt");
            String prepSQL = "insert into RESIDENTS(Name, Language) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(prepSQL);
            preparedStatement.setString(1, nam);
            preparedStatement.setString(2, lang);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(savepoint);
        }
    }

    public void CloseDAO() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("DAO connection close failure");
            e.printStackTrace();
        }
    }
}