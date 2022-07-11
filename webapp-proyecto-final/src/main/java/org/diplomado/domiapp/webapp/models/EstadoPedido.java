package org.diplomado.domiapp.webapp.models;

public enum EstadoPedido {

    PENDIENTE("Pendiente"), ENVIADO("Enviado");

    String nombre;

    EstadoPedido(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
