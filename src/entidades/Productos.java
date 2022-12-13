
package entidades;

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

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

    
    @Override
    public String toString() {
        return "\nProductos{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio="
                + "" + precio + ", categoria=" + categoria + "}";
    }
}
