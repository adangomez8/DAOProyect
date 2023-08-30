package Daos;

import Entities.Producto;

import java.util.List;

public interface ProductoDAO {
    public void createTable();
    public List<Producto> getAll();
    public Producto get(int idProducto);
    public void insert(Producto p);
    public boolean delete(int idProducto);
    public boolean update(Producto p);
    public void readCSV();
}
