package main;

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
        int busqueda;
        
            while (opcion) {
            int tipoBusq;
            List <Productos> pr;
            String genCom;
            
            System.out.println("Elija una OPCION numerica:\n"
                    + "1. Buscar PRODUCTO por 'CODIGO'\n"
                    + "2. Buscar PRODUCTO por 'NOMBRE'\n"
                    + "3. Buscar PRODUCTO por 'PRECIO'\n"
                    + "4. Buscar PRODUCTO por 'CATEGORIA'\n"
                    + "5. Ver 'COMISIÓN' de un Vendedor\n");
            busqueda = in.nextInt();
            switch (busqueda) {
                case 1:
                    System.out.println("Ingrese el codigo de producto.");
                    tipoBusq = in.nextInt();
                    
                    if (servi.buscarPorCodigo(tipoBusq) == null) {
                        //JOptionPane.showMessageDialog(null, "No se encontro el Producto.");
                        System.out.println("No se encontro el Producto");
                    }else{
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
                    
                    pr.forEach((productos)->{
                        System.out.println(productos);
                    });
                    
                    if (pr.isEmpty()) {
                        //JOptionPane.showMessageDialog(null, "No se encontro el Producto.");
                        System.out.println("No se encontro ningun Producto con ese nombre");
                    }else{
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
                    }else{
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
                    }else{
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
        int codV;
        int codP;
        int cantVentas;
        Servicios servi = new Servicios();
                    
            System.out.println("Ingrese el numero del codigo del VENDEDOR.");
            codV = in.nextInt();
            
            System.out.println("Ingrese el numero del codigo del PRODUCTO.");
            codP = in.nextInt();
            
            System.out.println("Ingrese la CANTIDAD DE VENTAS.");
            cantVentas = in.nextInt();
            
            servi.generarVenta(codP, codV, cantVentas);
            
    }

    private static void calcularComision() {
        int numV;
        Servicios servi = new Servicios();
        
        System.out.println("Ingrese su CODIGO de vendedor");
        numV = in.nextInt();
        
        System.out.println("Su comision es: " + servi.calcularComision(numV) + "\n");
    }
}
