package Daos.MYSQL;

import Entities.Producto;
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

public class MYSQL_ProductoDAO extends MYSQL_FactoryDAO implements DAOCrud<Producto> {
    public MYSQL_ProductoDAO(){
        super();
    }

    @Override
    public void createTable() {
        String sentencia = "CREATE TABLE producto (idProducto INT, nombre VARCHAR(500), valor float, PRIMARY KEY (idProducto))";
        try {
            this.createConnection();
            conn.prepareStatement(sentencia).execute();
            conn.commit();
            this.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Producto> getAll() {
        String sentencia = "SELECT * FROM producto";
        try{
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Producto> aux = new ArrayList<>();
            while (rs.next()){
                aux.add(new Producto(rs.getInt("idProducto"),rs.getString("nombre"),rs.getFloat("valor")));
            }
            this.closeConnection();
            return aux;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Producto get(int idProducto) {
        return null;
    }

    @Override
    public void insert(Producto p) {
        String sentencia = "INSERT INTO producto (idProducto,nombre,valor) VALUES (?,?,?)";
        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,p.getIdProducto());
            ps.setString(2,p.getNombre());
            ps.setFloat(3,p.getValor());
            ps.executeUpdate();
            conn.commit();
            this.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean delete(int idProducto) {
        String sentencia = "DELETE FROM producto WHERE idProducto = (?)";
        try{
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,idProducto);
            ps.executeUpdate();
            conn.commit();
            this.closeConnection();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    public boolean update(Producto p) {
        String sentencia = "UPDATE producto SET 'idProducto' = ?, 'nombre' = ?, 'valor' = ? WHERE idProducto = ?";
        try{
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,p.getIdProducto());
            ps.setString(2,p.getNombre());
            ps.setFloat(3,p.getValor());
            ps.setInt(4,p.getIdProducto());
            ps.executeUpdate();
            conn.commit();
            this.closeConnection();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    /*public void getProductoConMasRecaudacion(){

        String sentencia = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";

        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombreProducto = rs.getString("nombre");
                double recaudacion = rs.getDouble("recaudacion");

                System.out.println("Producto que más recaudó:");
                System.out.println("ID: " + idProducto);
                System.out.println("Nombre: " + nombreProducto);
                System.out.println("Recaudación Total: " + recaudacion);
            } else {
                System.out.println("No se encontraron productos.");
            }

                this.closeConnection();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/

    public Producto getProductoConMasRecaudacion(){

        String sentencia = "SELECT p.idProducto, p.nombre,p.valor, SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";

        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombreProducto = rs.getString("nombre");
                double recaudacion = rs.getDouble("recaudacion");
                float valor = rs.getFloat("valor");
                Producto aux = new Producto(idProducto,nombreProducto,valor);
                System.out.println("Producto que más recaudó:");
                System.out.println("ID: " + idProducto);
                System.out.println("Nombre: " + nombreProducto);
                System.out.println("Recaudación Total: " + recaudacion);
                return aux;
            } else {
                System.out.println("No se encontraron productos.");
            }

            this.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void readCSV(){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/Csv/productos.csv"));
            for(CSVRecord row: parser) {
                int idProducto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                float valor = Float.parseFloat((row.get("valor")));
                Producto aux = new Producto(idProducto,nombre,valor);
                insert(aux);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
