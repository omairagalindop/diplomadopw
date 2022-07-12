<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<c:if test="${username != null}">
    <c:set var="adminRol" value="admin"/>
    <c:set var="proveedorRol" value="proveedor"/>
    <c:if test="${rol == adminRol}">
        <jsp:include page="layout/header.jsp"/>
    </c:if>
    <c:if test="${rol == proveedorRol}">
        <jsp:include page="layout/headerProveedor.jsp"/>
    </c:if>
</c:if>
<c:if test="${username == null}">
    <h3>Lo sentimos, pero no está autorizado para ingresar a esta página!</h3>
</c:if>
<jsp:include page="layout/footer.jsp"/>
