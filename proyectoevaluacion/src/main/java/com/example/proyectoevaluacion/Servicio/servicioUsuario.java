package com.example.proyectoevaluacion.Servicio;
import com.example.proyectoevaluacion.Entidad.Usuario;
import com.example.proyectoevaluacion.Repositorio.repositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class servicioUsuario {

    private repositorioUsuario repositorio;

    public servicioUsuario(repositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }
    public ArrayList<Usuario> listarUsuarios(){
        return (ArrayList<Usuario>) repositorio.findAll(); //Traiga  todos los datos de la tabla usuarios
    }


    public Usuario buscarUsuario(String id){

        if(repositorio.findById(id).isPresent()) //si existe un usuario
            return repositorio.findById(id).get(); //Traer el usuario con get por documento
        else return null;
    }

    public  ArrayList<Usuario> buscarEmail(String email){
        return repositorio.findByEmail(email);
    }


    public String agregarUsuario(Usuario usuario){ //POST - body - raw- JSON
        if (repositorio.findById(usuario.getIdUsuario()).isPresent())
            return "Usuario ya se encuentra Registrado.";
        else{
            repositorio.save(usuario);
            return "El usuario se agrego EXITOSAMENTE";
        }
    }


    public String ActualizarUsuario(Usuario usuario){ //POST - body - raw- JSON
        if (repositorio.findById(usuario.getIdUsuario()).isPresent()) {
            repositorio.save(usuario);
            return "Usuario ya se encuentra Actualizado.";
        }
        else{
            return "El usuario no se encuentra Registrado";
        }
    }


    public  String eliminarUsuario(String doc){
        if(repositorio.findById(doc).isPresent()){
            repositorio.deleteById(doc);
            return "Usuario ELIMINADO SATISFACTORIAMENTE";

        }else {
            return "El usuario no se encuentra registrado";
        }
    }

}
