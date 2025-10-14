package model;

public abstract class Producto {

    private final int id;
    private String nombre;
    private Double precio;
    private int cantidad;

    private static int ID_CONTADOR = 0;

    public Producto(String nombre, Double precio, int cantidad) {
        ID_CONTADOR++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = ID_CONTADOR;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Nombre: " + this.getNombre() + "\n" + "Precio: " + this.getPrecio() + "\n" + "Cantidad: " + this.getCantidad();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getId() {
        return id;
    }
}
