package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.modelo.Categoria;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConnectionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class EjemploJDBCUpdate {

    public static void main(String[] args) {
        try (Connection connection = ConnectionBD.getConnection();) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl(connection);
            System.out.println("***** Listar ********");
            repositorio.listar().forEach(System.out::println);
            System.out.println("******Obtener por id******");
            repositorio.obtenerPorId(1);
            System.out.println("******Insertar nuevo producto******");
            Producto producto = new Producto();
            producto.setId(8);
            producto.setNombre("Chaqueta de jean");
            producto.setPrecio(8441685.6);
            producto.setFechaRegistro(LocalDate.now());
            Categoria categoria = new Categoria();
            categoria.setId(2);
            producto.setCategoria(categoria);
            repositorio.guardar(producto);
            System.out.println("Producto editado con exito");
            repositorio.listar().forEach(System.out::println);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
