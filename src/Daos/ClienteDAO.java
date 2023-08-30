package Daos;

import Entities.Cliente;

import java.util.List;

public interface ClienteDAO {
    public void createTable();
    public Cliente get(int idCliente);
    public List<Cliente> getAll();
    public void insert(Cliente c);
    public void readCSV();
}
