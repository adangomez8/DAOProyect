
/*import Entities.Cliente;
import Entities.Factura;
import Entities.Factura_Producto;
import Entities.Producto;*/
import Daos.MYSQL.MYSQL_ClienteDAO;
import Daos.MYSQL.MYSQL_FacturaDAO;
import Daos.MYSQL.MYSQL_FacturaProductoDAO;
import Daos.MYSQL.MYSQL_ProductoDAO;
import Factory.FactoryDAO;

public class Main {
    public static void main(String[] args) {
        FactoryDAO factoryDAO = FactoryDAO.getFactoryDAO(1);

        MYSQL_ClienteDAO clienteDAO= factoryDAO.getClienteDAO();
        MYSQL_ProductoDAO productoDAO = factoryDAO.getProductoDAO();
        MYSQL_FacturaDAO facturaDAO = factoryDAO.getFacturaDAO();
        MYSQL_FacturaProductoDAO factura_productoDAO = factoryDAO.getFacturaProductoDAO();

        /*clienteDAO.createTable();
        productoDAO.createTable();
        facturaDAO.createTable();
        factura_productoDAO.createTable();*/

        productoDAO.getProductoConMasRecaudacion();
    }
}