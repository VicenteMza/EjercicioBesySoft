package controlador;

import entidades.Productos;
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

    private void busquedaPorProducto() {
        int tipoBusq = 0;

        do {
            System.out.println("Ingrese el codigo de producto.");

            try {
                tipoBusq = in.nextInt();

            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un codigo de producto(Numerico)");
                System.out.println("Debe ingresar un codigo de producto(Numerico)"
                        + "\n--------------------");
                in.nextLine();
                //Va a la siguiente Iteracion
                continue;
            }

            if (!iServicios.validarProducto(tipoBusq)) {
                JOptionPane.showMessageDialog(jOptionPane, "***No se encontro el Producto***");
            } else {
                System.out.println(iServicios.buscarPorCodigo(tipoBusq));

                elegirSiCompraONo();
                break;
            }
        } while (true);
    }

    private int seleccionarOpcionMenu() {
        int busqueda = 0;

        do {
            
            System.out.println("Elija una OPCION numerica:\n"
                    + "1. Buscar PRODUCTO por 'CODIGO'\n"
                    + "2. Buscar PRODUCTO por 'NOMBRE'\n"
                    + "3. Buscar PRODUCTO por 'PRECIO'\n"
                    + "4. Buscar PRODUCTO por 'CATEGORIA'\n"
                    + "5. Ver 'COMISIÓN' de un Vendedor\n"
                    + "6. SALIR");

            try {
                busqueda = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un NUMERO estero\n");
               
                //limpio la variable 'in' para recibir un nuevo dato
                in.next();

            }
        } while (true);
        return busqueda;
    }

    private void generarVenta() {
        int codV = 0;
        int codP = 0;
        int cantVentas = 0;
        boolean salida;

        do {
            salida = false;
            try {
                System.out.println("Ingrese el numero del codigo del VENDEDOR.");
                codV = in.nextInt();

                System.out.println("Ingrese el numero del codigo del PRODUCTO.");
                codP = in.nextInt();

                System.out.println("Ingrese la CANTIDAD DE VENTAS.");
                cantVentas = in.nextInt();
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Todos los valores deben ser NUMERICOS");
                System.out.println("Todos los valores deben ser NUMERICOS\n");
                in.next();
                salida = true;
            }

            if (iServicios.generarVenta(codP, codV, cantVentas)) {
                JOptionPane.showMessageDialog(jOptionPane, "Se registro la venta Exitosamente");
                System.out.println("Se registro la venta Exitosamente");
            } else {
                JOptionPane.showMessageDialog(jOptionPane, "NO se cargo la venta.");
                System.out.println("NO se cargo la venta.\n");

                salida = true;
            }
        } while (salida);
    }

    private void calcularComision() {
        int numV = 0;
        boolean salida = true;

        do {
            System.out.println("Ingrese su CODIGO de vendedor");
            try {
                numV = in.nextInt();
                salida = false;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un CODIGO NUMERICO entero");
                System.out.println("Debe ingresar un CODIGO NUMERICO entero\n");
                in.next();
                salida = true;
            }

        } while (salida);

        System.out.println("Su comision es: " + iServicios.calcularComision(numV) + "\n");
    }

    private void elegirSiCompraONo() {
        boolean salida = true;
        String genCom = "";
        do {
            System.out.println("Quiere Realizar una venta?\n"
                    + "Y/N");
            genCom = in.next();

            if (genCom.equalsIgnoreCase("Y")) {
                generarVenta();
                salida = false;
            } else if (genCom.equalsIgnoreCase("N")) {
                System.out.println("Regresa al Menú de Busqueda de productos");
                salida = false;
            }
        } while (salida);
    }
}
