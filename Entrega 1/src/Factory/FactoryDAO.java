package Factory;

import Daos.MYSQL.MYSQL_ClienteDAO;
import Daos.MYSQL.MYSQL_FacturaDAO;
import Daos.MYSQL.MYSQL_FacturaProductoDAO;
import Daos.MYSQL.MYSQL_ProductoDAO;

public abstract class FactoryDAO {
    private static int MYSQL_FACTORY = 1;

    public static FactoryDAO getFactoryDAO(int switchFactory){
        switch (switchFactory){
            case 1: return MYSQL_FactoryDAO.getInstance();
            default: return null;
        }
    }

    public abstract MYSQL_ProductoDAO getProductoDAO();
    public abstract MYSQL_ClienteDAO getClienteDAO();

    public abstract MYSQL_FacturaDAO getFacturaDAO();

    public abstract MYSQL_FacturaProductoDAO getFacturaProductoDAO();
}
