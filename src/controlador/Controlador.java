package controlador;

import entidades.Productos;
import entidades.Ventas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import servicios.IServicios;
import servicios.ServiciosImpl;
import javax.swing.JOptionPane;

public class Controlador {

    private Scanner in;
    private IServicios iServicios;
    private JOptionPane jOptionPane;

    public Controlador() {
        this.in = new Scanner(System.in);
        this.iServicios = new ServiciosImpl();
        this.jOptionPane = new JOptionPane();
    }

    public void menuPrincipal() {
        int busqueda = 0;

        while (true) {

            busqueda = this.seleccionarOpcionMenu();

            switch (busqueda) {
                case 1:
                    this.busquedaPorCodigoProducto();
                    break;
                case 2:
                    this.busquedaPorNombreDeProducto();
                    break;
                case 3:
                    this.busquedaPorPrecioDeProducto();
                    break;
                case 4:
                    this.BusquedaPorCategoria();
                    break;
                case 5:
                    this.calcularComision();
                    break;
                case 6:
                    return;
                default:
                    JOptionPane.showMessageDialog(jOptionPane, "Opcion ingresada INCORRECTA vuelva  intentar.");
                    System.out.println("Opcion ingresada INCORRECTA vuelva  intentar.");
                    break;
            }
        }
    }

    private void busquedaPorCodigoProducto() {
        int codProd = 0;
        Productos producto;
        String aux;

        do {
            aux = jOptionPane.showInputDialog("Ingrese el codigo de producto.");
            if (aux == null) {
                break;
            }

            try {
                codProd = Integer.parseInt(aux);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un codigo de producto(Numerico)");
                System.out.println(e);
                continue;
            }
            producto = iServicios.buscarPorCodigo(codProd);

            if (producto != null) {
                JOptionPane.showMessageDialog(jOptionPane, producto);
                elegirSiCompraONo();
                break;
            } else {
                JOptionPane.showMessageDialog(jOptionPane, "***El codigo " + codProd + " NO EXISTE***");
            }
        } while (true);
    }

    private void busquedaPorNombreDeProducto() {
        List<Productos> pr = new ArrayList<>();
        String nombreProducto = "";

        do {
            nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto.");
            pr = iServicios.buscarPorNombre(nombreProducto);

            if (pr.isEmpty()) {
                JOptionPane.showMessageDialog(jOptionPane, "***No se encontro ningun Producto con el nombre " + nombreProducto + " ***");
            } else {
                pr.forEach((productos) -> {
                    System.out.println(productos);
                });

                elegirSiCompraONo();
                break;
            }
        } while (true);
    }

    private void busquedaPorPrecioDeProducto() {
        double precioProducto = 0;
        List<Productos> pr;

        do {
            try {
                precioProducto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Precio maximo de busqueda."));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar Precio Numerico");
                System.out.println(e);
            }
        } while (true);

        pr = iServicios.buscarPorPrecio(precioProducto);

        JOptionPane.showMessageDialog(jOptionPane, pr.toString());

        if (pr.isEmpty()) {
            jOptionPane.showMessageDialog(jOptionPane, "***No se encontro ningun Producto con el Precio: " + precioProducto + " ***");
        } else {
            elegirSiCompraONo();

        }
    }

    private void BusquedaPorCategoria() {
        List<Productos> pr;

        do {
            System.out.println("Escriba la CATEGORIA del producto.");
            String cat = in.next();
            pr = iServicios.buscarPorCategoria(cat);

            if (pr.isEmpty()) {
                JOptionPane.showMessageDialog(jOptionPane, "***No se encontro Productos con esa categoria***");
            } else {
                for (Productos prod : pr) {
                    System.out.println(prod);
                }
                elegirSiCompraONo();
                break;
            }
        } while (true);
    }

    private int seleccionarOpcionMenu() {
        int busqueda;
        String aux;
        do {
            aux = JOptionPane.showInputDialog(""
                    + "1. Buscar PRODUCTO por 'CODIGO'\n"
                    + "2. Buscar PRODUCTO por 'NOMBRE'\n"
                    + "3. Buscar PRODUCTO por 'PRECIO'\n"
                    + "4. Buscar PRODUCTO por 'CATEGORIA'\n"
                    + "5. Ver 'COMISIÓN' de un Vendedor\n"
                    + "6. SALIR o CANCELAR");
            if (aux == null) {
                busqueda = 6;
                break;
            }

            try {
                busqueda = Integer.parseInt(aux);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un NUMERO entero\n");
            }

        } while (true);
        return busqueda;
    }

    private void generarVenta() {
        int codigoVendedor = 0;
        int codigoProducto = 0;
        int cantVentas = 0;
        String cVende, cProd, cantV;
        Ventas venta;

        do {
            cVende = jOptionPane.showInputDialog("Ingrese del codigo del VENDEDOR.");
            if (cVende == null) {
                break;
            }
            cProd = jOptionPane.showInputDialog("Ingrese del codigo del PRODUCTO.");
            if (cProd == null) {
                break;
            }
            cantV = jOptionPane.showInputDialog("Ingrese la CANTIDAD DE VENTAS.");
            if (cantV == null) {
                break;
            }
            
            try {
                codigoVendedor = Integer.parseInt(cVende);
                codigoProducto = Integer.parseInt(cProd);
                cantVentas = Integer.parseInt(cantV);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Todos los valores deben ser NUMERICOS");
                System.out.println("Todos los valores deben ser NUMERICOS" + e);
                continue;
            }

            venta = iServicios.generarVenta(codigoProducto, codigoVendedor, cantVentas);

            if (venta != null) {
                JOptionPane.showMessageDialog(jOptionPane, "Se registro la venta Exitosamente" + venta);
                break;
            }

        } while (true);
    }

    private void calcularComision() {
        int codVendedor = 0;
        double comision;
        String aux;
        
        do {
            aux = jOptionPane.showInputDialog("Ingrese su CODIGO de vendedor");
            if (aux == null) {
                break;
            }
            
            try {
                codVendedor = Integer.parseInt(aux);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un CODIGO NUMERICO entero");
                System.out.println(e);
                continue;
            }
            
            comision = iServicios.calcularComision(codVendedor);
            System.out.println(comision);
            if (comision >= 0) {
                JOptionPane.showMessageDialog(jOptionPane, "Su comision es: " + comision + "\n");
                break;
            }
        } while (true);

        
    }

    private void elegirSiCompraONo() {
        String siONo;

        do {

            siONo = jOptionPane.showInputDialog("Quiere Realizar una venta?\n"
                    + "Y/N");
            if (siONo == null) {
                break;
            }

            if (siONo.equalsIgnoreCase("Y")) {
                generarVenta();
                break;
            } else if (siONo.equalsIgnoreCase("N")) {
                JOptionPane.showMessageDialog(jOptionPane, "Regresaras al Menú de Busqueda de productos");
                break;
            }
        } while (true);
    }
}
