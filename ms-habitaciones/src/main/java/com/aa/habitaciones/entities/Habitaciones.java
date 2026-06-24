package com.aa.habitaciones.entities;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aa.common.enums.EstadoHabitacion;
import com.aa.common.enums.EstadoRegistro;
import com.aa.common.enums.TipoHabitacion;
import com.aa.common.utils.StringCustomUtils;
import com.aa.common.utils.ValoresNumericosUtils;

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
@Table(name = "HABITACIONES")
@ToString
public class Habitaciones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_HABITACION")
	private Long idHabitacion;
	
	@Column(name = "NUMERO_HABITACION", nullable = false)
	private Integer numeroHabitacion;
	
	@Column(name = "TIPO_HABITACION", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoHabitacion tipoHabitacion;
	
	@Column(name = "PRECIO_NOCHE", nullable = false)
	private BigDecimal precioNoche;
	
	@Column(name = "CAPACIDAD", nullable = false)
	private Integer capacidad;
	
	@Column(name = "ESTADO_HABITACION", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoHabitacion estadoHabitacion;
	
	@Column(name = "ESTADO_REGISTRO", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoRegistro estadoRegistro;
	
	@Column(name = "FECHA_CREACION", nullable = false)
	private LocalDateTime fechaCreacion;
	
	@Column(name = "FECHA_ACTUALIZACION", nullable = false)
	private LocalDateTime fechaActualizacion;

	public static Habitaciones crear(Integer numeroHabitacion, TipoHabitacion tipoHabitacion, BigDecimal precioNoche, Integer capacidad) {
		validarDatos(numeroHabitacion,tipoHabitacion,precioNoche,capacidad);
		return Habitaciones.builder()
				.numeroHabitacion(numeroHabitacion)
				.tipoHabitacion(tipoHabitacion)
				.precioNoche(precioNoche)
				.capacidad(capacidad)
				.estadoHabitacion(EstadoHabitacion.DISPONIBLE)
				.estadoRegistro(EstadoRegistro.ACTIVO)
				.fechaCreacion(LocalDateTime.now())
				.build();
	}

	
	public void actualizar(Integer numeroHabitacion, TipoHabitacion tipoHabitacion, BigDecimal precioNoche, Integer capacidad) {
		validarNoEliminado();
		validarDisponibilidad();
		validarDatos(numeroHabitacion,tipoHabitacion,precioNoche,capacidad);
		
		this.numeroHabitacion = numeroHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.precioNoche = precioNoche;
		this.capacidad = capacidad;
		this.fechaActualizacion = LocalDateTime.now();
	}
	
	public void eliminar() {
		validarNoEliminado();
		validarDisponibilidad();
		this.estadoRegistro = estadoRegistro.ELIMINADO;
	}
	
	private void actualizarEstadoHabitacion(EstadoHabitacion nuevoEstadoHabitacion) {
		validarNoEliminado();
		if(nuevoEstadoHabitacion == null)
			throw new IllegalArgumentException("El estado es requerido");
		this.estadoHabitacion = nuevoEstadoHabitacion;
		
	}
	
	
	private static void validarDatos(Integer numeroHabitacion, TipoHabitacion tipoHabitacion, BigDecimal precioNoche, Integer capacidad) {
		
		ValoresNumericosUtils.validarEnteroMinimoA(numeroHabitacion, 1 ,"El numero de habitacion es requerido y debe ser mayor a 0");
		ValoresNumericosUtils.validarBigDecimalPositivo(precioNoche, "El precio es requerido y debe ser positivo");
		ValoresNumericosUtils.validarEnteroMinimoA(capacidad, 1, "La capacidad es requerida y debe ser mayor o igual a 1");
		
		if(tipoHabitacion == null)
			throw new IllegalArgumentException("El tipo de habitacion es requerido");
			
	}
	
	
	private void validarNoEliminado() {
		if (this.estadoRegistro == EstadoRegistro.ELIMINADO) {
			throw new IllegalStateException("Ya se encuentra eliminado");
		}
	}
	
	private void validarDisponibilidad() {
		if (this.estadoHabitacion == EstadoHabitacion.OCUPADA) {
			throw new IllegalStateException("La habitacion se encuentra ocupada");
		}
	}


	
	

}
	
	
	
	
	
	
	
