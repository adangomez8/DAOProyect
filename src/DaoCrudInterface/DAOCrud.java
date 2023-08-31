package DaoCrudInterface;

import java.util.List;

public interface DAOCrud<T> {
	
	public void createTable();
    public T get(int valor);
    public List<T> getAll();
    public void insert(T clase);
    public void readCSV();

}
