package org.troy.webapp.jsf3.primefaces.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.troy.webapp.jsf3.primefaces.entitys.Categoria;

import java.util.List;

@RequestScoped
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager em;
    @Override
    public List<Categoria> listar() {
        return em.createQuery("from Categoria",Categoria.class).getResultList();
    }

    @Override
    public Categoria byId(Long id) {
        return em.find(Categoria.class,id);
    }

    @Override
    public void crear(Categoria categoria) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
