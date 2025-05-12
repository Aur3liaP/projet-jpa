package fr.diginamic.dao;

import java.util.List;

/**
 * Mise en place d'une interface commune pour chaque DAO.
 */
public interface BaseDao <T>{
    List<T> extraire();
    void insert(T entity);
    int update(T entity);
    boolean delete(T entity);

}
