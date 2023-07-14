package ma.fsa.employeesmanagement.dao.interfaces;

import java.util.List;

public interface Dao<T>{
    List<T> findAll()  ;
    T findOne(int id)  ;
    T save(T o)  ;
    boolean delete(T o);
    T update(T o)  ;
}
