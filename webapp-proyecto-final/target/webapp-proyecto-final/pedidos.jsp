<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>

<c:choose>
    <c:when test="${pedido.items.isEmpty()}">
        <div class="alert alert-warning">Lo sentimos no hay pedidos!</div>
    </c:when>
    <c:otherwise>
        <form name="formpedido" action="${pageContext.request.contextPath}/pedido/actualizar" method="post">
            <table class="table table-hover table-striped">
                <tr>
                    <th>Id</th>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>Direccion</th>
                    <th>Total</th>
                    <th>Estado</th>
                </tr>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td>${pedido.id}</td>
                        <td>${pedido.fechaHora}</td>
                        <td>${pedido.nombreCliente}</td>
                        <td>${pedido.direccionCliente}</td>
                        <td>${pedido.total}</td>
                        <td>${pedido.estado}</td>
                            <%--        <td><input type="checkbox" value="${item.id}" name="deleteProductos" /></td>--%>
                    </tr>
                </c:forEach>
            </table>
<%--            <a class="btn btn-primary" href="javascript:document.formpedido.submit();">Crear pedido</a>--%>
            <a class="btn btn-primary my-2 icon-bar fa-align-right col-lg-2 icon-bar"
               href="${pageContext.request.contextPath}/pedidos/form">Crear
                pedido</a>
        </form>
    </c:otherwise>
</c:choose>
<div class="my-2">
    <%--    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">Volver</a>--%>
    <%--    <a class="btn btn-success" href="${pageContext.request.contextPath}/productos">Seguir comprando</a>--%>
</div>

<jsp:include page="layout/footer.jsp"/>
