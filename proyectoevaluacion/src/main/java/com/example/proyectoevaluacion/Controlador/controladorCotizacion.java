package com.example.proyectoevaluacion.Controlador;
import com.example.proyectoevaluacion.Entidad.Producto;
import com.example.proyectoevaluacion.Entidad.Cotizacion;
import com.example.proyectoevaluacion.Servicio.servicioCotizacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.primefaces.json.JSONObject;
import java.util.*;

@RestController
public class controladorCotizacion {

    private servicioCotizacion servicio;

    public controladorCotizacion(servicioCotizacion servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/ListarCotizaciones")
    public ArrayList<Cotizacion> listarUsu(){
        return servicio.listarCotizaciones();
    }


    @GetMapping("/BuscarCotizacion/{idCotizacion}")
    public Cotizacion buscarCli(@PathVariable("idCotizacion") String idCotizacion){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.buscarCotizaciones(idCotizacion);
    }

    @GetMapping("/BuscarFecha/{fecha}")
    public ArrayList<Cotizacion> buscarFecha(@PathVariable("fecha") Date fecha){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.buscarFecha(fecha);
    }

    @PostMapping("/Agregarcotizacion/{usu}/{pro}")
    public String agregarcotizacion(@PathVariable("usu") String id, @PathVariable("pro") String cod){
        return servicio.agregarCotizacion(id, cod);
    }


    @PutMapping ("/ActualizarCotizacion")
    public String actualizarCotizacion(@RequestBody Cotizacion cotizacion){
        return servicio.ActualizarCotizacion(cotizacion);
    }


    @DeleteMapping("/EliminarCotizacion/{cod}")
    public String eliminarUsu(@PathVariable("cod") String cod){ //Path es la variable del servicio de la PK delStr documneto que vamos abuscar
        return  servicio.eliminarCotizacion(cod);
    }


    @GetMapping("/detallesCotizaciones")
    public List<Map<String, Object>> datosCotizacion() {
        List<Object[]> lista = servicio.datosCotizacion();
        List<Map<String, Object>> json = new ArrayList<>();
        for (Object[] objects : lista) {
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id_cotizacion", objects[0]);
            datos.put("fecha", objects[1]);
            datos.put("usuario", objects[2]);
            datos.put("producto", objects[3]);
            json.add(datos);
        }
        for (Map<String, Object> j : json) {
            System.out.println(j);
        }
        return json;
    }
//
    //@GetMapping("/detallesUsuuCotizaciones/{usu}")
    //public List<Map<String, Object>> usuarioCotizacion(@PathVariable("usu") String est) {
    //    List<Object[]> lista = servicio.usuarioCotizacion(est);
    //    List<Map<String, Object>> json = new ArrayList<>();
    //    for (Object[] objects : lista) {
    //        Map<String, Object> datos = new LinkedHashMap<>();
    //        datos.put("idCotizacion", objects[0]);
    //        datos.put("fecha", objects[1]);
    //        datos.put("nombre", objects[2]);
    //        datos.put("apellido", objects[3]);
    //        datos.put("titulo", objects[4]);
    //        datos.put("autor", objects[5]);
    //        json.add(datos);
    //    }
    //    for (Map<String, Object> j : json) {
    //        System.out.println(j);
    //    }
    //    return json;
    //}


}

