package org.troy.webapp.jsf3.primefaces.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.troy.webapp.jsf3.primefaces.entitys.Producto;

import java.util.List;

@RequestScoped
public class ProductoRepositoryImpl implements ProductoRepository{

    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() {
        return em.createQuery("select p from Producto p left outer join fetch p.categoria ",Producto.class).getResultList();
    }

    @Override
    public Producto byId(Long id) {
        //return em.find(Producto.class,id);
        return em.createQuery("select p from Producto p left outer join fetch p.categoria where p.id=:id",Producto.class)
                .setParameter("id",id)
                .getSingleResult();
    }
    @Override
    public void crear(Producto producto) {
        if(producto.getId() != null && producto.getId() > 0){
            em.merge(producto);
        }else{
            em.persist(producto);
        }
    }
    @Override
    public void eliminar(Long id) {
        Producto producto= byId(id);
        em.remove(producto);
    }

    @Override
    public List<Producto> searchByName(String name) {
        return em.createQuery("select p from Producto p left outer join fetch p.categoria where p.nombre like:nombre",
                Producto.class)
                .setParameter("nombre", "%" + name + "%")
                .getResultList();
    }
}
