package com.marketMillenium.marketmillenium.Dao;

import jakarta.persistence.*;

@Entity
@Table(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private String dni;

    private String telefono;

    private String correo;

    @Column(name = "producto_servicio")
    private String productoServicio;

    private String detalle;

    @Column(name = "tipo_reclamo", nullable = false)
    private String tipoReclamo;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProductoServicio() {
        return productoServicio;
    }

    public void setProductoServicio(String productoServicio) {
        this.productoServicio = productoServicio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipoReclamo() {
        return tipoReclamo;
    }

    public void setTipoReclamo(String tipoReclamo) {
        this.tipoReclamo = tipoReclamo;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}