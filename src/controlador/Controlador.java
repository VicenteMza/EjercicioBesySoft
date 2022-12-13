package controlador;

import entidades.Productos;
import entidades.Vendedor;
import entidades.Ventas;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import servicios.IServicios;
import servicios.ServiciosImpl;
import javax.swing.JOptionPane;

public class Controlador {

    private IServicios iServicios;
    private JOptionPane jOptionPane;

    public Controlador() {
        this.iServicios = new ServiciosImpl();
        this.jOptionPane = new JOptionPane();
    }

    public void menuPrincipal() {
        int busqueda = 0;
        String opcion = "";

        while (true) {

            while (true) {
                opcion = jOptionPane.showInputDialog("Elija una Opcion\n"
                        + "1. Menu Administracion\n"
                        + "2. Menu Venta\n"
                        + "3. Salir o Cancelar\n");
                if (opcion == null) {
                    busqueda = 3;
                    break;
                }

                try {
                    busqueda = Integer.parseInt(opcion);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un NUMERO entero\n");
                    System.out.println(e);
                }
            }

            switch (busqueda) {
                case 1:
                    this.menuAdministracion();
                    break;
                case 2:
                    this.menuVenta();
                    break;
                case 3:
                    System.out.println("Saliendo del Programa");
                    return;
                default:
                    JOptionPane.showMessageDialog(jOptionPane, "***Opcion ingresada INCORRECTA vuelva  intentar.***");
                    System.out.println("***Opcion ingresada INCORRECTA vuelva  intentar.***");
            }
        }
    }

    public void menuAdministracion() {
        String opcion;
        int busqueda = 0;

        while (true) {

            opcion = jOptionPane.showInputDialog("Elija una Opcion\n"
                    + "1. Cargar un Producto\n"
                    + "2. Ingresar un Vendedor\n"
                    + "3. Ver todas las ventas de un Vendedor\n"
                    + "4. Ver el Sueldo del mes de un vendedor\n"
                    + "5. Salir o Cancelar\n");

            if (opcion == null) {
                busqueda = 4;
                break;
            }

            try {
                busqueda = Integer.parseInt(opcion);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar un NUMERO entero\n");
                System.out.println(e);
            }

            switch (busqueda) {
                case 1:
                    this.cargarProducto();
                    break;
                case 2:
                    this.cargarUnVendedor();
                    break;
                case 3:
                    this.verVentasDeUnVendedor();
                    break;
                case 4:
                    this.calcularElSueldoDelMes();
                    break;
                case 5:
                    System.out.println("Volviendo al Menu Principal");
                    return;
                default:
                    JOptionPane.showMessageDialog(jOptionPane, "***Opcion ingresada INCORRECTA vuelva  intentar.***");
                    System.out.println("***Opcion ingresada INCORRECTA vuelva  intentar.***");
            }
        }
    }

    public void menuVenta() {
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
                    System.out.println("Vuelta al Menu Principal");
                    menuPrincipal();
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
        List<Productos> pr = new ArrayList<>();
        String precioIngresado;

        do {
            precioIngresado = JOptionPane.showInputDialog("Ingrese el Precio maximo de busqueda.");

            if (precioIngresado == null) {
                break;
            } else {
                try {
                    precioProducto = Double.parseDouble(precioIngresado);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(jOptionPane, "Debe ingresar Precio Numerico");
                    System.out.println(e);
                    continue;
                }

                pr = iServicios.buscarPorPrecio(precioProducto);

                if (pr.isEmpty()) {
                    jOptionPane.showMessageDialog(jOptionPane, "***No se encontro ningun Producto con el Precio: " + precioProducto + " ***");
                } else {
                    for (Productos prod : pr) {
                        System.out.println(prod);
                    }
                    JOptionPane.showMessageDialog(jOptionPane, pr.toString());
                    elegirSiCompraONo();
                    break;
                }
            }

        } while (true);
    }

    private void BusquedaPorCategoria() {
        List<Productos> pr = new ArrayList<>();
        String categoria;

        do {
            categoria = JOptionPane.showInputDialog("Ingrese la CATEGORIA del producto");
            if (categoria == null) {
                break;
            } else {
                if (!categoria.equals("")) {
                    pr = iServicios.buscarPorCategoria(categoria);
                }

                if (pr.isEmpty()) {
                    JOptionPane.showMessageDialog(jOptionPane, "***No se encontro Productos con esa categoria***");
                } else {
                    for (Productos prod : pr) {
                        System.out.println(prod);
                    }
                    JOptionPane.showMessageDialog(jOptionPane, pr);
                    elegirSiCompraONo();
                    break;
                }
            }
        } while (true);
    }

    private int seleccionarOpcionMenu() {
        int busqueda;
        String opcion = "";
        do {
            opcion = JOptionPane.showInputDialog(""
                    + "1. Buscar PRODUCTO por 'CODIGO'\n"
                    + "2. Buscar PRODUCTO por 'NOMBRE'\n"
                    + "3. Buscar PRODUCTO por 'PRECIO'\n"
                    + "4. Buscar PRODUCTO por 'CATEGORIA'\n"
                    + "5. Ver 'COMISIÓN' de un Vendedor\n"
                    + "6. SALIR o CANCELAR");

            if (opcion == null) {
                busqueda = 6;
                break;
            }

            try {
                busqueda = Integer.parseInt(opcion);
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
                System.out.println(e);
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
        String siONo = "";

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
                System.out.println("Regresaras al Menú de Busqueda de productos");
                break;
            }
        } while (true);
    }

    private void cargarProducto() {
        Productos producto;
        int codProduc;
        String nombProd;
        double precioProd;
        String categProd;
        String aux, aux1;

        while (true) {
            aux = jOptionPane.showInputDialog("Ingrese del codigo del PRODUCTO");
            if (aux == null) {
                break;
            }
            nombProd = jOptionPane.showInputDialog("Ingrese El nombre del PRODUCTO");
            if (nombProd == null) {
                break;
            }
            aux1 = jOptionPane.showInputDialog("Ingrese el PRECIO del PRODUCTO.");
            if (aux1 == null) {
                break;
            }
            categProd = jOptionPane.showInputDialog("Ingrese CATEGORIA del PRODUCTO.");
            if (categProd == null) {
                break;
            }

            try {
                codProduc = Integer.parseInt(aux);
                precioProd = Double.parseDouble(aux1);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "El Codigo de producto y el precio deben ser numericos");
                System.out.println(e);
                continue;
            }

            producto = iServicios.cargarProducto(codProduc, nombProd, precioProd, categProd);

            if (producto != null) {
                JOptionPane.showMessageDialog(jOptionPane, "Se registro la carga del Producto Exitosamente\n" + producto);
                System.out.println("Se registro la carga del Producto Exitosamente\n" + producto);
                break;
            }

        }
    }

    private void cargarUnVendedor() {
        Vendedor vendedor;
        int codVendedor;
        String nombreVendedor;
        double sueldoVendedor;
        String aux, aux1;

        while (true) {
            aux = jOptionPane.showInputDialog("Ingrese el Codigo del Vendedor");
            if (aux == null) {
                break;
            }
            nombreVendedor = jOptionPane.showInputDialog("Ingrese El nombre del Vendedor");
            if (nombreVendedor == null) {
                break;
            }
            aux1 = jOptionPane.showInputDialog("Ingrese el Sueldo del Vendedor.");
            if (aux1 == null) {
                break;
            }

            try {
                codVendedor = Integer.parseInt(aux);
                sueldoVendedor = Double.parseDouble(aux1);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "El Codigo del vendedor y el sueldo deben ser Numericos");
                System.out.println(e);
                continue;
            }

            vendedor = iServicios.cargarVededor(codVendedor, nombreVendedor, sueldoVendedor);

            if (vendedor != null) {
                JOptionPane.showMessageDialog(jOptionPane, "Se registro la carga del Vendedor Exitosamente\n" + vendedor);
                System.out.println("Se registro la carga del Vendedor Exitosamente\n" + vendedor);
                break;
            }

        }
    }

    private void verVentasDeUnVendedor() {
        List<Ventas> vent = new ArrayList<>();
        int codVendedor;
        String aux;

        while (true) {
            aux = jOptionPane.showInputDialog("Ingrese el Codigo del Vendedor");
            if (aux == null) {
                break;
            }

            try {
                codVendedor = Integer.parseInt(aux);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "El Codigo del vendedor debe ser Numericos");
                System.out.println(e);
                continue;
            }

            vent = iServicios.verTodasLasVentas(codVendedor);

            if (!vent.isEmpty()) {
                JOptionPane.showMessageDialog(jOptionPane, "Todas las ventas de " + iServicios.buscarVendedorPorCodigo(codVendedor).getNombre() + "\n" + vent);
                System.out.println("Todas las ventas son: " + vent);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El codigo Ingresado es Incorrecto");
            }

        }
    }

    private void calcularElSueldoDelMes() {
        int codVendedor;
        String aux;

//        String[] date = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
//        String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//        String[] year = {"2016", "2017", "2018", "2019", "2020"};
//        JComboBox jcd = new JComboBox(date);
//        JComboBox jcm = new JComboBox(month);
//        JComboBox jcy = new JComboBox(year);
//
//        jcd.setEditable(true);
//        jcm.setEditable(true);
//        jcy.setEditable(true);
//
//        //create a JOptionPane
//        Object[] options = new Object[]{};
//        JOptionPane jop = new JOptionPane("Please Select",
//                JOptionPane.QUESTION_MESSAGE,
//                JOptionPane.DEFAULT_OPTION,
//                null, options, null);
//
//        //add combos to JOptionPane
//        jop.add(jcd);
//        jop.add(jcm);
//        jop.add(jcy);
//
//        //create a JDialog and add JOptionPane to it 
//        JDialog diag = new JDialog();
//        diag.getContentPane().add(jop);
//        diag.pack();
//        diag.setVisible(true);


        while (true) {
            
            
            aux = jOptionPane.showInputDialog("Ingrese el Codigo del Vendedor");

            if (aux == null) {
                break;
            }

            try {
                codVendedor = Integer.parseInt(aux);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jOptionPane, "El Codigo del vendedor debe ser Numericos");
                System.out.println(e);
                continue;
            }
            
            String[] meses = {"Enero", "Febrero", "Marzo", "Abril",
                "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            JComboBox<String> combo = new JComboBox<>(meses);

            String[] options = {"OK", "Cancel"};

            int selection = JOptionPane.showOptionDialog(null, combo, "Lista de Meses",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                    options[0]);

            int mesElegido = combo.getSelectedIndex();

            if (selection == 1) {
                System.out.println("cancelaste");
                break;

            } else {
                System.out.println("selection is: " + mesElegido);

                double sueldoMes = iServicios.calcularSueldoMes(codVendedor, mesElegido);
                JOptionPane.showMessageDialog(jOptionPane, "El sueldo del mes de " + combo.getSelectedItem().toString() + 
                        ", es: " + sueldoMes);
                System.out.println(sueldoMes);
                break;
            }
        }

    }
}
