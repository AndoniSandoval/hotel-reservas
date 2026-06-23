package com.aa.habitaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aa.habitaciones", "com.common"})
public class MsHabitacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHabitacionesApplication.class, args);
	}

}
