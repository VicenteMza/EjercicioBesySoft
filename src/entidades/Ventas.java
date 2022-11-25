package entidades;

import java.time.LocalDate;

public class Ventas {
    private int codProd;
    private int codVendedor;
    private int cantVentas;
    private LocalDate localDate;
    
    public Ventas() {
    }

    public Ventas(int codProd, int codVendedor, int cantVentas, LocalDate localDate) {
        this.codProd = codProd;
        this.codVendedor = codVendedor;
        this.cantVentas = cantVentas;
    }

    public int getCodProd() {
        return codProd;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public int getCantVentas() {
        return cantVentas;
    }

    @Override
    public String toString() {
        return "Ventas{" + "codProd=" + codProd + ", codVendedor=" + codVendedor + ", cantVentas=" + cantVentas + '}';
    }

}
