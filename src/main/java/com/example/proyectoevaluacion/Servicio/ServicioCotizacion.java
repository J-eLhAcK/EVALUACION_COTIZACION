package com.example.proyectoevaluacion.Servicio;
import com.example.proyectoevaluacion.Entidad.Usuario;
import com.example.proyectoevaluacion.Entidad.Producto;
import com.example.proyectoevaluacion.Entidad.Cotizacion;
import com.example.proyectoevaluacion.Repositorio.RepositorioCotizacion;
import com.example.proyectoevaluacion.Repositorio.repositorioProducto;
import com.example.proyectoevaluacion.Repositorio.repositorioUsuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCotizacion {

    private RepositorioCotizacion repositorio;
    private repositorioProducto repositorioPro;
    private repositorioUsuario repositorioUsu;

    public ServicioCotizacion(RepositorioCotizacion repositorio, repositorioProducto repositorioPro, repositorioUsuario repositorioUsu) {
        this.repositorio = repositorio;
        this.repositorioPro = repositorioPro;
        this.repositorioUsu = repositorioUsu;
    }

    public List<Cotizacion> listarCotizaciones() {
        return repositorio.findAll();
    }

    public Cotizacion buscarCotizacion(String idCotizacion) {
        return repositorio.findById(Integer.valueOf(idCotizacion)).orElse(null);
    }

    public List<Cotizacion> buscarPorFecha(LocalDate fecha) {
        return repositorio.findByFecha(fecha);
    }

    public String agregarCotizacion(String idUsuario, String codigoProducto, int cantSolicitada, String idMoneda) {
        Optional<Usuario> usuarioOptional = repositorioUsu.findById(idUsuario);
        Optional<Producto> productoOptional = repositorioPro.findById(codigoProducto);

        if (usuarioOptional.isPresent() && productoOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Producto producto = productoOptional.get();

            Cotizacion cotizacion = new Cotizacion(usuario, producto, cantSolicitada, idMoneda);
            repositorio.save(cotizacion);
            return "Cotización registrada exitosamente.";
        } else {
            return "ERROR AL REGISTRAR COTIZACIÓN. Usuario o producto no encontrado.";
        }
    }


    public Cotizacion actualizarCotizacion(Cotizacion cotizacion) {
        // verificar si la cotizacion existe en la base de datos
        Optional<Cotizacion> cotizacionExistente = repositorio.findById(cotizacion.getIdCotizacion());

        if (cotizacionExistente.isPresent()) {
            // actualizar los datos de la cotizacion existente
            Cotizacion cotizacionActualizada = cotizacionExistente.get();
            cotizacionActualizada.setCantSolicitada(cotizacion.getCantSolicitada());
            cotizacionActualizada.setIdMoneda(cotizacion.getIdMoneda());
            // guardar la cotizacion actualizada en la base de datos
            return repositorio.save(cotizacionActualizada);
        } else {
            throw new RuntimeException("La cotización no existe.");
        }
    }

    public String eliminarCotizacion(String idCotizacion) {
        if (repositorio.existsById(Integer.valueOf(idCotizacion))) {
            repositorio.deleteById(Integer.valueOf(idCotizacion));
            return "Cotización eliminada exitosamente.";
        } else {
            return "La cotización no se encuentra registrada.";
        }
    }

    public List<Object[]> obtenerDatosCotizacion() {
        return repositorio.findDatosCotizacion();
    }


    //public List<Object[]> usuarioCotizacion(String usu){
    //    return repositorio.findUsuarioCotizacion(usu);
    //}


}

