package com.example.proyectoevaluacion.Repositorio;
import com.example.proyectoevaluacion.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface repositorioProducto extends JpaRepository <Producto, String> { //Tabla y Tipo de  dato de
    ArrayList<Producto> findByNombre(String nombre);

}

