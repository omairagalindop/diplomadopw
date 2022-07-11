package org.diplomado.domiapp.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diplomado.domiapp.webapp.models.Pedido;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/pedido/actualizar")
public class ActualizarCarroServlet extends HttpServlet {

    @Inject
    private Pedido pedido;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        if (session.getAttribute("carro") != null) {
//            Carro carro = (Carro) session.getAttribute("carro");
            updateProductos(req, pedido);
            updateCantidades(req, pedido);
//        }

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
