

import Daos.MYSQL.MYSQL_ClienteDAO;
import Daos.MYSQL.MYSQL_FacturaDAO;
import Daos.MYSQL.MYSQL_FacturaProductoDAO;
import Daos.MYSQL.MYSQL_ProductoDAO;
import Factory.FactoryDAO;

public class Main {
    public static void main(String[] args) {
        FactoryDAO factoryDAO = FactoryDAO.getFactoryDAO(1);

        MYSQL_ClienteDAO clienteDAO= factoryDAO.getClienteDAO();
        clienteDAO.createTable();
        clienteDAO.readCSV();
        
        MYSQL_ProductoDAO productoDAO = factoryDAO.getProductoDAO();
        productoDAO.createTable();
        productoDAO.readCSV();
        
        MYSQL_FacturaDAO facturaDAO = factoryDAO.getFacturaDAO();
        facturaDAO.createTable();
        facturaDAO.readCSV();
        
        MYSQL_FacturaProductoDAO factura_productoDAO = factoryDAO.getFacturaProductoDAO();
        factura_productoDAO.createTable();
        factura_productoDAO.readCSV();

        System.out.println(productoDAO.getProductoConMasRecaudacion());
        
        System.out.println("-----------");

        clienteDAO.getClientesPorFacturacion();
    }
}