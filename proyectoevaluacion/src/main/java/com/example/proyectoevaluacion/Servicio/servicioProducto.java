package com.example.proyectoevaluacion.Servicio;
import com.example.proyectoevaluacion.Entidad.Producto;
import org.springframework.stereotype.Service;
import com.example.proyectoevaluacion.Repositorio.repositorioProducto;


import java.util.ArrayList;

@Service
public class servicioProducto {

    private repositorioProducto repositorio;

    public servicioProducto(repositorioProducto repositorio) {
        this.repositorio = repositorio;
    }
    public ArrayList<Producto> listarProductos(){
        return (ArrayList<Producto>) repositorio.findAll(); //Traiga  todos los datos de la tabla productos
    }


    public Producto buscarProducto(String cod){

        if(repositorio.findById(cod).isPresent()) //si existe un producto
            return repositorio.findById(cod).get(); //Traer el producto con get por isbn
        else return null;
    }

    public  ArrayList<Producto> buscarNombre(String nombre){
        return repositorio.findByNombre(nombre);
    }


    public String agregarProducto(Producto producto){ //POST - body - raw- JSON
        if (repositorio.findById(producto.getCodigoProducto()).isPresent())
            return "Producto ya se encuentra Registrado.";
        else{
            repositorio.save(producto);
            return "El producto se agrego EXITOSAMENTE";
        }
    }


    public String ActualizarProducto(Producto producto){ //POST - body - raw- JSON
        if (repositorio.findById(producto.getCodigoProducto()).isPresent()) {
            repositorio.save(producto);
            return "Producto ya se encuentra Actualizado.";
        }
        else{
            return "El producto no se encuentra Registrado";
        }
    }


    public  String eliminarProducto(String cod){
        if(repositorio.findById(cod).isPresent()){
            repositorio.deleteById(cod);
            return "Producto ELIMINADO SATISFACTORIAMENTE";

        }else {
            return "El producto no se encuentra registrado";
        }
    }

}

