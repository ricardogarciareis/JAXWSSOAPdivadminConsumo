package com.dzrrcreations.JAXWSSOAPdivadminConsumo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dzrrcreations.JAXWSSOAPdivadminConsumo.service.DivAdminService;
import com.dzrrcreations.JAXWSSOAPdivadminConsumo.service.DivAdminServiceImpl;

@SpringBootApplication
public class JaxwssoaPdivadminConsumoApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JaxwssoaPdivadminConsumoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JaxwssoaPdivadminConsumoApplication.class, args);
		
		LOGGER.info("Aplicacao de Consumo de JAX WS SOAP Divisoes Administrativas inicializada...");
		//LOGGER.error("Teste de Log de Erro");
		//LOGGER.warn("Teste de Log de Aviso");
		
		DivAdminService divAdminService = new DivAdminServiceImpl();
		
		//divAdminService.listarDistritos().stream().forEach(n -> System.out.println(n));
		//divAdminService.listarConcelhos().stream().forEach(n -> System.out.println(n));
		//divAdminService.listarFreguesias().stream().forEach(n -> System.out.println(n));
		
		//divAdminService.listarFreguesiasPorIdConcelho(7L).stream().forEach(n -> System.out.println(n));
		//divAdminService.listarFreguesiasPorIdDistrito(7L).stream().forEach(n -> System.out.println(n));
		//divAdminService.listarConcelhosPorIdDistrito(7L).stream().forEach(n -> System.out.println(n));
		
		//System.out.println(divAdminService.mostrarConcelhoPorIdFreguesia(7L));
		//System.out.println(divAdminService.mostrarDistritoPorIdFreguesia(177L));
		System.out.println(divAdminService.mostrarDistritoPorIdConcelho(3000L));
		
	}

}
