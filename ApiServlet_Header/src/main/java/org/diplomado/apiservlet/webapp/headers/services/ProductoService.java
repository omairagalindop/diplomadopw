package org.diplomado.apiservlet.webapp.headers.services;

import org.diplomado.apiservlet.webapp.headers.models.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listar();
}
