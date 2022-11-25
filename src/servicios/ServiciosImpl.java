package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entidades.Productos;
import entidades.Vendedor;
import entidades.Ventas;
import java.time.LocalDate;

public class ServiciosImpl implements IServicios {

    private List<Productos> producto;
    private List<Vendedor> vendedor;
    private List<Ventas> ventas;

    public ServiciosImpl() {
        this.producto = new ArrayList<>();
        this.vendedor = new ArrayList<>();
        this.ventas = new ArrayList<>();

        producto.add(new Productos(1, "Lavarropa", 150000, "Eletrodomestico"));
        producto.add(new Productos(2, "Cocina", 100000, "Eletrodomestico"));
        producto.add(new Productos(3, "Cerveza", 500, "Bebida"));
        producto.add(new Productos(4, "Gaseosa", 400, "Bebida"));
        producto.add(new Productos(5, "Leche", 200, "Lacteo"));
        producto.add(new Productos(6, "Leche Descremada", 100, "Lacteo"));
        producto.add(new Productos(7, "Yogurt", 300, "Lacteo"));
        producto.add(new Productos(8, "Maiz", 100, "Cereal"));

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

    @Override
    public Productos buscarPorCodigo(int id) {
        Productos prod = new Productos();

        for (Productos productos : producto) {
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
        for (Productos productos : producto) {
            if (productos.getNombre().toLowerCase().contains(nomb)) {
                pr.add(productos);
            }
        }
        return pr;
    }

    @Override
    public List<Productos> buscarPorPrecio(double precio) {
        List<Productos> pr = new ArrayList<>();
        for (Productos productos : producto) {
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

        for (Productos productos : producto) {
            if (productos.getCategoria().toLowerCase().contains(cat)) {
                pr.add(productos);
            }
        }
        return pr;
    }

    @Override
    public Ventas generarVenta(int codProd, int codVend, int cantVent) {
        boolean productoValido = validarProducto(codProd);
        boolean vendedorValido = validarVendedor(codVend);
        boolean cantVentaValida = validarCantVent(cantVent);
        Ventas venta = null;

        if (productoValido && vendedorValido && cantVentaValida) {
            venta = new Ventas();
            venta.setCodProd(codProd);
            venta.setCodVendedor(codVend);
            venta.setCantVentas(cantVent);
            venta.setLocalDate(LocalDate.now());

            ventas.add(venta);
        }
        
        System.out.println(ventas.size());
        return venta;
    }

    @Override
    public double calcularComision(int codVendedor) {
        double comision = 0;

        for (Ventas ventas : ventas) {
            double precio = 0;
            double porcentaje;

            if (codVendedor == ventas.getCodVendedor()) {
                if (ventas.getCantVentas() <= 2) {
                    porcentaje = 0.05;
                } else {
                    porcentaje = 0.1;
                }

                precio = precioProducto(ventas.getCodProd());
                comision += ventas.getCantVentas() * precio * porcentaje;
            }
        }
        return comision;
    }

    private double precioProducto(int codProd) {

        return buscarPorCodigo(codProd).getPrecio();
    }

    @Override
    public boolean validarProducto(int codProd) {
        boolean productoValido = false;

        for (Productos productos : producto) {
            if (codProd == productos.getCodigo()) {
                productoValido = true;
                break;
            }
        }
        if (!productoValido) {
        JOptionPane.showMessageDialog(null, "El Producto " + codProd + " NO EXISTE");    
        }
        return productoValido;
    }

    @Override
    public boolean validarVendedor(int codVend) {
        boolean vendedorValido = false;

        for (Ventas vent : ventas) {
            if (codVend == vent.getCodVendedor()) {
                vendedorValido = true;
                break;
            }
        }
        if (!vendedorValido) {
            JOptionPane.showMessageDialog(null, "El vendedor " + codVend + " NO EXISTE");
        }
        return vendedorValido;
    }

    @Override
    public boolean validarCantVent(int cantVent) {
        boolean cantVentaValida = false;
        if (cantVent >= 1) {
            cantVentaValida = true;
        } 
        if (!cantVentaValida) {
            JOptionPane.showMessageDialog(null, "La cantidad de Venta NO puede ser menos a 1");
        }
        return cantVentaValida;
    }

}
