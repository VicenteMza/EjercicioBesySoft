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

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public void setCantVentas(int cantVentas) {
        this.cantVentas = cantVentas;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Ventas{" + "codProd=" + codProd + ", codVendedor=" + codVendedor + ", cantVentas=" + cantVentas + ", localDate=" + localDate + '}';
    }
}
