package com.example.proyectoevaluacion.Controlador;
import com.example.proyectoevaluacion.Entidad.Producto;
import com.example.proyectoevaluacion.Entidad.Cotizacion;
import com.example.proyectoevaluacion.Servicio.ServicioCotizacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.primefaces.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

@RestController
public class ControladorCotizacion {

    private ServicioCotizacion servicio;

    public ControladorCotizacion(ServicioCotizacion servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listarCotizaciones") // http://localhost:8080/listarCotizaciones
    public List<Cotizacion> listarCotizaciones() {
        return servicio.listarCotizaciones();
    }

    @GetMapping("/buscarCotizaciones/{idCotizacion}") //EJ: http://localhost:8080/buscarCotizaciones/1
    public Cotizacion buscarCotizacion(@PathVariable("idCotizacion") String idCotizacion) {
        return servicio.buscarCotizacion(idCotizacion);
    }

    @GetMapping("/buscarFechaCot/fecha/{fecha}") //Ej: http://localhost:8080/buscarFechaCot/fecha/2023-05-07
    public List<Cotizacion> buscarCotizacionesPorFecha(@PathVariable("fecha") LocalDate fecha) {
        return servicio.buscarPorFecha(fecha);
    }

    @PostMapping("/agregarCotizacion/{idUsuario}/{codigoProducto}") //Ej: http://localhost:8080/agregarCotizacion/usu4/pro4?cantSolicitada=2&idMoneda=USD
    public String agregarCotizacion(@PathVariable("idUsuario") String idUsuario, @PathVariable("codigoProducto") String codigoProducto, @RequestParam("cantSolicitada") int cantSolicitada, @RequestParam("idMoneda") String idMoneda) {
        return servicio.agregarCotizacion(idUsuario, codigoProducto, cantSolicitada, idMoneda);
    }


    @PutMapping("/actualizarCotizacion/{id}") //EJ: http://localhost:8080/actualizarCotizacion/6
    //JSON Ej:
    // {
    //    "cantSolicitada": 12,
    //    "idMoneda": "USD"
    //}
    public ResponseEntity<Cotizacion> actualizarCotizacion(@PathVariable Integer id, @RequestBody Cotizacion cotizacion) {
        cotizacion.setIdCotizacion(id);
        Cotizacion cotizacionActualizada = servicio.actualizarCotizacion(cotizacion);
        return ResponseEntity.ok(cotizacionActualizada);
    }

    @DeleteMapping("/eliminarCotizacion/{idCotizacion}") //EJ: http://localhost:8080/eliminarCotizacion/4
    public String eliminarCotizacion(@PathVariable("idCotizacion") String idCotizacion) {
        return servicio.eliminarCotizacion(idCotizacion);
    }

    @GetMapping("/obtenerDetallesCotizaciones")//CON INNER JOIN: http://localhost:8080/obtenerDetallesCotizaciones
    public List<Map<String, Object>> obtenerDetallesCotizaciones() {
        List<Object[]> lista = servicio.obtenerDatosCotizacion();
        List<Map<String, Object>> json = new ArrayList<>();
        for (Object[] objects : lista) {
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id_cotizacion", objects[0]);
            datos.put("fecha", objects[1]);
            datos.put("usuario", objects[2]);
            datos.put("producto", objects[3]);
            json.add(datos);
        }
        return json;
    }
}

