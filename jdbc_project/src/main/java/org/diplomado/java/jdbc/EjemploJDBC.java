package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.modelo.Categoria;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConnectionBD;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EjemploJDBC {
    public static void main(String[] args) {
//
////        String url = "jdbc:mariadb://localhost:3306/diplomado_javaee";
////        String user = "root";
////        String password = "";
//        try (Connection connection = ConnectionBD.getInstance();
//             Statement stmnt = connection.createStatement();
//             ResultSet resultSet = stmnt.executeQuery("SELECT * FROM producto");) {
//            stmnt.executeQuery("INSERT INTO curso_java.producto (nombre, precio, fecha_registro)\n" +
//                    "VALUES ('Zapatos', 1465160, '2022-06-02 21:29:21')");
//
//            stmnt.executeQuery("DELETE\n" +
//                    "FROM curso_java.producto\n" +
//                    "WHERE nombre LIKE '%Medias%'");
//            while (resultSet.next()) {
//                System.out.print(resultSet.getInt("id"));
//                System.out.print("|");
//                System.out.print(resultSet.getString("nombre"));
//                System.out.print("|");
//                System.out.print(resultSet.getDouble("precio"));
//                System.out.print("|");
//                System.out.println(resultSet.getDate("fecha_registro"));
//            }
//            ResultSet resultSet3 = stmnt.executeQuery("SELECT * FROM producto");
//            while (resultSet3.next()) {
//                System.out.print(resultSet3.getInt("id"));
//                System.out.print("|");
//                System.out.print(resultSet3.getString("nombre"));
//                System.out.print("|");
//                System.out.print(resultSet3.getDouble("precio"));
//                System.out.print("|");
//                System.out.println(resultSet3.getDate("fecha_registro"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e.getMessage());
//        }
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
//            System.out.println("******Insertar nuevo producto******");
//            Producto producto = new Producto();
//            producto.setNombre("Chaqueta");
//            producto.setPrecio(15640145.0);
//            producto.setFechaRegistro(LocalDate.now());
//            Categoria categoria = new Categoria();
//            categoria.setId(1);
//            producto.setCategoria(categoria);
//            repositorio.guardar(producto);
//            System.out.println("Producto agregado con exito");
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
