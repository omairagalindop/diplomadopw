package org.diplomado.java.jdbc.util;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static String url = "jdbc:mariadb://localhost:3306/curso_java";
    private static String user = "root";
    private static String password = "";
    private static Connection connection;
    private static BasicDataSource pool;

//    public static Connection getInstance() throws SQLException {
//        if (connection == null) {
//            connection = DriverManager.getConnection(url, user, password);
//        }
//        return connection;
//    }

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
//            pool.setMaxTotal(8);
            connection = DriverManager.getConnection(url, user, password);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
