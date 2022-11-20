package main;

import java.time.LocalDate;

public class Ventas {
    private int codProd;
    private int codVendedor;
    private int cantVentas;
    private LocalDate date;

    public Ventas() {
    }

    public Ventas(int codProd, int codVendedor, int cantVentas, LocalDate date) {
        this.codProd = codProd;
        this.codVendedor = codVendedor;
        this.cantVentas = cantVentas;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ventas{" + "codProd=" + codProd + ", codVendedor=" + codVendedor + ", cantVentas=" + cantVentas + ", date=" + date + '}';
    }
    
    
}
