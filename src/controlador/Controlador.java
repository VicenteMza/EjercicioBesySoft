package controlador;

import entidades.Productos;
import entidades.Ventas;
import java.util.InputMismatchException;
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
                    this.busquedaPorProducto();
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

    private void busquedaPorProducto() {
        int tipoBusq = 0;

        do {

            try {
                tipoBusq = Integer.parseInt(jOptionPane.showInputDialog("Ingrese el codigo de producto."));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un codigo de producto(Numerico)");
                System.out.println("Debe ingresar un codigo de producto(Numerico)"
                        + "\n--------------------");
                continue;
            }

            if (!iServicios.validarProducto(tipoBusq)) {
                JOptionPane.showMessageDialog(jOptionPane, "***No se encontro el Producto***");
            } else {
                JOptionPane.showMessageDialog(jOptionPane, iServicios.buscarPorCodigo(tipoBusq));
                //System.out.println(iServicios.buscarPorCodigo(tipoBusq));

                elegirSiCompraONo();
                break;
            }
        } while (true);
    }

    private void busquedaPorPrecioDeProducto() {
        int tipoBusq = 0;
        List<Productos> pr;

        do {
            System.out.println("Ingrese el Precio maximo del Producto.");

            try {
                tipoBusq = in.nextInt();
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar Precio Numerico");
                System.out.println("Debe ingresar Precio Numerico"
                        + "\n--------------------");
                in.nextLine();
            }
            break;
        } while (true);

        pr = iServicios.buscarPorPrecio(tipoBusq);
        for (Productos prod : pr) {
            System.out.println(prod);
        }

        if (pr.isEmpty()) {
            jOptionPane.showMessageDialog(jOptionPane, "***No se encontro ningun Producto con ese Precio***");
        } else {
            elegirSiCompraONo();

        }
    }

    private void busquedaPorNombreDeProducto() {
        List<Productos> pr;

        do {
            System.out.println("Ingrese el nombre del producto.");
            String nomP = in.next();
            pr = iServicios.buscarPorNombre(nomP);

            if (pr.isEmpty()) {
                JOptionPane.showMessageDialog(jOptionPane, "***No se encontro ningun Producto con ese nombre***");

            } else {
                pr.forEach((productos) -> {
                    System.out.println(productos);
                });

                elegirSiCompraONo();
                break;
            }
        } while (true);
    }

    private int seleccionarOpcionMenu() {
        int busqueda = 0;
        do {

            try {
                busqueda = Integer.parseInt(jOptionPane.showInputDialog("1. Buscar PRODUCTO por 'CODIGO'\n"
                        + "2. Buscar PRODUCTO por 'NOMBRE'\n"
                        + "3. Buscar PRODUCTO por 'PRECIO'\n"
                        + "4. Buscar PRODUCTO por 'CATEGORIA'\n"
                        + "5. Ver 'COMISIÓN' de un Vendedor\n"
                        + "6. SALIR"));
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
        Ventas venta;

        do {
            try {
                codigoVendedor = Integer.parseInt(jOptionPane.showInputDialog("Ingrese el numero del codigo del VENDEDOR."));

                codigoProducto = Integer.parseInt(jOptionPane.showInputDialog("Ingrese el Codigo del PRODUCTO."));

                cantVentas = Integer.parseInt(jOptionPane.showInputDialog("Ingrese la CANTIDAD DE VENTAS."));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Todos los valores deben ser NUMERICOS");
                System.out.println("Todos los valores deben ser NUMERICOS" + e);
                continue;
            }

            venta = iServicios.generarVenta(codigoProducto, codigoVendedor, cantVentas);

            if (venta != null) {
                JOptionPane.showMessageDialog(jOptionPane, "Se registro la venta Exitosamente" + venta);
                System.out.println("Se registro la venta Exitosamente");
                break;
            }

        } while (true);
    }

    private void calcularComision() {
        int codVendedor = 0;

        do {
            try {
                codVendedor = Integer.parseInt(jOptionPane.showInputDialog("Ingrese su CODIGO de vendedor"));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un CODIGO NUMERICO entero");
                System.out.println("Debe ingresar un CODIGO NUMERICO entero\n" + e);
            }
        } while (true);

        if (iServicios.validarVendedor(codVendedor)) {
            JOptionPane.showMessageDialog(jOptionPane, "Su comision es: " + iServicios.calcularComision(codVendedor) + "\n");
        } else {
            JOptionPane.showMessageDialog(jOptionPane, "El vendedor " + codVendedor + " NO EXISTE");
        }

    }

    private void elegirSiCompraONo() {
        String siONo = "";
        do {

            siONo = jOptionPane.showInputDialog("Quiere Realizar una venta?\n"
                    + "Y/N");

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
