package Daos;

import Entities.Factura_Producto;

import java.util.List;

public interface Factura_ProductoDAO {
    public void createTable();
    public Factura_Producto get(int id);
    public List<Factura_Producto> getAll();
    public void insert(Factura_Producto fp);
    public void readCSV();
}
