package com.example.proyectoevaluacion.Controlador;
import com.example.proyectoevaluacion.Entidad.Producto;
import com.example.proyectoevaluacion.Servicio.servicioProducto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class controladorProducto {

    private servicioProducto servicio;

    public controladorProducto(servicioProducto servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/Listarproductos")
    public ArrayList<Producto> listarPro(){
        return servicio.listarProductos();
    }


    @GetMapping("/Buscarproducto/{cod}")
    public Producto buscarPro(@PathVariable("cod") String cod){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.buscarProducto(cod);
    }

    @GetMapping("/BuscarTitulo/{nombre}")
    public ArrayList<Producto> buscarNombre(@PathVariable("nombre") String n){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.buscarNombre(n);
    }


    @PostMapping("/Agregarproducto")
    public String agregarproducto(@RequestBody Producto producto){
        return servicio.agregarProducto(producto);
    }

    @PutMapping ("/Actualizarproducto")
    public String actualizarproducto(@RequestBody Producto producto){
        return servicio.ActualizarProducto(producto);
    }


    @DeleteMapping("/Eliminarproducto/{cod}")
    public String eliminarPro(@PathVariable("cod") String cod){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.eliminarProducto(cod);
    }

}
