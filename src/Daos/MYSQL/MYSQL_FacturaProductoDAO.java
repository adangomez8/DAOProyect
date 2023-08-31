package Daos.MYSQL;

import Entities.Factura_Producto;
import Factory.MYSQL_FactoryDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import DaoCrudInterface.DAOCrud;

import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MYSQL_FacturaProductoDAO extends MYSQL_FactoryDAO implements DAOCrud<Factura_Producto> {
    public MYSQL_FacturaProductoDAO(){
        super();
    }

    @Override
    public void createTable() {
        String sentencia = "CREATE TABLE factura_producto (" +
                "idProducto INT, " +
                "idFactura INT, " +
                "cantidad INT, " +
                "PRIMARY KEY (idProducto, idFactura), " +
                "CONSTRAINT fk_idProducto FOREIGN KEY (idProducto) REFERENCES producto(idProducto), " +
                "CONSTRAINT fk_idFactura FOREIGN KEY (idFactura) REFERENCES factura(idFactura)" +
                ")";
        try {
            this.createConnection();
            conn.prepareStatement(sentencia).execute();
            conn.commit();
            this.readCSV();
            this.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Factura_Producto get(int id) {
        return null;
    }

    @Override
    public List<Factura_Producto> getAll() {
        String sentencia = "SELECT * FROM factura";
        try{
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Factura_Producto> aux = new ArrayList<>();
            while (rs.next()){
                aux.add(new Factura_Producto(rs.getInt("idProducto"),rs.getInt("idFactura"), rs.getInt("cantidad")));
            }
            this.closeConnection();
            return aux;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void insert(Factura_Producto fp) {
        String sentencia = "INSERT INTO factura_producto (idProducto,idFactura,cantidad) VALUES (?,?,?)";
        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,fp.getIdProducto());
            ps.setInt(2,fp.getIdFactura());
            ps.setInt(3,fp.getCantidad());
            ps.executeUpdate();
            conn.commit();
            this.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void readCSV(){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/Csv/facturas-productos.csv"));
            for(CSVRecord row: parser) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));
                Factura_Producto aux = new Factura_Producto(idFactura,idProducto,cantidad);
                insert(aux);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
