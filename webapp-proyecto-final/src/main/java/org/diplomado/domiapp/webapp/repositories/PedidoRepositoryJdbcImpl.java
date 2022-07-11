package org.diplomado.domiapp.webapp.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.diplomado.domiapp.webapp.configs.MysqlConn;
import org.diplomado.domiapp.webapp.configs.Repository;
import org.diplomado.domiapp.webapp.models.Categoria;
import org.diplomado.domiapp.webapp.models.EstadoPedido;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class PedidoRepositoryJdbcImpl implements CrudRepository<Pedido> {

    @Inject
    private Logger log;

    @Inject
    @MysqlConn
    private Connection conn;

    @PostConstruct
    public void inicializar() {
        log.info("Inicializando el beans " + this.getClass().getName());
    }

    @PreDestroy
    public void destruir() {
        log.info("Destruyendo el beans " + getClass().getName());
    }

    @Override
    public List<Pedido> listar() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        log.info("Listando pedidos antes: " + pedidos.size());
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos p")) {
            while (rs.next()) {
                Pedido p = obtenerPedido(rs);
                pedidos.add(p);
            }
            log.info("Listando pedidos después: " + pedidos.size());
        }
        return pedidos;
    }

    @Override
    public Pedido obtenerPorId(Long id) throws SQLException {
        Pedido pedido = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pedidos p WHERE p.id = ?;")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = obtenerPedido(rs);
                }
            }
        }
        return pedido;
    }

    @Override
    public void guardar(Pedido pedido) throws SQLException {

        String sql = "";
        if (pedido.getId() != null && pedido.getId() > 0) {
//            sql = "update pedidos set nombre=?, precio=?, categoria_id=? where id=?";
        } else {
            sql = "insert into pedidos (fecha_generacion, estado, nombre_cliente, direccion_cliente, total) values (?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(pedido.getFechaHora()));
            stmt.setString(2, pedido.getEstado().toString());
            stmt.setString(3, pedido.getNombreCliente());
            stmt.setString(4, pedido.getDireccionCliente());
            stmt.setDouble(5, pedido.getTotal());

            if (pedido.getId() != null && pedido.getId() > 0) {
//                stmt.setLong(5, pedido.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from pedidos where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Pedido obtenerPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setId(rs.getLong("id"));
        p.setFechaHora(rs.getTimestamp("fecha_generacion").toLocalDateTime());
        p.setTotal(rs.getDouble("total"));
        p.setEstado(crearEstado(rs.getString("estado")));
        p.setNombreCliente(rs.getString("nombre_cliente"));
        p.setDireccionCliente(rs.getString("direccion_cliente"));

        return p;
    }

    private EstadoPedido crearEstado(String estado) {
        switch (estado) {
            case "Enviado":
                return EstadoPedido.ENVIADO;
            case "Pendiente":
                return EstadoPedido.PENDIENTE;
            default:
                return EstadoPedido.PENDIENTE;
        }
    }

}
