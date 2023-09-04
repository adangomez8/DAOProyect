package Daos.MYSQL;

import Entities.Cliente;
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

public class MYSQL_ClienteDAO extends MYSQL_FactoryDAO implements DAOCrud<Cliente> {

    public MYSQL_ClienteDAO() {
        super();
    }

    @Override
    public void createTable() {
        String sentencia = "CREATE TABLE cliente (idCliente INT, nombre VARCHAR(500), email VARCHAR(150), PRIMARY KEY (idCliente))";
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
    public Cliente get(int idCliente) {
        String sentencia = "SELECT * from cliente where idCliente = (?)";
        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,idCliente);
            ResultSet resultado = ps.executeQuery();
            Cliente aux = new Cliente(resultado.getInt("idCliente"),resultado.getString("nombre"),resultado.getString("email"));
            this.closeConnection();
            return aux;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> getAll() {
        String sentencia = "SELECT * FROM cliente";
        try{
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Cliente> aux = new ArrayList<>();
            while (rs.next()){
                aux.add(new Cliente(rs.getInt("idCliente"),rs.getString("nombre"),rs.getString("email")));
            }
            this.closeConnection();
            return aux;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void insert(Cliente c) {
        String sentencia = "INSERT INTO cliente (idCliente,nombre,email) VALUES (?,?,?)";
        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1,c.getIdCliente());
            ps.setString(2,c.getNombre());
            ps.setString(3,c.getEmail());
            ps.executeUpdate();
            conn.commit();
            this.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getClientesPorFacturacion(){

        String sentencia = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS total_facturado "
                + "FROM cliente c "
                + "JOIN factura f ON c.idCliente = f.idCliente "
                + "JOIN factura_producto fp ON f.idFactura = fp.idFactura "
                + "JOIN producto p ON fp.idProducto = p.idProducto "
                + "GROUP BY c.idCliente, c.nombre "
                + "ORDER BY total_facturado DESC";

        try {
            this.createConnection();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();

            System.out.println("Lista de clientes ordenada por el total facturado:");

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombreCliente = rs.getString("nombre");
                double totalFacturado = rs.getDouble("total_facturado");

                System.out.println("ID Cliente: " + idCliente + ", Nombre: " + nombreCliente + ", Total Facturado: $" + totalFacturado);
            }
            this.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void readCSV(){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/Csv/clientes.csv"));
            for(CSVRecord row: parser) {
                int idCliente = Integer.parseInt(row.get("idCliente"));
                Cliente aux = new Cliente(idCliente,row.get("nombre"),row.get("email"));
                insert(aux);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
