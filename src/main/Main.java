package main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
*Deberá implementarse la funcionalidad básica de una tienda de productos:

- Se podrán almacenar productos (código, nombre, precio, categoría), vendedor (código, nombre, sueldo).

- Al registrar una venta, se tendrá que relacionar el producto con el vendedor que realizo la venta. 

- Se debe de poder calcular la comisión de ventas por cada vendedor, el cual se obtiene de un 5% de las
  ventas realizadas en el caso de vender hasta dos productos y un 10% al vender más de dos productos.

- Debe implementarse distintos tipos de buscadores de productos, por ejemplo el buscar por categoría.

- La aplicación tendrá que implementar el manejo de excepciones correctamente.

- Deberá diseñarse un Diagrama de Entidad Relación para la solución.

- Deberá ejecutarse por consola y se almacenarán los datos en memoria.

 */
public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Servicios servi = new Servicios();
        boolean opcion = true;
        int busqueda = 0;
        String genCom = "";

        while (opcion) {
            int tipoBusq = 0;
            boolean salida = false;
            List<Productos> pr;

            System.out.println("Elija una OPCION numerica:\n"
                    + "1. Buscar PRODUCTO por 'CODIGO'\n"
                    + "2. Buscar PRODUCTO por 'NOMBRE'\n"
                    + "3. Buscar PRODUCTO por 'PRECIO'\n"
                    + "4. Buscar PRODUCTO por 'CATEGORIA'\n"
                    + "5. Ver 'COMISIÓN' de un Vendedor\n");

            try {
                busqueda = in.nextInt();
            } catch (InputMismatchException e) {
                //JOptionPane.showMessageDialog(null, "Debe ingresar un NUMERO estero\n");
                System.out.println("Debe ingresar un NUMERO entero"
                        + "\n--------------------");
                //limpio la variable 'in' para recibir un nuevo dato
                in.next();
            }

            switch (busqueda) {
                case 1:
                    do {
                        System.out.println("Ingrese el codigo de producto.");

                        try {
                            tipoBusq = in.nextInt();
                            salida = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar un codigo de producto(Numerico)"
                                    + "\n--------------------");
                            in.nextLine();
                            salida = true;
                        }
                    } while (salida);

                    if (servi.buscarPorCodigo(tipoBusq) == null) {
                        //JOptionPane.showMessageDialog(null, "No se encontro el Producto.");
                        System.out.println("No se encontro el Producto");
                    } else {
                        System.out.println(servi.buscarPorCodigo(tipoBusq));

                        System.out.println("Quiere Realizar una compra?\n"
                                + "Y/N");
                        genCom = in.next();

                        if (genCom.equalsIgnoreCase("Y")) {
                            generarVenta();
                        } else {
                            System.out.println("Regresa al Menu de Busqueda de productos");
                        }
                    }

                    break;
                case 2:
                    System.out.println("Ingrese el nombre del producto.");
                    String nomP = in.next();
                    pr = servi.buscarPorNombre(nomP);

                    pr.forEach((productos) -> {
                        System.out.println(productos);
                    });

                    if (pr.isEmpty()) {
                        //JOptionPane.showMessageDialog(null, "No se encontro el Producto.");
                        System.out.println("No se encontro ningun Producto con ese nombre");
                    } else {
                        System.out.println("Quiere Realizar una compra?\n"
                                + "Y/N");
                        genCom = in.next();

                        if (genCom.equalsIgnoreCase("Y")) {
                            generarVenta();
                        } else {
                            System.out.println("Regresa al Menu de Busqueda de productos");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el PRECIO del producto.");
                    tipoBusq = in.nextInt();
                    pr = servi.buscarPorPrecio(tipoBusq);
                    for (Productos prod : pr) {
                        System.out.println(prod);
                    }

                    if (pr.isEmpty()) {
                        //JOptionPane.showMessageDialog(null, "No se encontro el Producto.");
                        System.out.println("No se encontro ningun Producto con ese Precio");
                    } else {
                        boolean rCom = true;
                        while (rCom) {
                            System.out.println("Quiere Realizar una compra?\n"
                                    + "Y/N");
                            genCom = in.next();
                        }

                        if (genCom.equalsIgnoreCase("Y")) {
                            generarVenta();
                        } else {
                            System.out.println("Regresa al Menu de Busqueda de productos");
                        }
                    }
                    break;
                case 4:

                    System.out.println("Ingrese la CATEGORIA del producto.");
                    String cat = in.next();
                    pr = servi.buscarPorCategoria(cat);

                    for (Productos prod : pr) {
                        System.out.println(prod);
                    }

                    if (pr.isEmpty()) {
                        //JOptionPane.showMessageDialog(null, "No se encontro el Producto.");
                        System.out.println("No se encontro Productos con esa categoria");
                    } else {
                        System.out.println("Quiere Realizar una compra?\n"
                                + "Y/N");
                        genCom = in.next();

                        if (genCom.equalsIgnoreCase("Y")) {
                            generarVenta();
                        } else {
                            System.out.println("Regresa al Menu de Busqueda de productos");
                        }
                    }

                    break;
                case 5:
                    calcularComision();
                    break;
                default:
                    System.out.println("Opcion ingresada INCORRECTA vuelva  intentar.");
                    //JOptionPane.showMessageDialog(null, "Opcion ingresada INCORRECTA vuelva  intentar");
                    break;
            }
        }
    }

    public static void generarVenta() {
        int codV = 0;
        int codP = 0;
        int cantVentas = 0;
        Servicios servi = new Servicios();
        boolean salida = false;

        do {

            try {
                System.out.println("Ingrese el numero del codigo del VENDEDOR.");
                codV = in.nextInt();

                System.out.println("Ingrese el numero del codigo del PRODUCTO.");
                codP = in.nextInt();

                System.out.println("Ingrese la CANTIDAD DE VENTAS.");
                cantVentas = in.nextInt();
                salida = false;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar NUMEROS estero\n" + e);
                in.next();
                salida = true;
            }
        } while (salida);

        servi.generarVenta(codP, codV, cantVentas);

    }

    private static void calcularComision() {
        int numV = 0;
        boolean salida = true;
        Servicios servi = new Servicios();

        do {
            System.out.println("Ingrese su CODIGO de vendedor");
            try {
                numV = in.nextInt();
                salida = false;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un CODIGO NUMERICO entero\n" + e);
                in.next();
                salida = true;
            }

        } while (salida);

        System.out.println("Su comision es: " + servi.calcularComision(numV) + "\n");
    }
}
