package service;

import java.util.ArrayList;

import model.Producto;
import model.Pedido;
import utils.InputHelper;

public class PedidoService {

    private final ArrayList<Pedido> pedidos;
    private final ProductoService productoService;
    
    public PedidoService(ArrayList<Pedido> pedidos, ProductoService productoService) {
        this.pedidos = pedidos;
        this.productoService = productoService;
    }

    public void mostrarMenuPedido() {
        System.out.println("""
                - [1] Crear pedido
                - [2] Agregar producto a pedido (ID)
                - [3] Eliminar producto de pedido (ID)
                - [4] Salir
                """);
        int opcion = InputHelper.leerInt("Ingrese una opción: ");

        switch(opcion) {
            case 1 -> this.crearPedido();
            case 2 -> this.agregarProductoPedido();
            case 3 -> this.eliminarProductoPedido();
            case 4 -> { return; }
            default -> { System.out.println("[!] Opción invalida"); return; }
        }
    }

    private void crearPedido() {
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
        if(pedido == null) { System.out.println("[!] No existe pedido con ese ID"); return; }
        
        this.agregarProductoPedido(pedido);
    }
    
    private void agregarProductoPedido(Pedido pedido) {
        System.out.println("[!] Busque el producto que quiera agregar al pedido");
        Producto p = this.productoService.buscarProductoPorID();
        if(p == null) { System.out.println("[!] No existe producto con ese ID"); return; }
        
        pedido.agregarProducto(p);
    }
    
    private void eliminarProductoPedido() {
        System.out.println("[!] Busque el PEDIDO que quiere eliminarle un PRODUCTO");
        Pedido pedido = this.buscarPedidoPorID();
        if(pedido == null) { System.out.println("[!] No existe pedido con ese ID"); return; }

        System.out.println("[!] Busque el PRODUCTO que quiera eliminar");
        Producto p = this.productoService.buscarProductoPorID();
        if(p == null) { System.out.println("[!] No existe producto con ese ID"); return; }

        pedido.eliminarProducto(p);
    }

    private Pedido buscarPedidoPorID() {
        int idPedido = InputHelper.leerInt("Ingrese el ID del Pedido: ");

        return this.pedidos.stream()
                            .filter(p -> p.getId() == idPedido)
                            .findFirst()
                            .orElse(null);
    }

    public void listarPedidos() {
        if(this.pedidos.size() == 0) { System.out.println("[!] No hay pedidos disponibles"); return; }

        this.pedidos.forEach(p -> System.out.println(p.toString()));
    }

}
