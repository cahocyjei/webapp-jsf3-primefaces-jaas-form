package org.troy.webapp.jsf3.primefaces.service;

import jakarta.ejb.Local;
import org.troy.webapp.jsf3.primefaces.entitys.Categoria;
import org.troy.webapp.jsf3.primefaces.entitys.Producto;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> byId(Long id);
    void crear(Producto producto);
    void eliminar(Long id);
    List<Categoria> listarCategorias();
    Optional<Categoria> byIdCategoria(Long id);
    List<Producto> searchByName(String name);
}
