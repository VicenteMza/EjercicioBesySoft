package controlador;

import entidades.Productos;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import servicios.IServicios;
import servicios.ServiciosImpl;

public class Controlador {

    private Scanner in = new Scanner(System.in);
    private IServicios iServicios = new ServiciosImpl();
    private boolean opcion = true;
    private int busqueda = 0;

    public void menuPrincipal() {

        while (opcion) {
            int tipoBusq = 0;
            boolean salida = false;
            List<Productos> pr;
            Productos producto;

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

                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar un codigo de producto(Numerico)"
                                    + "\n--------------------");
                            in.nextLine();
                            salida = true;
                        }

                        if (!iServicios.validarProducto(tipoBusq)) {
                            mensajeNoEncontrado(busqueda);
                            salida = true;

                        } else {
                            System.out.println(iServicios.buscarPorCodigo(tipoBusq));

                            elegirSiCompraONo();
                            salida = false;
                        }
                    } while (salida);
                    break;
                case 2:
                    do {
                        System.out.println("Ingrese el nombre del producto.");
                        String nomP = in.next();
                        pr = iServicios.buscarPorNombre(nomP);

                        if (pr.isEmpty()) {
                            mensajeNoEncontrado(busqueda);
                            salida = true;
                        } else {
                            pr.forEach((productos) -> {
                                System.out.println(productos);
                            });
                            elegirSiCompraONo();
                            salida = false;
                        }
                    } while (salida);

                    break;
                case 3:
                    salida = false;
                    do {
                        try {
                            System.out.println("Ingrese el Precio maximo del Producto.");
                            tipoBusq = in.nextInt();
                            salida = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar Precio Numerico"
                                    + "\n--------------------");
                            in.nextLine();
                            salida = true;
                        }
                    } while (salida);

                    pr = iServicios.buscarPorPrecio(tipoBusq);
                    for (Productos prod : pr) {
                        System.out.println(prod);
                    }

                    if (pr.isEmpty()) {
                        mensajeNoEncontrado(busqueda);
                    } else {
                        elegirSiCompraONo();

                    }
                    break;
                case 4:
                    salida = true;

                    do {
                        System.out.println("Escriba la CATEGORIA del producto.");
                        String cat = in.next();
                        pr = iServicios.buscarPorCategoria(cat);

                        if (pr.isEmpty()) {
                            mensajeNoEncontrado(busqueda);
                        } else {
                            for (Productos prod : pr) {
                                System.out.println(prod);
                            }
                            elegirSiCompraONo();
                            salida = false;
                        }
                    } while (salida);

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

    private void generarVenta() {
        int codV = 0;
        int codP = 0;
        int cantVentas = 0;
        boolean salida = false;
        boolean valProd;
        boolean valVend;
        boolean valCantVenta;

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
                System.out.println("Todos los valores deben ser NUMERICOS\n");
                in.next();
                salida = true;
            }
            valProd = iServicios.validarProducto(codP);
            valVend = iServicios.validarVendedor(codV);
            valCantVenta = iServicios.validarCantVent(cantVentas);

            if (valProd && valVend && valCantVenta) {
                iServicios.generarVenta(codP, codV, cantVentas);
            } else {
                int aux = 0;
                if (!valProd) {
                    aux = 1;
                }
                if (valVend) {
                    aux = 2;
                }
                if (valCantVenta) {
                    aux = 3;
                }
                mensajeNoEncontrado(aux);

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
            System.out.println("Quiere Realizar una compra?\n"
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

    private void mensajeNoEncontrado(int tipoBusq) {
        String mensaje = "";

        if (tipoBusq == 1) {
            mensaje = "***No se encontro el Producto***";
        }
        if (tipoBusq == 2) {
            mensaje = "***No se encontro ningun Producto con ese nombre***";
        }
        if (tipoBusq == 3) {
            mensaje = "***No se encontro ningun Producto con ese Precio***";
        }
        if (tipoBusq == 4) {
            mensaje = "***No se encontro Productos con esa categoria***";
        }
        //JOptionPane.showMessageDialog(null, mensaje);
        System.out.println(mensaje);
    }
}
