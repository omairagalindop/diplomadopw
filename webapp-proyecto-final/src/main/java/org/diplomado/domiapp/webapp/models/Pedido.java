package org.diplomado.domiapp.webapp.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Pedido implements Serializable {

    private Long id;
    private LocalDateTime fechaHora;
    private Double total;
    private EstadoPedido estado;
    private String nombreCliente;
    private String direccionCliente;
    private List<ItemPedido> items = new ArrayList<>();

    @Inject
    private transient Logger log;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    @PostConstruct
    public void inicializar() {
        this.items = new ArrayList<>();
        log.info("Iniciando pedido!");
    }

    @PreDestroy
    public void destruir() {
        log.info("Destruyendo pedido!");
    }

    public void addItemPedido(ItemPedido itemPedido) {
        if (items.contains(itemPedido)) {
            Optional<ItemPedido> optionalItemPedido = items.stream()
                    .filter(i -> i.equals(itemPedido))
                    .findAny();
            if (optionalItemPedido.isPresent()) {
                ItemPedido i = optionalItemPedido.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        } else {
            this.items.add(itemPedido);
        }
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public Double getTotal() {
        return items.stream().mapToDouble(ItemPedido::getImporte).sum();
    }

    public void eliminarProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::eliminarProducto);
        }
    }

    public void eliminarProducto(String productoId) {
        Optional<ItemPedido> producto = buscarProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void actualizarCantidad(String productoId, int cantidad) {
        Optional<ItemPedido> producto = buscarProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemPedido> buscarProducto(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
