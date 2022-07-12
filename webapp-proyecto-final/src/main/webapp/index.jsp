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
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
