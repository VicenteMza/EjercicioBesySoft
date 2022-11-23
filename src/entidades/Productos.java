package entidades;

import java.util.List;

public class Productos {
    private int codigo;
    private String nombre;
    private double precio;
    private String categoria;

    public Productos() {
    }

    public Productos(int codigo, String nombre, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    
    @Override
    public String toString() {
        return "Productos{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria + '}';
    }
}
