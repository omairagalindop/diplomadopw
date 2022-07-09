package org.diplomado.java.jdbc.repositorio;

import org.diplomado.java.jdbc.modelo.Categoria;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.util.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection connection;

    private Connection getConnection() throws SQLException {
        return ConnectionBD.getConnection();
    }

    public ProductoRepositorioImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> listar() throws SQLException{
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();) {
//            ResultSet resultSet = stmt.executeQuery("SELECT * FROM producto");
            ResultSet resultSet = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos p " +
                    "inner join categorias as c ON (p.categoria_id = c.id)");
            while (resultSet.next()) {
                Producto producto = crearProducto(resultSet);
                productos.add(producto);
            }
        }
        return productos;
    }

    private Producto crearProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getInt("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getDouble("precio"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getInt("categoria_id"));
        categoria.setNombre(resultSet.getString("categoria"));
        producto.setCategoria(categoria);
        return producto;
    }

    @Override
    public Producto obtenerPorId(Integer id) throws SQLException{
        Producto producto = null;

//        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM producto WHERE id =?")) {
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT p.*, c.nombre as categoria FROM productos p " +
                "inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    producto = crearProducto(resultSet);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException{
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
//            sql = "UPDATE producto SET nombre = ?, precio = ? WHERE id = ?";
            sql = "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?";
        } else {
//            sql = "INSERT INTO producto(nombre, precio, fecha_registro) VALUES(?,?,?)";
            sql = "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setInt(4, producto.getId());
            } else {
                stmt.setDate(4, Date.valueOf(producto.getFechaRegistro()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException{
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
