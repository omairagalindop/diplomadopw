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
        <c:when test="${carro.items.isEmpty()}">
            <div class="alert alert-warning">Lo sentimos el pedido no tiene ítems!</div>
        </c:when>
        <c:otherwise>
            <form name="formitempedido" action="${pageContext.request.contextPath}/items/agregar/" method="post">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Total</th>
                        <th>Borrar</th>
                    </tr>
                    <c:forEach items="${pedido.items}" var="item">
                        <tr>
                            <td>${item.producto.id}</td>
                            <td>${item.producto.nombre}</td>
                            <td>${item.producto.precio}</td>
                            <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}"/>
                            </td>
                            <td>${item.importe}</td>
                            <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="text-align: right">Total:</td>
                        <td>${pedido.total}</td>
                    </tr>
                </table>
                <a class="btn btn-primary" href="javascript:document.formitempedido.submit();">Actualizar</a>
            </form>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${username == null}">
    <h3>Lo sentimos, pero no está autorizado para ingresar a esta página, por favor inicie sesión!</h3>
</c:if>

<jsp:include page="layout/footer.jsp"/>
