package org.diplomado.domiapp.webapp.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.diplomado.domiapp.webapp.configs.MysqlConn;
import org.diplomado.domiapp.webapp.configs.Repository;
import org.diplomado.domiapp.webapp.models.Categoria;
import org.diplomado.domiapp.webapp.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Repository
public class ProductoRepositoryJdbcImpl implements CrudRepository<Producto> {

    @Inject
    private Logger log;

    @Inject
    @MysqlConn
    private Connection conn;

    @PostConstruct
    public void inicializar(){
        log.info("Inicializando el beans " + this.getClass().getName());
    }

    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el beans " + getClass().getName());
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     " inner join categorias as c ON (p.categoria_id = c.id) order by p.id ASC")) {
            while (rs.next()) {
                Producto p = obtenerProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto obtenerPorId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                " inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = obtenerProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "update productos set nombre=?, precio=?, categoria_id=? where id=?";
        } else {
            sql = "insert into productos (nombre, precio, categoria_id) values (?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(3, producto.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from productos where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Producto obtenerProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
