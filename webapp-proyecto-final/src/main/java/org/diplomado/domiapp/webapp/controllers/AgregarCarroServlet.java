package org.diplomado.domiapp.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diplomado.domiapp.webapp.configs.ProductoServicePrincipal;
import org.diplomado.domiapp.webapp.models.ItemPedido;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.models.Producto;
import org.diplomado.domiapp.webapp.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/items/agregar/")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Inject
    private Pedido pedido;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Producto> producto = service.obtenerPorId(id);
        if (producto.isPresent()) {
            ItemPedido item = new ItemPedido(1, producto.get());
            pedido.addItemPedido(item);
        }
        resp.sendRedirect(req.getContextPath() + "/pedidos/ver");
    }
}
