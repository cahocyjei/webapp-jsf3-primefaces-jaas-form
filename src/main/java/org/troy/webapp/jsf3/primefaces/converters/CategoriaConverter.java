package org.troy.webapp.jsf3.primefaces.converters;

import jakarta.enterprise.inject.Model;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import org.troy.webapp.jsf3.primefaces.entitys.Categoria;
import org.troy.webapp.jsf3.primefaces.service.ProductoService;

import java.util.Optional;

@Model
public class CategoriaConverter implements Converter<Categoria> {
    @Inject
    private ProductoService service;

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String id) {
        if(id == null){
            return null;
        }
        Optional<Categoria> catOptional = service.byIdCategoria(Long.valueOf(id));
        if (catOptional.isPresent()){
            return catOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
        if (categoria == null){
            return "0";
        }
        return categoria.getId().toString();
    }
}
