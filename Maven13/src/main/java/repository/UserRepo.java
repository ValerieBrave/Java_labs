package repository;

import classes.FormService;
import classes.User;
import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRepo<User> implements IGenericRepository {
    private ConnectionPool connectionPool;
    private Connection connection;

    public UserRepo() {
        this.connectionPool = ConnectionPool.getInstance();
    }


    @Override
    public ArrayList<User> GetAll() throws SQLException {
        ArrayList<User> rc = new ArrayList<>();
        Statement st = null;
        try {
            this.connection = connectionPool.getConnection();
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT username, password, role FROM USERS");
            while(rs.next()) {
                FormService.AddUserToList((ArrayList<classes.User>) rc, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st.close();
            connectionPool.releaseConnection(this.connection);
        }
        return rc;
    }

    @Override
    public void Add(Object obj) throws SQLException {
        Statement st = null;
        try {
            this.connection = connectionPool.getConnection();
            st = connection.createStatement();
            st.execute("insert into USERS (username, password, role) values ('"+
                    ((classes.User)obj).username +"', "+
                    ((classes.User)obj).getPassword()+", "+"'user')");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st.close();
            connectionPool.releaseConnection(this.connection);
        }

    }

}
