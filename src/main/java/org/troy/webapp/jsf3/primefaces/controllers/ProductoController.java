package org.troy.webapp.jsf3.primefaces.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.troy.webapp.jsf3.primefaces.entitys.Categoria;
import org.troy.webapp.jsf3.primefaces.entitys.Producto;
import org.troy.webapp.jsf3.primefaces.service.ProductoService;

import java.util.List;
import java.util.ResourceBundle;

@Model
public class ProductoController {
    private Producto producto;
    private Long id;
    @Inject
    private ProductoService productoService;
    @Inject
    private FacesContext facesContext;

    @Inject
    private ResourceBundle bundle;

    private List<Producto> listado;

    private String textoBuscar;

    @Produces
    @Model
    public String titulo() {
        return bundle.getString("form.app.texto.titulo");
    }

    @PostConstruct
    public void init() {
        this.listado = productoService.listar();
        this.producto = new Producto();
    }

    @Produces
    @Model
    public List<Categoria> categorias(){
        System.out.println("Imprimiendo Categorias");
        return productoService.listarCategorias();
    }
    public void buscar(){
        this.listado = productoService.searchByName(this.textoBuscar);
    }

    /*@Produces
    @Model*/
    public Producto product() {
        //this.producto = new Producto();
        if (producto.getId() != null && producto.getId() > 0) {
            productoService.byId(producto.getId()).ifPresent(p -> {
                this.producto = p;
            });
        }
        System.out.println("Imprimiendo Producto" + producto);
        return this.producto;
    }

    public void guardar() {
        if (producto.getId() != null && producto.getId() > 0){
            facesContext.addMessage(null,new FacesMessage(String.format(bundle.getString("form.app.mensaje.editar"),
                    producto.getNombre())));
        }else{
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("form.app.mensaje.crear"),
                    producto.getNombre())));
        }
        productoService.crear(producto);
        listado= productoService.listar();
        producto = new Producto();
    }

    public void editar(long id) {
        producto.setId(id);
        product();
        System.out.println("productoController.producto.id " + producto.getId());
    }

    public void eliminar(Producto producto) {
        productoService.eliminar(producto.getId());
            facesContext.addMessage(null,new FacesMessage(String.format(bundle.getString("form.app.mensaje.eliminar"),
                    producto.getNombre())));
            listado= productoService.listar();
    }
    public void cerrarDialogo(){
        System.out.println("Cerrando cuadro de dialogo...");
        producto = new Producto();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getListado() {
        return listado;
    }

    public void setListado(List<Producto> listado) {
        this.listado = listado;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
