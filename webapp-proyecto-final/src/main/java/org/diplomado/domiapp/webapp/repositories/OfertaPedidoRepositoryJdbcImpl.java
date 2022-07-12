package org.diplomado.domiapp.webapp.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.diplomado.domiapp.webapp.configs.MysqlConn;
import org.diplomado.domiapp.webapp.configs.Repository;
import org.diplomado.domiapp.webapp.models.EstadoOfertaPedidoEnum;
import org.diplomado.domiapp.webapp.models.OfertaEntregaPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class OfertaPedidoRepositoryJdbcImpl implements CrudRepository<OfertaEntregaPedido> {

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
    public List<OfertaEntregaPedido> listar() throws SQLException {
        List<OfertaEntregaPedido> pedidos = new ArrayList<>();
        log.info("Listando pedidos antes: " + pedidos.size());
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ofertas_entrega_pedido p")) {
            while (rs.next()) {
                OfertaEntregaPedido p = obtenerOfertasEntregasPedido(rs);
                pedidos.add(p);
            }
            log.info("Listando pedidos después: " + pedidos.size());
        }
        return pedidos;
    }

    @Override
    public OfertaEntregaPedido obtenerPorId(Long id) throws SQLException {
        OfertaEntregaPedido oferta = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM ofertas_entrega_pedido p WHERE p.id = ?;")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oferta = obtenerOfertasEntregasPedido(rs);
                }
            }
        }
        return oferta;
    }

    @Override
    public void guardar(OfertaEntregaPedido ofertaPedido) throws SQLException {

        String sql = "";
        if (ofertaPedido.getId() != null && ofertaPedido.getId() > 0) {
//            sql = "update pedidos set nombre=?, precio=?, categoria_id=? where id=?";
        } else {
            sql = "INSERT INTO ofertas_entrega_pedido (proveedor_id, pedido_id, valor_domicilio, tiempo_entrega, estado) VALUES (?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, ofertaPedido.getIdProveedor());
            stmt.setLong(2, ofertaPedido.getIdPedido());
            stmt.setDouble(3, ofertaPedido.getValorDomicilio());
            stmt.setDouble(4, ofertaPedido.getTiempoEntrega());
            stmt.setString(5, ofertaPedido.getEstado().toString());

            if (ofertaPedido.getId() != null && ofertaPedido.getId() > 0) {
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

    private OfertaEntregaPedido obtenerOfertasEntregasPedido(ResultSet rs) throws SQLException {
        OfertaEntregaPedido p = new OfertaEntregaPedido();
        p.setId(rs.getLong("id"));
        p.setIdProveedor(rs.getLong("proveedor_id"));
        p.setIdPedido(rs.getLong("pedido_id"));
        p.setValorDomicilio(rs.getDouble("valor_domicilio"));
        p.setTiempoEntrega(rs.getDouble("tiempo_entrega"));
        p.setEstado(crearEstado(rs.getString("estado")));
        return p;
    }

    private EstadoOfertaPedidoEnum crearEstado(String estado) {
        switch (estado) {
            case "RECHAZADA":
                return EstadoOfertaPedidoEnum.RECHAZADA;
            case "ACEPTADA":
                return EstadoOfertaPedidoEnum.ACEPTADA;
            default:
                return EstadoOfertaPedidoEnum.PENDIENTE;
        }
    }
}
