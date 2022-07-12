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
    <div>
        <h3>${title}</h3>
        <a class="btn btn-primary my-2 icon-bar fa-align-right col-lg-2"
           href="${pageContext.request.contextPath}/productos/form">Crear
            producto</a>
    </div>
    <table class="table table-hover table-striped">
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Categoria</th>
            <th>Precio</th>
            <th>Editar</th>
            <th>Eliminar</th>
        </tr>
        <c:forEach items="${productos}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.categoria.nombre}</td>
                <td>${p.precio}</td>
                <td><a class="btn btn-sm btn-success"
                       href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Editar</a>
                </td>
                <td><a class="btn btn-sm btn-danger"
                       onclick="return confirm('¿Está seguro que desea eliminar el producto?');"
                       href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}">Eliminar</a></td>
            </tr>
        </c:forEach>
    </table>
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>
</c:if>
<c:if test="${username == null}">
    <h3>Lo sentimos, pero no está autorizado para ingresar a esta página, por favor inicie sesión!</h3>
</c:if>
<jsp:include page="layout/footer.jsp"/>
