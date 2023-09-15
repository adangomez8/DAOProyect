package Factory;

import Daos.MYSQL.MYSQL_ClienteDAO;
import Daos.MYSQL.MYSQL_FacturaDAO;
import Daos.MYSQL.MYSQL_FacturaProductoDAO;
import Daos.MYSQL.MYSQL_ProductoDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class MYSQL_FactoryDAO extends FactoryDAO{
    public static final String DRIVER =  "com.mysql.cj.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/arquitecturas_tp1_entregable";
    public static final String user =  "root";
    public static final String pass="";
    protected static Connection conn;

    public static MYSQL_FactoryDAO instance = new MYSQL_FactoryDAO();
    protected MYSQL_FactoryDAO() {

    }
    public static MYSQL_FactoryDAO getInstance() {
        return instance;
    }


    public void createConnection(){
        try{
            Class.forName(DRIVER).getConstructor().newInstance();
            conn = DriverManager.getConnection(DBURL,user,pass);
            conn.setAutoCommit(false);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(){
        try{
            if(!conn.isClosed()) {
                conn.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public MYSQL_ProductoDAO getProductoDAO() {
        return new MYSQL_ProductoDAO();
    }

    @Override
    public MYSQL_ClienteDAO getClienteDAO() {
        return new MYSQL_ClienteDAO();
    }

    @Override
    public MYSQL_FacturaProductoDAO getFacturaProductoDAO() {
        return new MYSQL_FacturaProductoDAO();
    }

    @Override
    public MYSQL_FacturaDAO getFacturaDAO() {
        return new MYSQL_FacturaDAO();
    }

}
