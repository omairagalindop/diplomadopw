<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${username != null}">
    <c:set var="adminRol" value="admin"/>
    <c:set var="proveedorRol" value="proveedor"/>
    <c:if test="${rol == adminRol}">
        <jsp:include page="layout/header.jsp"/>
    </c:if>
    <c:if test="${rol == proveedorRol}">
        <jsp:include page="layout/headerProveedor.jsp"/>
    </c:if>

    <h3>${title}</h3>

    <c:choose>
        <c:when test="${pedido.items.isEmpty()}">
            <div class="alert alert-warning">Lo sentimos no hay pedidos!</div>
        </c:when>
        <c:otherwise>
            <a class="btn btn-primary my-2 icon-bar fa-align-right col-lg-2 icon-bar"
               href="${pageContext.request.contextPath}/pedidos/form">Crear
                pedido</a>
            <form name="formpedido" action="${pageContext.request.contextPath}/oferta/crear" method="post">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Fecha</th>
                        <th>Cliente</th>
                        <th>Direccion</th>
                        <th>Tiempo espera oferta</th>
                        <th>Total</th>
                        <c:if test="${rol == adminRol}">
                            <th>Estado</th>
                        </c:if>
                        <c:if test="${rol == proveedorRol}">
                            <th>Tiempo entrega</th>
                            <th>Costo domicilio</th>
                            <th>Enviar oferta</th>
                        </c:if>
                    </tr>
                    <c:forEach items="${pedidos}" var="pedido">
                        <tr>
                            <td>${pedido.id}</td>
                            <td>${pedido.fechaHora}</td>
                            <td>${pedido.nombreCliente}</td>
                            <td>${pedido.direccionCliente}</td>
                            <td>${pedido.tiempoEsperaOferta}</td>
                            <td>${pedido.total}</td>
                            <c:if test="${rol == adminRol}">
                                <td>${pedido.estado}</td>
                            </c:if>
                            <c:if test="${rol == proveedorRol}">
                                <td>
                                    <input type="number" name="tiempoEntrega" id="tiempoEntrega"
                                           value="0" class="form-control">
                                </td>
                                <td>
                                    <input type="number" name="valorDomicilio" id="valorDomicilio"
                                           value="0" class="form-control">
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-primary"
                                       href="${pageContext.request.contextPath}/oferta/crear">Ofertar</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${username == null}">
    <h3>Lo sentimos, pero no está autorizado para ingresar a esta página!</h3>
</c:if>

<jsp:include page="layout/footer.jsp"/>
