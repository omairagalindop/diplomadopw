package org.diplomado.domiapp.webapp.services;

import jakarta.inject.Inject;
import org.diplomado.domiapp.webapp.configs.PedidoServicePrincipal;
import org.diplomado.domiapp.webapp.configs.ProductoServicePrincipal;
import org.diplomado.domiapp.webapp.configs.Service;
import org.diplomado.domiapp.webapp.models.Categoria;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.models.Producto;
import org.diplomado.domiapp.webapp.repositories.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
//@PedidoServicePrincipal
public class PedidoServiceJdbcImpl implements PedidoService{
    @Inject
    private CrudRepository<Pedido> crudRepositoryJdbc;

    @Override
    public List<Pedido> listar() {
        try {
            return crudRepositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Pedido> obtenerPorId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryJdbc.obtenerPorId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Pedido pedido) {
        try {
            crudRepositoryJdbc.guardar(pedido);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            crudRepositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
