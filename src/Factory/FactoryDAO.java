package Factory;

import Daos.ClienteDAO;
import Daos.FacturaDAO;
import Daos.Factura_ProductoDAO;
import Daos.ProductoDAO;

public abstract class FactoryDAO {
    private static int MYSQL_FACTORY = 1;

    public static FactoryDAO getFactoryDAO(int switchFactory){
        switch (switchFactory){
            case 1: return MYSQL_FactoryDAO.getInstance();
            default: return null;
        }
    }

    public abstract ProductoDAO getProductoDAO();
    public abstract ClienteDAO getClienteDAO();

    public abstract FacturaDAO getFacturaDAO();

    public abstract Factura_ProductoDAO getFacturaProductoDAO();
}
