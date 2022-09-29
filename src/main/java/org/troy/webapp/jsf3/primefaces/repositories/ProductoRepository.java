package org.troy.webapp.jsf3.primefaces.repositories;

import org.troy.webapp.jsf3.primefaces.entitys.Producto;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto>{
    List<Producto> searchByName(String name);
}
