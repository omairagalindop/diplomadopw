package org.diplomado.domiapp.webapp.models;

public class OfertaEntregaPedido {

    private Long id;
    private Long idProveedor;
    private Long idPedido;
    private float tiempoEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public float getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(float tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }
}
