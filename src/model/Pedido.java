package model;

import java.util.HashMap;
import java.util.Map;

import enums.estadoPedido;
import exceptions.StockInsuficienteException;

public class Pedido {

    private final int ID_PEDIDO;
    private Map<Integer, Producto> listaProductos;
    private estadoPedido estado;

    private static int ID_CONTADOR = 0;

    public Pedido() {
        ID_CONTADOR++;
        this.ID_PEDIDO = ID_CONTADOR;
        this.estado = estadoPedido.PENDIENTE;
        this.listaProductos = new HashMap<Integer, Producto>();
    }

    public void agregarProducto(Producto producto) {
        if(producto.getCantidad() > 0) {
            this.listaProductos.put(producto.getId(), producto);
            producto.setCantidad(producto.getCantidad() - 1);
            System.out.println("[+] Se AGREGO el producto con el ID: " + producto.getId() + " del pedido ID: " + this.getId());
        } else {
            throw new StockInsuficienteException();
        }
    }
    
    public void eliminarProducto(Producto producto) {
        if(this.getListaProductosSize() > 0) {
            if(this.listaProductos.containsValue(producto)) {
                this.listaProductos.remove(producto.getId(), producto);
                producto.setCantidad(producto.getCantidad() + 1);
                System.out.println("[!] Se ELIMINO el producto con el ID: " + producto.getId() + " del pedido ID: " + this.getId());
            } else {
                System.out.println("[!] El pedido no tiene ese producto");
            }
        } else {
            System.out.println("[!] Este pedido esta vacio!");
        }
    }

    public int getId() {
        return this.ID_PEDIDO;
    }

    public int getListaProductosSize() {
        return this.listaProductos.size();
    }

    public estadoPedido getEstado() {
        return this.estado;
    }

    public void setEstado(estadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "--------------------\n" + "ID PEDIDO: " + this.getId() + "\n" + "Estado de pedido: " + this.getEstado() + "\n" + "Cantidad productos: " + this.getListaProductosSize() + "\n--------------------";
    }
}
