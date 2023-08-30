package Daos;

import Entities.Factura;

import java.util.List;

public interface FacturaDAO {
    public void createTable();
    public Factura get(int idFactura);
    public List<Factura> getAll();
    public void insert(Factura f);
    public void readCSV();
}
