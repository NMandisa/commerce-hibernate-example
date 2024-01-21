package za.co.fynbos.dao;

import java.util.Optional;

/**
 * @author Noxolo.Mkhungo
 */

public interface GlobalDAO <T>{
    public void save(T t);
    public Optional<T> find(Long id);
    public T delete(Long id);
    public T edit(Long id,T t);
}