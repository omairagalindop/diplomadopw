package org.diplomado.domiapp.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diplomado.domiapp.webapp.models.EstadoOfertaPedidoEnum;
import org.diplomado.domiapp.webapp.models.OfertaEntregaPedido;
import org.diplomado.domiapp.webapp.models.Pedido;
import org.diplomado.domiapp.webapp.services.OfertaPedidoService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/oferta/crear")
public class AgregarOfertaServlet extends HttpServlet {

    @Inject
    private OfertaPedidoService ofertaPedidoService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idProveedor = Long.valueOf(req.getParameter("idProveedor"));
        Long idPedido = Long.valueOf(req.getParameter("idPedido"));
        Double tiempoEntrega;
        try {
            tiempoEntrega = Double.valueOf(req.getParameter("tiempoEntrega"));
        } catch (NumberFormatException e) {
            tiempoEntrega = 0.0;
        }
        Double valorDomicilio;
        try {
            valorDomicilio = Double.valueOf(req.getParameter("valorDomicilio"));
        } catch (NumberFormatException e) {
            valorDomicilio = 0.0;
        }

        OfertaEntregaPedido ofertaEntregaPedido = new OfertaEntregaPedido();
        ofertaEntregaPedido.setIdProveedor(idProveedor);
        ofertaEntregaPedido.setIdPedido(idPedido);
        ofertaEntregaPedido.setTiempoEntrega(tiempoEntrega);
        ofertaEntregaPedido.setValorDomicilio(valorDomicilio);

        resp.sendRedirect(req.getContextPath() + "/pedidos/ver");
    }

    private void updateProductos(HttpServletRequest request, Pedido carro) {
        String[] deleteIds = request.getParameterValues("deleteProductos");

        if (deleteIds != null && deleteIds.length > 0) {
            List<String> productoIds = Arrays.asList(deleteIds);
            // Borramos los productos del carrito.
            carro.eliminarProductos(productoIds);
        }

    }

    private void updateCantidades(HttpServletRequest request, Pedido carro) {

        Enumeration<String> enumer = request.getParameterNames();

        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                String id = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    carro.actualizarCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }
    }
}
