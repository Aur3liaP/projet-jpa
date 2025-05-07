package fr.diginamic.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Mise en place d'une interface commune pour chaque DAO.
 */
public interface BaseDao <T>{
    List<T> extraire() throws SQLException;
    void insert(T entity);
    int update(T entity) throws SQLException;
    boolean delete(T entity) throws SQLException;

}
