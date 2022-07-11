package org.diplomado.domiapp.webapp.services;

import org.diplomado.domiapp.webapp.models.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> listar();

    Optional<Pedido> obtenerPorId(Long id);

    void guardar(Pedido pedido);

    void eliminar(Long id);
}
