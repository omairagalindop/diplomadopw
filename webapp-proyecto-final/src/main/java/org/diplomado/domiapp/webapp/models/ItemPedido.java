package org.diplomado.domiapp.webapp.models;

import java.util.Objects;

public class ItemPedido {
    private int cantidad;
    private Producto producto;

    public ItemPedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido itemCarro = (ItemPedido) o;
        return Objects.equals(producto.getId(), itemCarro.producto.getId())
                && Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    public double getImporte() {
        return cantidad * producto.getPrecio().doubleValue();
    }
}
