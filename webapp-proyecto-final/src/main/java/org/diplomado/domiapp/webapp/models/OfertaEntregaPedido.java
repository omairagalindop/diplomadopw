package org.diplomado.domiapp.webapp.models;

public class OfertaEntregaPedido {

    private Long id;
    private Long idProveedor;
    private Long idPedido;
    private Double tiempoEntrega;
    private Double valorDomicilio;
    private EstadoOfertaPedidoEnum estado;

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

    public Double getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Double tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public Double getValorDomicilio() {
        return valorDomicilio;
    }

    public void setValorDomicilio(Double valorDomicilio) {
        this.valorDomicilio = valorDomicilio;
    }

    public EstadoOfertaPedidoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoOfertaPedidoEnum estado) {
        this.estado = estado;
    }
}
