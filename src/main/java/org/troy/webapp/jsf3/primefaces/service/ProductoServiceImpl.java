package org.troy.webapp.jsf3.primefaces.service;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import org.troy.webapp.jsf3.primefaces.entitys.Categoria;
import org.troy.webapp.jsf3.primefaces.entitys.Producto;
import org.troy.webapp.jsf3.primefaces.repositories.CrudRepository;
import org.troy.webapp.jsf3.primefaces.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER","ADMIN"})
public class ProductoServiceImpl implements ProductoService{
    @Inject
    private ProductoRepository repository;

    @Inject
    private CrudRepository<Categoria> repoCategoria;
    @Override
    @PermitAll
    public List<Producto> listar() {
        return repository.listar();
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Optional<Producto> byId(Long id) {
            return Optional.ofNullable(repository.byId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void crear(Producto producto) {
        repository.crear(producto);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public List<Categoria> listarCategorias() {
        return repoCategoria.listar();
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Optional byIdCategoria(Long id) {
        return Optional.ofNullable(repoCategoria.byId(id));

    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public List<Producto> searchByName(String name) {
        return repository.searchByName(name);
    }

}
