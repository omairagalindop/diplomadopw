<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>

<form action="${pageContext.request.contextPath}/pedidos/form" method="post">
    <label for="estado" class="col-form-label col-sm-3 text-center">Estado</label>
    <label for="nombreCliente" class="col-form-label col-sm-4 text-center">Nombre cliente</label>
    <label for="direccionCliente" class="col-form-label col-sm-4 text-center">Dirección cliente</label>
    <select class="col-sm-3" name="estado" id="estado" class="form-select">
        <option value="">--- Seleccionar ---</option>
        <%--                <c:forEach items="${estados}" var="e">--%>
        <option value="PENDIENTE">Pendiente</option>
        <option value="ENVIADO">Enviado</option>
        <%--                </c:forEach>--%>
    </select>
    <c:if test="${errores != null && not empty errores.estado}">
        <div style="color:red;">${errores.estado}</div>
    </c:if>
    <input class="col-sm-4" type="text" name="nombreCliente" id="nombreCliente" value="${pedido.nombreCliente}"
           class="form-control">
    <c:if test="${errores != null && errores.containsKey('nombreCliente')}">
        <div style="color:red;">${errores.nombreCliente}</div>
    </c:if>

    <input class="col-sm-4" type="text" name="direccionCliente" id="direccionCliente" value="${pedido.direccionCliente}"
           class="form-control">
    <c:if test="${errores != null && errores.containsKey('direccionCliente')}">
        <div style="color:red;">${errores.direccionCliente}</div>
    </c:if>

    <label for="tiempoEsperaOferta" class="col-form-label col-sm-3 text-center">Tiempo espera oferta</label>
    <div class="col-sm-3">
        <input type="number" name="tiempoEsperaOferta" id="tiempoEsperaOferta"
               value="${pedido.tiempoEsperaOferta > 0? pedido.tiempoEsperaOferta: ""}" class="form-control">
    </div>

    <%--    <div class="row mb-2">--%>
    <%--        <label for="total" class="col-form-label col-sm-2">Total</label>--%>
    <%--        <div class="col-sm-4">--%>
    <%--            <input type="number" name="total" id="total" value="${pedido.total > 0? pedido.total: ""}" class="form-control">--%>
    <%--        </div>--%>
    <%--        <c:if test="${errores != null && not empty errores.total}">--%>
    <%--            <div style="color:red;">${errores.total}</div>--%>
    <%--        </c:if>--%>
    <%--    </div>--%>


    <%--    <div class="row mb-2 mt-2 col-12">--%>
    <%--            <input class="btn btn-primary d-inline-block col-lg-2" type="submit" onclick="" value="${"Agregar ítem"}">--%>
    <%--    </div>--%>
    <%--    <input type="hidden" name="id" value="${pedido.id}">--%>
    <div class="row mt-2">
        <input class="btn btn-primary flex-end ml-1 col-lg-2" type="submit"
               value="${pedido.id!=null && pedido.id>0? "Editar": "Crear pedido"}">
    </div>
</form>

<jsp:include page="layout/footer.jsp"/>
