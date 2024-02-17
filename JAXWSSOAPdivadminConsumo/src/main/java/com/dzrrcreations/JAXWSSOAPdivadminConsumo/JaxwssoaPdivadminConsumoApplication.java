package com.dzrrcreations.JAXWSSOAPdivadminConsumo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JaxwssoaPdivadminConsumoApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JaxwssoaPdivadminConsumoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JaxwssoaPdivadminConsumoApplication.class, args);
		
		LOGGER.info("Aplicacao de Consumo de JAX WS SOAP Divisoes Administrativas inicializada...");
	}

}
