package main;

import controlador.Controlador;

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

    public static void main(String[] args) {
        Controlador control = new Controlador();
        control.menuPrincipal();
    }
}
