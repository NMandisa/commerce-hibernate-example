package za.co.fynbos.dao;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */

public interface GenericDAO<T>{
    public void save(T t);
    public void saveAll(List<T>ts);
    public T find(Long id);
    public boolean delete(Long id);
    public boolean deleteAll(List<T> ts);
    public boolean edit(Long id,T t);
    public boolean editAll(List<T>oldT,List<T>newTs);
}