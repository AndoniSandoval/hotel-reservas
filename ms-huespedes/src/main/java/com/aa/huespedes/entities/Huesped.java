package com.aa.huespedes.entities;

import java.time.LocalDate;

import com.aa.common.enums.EstadoRegistro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "HUESPEDES")
public class Huesped {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HUESPED")
    private Long id;
	
	@Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 50, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "TELEFONO", length = 10, nullable = false)
    private String telefono;

    @Column(name = "DOCUMENTO", length = 30, nullable = false)
    private String documento;

    @Column(name = "NACIONALIDAD", length = 50, nullable = false)
    private String nacionalidad;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_REGISTRO", length = 10, nullable = false)
    private EstadoRegistro estadoRegistro;

    @Column(name = "FECHA_CREACION", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDate fechaActualizacion;
    
    
    //toma la fecha de sistema, evita errores de compatibilidad
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now();
        this.estadoRegistro = EstadoRegistro.ACTIVO;
    }

    
    //toma la fecha de sistema, evita errores de compatibilidad
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDate.now();
    }

    public void actualizar(String nombre, String apellidoPaterno, String apellidoMaterno,
                           String email, String telefono, String documento, String nacionalidad) {
        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.email = email.trim();
        this.telefono = telefono.trim();
        this.documento = documento.trim();
        this.nacionalidad = nacionalidad.trim();
    }

}
