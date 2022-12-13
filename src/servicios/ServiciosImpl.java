package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entidades.Productos;
import entidades.Vendedor;
import entidades.Ventas;
import java.time.LocalDate;
import java.time.Month;

public class ServiciosImpl implements IServicios {

    private List<Productos> productos;
    private List<Vendedor> vendedor;
    private List<Ventas> ventas;

    public ServiciosImpl() {
        this.productos = new ArrayList<>();
        this.vendedor = new ArrayList<>();
        this.ventas = new ArrayList<>();

        productos.add(new Productos(1, "Lavarropa", 150000, "Eletrodomestico"));
        productos.add(new Productos(2, "Cocina", 100000, "Eletrodomestico"));
        productos.add(new Productos(3, "Cerveza", 500, "Bebida"));
        productos.add(new Productos(4, "Gaseosa", 400, "Bebida"));
        productos.add(new Productos(5, "Taladro", 200, "Ferreteria"));
        productos.add(new Productos(6, "Celular", 10000, "Tecnologia"));
        productos.add(new Productos(7, "Yogurt", 300, "Lacteo"));
        productos.add(new Productos(8, "Maiz", 100, "Cereal"));

        vendedor.add(new Vendedor(1, "Matias", 40000));
        vendedor.add(new Vendedor(2, "Maria", 50000));

        ventas.add(new Ventas(1, 1, 2, LocalDate.of(2022, Month.JANUARY, 5)));
        ventas.add(new Ventas(2, 1, 5, LocalDate.of(2022, Month.JANUARY, 8)));
        ventas.add(new Ventas(4, 1, 1, LocalDate.of(2022, Month.JANUARY, 10)));
        ventas.add(new Ventas(6, 1, 1, LocalDate.of(2022, Month.JANUARY, 13)));
        ventas.add(new Ventas(1, 2, 2, LocalDate.of(2022, Month.JULY, 15)));
    }

    @Override
    public Productos buscarPorCodigo(int id) {
        Productos product = null;

        for (Productos producto : productos) {
            if (id == producto.getCodigo()) {
                product = producto;
                break;
            }
        }
        return product;
    }

    @Override
    public List<Productos> buscarPorNombre(String nombrePr) {
        String nomb = nombrePr.toLowerCase();
        List<Productos> pr = new ArrayList<>();

        for (Productos productos : productos) {
            if (productos.getNombre().toLowerCase().contains(nomb)) {
                pr.add(productos);
            }
        }
        return pr;
    }

    @Override
    public List<Productos> buscarPorPrecio(double precio) {
        List<Productos> pr = new ArrayList<>();

        for (Productos productos : productos) {
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

        for (Productos productos : productos) {
            if (productos.getCategoria().toLowerCase().contains(cat)) {
                pr.add(productos);
            }
        }
        return pr;
    }

    @Override
    public Ventas generarVenta(int codProd, int codVend, int cantVent) {
        boolean productoValido = validarCodigoProducto(codProd);
        boolean vendedorValido = validarVendedor(codVend);
        boolean cantVentaValida = validarCantVent(cantVent);

        Ventas venta = new Ventas();
        venta.setCodProd(codProd);
        venta.setCodVendedor(codVend);
        venta.setCantVentas(cantVent);
        venta.setLocalDate(LocalDate.now());

        if (productoValido && vendedorValido && cantVentaValida) {
            if (menuConfirmacion(venta)) {
                ventas.add(venta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Venta de: " + venta + 
                    " CANCELADA. \n***REVISE LOS VALORES INGRESADOS***");
            System.out.println("Venta de: " + venta + " CANCELADA");
            venta = null;
        }

        System.out.println(ventas.size());
        return venta;
    }

    @Override
    public double calcularComision(int codVendedor) {
        double comision = 0;

        if (validarVendedor(codVendedor)) {
            for (Ventas ventas : ventas) {
                double precio = 0;

                if (codVendedor == ventas.getCodVendedor()) {
                    comision += this.comisionIndividualPorVenta(ventas);
                }
            }
        } else {
            comision = -1;
        }
        return comision;
    }

    private double precioProducto(int codProd) {

        return buscarPorCodigo(codProd).getPrecio();
    }

    private boolean validarCodigoProducto(int codProd) {
        boolean productoValido = false;

        for (Productos productos : productos) {
            if (codProd == productos.getCodigo()) {
                productoValido = true;
                break;
            }
        }
        if (!productoValido) {
            JOptionPane.showMessageDialog(null, "***El Producto " + codProd + " NO EXISTE***");
        }
        return productoValido;
    }

    private boolean validarVendedor(int codVend) {
        boolean vendedorValido = false;

        for (Ventas vent : ventas) {
            if (codVend == vent.getCodVendedor()) {
                vendedorValido = true;
                break;
            }
        }
        if (!vendedorValido) {
            JOptionPane.showMessageDialog(null, "***El vendedor " + codVend + " NO EXISTE***");
        }
        return vendedorValido;
    }

    private boolean validarCantVent(int cantVent) {
        boolean cantVentaValida = false;
        if (cantVent >= 1) {
            cantVentaValida = true;
        } else {
            System.out.println("La cantidad de venta debe ser mayor o igual que 1");
        }

        return cantVentaValida;
    }

    @Override
    public Productos cargarProducto(int codProduc, String nombProd, double precioProd, String categProd) {
        Productos prod = null;
        boolean codigoValido = validarCodigoProducto(codProduc);

        if (!codigoValido) {
            prod = new Productos();
            prod.setCodigo(codProduc);
            prod.setNombre(nombProd);
            prod.setPrecio(precioProd);
            prod.setCategoria(categProd);

            if (menuConfirmacion(prod)) {
                productos.add(prod);
            } else {
                prod = null;
            }

        } else {
            JOptionPane.showMessageDialog(null, "***El codigo ingresado ya Existe***");
        }
        for (Productos producto : productos) {
            System.out.println(producto);
        }
        System.out.println(productos.size());
        return prod;
    }

    private boolean menuConfirmacion(Object objeto) {
        String siONo;
        boolean confirmacion = false;

        while (true) {
            siONo = JOptionPane.showInputDialog("Quiere Realizar la carga de:\n"
                    + objeto
                    + "\nY/N");
            if (siONo == null) {
                break;
            }

            if (siONo.equalsIgnoreCase("Y")) {
                confirmacion = true;
                break;
            } else if (siONo.equalsIgnoreCase("N")) {
                System.out.println("Regresaras al Menú de Carga de " + objeto.getClass().getSimpleName());
                break;
            }
        }
        return confirmacion;
    }

    @Override
    public Vendedor cargarVededor(int codVendedor, String nombreVendedor, double sueldoVendedor) {
        Vendedor vende = null;
        boolean codigoValido = validarVendedor(codVendedor);

        if (!codigoValido) {
            vende = new Vendedor(codVendedor, nombreVendedor, sueldoVendedor);

            if (menuConfirmacion(vende)) {
                vendedor.add(vende);
            } else {
                vende = null;
            }

        } else {
            JOptionPane.showMessageDialog(null, "***El codigo de Vendedor Ingresado ya Existe***");
        }
        for (Vendedor v : vendedor) {
            System.out.println(v);
        }

        System.out.println(vendedor.size());
        return vende;
    }

    @Override
    public List<Ventas> verTodasLasVentas(int codVendedor) {
        List<Ventas> v = new ArrayList<>();

        if (validarVendedor(codVendedor)) {
            for (Ventas vent : ventas) {
                if (vent.getCodVendedor() == codVendedor) {
                    v.add(vent);
                }
            }
        }

        return v;
    }

    @Override
    public Vendedor buscarVendedorPorCodigo(int codVendedor) {
        Vendedor vendedor = null;
        for (Vendedor vende : this.vendedor) {
            if (vende.getCodigo() == codVendedor) {
                return vendedor = vende;
            }
        }
        return vendedor;
    }

    @Override
    public double calcularSueldoMes(int codVendedor, int mesElegido) {
        List<Ventas> lVentas = new ArrayList();
        Vendedor vendedor = this.buscarVendedorPorCodigo(codVendedor);
        double comisionDelMes = 0;
        double sueldoDelMes = 0;

        if (validarVendedor(codVendedor)) {
            lVentas = this.verTodasLasVentas(codVendedor);

            for (Ventas lVenta : lVentas) {
                if (lVenta.getLocalDate().getMonthValue() == (mesElegido + 1)) {
                    comisionDelMes += this.comisionIndividualPorVenta(lVenta);
                }
            }
            sueldoDelMes = vendedor.getSueldo() + comisionDelMes;
        }
        return sueldoDelMes;
    }

    private double comisionIndividualPorVenta(Ventas ventas) {
        double comision = -1;

        double precio = 0;
        double porcentaje;

        if (ventas.getCantVentas() <= 2) {
            porcentaje = 0.05;
        } else {
            porcentaje = 0.1;
        }

        precio = precioProducto(ventas.getCodProd());
        comision = ventas.getCantVentas() * precio * porcentaje;

        return comision;
    }
}
