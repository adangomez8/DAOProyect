import Daos.ClienteDAO;
import Daos.FacturaDAO;
import Daos.Factura_ProductoDAO;
import Daos.ProductoDAO;
import Entities.Cliente;
import Entities.Factura;
import Entities.Factura_Producto;
import Entities.Producto;
import Factory.FactoryDAO;

public class Main {
    public static void main(String[] args) {
        FactoryDAO factoryDAO = FactoryDAO.getFactoryDAO(1);
        ClienteDAO clienteDAO = factoryDAO.getClienteDAO();
        clienteDAO.createTable();
       /*
        clienteDAO.insert(new Cliente(1,"Jose","joseguidi02@gmail.com"));
        clienteDAO.insert(new Cliente(2,"Pedro","pedro@gmail.com"));
        clienteDAO.insert(new Cliente(3,"Lucas","Lucas@gmail.com"));
        clienteDAO.insert(new Cliente(4,"Nacho","Nacho@gmail.com"));
        clienteDAO.insert(new Cliente(5,"Emiliano","Emiliano@gmail.com"));
        */

        ProductoDAO productoDAO = factoryDAO.getProductoDAO();
        productoDAO.createTable();
       /*
        productoDAO.insert(new Producto(1,"Manteca",25));
        productoDAO.insert(new Producto(2,"Queso Crema",15));
        productoDAO.insert(new Producto(3,"Jamon",8));
        productoDAO.insert(new Producto(4,"Palta",250));
        */

        FacturaDAO facturaDAO = factoryDAO.getFacturaDAO();
        facturaDAO.createTable();
        /*
        facturaDAO.insert(new Factura(1,1));
        facturaDAO.insert(new Factura(1,2));
        facturaDAO.insert(new Factura(2,3));
        facturaDAO.insert(new Factura(3,4));
        facturaDAO.insert(new Factura(1,5));
         */

        Factura_ProductoDAO factura_productoDAO = factoryDAO.getFacturaProductoDAO();
        factura_productoDAO.createTable();
        /*
        factura_productoDAO.insert(new Factura_Producto(1,4,3));
        factura_productoDAO.insert(new Factura_Producto(1,1,10));
        factura_productoDAO.insert(new Factura_Producto(2,3,2));
        factura_productoDAO.insert(new Factura_Producto(4,2,1));
        factura_productoDAO.insert(new Factura_Producto(4,3,4));
        factura_productoDAO.insert(new Factura_Producto(4,4,100));
         */
    }
}