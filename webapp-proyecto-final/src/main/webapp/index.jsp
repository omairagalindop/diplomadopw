<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<body>
<%--<jsp:include page="layout/header.jsp" />--%>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 login-sec px-4 pt-5 col-lg-4">
            <div class="d-block p-0 m-0 w-100 p-lg-4 p-md-0 p-sm-0 p-xl-4">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <h2 class="text-center mt-5">Iniciar sesión</h2>
                    <h6 class="text-center">Frango Simplemente Delicioso!</h6>
                    <div class="row my-2 text-center">
                        <input type="text" name="username" id="username" class="form-control" placeholder="Usuario">
                    </div>
                    <div class="row my-2 text-center">
                        <input type="password" name="password" id="password" class="form-control"
                               placeholder="Contraseña">
                    </div>
                    <div class="row my-2 text-center">
                        <input type="submit" value="Ingresar" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
<%--        <div class="col-lg-6">--%>
<%--            <img src="images/Banderin.png" class="img-fluid">--%>
<%--            <div class="banner-text text-color-background">--%>
<%--                <h2>Tradición con toque de sabor!</h2>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>

<%--<div class="limiter">--%>
<%--    <div class="container-login100">--%>
<%--        <div class="wrap-login100 login-sec px-4 pt-5">--%>
<%--            <h2 class="text-center">Iniciar sesión</h2>--%>
<%--            <h6 class="text-center">Frango Simplemente Delicioso!</h6>--%>

<%--            <div class="d-block p-0 m-0 w-100 p-lg-4 p-md-0 p-sm-0 p-xl-4 col-lg-4">--%>
<%--                <form [formGroup]="form">--%>
<%--                    <div class="form-group">--%>
<%--                        <input type="text" name="username" id="username" class="form-control" placeholder="Usuario">--%>
<%--                        &lt;%&ndash;                        <input type="text" class="form-control px-3"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                               placeholder="Usuario" name="cusername" autocomplete="on"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                               formControlName="usuario">&ndash;%&gt;--%>
<%--                    </div>--%>

<%--                    <div class="form-group mt-3">--%>
<%--                        <input type="password" name="password" id="password" class="form-control"--%>
<%--                               placeholder="Contraseña">--%>
<%--                        &lt;%&ndash;                        <input class="form-control px-3"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                               type="{{ typePassword }}"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                               placeholder="Contraseña"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                               formControlName="contrasenia">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        <i nz-icon nzType="eye-invisible" nzTheme="outline" class="btn-show-password"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                           (click)="show()"></i>&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                    <div class="d-block w-100 text-center mt-3 mb-3">--%>
<%--                        <input type="submit" value="INGRESAR" class="btn btn-primary w-100 pt-2 pb-2">--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--            <div class="login100-more col-lg-6">--%>
<%--                <img src="images/Banderin.png" class="img-fluid">--%>
<%--                <div class="banner-text text-color-background">--%>
<%--                    <h2>Tradición con toque de sabor!</h2>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="layout/footer.jsp"/>
