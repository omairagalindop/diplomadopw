package org.diplomado.domiapp.webapp.services;

import org.diplomado.domiapp.webapp.models.Categoria;
import org.diplomado.domiapp.webapp.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> obtenerPorId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
