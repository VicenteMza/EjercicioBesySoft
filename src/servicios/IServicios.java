package servicios;

import entidades.Productos;
import java.util.List;

public interface IServicios {
    
        public Productos buscarPorCodigo(int id);
        public List<Productos> buscarPorNombre(String nombrePr);
        public List<Productos> buscarPorPrecio(double precio);
        public List<Productos> buscarPorCategoria(String categ);
        public boolean generarVenta(int numProd, int codVend, int cantVent);
        public double calcularComision(int codVendedor);
        
        public boolean validarProducto(int numProd);
        public boolean validarVendedor(int codVend);
        public boolean validarCantVent(int cantVent);
        

}
