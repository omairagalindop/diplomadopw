package org.diplomado.domiapp.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diplomado.domiapp.webapp.configs.ProductoServicePrincipal;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.services.LoginService;
import org.diplomado.domiapp.webapp.services.PedidoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/pedidos/ver")
public class PedidoServlet extends HttpServlet {

    @Inject
//    @ProductoServicePrincipal
    private PedidoService pedidoService;

    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pedido> pedidos = pedidoService.listar();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("pedidos", pedidos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", "Listado de pedidos");

        getServletContext().getRequestDispatcher("/pedidos.jsp").forward(req, resp);
    }
}
