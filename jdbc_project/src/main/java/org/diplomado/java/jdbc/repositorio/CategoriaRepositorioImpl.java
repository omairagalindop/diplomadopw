package org.diplomado.java.jdbc.repositorio;

import org.diplomado.java.jdbc.modelo.Categoria;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.util.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria> {
    private Connection connection;

    public CategoriaRepositorioImpl(Connection connection) {
        this.connection = connection;
    }

    private Connection getConnection() throws SQLException {
        return ConnectionBD.getConnection();
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM categorias");
            while (resultSet.next()) {
                Categoria categoria = crearCategoria(resultSet);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    private Categoria crearCategoria(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getInt("id"));
        categoria.setNombre(resultSet.getString("nombre"));
        return categoria;
    }

    @Override
    public Categoria obtenerPorId(Integer id) throws SQLException {
        Categoria categoria = null;

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM categorias as c WHERE c.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    categoria = crearCategoria(resultSet);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        if (categoria.getId() != null && categoria.getId() > 0) {
            sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO categorias(nombre) VALUES(?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());

            if (categoria.getId() != null && categoria.getId() > 0) {
                stmt.setInt(2, categoria.getId());
            } else {
                stmt.setInt(2, categoria.getId());
            }
            stmt.executeUpdate();
            if (categoria.getId() == null) {
                try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        categoria.setId(resultSet.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM categorias WHERE id =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
