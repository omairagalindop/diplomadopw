package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConnectionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJDBCCategoria {

    public static void main(String[] args) {
        try (Connection connection = ConnectionBD.getConnection();) {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                Repositorio<Producto> repositorio = new ProductoRepositorioImpl(connection);
                System.out.println("***** Listar ********");
                repositorio.listar().forEach(System.out::println);
                System.out.println("******Obtener por id******");
                repositorio.obtenerPorId(1);
                repositorio.listar().forEach(System.out::println);
                connection.commit();
            } catch (SQLException sqlException) {
                connection.rollback();
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
