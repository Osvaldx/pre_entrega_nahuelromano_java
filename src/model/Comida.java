package model;

public class Comida extends Producto {

    private int pesoGramos;

    public Comida(String nombre, Double precio, int cantidad, int pesoGramos) {
        super(nombre, precio, cantidad);
        this.pesoGramos = pesoGramos;
    }

    public void setPesoGramos(int pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public int getPesoGramos() {
        return this.pesoGramos;
    }

    @Override
    public String toString() {
        return "-----------------------\n" + super.toString() + "\n" + "Peso (gramos): " + this.pesoGramos + "\n-----------------------";
    }

}
