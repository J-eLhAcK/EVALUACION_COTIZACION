package com.example.proyectoevaluacion.Entidad;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto {
    @Id //Llave primaria
    @Column(unique = true, length = 11)
    private String codigoProducto;
    @Column(nullable = false, length = 21)
    private String nombre;
    @Column(nullable = false, length = 11)
    private int precio;
    @Column(nullable = false, length = 11)
    private int cantidad;

    /*
     * RELACIONES
     * */

    @OneToMany(mappedBy =  "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cotizacion> cotizaciones;

    public Producto() {
    }

    public Producto(String codigoProducto, String nombre, int precio, int cantidad) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Set<Cotizacion> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(Set<Cotizacion> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigoProducto='" + codigoProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
