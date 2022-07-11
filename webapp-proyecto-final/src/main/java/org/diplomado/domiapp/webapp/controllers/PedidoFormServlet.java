package org.diplomado.domiapp.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diplomado.domiapp.webapp.configs.ProductoServicePrincipal;
import org.diplomado.domiapp.webapp.models.Categoria;
import org.diplomado.domiapp.webapp.models.EstadoPedido;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.models.Producto;
import org.diplomado.domiapp.webapp.services.PedidoService;
import org.diplomado.domiapp.webapp.services.ProductoService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/pedidos/form")
public class PedidoFormServlet extends HttpServlet {
    @Inject
//    @ProductoServicePrincipal
    private PedidoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Pedido pedido = new Pedido();
        if (id > 0) {
            Optional<Pedido> o = service.obtenerPorId(id);
            if (o.isPresent()) {
                pedido = o.get();
            }
        }
        req.setAttribute("pedido", pedido);
        req.setAttribute("title", "Formulario de pedidos");

        getServletContext().getRequestDispatcher("/formPedido.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String estado = req.getParameter("estado");
        String nombreCliente = req.getParameter("nombreCliente");
        String direccionCliente = req.getParameter("direccionCliente");

        Double total;
        try {
//            total = Double.valueOf(req.getParameter("total"));
            total = 0.0;
        } catch (NumberFormatException e) {
            total = 0.0;
        }

        Map<String, String> errores = new HashMap<>();
        if (estado == null || estado.isBlank()) {
            errores.put("estado", "El estado es requerido!");
        }
        if (nombreCliente == null || nombreCliente.isBlank()) {
            errores.put("nombreCliente", "El nombre del cliente es requerido!");
        }
        if (direccionCliente == null || direccionCliente.isBlank()) {
            errores.put("direccionCliente", "La direccion del Cliente es requerida!");
        }
//        if (total.equals(0)) {
//            errores.put("total", "El precio es requerido!");
//        }

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setFechaHora(LocalDateTime.now());
        pedido.setEstado(crearEstado(estado));
        pedido.setNombreCliente(nombreCliente);
        pedido.setDireccionCliente(direccionCliente);
        pedido.setTotal(total);

        if (errores.isEmpty()) {
            service.guardar(pedido);
            resp.sendRedirect(req.getContextPath() + "/pedidos/ver");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("pedido", pedido);
            req.setAttribute("estados", EstadoPedido.values());
            req.setAttribute("title", "Formulario de pedidos");
            getServletContext().getRequestDispatcher("/formPedido.jsp").forward(req, resp);
        }
    }

    private EstadoPedido crearEstado(String estado) {
        switch (estado) {
            case "Enviado":
                return EstadoPedido.ENVIADO;
            case "Pendiente":
                return EstadoPedido.PENDIENTE;
            default:
                return EstadoPedido.PENDIENTE;
        }
    }
}
