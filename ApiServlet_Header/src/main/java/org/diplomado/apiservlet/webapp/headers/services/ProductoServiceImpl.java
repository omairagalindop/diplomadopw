package org.diplomado.apiservlet.webapp.headers.services;

import org.diplomado.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1, "Notebook", "Computacion", 175000.0),
                new Producto(2, "Mesa escritorio", "Oficina", 100000.0),
                new Producto(3, "Teclado mecánico", "Computacion", 40000.0));
    }
}
