package entidades;

public class Vendedor {
    private int codigo;
    private String nombre;
    private double sueldo;

    public Vendedor(int codigo, String nombre, double sueldo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "codigo=" + codigo + ", nombre=" + nombre + ", sueldo=" + sueldo + '}';
    }
}
