package Daos.MYSQL;

import Daos.FacturaDAO;
import Entities.Factura;
import Entities.Factura_Producto;
import Factory.MYSQL_FactoryDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MYSQL_FacturaDAO extends MYSQL_FactoryDAO implements FacturaDAO {
    public MYSQL_FacturaDAO(){
        super();
    }
    @Override
    public void createTable() {
        String sentencia = "CREATE TABLE factura (" +
                "idCliente INT, " +
                "idFactura INT, " +
                "CONSTRAINT pk_idFactura PRIMARY KEY (idFactura), " +
                "CONSTRAINT fk_IdCliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)" +
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
    public Factura get(int idFactura) {
        return null;
    }

    @Override
    public List<Factura> getAll() {
        String sentencia = "SELECT * FROM factura";
        try{
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Factura> aux = new ArrayList<>();
            while (rs.next()){
                aux.add(new Factura(rs.getInt("idCliente"),rs.getInt("idFactura")));
            }
            this.closeConnection();
            return aux;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void insert(Factura f) {
        String sentencia = "INSERT INTO factura (idCliente,idFactura) VALUES (?,?)";
        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,f.getIdCliente());
            ps.setInt(2,f.getIdFactura());
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
                    FileReader("src/Csv/facturas.csv"));
            for(CSVRecord row: parser) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idCliente"));
                Factura aux = new Factura(idCliente,idFactura);
                insert(aux);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
