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
    public T delete(Long id);
    public T edit(Long id,T t);
}