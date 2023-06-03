package com.example.proyectoevaluacion.Entidad;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id //Llave primaria
    @Column(unique = true, length = 11)
    private String idUsuario;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 25, unique = true)
    private String email;
    @Column(nullable = false, length = 25)
    private String pais;

    /*
     * RELACIONES
     * */

    @OneToMany(mappedBy =  "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cotizacion> cotizaciones;

    public Usuario() {
    }

    public Usuario(String idUsuario, String nombre, String email, String pais) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.pais = pais;
    }

    public Set<Cotizacion> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(Set<Cotizacion> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
