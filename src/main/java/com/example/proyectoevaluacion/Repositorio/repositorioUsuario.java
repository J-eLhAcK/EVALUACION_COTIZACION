package com.example.proyectoevaluacion.Repositorio;
import com.example.proyectoevaluacion.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface repositorioUsuario extends JpaRepository <Usuario, String> { //Tabla y Tipo de  dato de
    ArrayList<Usuario> findByEmail(String email);

}

