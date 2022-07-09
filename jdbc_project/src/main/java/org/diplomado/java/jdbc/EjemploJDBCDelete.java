package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConnectionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class EjemploJDBCDelete {

    public static void main(String[] args) {
        try (Connection connection = ConnectionBD.getConnection();) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl(connection);
            System.out.println("***** Listar ********");
            repositorio.listar().forEach(System.out::println);
            System.out.println("******Obtener por id******");
            repositorio.obtenerPorId(1);
            System.out.println("******Eliminar producto******");
            repositorio.eliminar(5);
            System.out.println("Producto eliminado con exito");
            repositorio.listar().forEach(System.out::println);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
