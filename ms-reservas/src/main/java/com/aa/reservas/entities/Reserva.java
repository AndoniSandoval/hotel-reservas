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
	

}
