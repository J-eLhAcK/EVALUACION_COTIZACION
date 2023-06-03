package com.example.proyectoevaluacion.Servicio;
import com.example.proyectoevaluacion.Entidad.Usuario;
import com.example.proyectoevaluacion.Entidad.Producto;
import com.example.proyectoevaluacion.Entidad.Cotizacion;
import com.example.proyectoevaluacion.Repositorio.repositorioCotizacion;
import com.example.proyectoevaluacion.Repositorio.repositorioProducto;
import com.example.proyectoevaluacion.Repositorio.repositorioUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class servicioCotizacion {

    private repositorioCotizacion repositorio;
    private  repositorioProducto repositorioPro;
    private  repositorioUsuario repositorioUsu;

    public servicioCotizacion(repositorioCotizacion repositorio, repositorioProducto repositorioPro, repositorioUsuario repositorioUsu) {
        this.repositorio = repositorio;
        this.repositorioPro = repositorioPro;
        this.repositorioUsu = repositorioUsu;
    }

    public ArrayList<Cotizacion> listarCotizaciones(){
        return (ArrayList<Cotizacion>) repositorio.findAll(); //Traiga  todos los datos de la tabla cotizaciones
    }

    public Cotizacion buscarCotizaciones(String id_cotizacion){

        if(repositorio.findById(Integer.valueOf(id_cotizacion)).isPresent()) //si existe un cotizacion
            return repositorio.findById(Integer.valueOf(id_cotizacion)).get(); //Traer el cotizacion con get por cod
        else return null;
    }

    public  ArrayList<Cotizacion> buscarFecha(Date fecha){
        return repositorio.findByFecha(String.valueOf(fecha));
    }


    public String agregarCotizacion(String id, String cod){
        Cotizacion c = new Cotizacion();
        if(repositorioUsu.findById(id).isPresent() && repositorioPro.findById(cod).isPresent()){
            Usuario usu = repositorioUsu.findById(id).get();
            Producto pro = repositorioPro.findById(cod).get();
            c.setUsuario(usu); //Llamamos a los setter
            c.setProducto(pro);
            repositorio.save(c);
        }else{
            return "ERROR AL REGISTRAR PRESTAMO...";
        }
        return id;
    }

    public String ActualizarCotizacion(Cotizacion cotizacion){ //POST - body - raw- JSON
        if (repositorio.findById(Integer.valueOf(String.valueOf(cotizacion.getFecha()))).isPresent()) {
            repositorio.save(cotizacion);
            return "Cotizacion ya se encuentra Actualizado.";
        }
        else{
            return "El cotizacion no se encuentra Registrado";
        }
    }


    public String eliminarCotizacion(String isb){
        if(repositorio.findById(Integer.valueOf(isb)).isPresent()){
            repositorio.deleteById(Integer.valueOf(isb));
            return "Cotizacion ELIMINADO SATISFACTORIAMENTE";

        }else {
            return "El cotizacion no se encuentra registrado";
        }
    }

    public List<Object[]> datosCotizacion(){
        return repositorio.findDatosCotizacion();
    }
    //public List<Object[]> usuarioCotizacion(String usu){
    //    return repositorio.findUsuarioCotizacion(usu);
    //}


}

