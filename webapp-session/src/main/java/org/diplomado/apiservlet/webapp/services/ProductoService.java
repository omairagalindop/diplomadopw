package org.diplomado.apiservlet.webapp.services;

import org.diplomado.apiservlet.webapp.models.Categoria;
import org.diplomado.apiservlet.webapp.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
