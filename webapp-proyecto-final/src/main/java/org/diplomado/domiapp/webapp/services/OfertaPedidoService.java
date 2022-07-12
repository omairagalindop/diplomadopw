package org.diplomado.domiapp.webapp.services;

import org.diplomado.domiapp.webapp.models.OfertaEntregaPedido;
import org.diplomado.domiapp.webapp.models.Pedido;

import java.util.List;
import java.util.Optional;

public interface OfertaPedidoService {
    List<OfertaEntregaPedido> listar();

    Optional<OfertaEntregaPedido> obtenerPorId(Long id);

    void guardar(OfertaEntregaPedido pedido);

    void eliminar(Long id);
}
