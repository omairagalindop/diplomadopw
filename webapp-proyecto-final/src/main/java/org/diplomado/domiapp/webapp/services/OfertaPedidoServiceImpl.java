package org.diplomado.domiapp.webapp.services;

import jakarta.inject.Inject;
import org.diplomado.domiapp.webapp.configs.Service;
import org.diplomado.domiapp.webapp.models.OfertaEntregaPedido;
import org.diplomado.domiapp.webapp.repositories.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaPedidoServiceImpl implements OfertaPedidoService{
    @Inject
    private CrudRepository<OfertaEntregaPedido> crudRepositoryJdbc;

    @Override
    public List<OfertaEntregaPedido> listar() {
        try {
            return crudRepositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<OfertaEntregaPedido> obtenerPorId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryJdbc.obtenerPorId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(OfertaEntregaPedido ofertaPedido) {
        try {
            crudRepositoryJdbc.guardar(ofertaPedido);
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
