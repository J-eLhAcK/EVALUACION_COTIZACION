package com.example.proyectoevaluacion.Repositorio;
import com.example.proyectoevaluacion.Entidad.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioCotizacion extends JpaRepository<Cotizacion, Integer> {

    @Query(value = "SELECT cot.id_cotizacion, cot.fecha, usu.nombre AS usuario, pro.nombre AS producto FROM cotizacion AS cot INNER JOIN usuario AS usu ON cot.id_usuario = usu.id_usuario INNER JOIN producto AS pro ON cot.codigo_producto = pro.codigo_producto", nativeQuery = true)
    List<Object[]> findDatosCotizacion();

    List<Cotizacion> findByFecha(LocalDate fecha);

    Optional<Cotizacion> findByIdCotizacion(Integer idCotizacion);
}



