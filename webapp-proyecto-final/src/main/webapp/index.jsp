<%--<jsp:include page="layout/header.jsp" />--%>

<h3>${title}</h3>
<div class="limiter">
    <div class="container-login100">

        <div class="wrap-login100 login-sec px-4 pt-5">

            <h2 class="text-center mt-5" *ngIf="!smallHeight()">Iniciar sesión</h2>
            <h6 class="text-center">Frango Simplemente Delicioso!</h6>

            <div class="d-block p-0 m-0 w-100 p-lg-4 p-md-0 p-sm-0 p-xl-4">
                <form [formGroup]="form" (ngSubmit)="onSubmit()">
                    <div class="form-group">
                        <input type="text" class="form-control px-3"
                               placeholder="Usuario" name="cusername" autocomplete="on"
                               formControlName="usuario">
                    </div>

                    <div class="form-group mt-3">
                        <input class="form-control px-3"
                               type="{{ typePassword }}"
                               placeholder="Contraseña"
                               formControlName="contrasenia">
                        <i nz-icon nzType="eye-invisible" nzTheme="outline" class="btn-show-password"
                           (click)="show()"></i>
                    </div>
                    <div class="d-block w-100 text-center mt-3 mb-3">
                        <button [disabled]="!form.valid"
                                type="submit" class="btn btn-login w-100 pt-2 pb-2">INGRESAR
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="layout/footer.jsp"/>
