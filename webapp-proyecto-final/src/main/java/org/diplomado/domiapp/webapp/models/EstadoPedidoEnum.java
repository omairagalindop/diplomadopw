package org.diplomado.domiapp.webapp.models;

public enum EstadoPedidoEnum {

    PENDIENTE("Pendiente"), ENVIADO("Enviado");

    String nombre;

    EstadoPedidoEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
