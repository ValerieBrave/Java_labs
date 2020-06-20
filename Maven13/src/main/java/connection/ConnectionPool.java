package connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final String PROPERTY_PATH = "db";
    private static final int INITIAL_CAPACITY = 10;
    private ArrayBlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private ArrayBlockingQueue<Connection> releaseConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private static ReentrantLock lock = new ReentrantLock();
    private volatile static ConnectionPool connectionPool;
    public static ConnectionPool getInstance() {
        try {
            lock.lock();
            if (connectionPool == null) {
                connectionPool = new ConnectionPool();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can not get Instance", e);
        } finally {
            lock.unlock();
        }
        return connectionPool;
    }
    private void init() {
        Properties properties = new Properties();
        ResourceBundle resource = ResourceBundle.getBundle(PROPERTY_PATH, Locale.getDefault());
        if (resource == null) {
            throw new RuntimeException("Error while reading resources file");
        } else {
            String connectionURL = resource.getString("db.url");
            String initialCapacityString = resource.getString("db.poolsize");
            Integer initialCapacity = Integer.valueOf(initialCapacityString);
            for (int i = 0; i < initialCapacity; i++) {
                try {
                    Connection connection = DriverManager.getConnection(connectionURL);
                    freeConnections.add(connection);
                } catch (SQLException e) {
                    throw new RuntimeException("Pool can not initialize", e);
                }
            }
        }
    }
    private ConnectionPool() throws SQLException {
        try {
            lock.lock();
            if (connectionPool != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new org.sqlite.JDBC());
                init();
            }
        } finally {
            lock.unlock();
        }
    }
    public Connection getConnection() {
        try {
            Connection connection = freeConnections.take();
            releaseConnections.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException("Can not get database", e);
        }
    }
    public void releaseConnection(Connection connection) {
        releaseConnections.remove(connection);
        freeConnections.offer(connection);
    }
    public void destroy() {
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                Connection connection = (Connection) freeConnections.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException("database is not closed", e);
            }
        }
        try {
            Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                java.sql.Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Drivers were not deregistrated", e);
        }
    }
}

