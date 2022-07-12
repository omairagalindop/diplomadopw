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
        <c:when test="${ofertas.isEmpty()}">
            <div class="alert alert-warning">Lo sentimos no hay ofertas!</div>
        </c:when>
        <c:otherwise>
            <table class="table table-hover table-striped">
                <tr>
                    <th>Id</th>
                    <th>Proveedor</th>
                    <th># Pedido</th>
                    <th>Tiempo entrega</th>
                    <th>Costo domicilio</th>
                    <th>Estado</th>
                </tr>
                <c:forEach items="${ofertas}" var="pedido">
                    <tr>
                        <td>${pedido.id}</td>
                        <td>${pedido.idProveedor}</td>
                        <td>${pedido.idPedido}</td>
                        <td>${pedido.tiempoEntrega}</td>
                        <td>${pedido.valorDomicilio}</td>
                        <td>${pedido.estado}</td>
                    </tr>
                </c:forEach>
            </table>
            <a class="btn btn-sm btn-success"
               href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Asignar ofertas</a>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${username == null}">
    <h3>Lo sentimos, pero no está autorizado para ingresar a esta página!</h3>
</c:if>

<jsp:include page="layout/footer.jsp"/>
