package org.diplomado.apiservlet.webapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://127.0.0.1:3306/java_curso";
    private static String username = "root";
    private static String password = "Asdf1234$";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
