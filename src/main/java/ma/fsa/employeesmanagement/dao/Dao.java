package ma.fsa.employeesmanagement.dao;

import java.util.List;

public interface Dao<T>{
    List<T> getAll()  ;
    T getById(int id)  ;
    T save(T o)  ;
    boolean delete(T o);
    T update(T o)  ;
}
