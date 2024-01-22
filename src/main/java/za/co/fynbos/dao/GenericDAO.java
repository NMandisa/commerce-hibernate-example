package za.co.fynbos.dao;

import java.util.Optional;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */

public interface GenericDAO<T>{
    public void save(T t);
    public void saveAll(Set<T>ts);
    public Optional<T> find(Long id);
    public boolean delete(Long id);
    public boolean deleteAll(Set<T>ts);
    public boolean edit(Long id,T t);
    public boolean editAll(Set<T>oldT,Set<T>newTs);
}