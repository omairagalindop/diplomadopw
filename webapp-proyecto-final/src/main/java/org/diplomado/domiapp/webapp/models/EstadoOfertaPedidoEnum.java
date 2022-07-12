package org.diplomado.domiapp.webapp.models;

public enum EstadoOfertaPedidoEnum {

    ACEPTADA("Aceptada"), RECHAZADA("Rechazada"), PENDIENTE("Pendiente");

    String nombre;

    EstadoOfertaPedidoEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
