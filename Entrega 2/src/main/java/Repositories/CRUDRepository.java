package Repositories;

public interface CRUDRepository<T> {

	public T getById(int id);
	public void create(T element);
	public void delete(T element);
	public void update(T element);



}
