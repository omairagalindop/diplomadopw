package org.diplomado.domiapp.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diplomado.domiapp.webapp.models.OfertaEntregaPedido;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.services.LoginService;
import org.diplomado.domiapp.webapp.services.OfertaPedidoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/ofertas/entregas")
public class OfertaEntregaPedidoServlet extends HttpServlet {

    @Inject
    private OfertaPedidoService ofertaPedidoService;

    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OfertaEntregaPedido> ofertaEntregaPedidos = ofertaPedidoService.listar();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("ofertas", ofertaEntregaPedidos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", "Listado de ofertas de entrega");

        getServletContext().getRequestDispatcher("/ofertasEntregaPedidos.jsp").forward(req, resp);
    }
}
