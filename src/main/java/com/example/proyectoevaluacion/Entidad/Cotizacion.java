package com.example.proyectoevaluacion.Entidad;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar automáticamente valores incrementales
    @Column(length = 20)
    private Integer idCotizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigoProducto", referencedColumnName = "codigoProducto", nullable = false)
    @JsonIgnore
    private Producto producto;

    @Column(nullable = false)
    private int cantSolicitada;

    @Column(nullable = true, length = 11)
    private String idMoneda;

    @Column(name = "fecha")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    public Cotizacion() {
    }

    public Cotizacion(Integer idCotizacion, Usuario usuario, Producto producto, int cantSolicitada, String idMoneda, LocalDate fecha) {
        this.idCotizacion = idCotizacion;
        this.usuario = usuario;
        this.producto = producto;
        this.cantSolicitada = cantSolicitada;
        this.idMoneda = idMoneda;
        this.fecha = fecha;
    }

    public Cotizacion(Usuario usuario, Producto producto, int cantSolicitada, String idMoneda) {
        this.usuario = usuario;
        this.producto = producto;
        this.cantSolicitada = cantSolicitada;
        this.idMoneda = idMoneda;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantSolicitada() {
        return cantSolicitada;
    }

    public void setCantSolicitada(int cantSolicitada) {
        this.cantSolicitada = cantSolicitada;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "idCotizacion=" + idCotizacion +
                ", usuario=" + usuario +
                ", producto=" + producto +
                ", cantSolicitada=" + cantSolicitada +
                ", idMoneda=" + idMoneda +
                ", fecha=" + fecha +
                '}';
    }
}

