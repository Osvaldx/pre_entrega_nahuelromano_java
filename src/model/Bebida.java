package model;

public class Bebida extends Producto{

    private int volumenML;

    public Bebida (String nombre, Double precio, int cantidad, int volumenML) {
        super(nombre, precio, cantidad);
        this.volumenML = volumenML;
    }

    public void setVolumenML(int volumenML) {
        this.volumenML = volumenML;
    }

    public int getVolumenML() {
        return this.volumenML;
    }

    @Override
    public String toString() {
        return "-----------------------\n" + super.toString() + "\n" + "Volumen (mL): " + this.volumenML + "\n-----------------------";
    }

}
