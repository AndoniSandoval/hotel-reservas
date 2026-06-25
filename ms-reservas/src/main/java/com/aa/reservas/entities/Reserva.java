package com.aa.reservas.entities;


import java.time.LocalDateTime;

import com.aa.common.enums.EstadoRegistro;
import com.aa.common.enums.EstadoReserva;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "RESERVAS")
@ToString

public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESERVA")
	private Long idReserva;
	
	@Column(name = "ID_HUESPED", nullable = false)
	private Long idHuesped;
	
	@Column(name = "ID_HABITACION", nullable = false)
	private Long idHabitacion;
	
	@Column(name = "FECHA_ENTRADA", nullable = false)
	private LocalDateTime fechaEntrada;
	
	@Column(name = "FECHA_SALIDA", nullable = false)
	private LocalDateTime fechaSalida;
	
	@Column(name = "ESTADO_RESERVA", nullable = false, length = 15)
	@Enumerated(EnumType.STRING)
	private EstadoReserva estadoReserva;
	
	@Column(name = "ESTADO_REGISTRO", nullable = false,length = 10)
	@Enumerated(EnumType.STRING)
	private EstadoRegistro estadoRegistro;
	
	@Column(name = "FECHA_CREACION", nullable = false)
	private LocalDateTime fechaCreacion;
	
	@Column(name = "FECHA_ACTUALIZACION")
	private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.estadoRegistro = EstadoRegistro.ACTIVO;
        this.estadoReserva = EstadoReserva.CONFIRMADA;
        this.fechaCreacion = LocalDateTime.now();
    }
	
	
	public static Reserva crear(Long idHuesped, Long idHabitacion, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		validaDatosReserva(idHuesped,idHabitacion,fechaEntrada,fechaSalida);
		return Reserva.builder()
				.idHuesped(idHuesped)
				.idHabitacion(idHabitacion)
				.fechaEntrada(fechaEntrada)
				.fechaSalida(fechaSalida)
				.build();
	};
	
	
	
	public void actualizar(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		validaNoEliminado();
		validaFecha(fechaEntrada, fechaSalida);
		
		this.fechaEntrada=fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.fechaActualizacion = LocalDateTime.now();
		
	}
	
	public void eliminar() {
		validaNoEliminado();
		this.estadoRegistro= EstadoRegistro.ELIMINADO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Validaciones
	private void validaNoEliminado() {
		if (this.estadoRegistro == EstadoRegistro.ELIMINADO)
			throw new IllegalStateException("Ya se encuentra eliminado");
	}
	
	
	
	private static  void validaDatosReserva(Long idHuesped, Long idHabitacion, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		validaId(idHuesped, "huesped");
		validaId(idHabitacion, "habitacion");
		validaFecha(fechaEntrada, fechaSalida);
	};
	
	private  static  void validaId(Long id, String campo) {
		if(id==null || id<=0)
			throw new IllegalArgumentException("El id "+campo+" es requerido y debe ser positivo");
	}
	
	private static void validaFecha(LocalDateTime fechaEntrada, LocalDateTime fechaSalidda) {
		
		if(fechaEntrada == null)
			throw new IllegalArgumentException("La fecha de entra es requerida");
		
		if(fechaSalidda == null)
			throw new IllegalArgumentException("La fecha de salida es requerida");
		
		if(fechaEntrada.isAfter(fechaSalidda))
			throw new IllegalArgumentException("La fecha de entrada no puede ser despues de la hora de salida");
		
		
	}
	
}
