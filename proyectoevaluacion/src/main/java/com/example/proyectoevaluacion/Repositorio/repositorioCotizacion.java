package com.example.proyectoevaluacion.Repositorio;
import com.example.proyectoevaluacion.Entidad.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface repositorioCotizacion extends JpaRepository <Cotizacion, Integer> { //Tabla y Tipo de  dato de

    @Query(value = "select cot.id_cotizacion, cot.fecha, usu.nombre as usuario, pro.nombre as producto from cotizacion as cot  inner join usuario as usu on cot.id_usuario = usu.id_usuario  inner join producto as pro on cot.codigo_producto = pro.codigo_producto", nativeQuery = true)
    List<Object[]> findDatosCotizacion();

    //@Query(value = "select pre.id_prestamo, pre.fecha, est.nombre, est.apellido, lib.titulo, lib.autor from prestamo as pre inner join estudiante as est on pre.documento = est.documento inner join libro as lib on pre.isbn = lib.isbn where pre.documento = :est", nativeQuery = true)
    //List<Object[]> findUsuarioCotizacion(String est);
    ArrayList<Cotizacion> findByFecha(String fecha);

    Optional<Cotizacion> findById(Integer idCotizacion);

}


