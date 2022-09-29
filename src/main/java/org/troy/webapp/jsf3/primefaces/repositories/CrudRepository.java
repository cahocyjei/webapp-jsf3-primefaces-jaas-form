package org.troy.webapp.jsf3.primefaces.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> listar();
    T byId(Long id);
    void crear(T t);
    void eliminar(Long id);
}