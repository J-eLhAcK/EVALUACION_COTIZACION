package com.example.proyectoevaluacion.Controlador;
import com.example.proyectoevaluacion.Entidad.Usuario;
import com.example.proyectoevaluacion.Servicio.servicioUsuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class controladorUsuario {

    private servicioUsuario servicio;

    public controladorUsuario(servicioUsuario servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/ListarUsuarios")
    public ArrayList<Usuario> listarUsu(){
        return servicio.listarUsuarios();
    }


    @GetMapping("/BuscarUsuario/{id}")
    public Usuario buscarUsu(@PathVariable("id") String id){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.buscarUsuario(id);
    }

    @GetMapping("/BuscarCorreo/{email}")
    public ArrayList<Usuario> buscarEmail(@PathVariable("email") String e){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.buscarEmail(e);
    }


    @PostMapping("/AgregarUsuario")
    public String agregarUsuario(@RequestBody Usuario usuario){
        return servicio.agregarUsuario(usuario);
    }

    @PutMapping ("/ActualizarUsuario")
    public String actualizarUsuario(@RequestBody Usuario usuario){
        return servicio.ActualizarUsuario(usuario);
    }


    @DeleteMapping("/EliminarUsuario/{id}")
    public String eliminarUsu(@PathVariable("id") String id){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.eliminarUsuario(id);
    }

}

