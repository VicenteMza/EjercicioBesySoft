package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Servicios {

    private List<Productos> product = new ArrayList<>();
    private List<Vendedor> vendedor = new ArrayList<>();
    private List<Ventas> ventas = new ArrayList<>();

    public Servicios() {
        product.add(new Productos(1, "Lavarropa", 150000, "Eletrodomestico"));
        product.add(new Productos(2, "Cocina", 100000, "Eletrodomestico"));
        product.add(new Productos(3, "Cerveza", 500, "Bebida"));
        product.add(new Productos(4, "Gaseosa", 400, "Bebida"));
        product.add(new Productos(5, "Leche", 200, "Lacteo"));
        product.add(new Productos(6, "Leche Descremada", 100, "Lacteo"));
        product.add(new Productos(7, "Yogurt", 300, "Lacteo"));
        product.add(new Productos(8, "Maiz", 100, "Cereal"));

        vendedor.add(new Vendedor(1, "Matias", 50000));
        vendedor.add(new Vendedor(2, "Maria", 50000));
        vendedor.add(new Vendedor(3, "Daiana", 50000));

        ventas.add(new Ventas(1, 1, 2, LocalDate.now()));
        ventas.add(new Ventas(2, 1, 5, LocalDate.now()));
        ventas.add(new Ventas(4, 1, 1, LocalDate.now()));
        ventas.add(new Ventas(6, 2, 1, LocalDate.now()));
        ventas.add(new Ventas(1, 2, 2, LocalDate.now()));
        ventas.add(new Ventas(8, 3, 3, LocalDate.now()));
    }

    public Productos buscarPorCodigo(int id) {
        Productos prod = null;

        for (Productos productos : product) {
            if (id == productos.getCodigo()) {
                prod = productos;
                break;
            }
        }
        return prod;
    }

    public List<Productos> buscarPorNombre(String nombrePr) {
        String nomb = nombrePr.toLowerCase();

        List<Productos> pr = new ArrayList<>();
        for (Productos productos : product) {
            if (productos.getNombre().toLowerCase().contains(nomb)) {
                pr.add(productos);
            }
        }
        return pr;
    }

    public List<Productos> buscarPorPrecio(double precio) {
        List<Productos> pr = new ArrayList<>();
        for (Productos productos : product) {
            if (precio == productos.getPrecio()) {
                pr.add(productos);
            }
        }
        return pr;
    }

    public List<Productos> buscarPorCategoria(String categ) {
        String cat = categ.toLowerCase();
        List<Productos> pr = new ArrayList<>();

        for (Productos productos : product) {
            if (productos.getCategoria().toLowerCase().contains(cat)) {
                pr.add(productos);
            }
        }
        return pr;
    }

    public void generarVenta(int numProd, int codVend, int cantVent) {
        int cantVenta = ventas.size();

        ventas.add(new Ventas(numProd, codVend, cantVent, LocalDate.now()));

        if (cantVenta != ventas.size()) {
            System.out.println("Se registro la venta Exitosamente");
            //JOptionPane.showMessageDialog(null, "Se regsitro la venta Exitosamente");
        }

    }

    public double calcularComision(int codVendedor) {
        double comision = 0;

        for (Ventas ventas : ventas) {
            double pres = 0;
            double porcentaje;

            if (codVendedor == ventas.getCodVendedor()) {
                if (ventas.getCantVentas() <= 2) {
                    porcentaje = 0.05;
                } else {
                    porcentaje = 0.1;
                }

                pres = precioProducto(ventas.getCodProd());
                comision += ventas.getCantVentas() * pres * porcentaje;
            }
        }
        return comision;
    }

    private double precioProducto(int codProd) {
        Productos prod = buscarPorCodigo(codProd);
        return prod.getPrecio();
    }
}
