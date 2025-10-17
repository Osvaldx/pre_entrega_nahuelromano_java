package service;

import java.util.ArrayList;

import enums.estadoPedido;
import interfaces.ICrud;
import model.Producto;
import model.Pedido;
import utils.InputHelper;

public class PedidoService implements ICrud<Pedido> {

    private final ArrayList<Pedido> pedidos;
    private final ProductoService productoService;
    
    public PedidoService(ArrayList<Pedido> pedidos, ProductoService productoService) {
        this.pedidos = pedidos;
        this.productoService = productoService;
    }

    public void mostrarMenuPedido() {
        System.out.println("""
                - [1] Crear pedido
                - [2] Listar pedidos
                - [3] Editar pedido (ID)
                - [4] Eliminar pedido (ID)
                - [5] Gestionar productos del pedido (ID)
                - [6] Salir
                """);
        int opcion = InputHelper.leerInt("Ingrese una opción: ");

        switch(opcion) {
            case 1 -> this.crear();
            case 2 -> this.listar();
            case 3 -> this.editar();
            case 4 -> this.eliminar();
            case 5 -> this.mostrarMenuGestionProductos();
            case 6 -> { return; }
            default -> { System.out.println("[!] Opción invalida"); return; }
        }
    }

    private void mostrarMenuGestionProductos() {
        System.out.println("""
                - [1] Agregar producto a pedido (ID)
                - [2] Eliminar producto de pedido (ID)
                - [3] Listar productos del pedido (ID)
                - [4] Salir
                """);

        int opcion = InputHelper.leerInt("Ingrese una opción: ");
        switch (opcion) {
            case 1 -> this.agregarProductoPedido();
            case 2 -> this.eliminarProductoDePedido();
            case 3 -> this.listarProductosDePedido();
            case 4 -> { return; }
            default -> { System.out.println("[!] Opción invalida"); return; }
        }
    }

    private void listarProductosDePedido() {
        Pedido pedido = this.buscarPedidoPorID();
        if(pedido == null) { return; }
        
        if(pedido.getListaProductosSize() == 0) {
            System.out.println("[!] No hay productos en este pedido");
            return;
        }
        
        System.out.println("--| LISTA DE PRODUCTOS |--");
        pedido.getListaProductos().forEach(p -> System.out.println(p.toString()));
    }

    @Override
    public void crear() {
        try {
            Pedido pedido = new Pedido();
            pedidos.add(pedido);
            System.out.println("[+] Pedido creado con el ID: " + pedido.getId() + "\nAgregue un producto al pedido...");
            this.agregarProductoPedido(pedido);
        } catch (Exception ex) {
            System.out.println("[!] Algo ocurrio " + ex.getMessage());
        }
    }

    private void agregarProductoPedido() {
        Pedido pedido = this.buscarPedidoPorID();
        if(pedido == null) { return; }
        
        this.agregarProductoPedido(pedido);
    }
    
    private void agregarProductoPedido(Pedido pedido) {
        System.out.println("[!] Busque el producto que quiera agregar al pedido");
        Producto p = this.productoService.buscarProductoPorID();
        if(p == null) { return; }
        
        pedido.agregarProducto(p);
    }

    @Override
    public void editar() {
        System.out.println("[!] Ingrese el ID del pedido a modificar");
        Pedido p = this.buscarPedidoPorID();

        if(p == null) { return; }

        System.out.println("""
                - [1] Estado PENDIENTE
                - [2] Estado CANCELADO
                - [3] Estado ENTREGADO
                """);

        int opcion = InputHelper.leerInt("Ingrese una opción: ");

        switch (opcion) {
            case 1 -> p.setEstado(estadoPedido.PENDIENTE);
            case 2 -> p.setEstado(estadoPedido.CANCELADO);
            case 3 -> p.setEstado(estadoPedido.ENTREGADO);
            default -> { System.out.println("[!] Opción invalida!"); return; }
        }
    }
    
    @Override
    public void eliminar() {
        System.out.println("[!] Busque el PEDIDO que quiere eliminar");
        Pedido pedido = this.buscarPedidoPorID();
        if(pedido == null) { return; }

        this.pedidos.remove(pedido);
        System.out.println("[+] Pedido eliminado con exito");
    }

    private void eliminarProductoDePedido() {
        System.out.println("[!] Busque el PEDIDO que quiere eliminarle un PRODUCTO");
        Pedido pedido = this.buscarPedidoPorID();
        if(pedido == null) { return; }

        System.out.println("[!] Busque el PRODUCTO que quiera eliminar");
        Producto p = this.productoService.buscarProductoPorID();
        if(p == null) { return; }

        pedido.eliminarProducto(p);
    }

    private Pedido buscarPedidoPorID() {
        int idPedido = InputHelper.leerInt("Ingrese el ID del Pedido: ");

        Pedido pedido = this.pedidos.stream()
                                    .filter(p -> p.getId() == idPedido)
                                    .findFirst()
                                    .orElse(null);

        if(pedido == null) { System.out.println("[!] No existe producto con ese ID"); }

        return pedido;
    }

    @Override
    public void listar() {
        if(this.pedidos.size() == 0) { System.out.println("[!] No hay pedidos disponibles"); return; }

        this.pedidos.forEach(p -> System.out.println(p.toString()));
    }

}
