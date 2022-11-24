package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entidades.Productos;
import entidades.Vendedor;
import entidades.Ventas;

public class ServiciosImpl implements IServicios {

    private static List<Productos> product = new ArrayList<>();
    private static List<Vendedor> vendedor = new ArrayList<>();
    private static List<Ventas> ventas = new ArrayList<>();

    public ServiciosImpl() {
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

    @Override
    public Productos buscarPorCodigo(int id) {
        Productos prod = new Productos();

        for (Productos productos : product) {
            if (id == productos.getCodigo()) {
                prod = productos;
                break;
            }
        }
        return prod;
    }

    @Override
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

    @Override
    public List<Productos> buscarPorPrecio(double precio) {
        List<Productos> pr = new ArrayList<>();
        for (Productos productos : product) {
            if (precio >= productos.getPrecio()) {
                pr.add(productos);
            }
        }
        return pr;
    }

    @Override
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

    @Override
    public boolean generarVenta(int numProd, int codVend, int cantVent) {
        boolean ventaGenerada = false;
        int cantVenta = ventas.size();
        boolean valProd = validarProducto(numProd);
        boolean valVend = validarVendedor(codVend);
        boolean valCantVenta = validarCantVent(cantVent);
        
        if (valProd && valVend && valCantVenta) {
            ventas.add(new Ventas(numProd, codVend, cantVent));
     
        }
        if (cantVenta != ventas.size()) {
            ventaGenerada = true;
        }
        
        if (!valProd) {
            JOptionPane.showMessageDialog(null, "***No se encontro ningun Producto con ese ese numero de producto***");
        }
        if (!valVend) {
            JOptionPane.showMessageDialog(null, "***No se encontro ningun Vendedor conese codigo ***");
        }
        if (!valCantVenta) {
            JOptionPane.showMessageDialog(null, "***La cantidad de productos no puede ser MENOR a '0'***");
        }
        
        
           return ventaGenerada;
    }

    @Override
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
        } else {
            System.out.println("El vendedor '" + codVendedor + "' NO EXISTE");
        }

        return comision;
    }

    private double precioProducto(int codProd) {

        return buscarPorCodigo(codProd).getPrecio();
    }

    @Override
    public boolean validarProducto(int numProd) {
        boolean validar = false;

        for (Productos productos : product) {
            if (numProd == productos.getCodigo()) {
                validar = true;
                break;
            }
        }
        
        return validar;
    }

    @Override
    public boolean validarVendedor(int codVend) {
        boolean validar = false;
        for (Ventas vent : ventas) {
            if (codVend == vent.getCodVendedor()) {
                validar = true;
                break;
            }
        }
        return validar;
    }

    @Override
    public boolean validarCantVent(int cantVent) {
        boolean validar = false;
        if (cantVent >= 1) {
            validar = true;
        }
        return validar;
    }

}
