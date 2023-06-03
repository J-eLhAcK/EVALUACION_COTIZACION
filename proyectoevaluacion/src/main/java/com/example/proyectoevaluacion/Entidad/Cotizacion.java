package com.example.proyectoevaluacion.Entidad;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, length = 20)
    private Integer idCotizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //que no sea opcional al digitarlo en un futuro
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @JsonIgnore//ignore el formato json dentro de tabla
    private Usuario usuario;//Para la FK llama al Estudiante con nombre estudiante

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //que no sea opcional al digitarlo en un futuro
    @JoinColumn(name = "codigoProducto", referencedColumnName = "codigoProducto", nullable = false)
    @JsonIgnore//ignore el formato json dentro de tabla
    private Producto producto;


    @Column(nullable = false, length = 11)
    private int cantSolicitada;
    @Column(nullable = false, length = 11)
    private String idMondeda;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private String fecha;

    @PrePersist //antes de guardar hacer esto
    public void fechaActual(){
        this.fecha = new String(); //este campo fecha va a ser la fecha actual
    }

    public Cotizacion() {
    }

    public Cotizacion(Integer idCotizacion, Usuario usuario, Producto producto, int cantSolicitada, String idMondeda, String fecha) {
        this.idCotizacion = idCotizacion;
        this.usuario = usuario;
        this.producto = producto;
        this.cantSolicitada = cantSolicitada;
        this.idMondeda = idMondeda;
        this.fecha = fecha;
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

    public String getIdMondeda() {
        return idMondeda;
    }

    public void setIdMondeda(String idMondeda) {
        this.idMondeda = idMondeda;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "idCotizacion=" + idCotizacion +
                ", usuario=" + usuario +
                ", producto=" + producto +
                ", cantSolicitada=" + cantSolicitada +
                ", idMondeda=" + idMondeda +
                ", fecha=" + fecha +
                '}';
    }
}

