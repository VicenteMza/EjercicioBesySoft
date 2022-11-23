package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entidades.Productos;
import entidades.Vendedor;
import entidades.Ventas;

public class Servicios {

    private static List<Productos> product = new ArrayList<>();
    private static List<Vendedor> vendedor = new ArrayList<>();
    private static List<Ventas> ventas = new ArrayList<>();

    public Servicios() {
        if (ventas.isEmpty()) {
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

            ventas.add(new Ventas(1, 1, 2));
            ventas.add(new Ventas(2, 1, 5));
            ventas.add(new Ventas(4, 1, 1));
            ventas.add(new Ventas(6, 2, 1));
            ventas.add(new Ventas(1, 2, 2));
            ventas.add(new Ventas(8, 3, 3));
        }
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
            if (precio >= productos.getPrecio()) {
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

        if (validarVenta(codVend, numProd, cantVent)) {
            ventas.add(new Ventas(numProd, codVend, cantVent));
            for (Ventas p : ventas) {
                System.out.println(p);
            }
            System.out.println("tventas" + ventas.size());
        }
        if (cantVenta != ventas.size()) {
            System.out.println("Se registro la venta Exitosamente");
        } else {
            //JOptionPane.showMessageDialog(null, "NO se cargo la venta.");
            System.out.println("NO se cargo la venta.");
        }

    }

    public void erroresGenerarProducto(int numProd, int codVend, int cantVent) {
        if (!validarProducto(numProd)) {
            System.out.println("***El Producto NO existe.***\n");
        }
        if (!validarVendedor(codVend)) {
            System.out.println("***El codigo de vendedor no corresponde a ningun Vendedor.***\n");

        }
        if (!validarCantVent(cantVent)) {
            System.out.println("***La cantidad de articulos vendidos no puede ser menor a 1.***\n");
        }
    }

    public boolean validarVenta(int cVendedor, int cProd, int cantVent) {
        boolean validarVenta = false;

        if (validarVendedor(cVendedor) && validarProducto(cProd) && validarCantVent(cantVent)) {
            validarVenta = true;
        }
        return validarVenta;
    }

    public double calcularComision(int codVendedor) {
        double comision = 0;

        if (validarVendedor(codVendedor)) {
            for (Ventas ventas : ventas) {
                double presio = 0;
                double porcentaje;

                if (codVendedor == ventas.getCodVendedor()) {
                    if (ventas.getCantVentas() <= 2) {
                        porcentaje = 0.05;
                    } else {
                        porcentaje = 0.1;
                    }

                    presio = precioProducto(ventas.getCodProd());
                    comision += ventas.getCantVentas() * presio * porcentaje;
                }
            }
        }else{
            System.out.println("El vendedor '" + codVendedor + "' NO EXISTE");
        }

        return comision;
    }

    private double precioProducto(int codProd) {
        Productos prod = buscarPorCodigo(codProd);
        return prod.getPrecio();
    }

    private boolean validarProducto(int numProd) {
        boolean validar = false;
        for (Productos productos : product) {
            if (numProd == productos.getCodigo()) {
                validar = true;
                break;
            }
        }
        return validar;
    }

    private boolean validarVendedor(int codVend) {
        boolean validar = false;
        for (Ventas vent : ventas) {
            if (codVend == vent.getCodVendedor()) {
                validar = true;
                break;
            }
        }
        return validar;
    }

    private boolean validarCantVent(int cantVent) {
        boolean validar = false;
        if (cantVent >= 1) {
            validar = true;
        }
        return validar;
    }

}
