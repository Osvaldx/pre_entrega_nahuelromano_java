package app;

import java.util.ArrayList;

import model.Pedido;
import model.Producto;
import service.PedidoService;
import service.ProductoService;
import utils.InputHelper;

public class Sistema {

    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ProductoService productoService = new ProductoService(productos);
    private static PedidoService pedidoService = new PedidoService(pedidos, productoService);

    public static void main(String[] args) {
        boolean continuar = true;

        do {
            System.out.println("""
                    - [1] Crear producto
                    - [2] Listar productos
                    - [3] Buscar / Actualizar producto
                    - [4] Eliminar producto
                    - [5] Gestionar pedidos
                    - [6] Listar pedidos
                    - [7] Salir
                    """);

            int opcion = InputHelper.leerInt("Ingrese una opción: ");

            switch (opcion) {
                case 1 -> productoService.crear();
                case 2 -> productoService.listar();
                case 3 -> productoService.editar();
                case 4 -> productoService.eliminar();
                case 5 -> pedidoService.mostrarMenuPedido();
                case 6 -> pedidoService.listar();
                case 7 -> continuar = false;
                default -> System.out.println("[!] Opción invalida, intente nuevamente");
            }
        } while(continuar);
    }
}
