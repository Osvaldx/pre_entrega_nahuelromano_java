package service;

import java.util.ArrayList;

import enums.tipoProducto;
import model.Bebida;
import model.Comida;
import model.Producto;
import utils.InputHelper;

public class ProductoService {

    private final ArrayList<Producto> productos;

    public ProductoService(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto() {
        tipoProducto tipo = null;

        System.out.println("""
                - [1] Agregar Bebida
                - [2] Agregar Comida
                - [3] Salir
                """);
        
        int opcion = InputHelper.leerInt("Ingrese una opción: ");

        if(opcion == 3) { return; };

        switch(opcion) {
            case 1 -> tipo = tipoProducto.BEBIDA;
            case 2 -> tipo = tipoProducto.COMIDA;
            default -> { System.out.println("[!] Opción invalida!"); return; }
        }

        String nombre = InputHelper.leerString("Ingrese el nombre del producto: ");
        Double precio = InputHelper.leerDouble("Ingrese el precio: ");
        int cantidad = InputHelper.leerInt("Ingrese la cantidad: ");

        switch (tipo) {
            case tipoProducto.BEBIDA -> this.agregarBebida(nombre, precio, cantidad);
            case tipoProducto.COMIDA -> this.agregarComida(nombre, precio, cantidad);
        }
    }

    private void agregarBebida(String nombre, Double precio, int cantidad) {
        int volumenML = InputHelper.leerInt("Ingrese los ML de la bebida: ");

        try {
            this.productos.add(new Bebida(nombre, precio, cantidad, volumenML));
            System.out.println("[+] Producto agregado con exito!");
        } catch(Exception ex) {
            System.out.println("[!] Algo ocurrio: " + ex.getMessage());
        }
    }

    private void agregarComida(String nombre, Double precio, int cantidad) {
        int pesoGramos = InputHelper.leerInt("Ingrese el peso en gramos del producto: ");

        try {
            this.productos.add(new Comida(nombre, precio, cantidad, pesoGramos));
            System.out.println("[+] Producto agregado con exito!");
        } catch(Exception ex) {
            System.out.println("[!] Algo ocurrio: " + ex.getMessage());
        }
    }

    public void listarProductos() {
        if(this.productos.isEmpty()) {
            System.out.println("[!] No hay productos disponibles");
            return;
        }
        

        this.productos.forEach(p -> System.out.println(p.toString()));
    }

    public void buscarYEditarProducto() {
        if(this.productos.isEmpty()) { System.out.println("[!] No hay productos en este momento"); return; };

        Producto p = this.buscarProductoPorID();
        
        if(p == null) {
            System.out.println("[!] No existe producto con ese ID");
            return;
        } else {
            System.out.println(p.toString());
        }

        String opcionEditar;
        do {
            opcionEditar = InputHelper.leerString("¿Quiere editar el producto si/no?: ");
        } while(!opcionEditar.equalsIgnoreCase("si") && !opcionEditar.equalsIgnoreCase("no"));
        
        if(opcionEditar.equalsIgnoreCase("no")) { return; };
        
        this.editarProducto(p);
        
    }

    private void editarProducto(Producto p) {
        System.out.println("""
                - [1] editar nombre
                - [2] editar precio
                - [3] editar cantidad""");
        if(p instanceof Bebida) {
            this.mostrarOpcionMenuBebida();
        } else if(p instanceof Comida) {
            this.mostrarOpcionMenuComida();
        }

        int opcionMenu = InputHelper.leerInt("Ingrese una opción: ");

        switch (opcionMenu) {
            case 1 -> p.setNombre(InputHelper.leerString("Ingrese el nuevo nombre del producto: "));
            case 2 -> p.setPrecio(InputHelper.leerDouble("Ingrese el nuevo precio del producto: "));
            case 3 -> p.setCantidad(InputHelper.leerInt("Ingrese la nueva cantidad del producto: "));
            case 4 -> {
                if(p instanceof Bebida) {
                    ((Bebida) p).setVolumenML(InputHelper.leerInt("Ingrese el nuevo volumen (ML) de la bebida: "));
                } else if(p instanceof Comida) {
                    ((Comida) p).setPesoGramos(InputHelper.leerInt("Ingrese el nuevo peso (gramos) de la comida: "));
                } else {
                    break;
                }
            }
        }
    }

    private void mostrarOpcionMenuBebida() {
        System.out.println("- [4] editar volumen (mL)");
    }
    
    private void mostrarOpcionMenuComida() {
        System.out.println("- [4] editar peso (gramos)");
    }
    
    public Producto buscarProductoPorID() {
        int idProducto = InputHelper.leerInt("Ingrese el ID del producto a buscar: ");
        
        return this.productos.stream()
                .filter(p -> p.getId() == idProducto)
                .findFirst()
                .orElse(null);
    }

    public void eliminarProducto() {
        Producto p = this.buscarProductoPorID();
        if(p == null) { System.out.println("[!] No hay producto con ese ID"); return; };

        this.productos.remove(p);
        System.out.println("[+] Producto ID: " + p.getId() + " eliminado con exito!");
    }
}
