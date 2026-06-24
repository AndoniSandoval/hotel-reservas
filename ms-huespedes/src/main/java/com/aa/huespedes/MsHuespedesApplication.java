package com.aa.huespedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aa.huespedes",
	    "com.aa.common"})
public class MsHuespedesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHuespedesApplication.class, args);
	}

}
