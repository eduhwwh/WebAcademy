package br.ufac.sgcmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import br.ufac.sgcmapi.service.ConvenioService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
@RestController
@OpenAPIDefinition(
	info = @Info(
		title = "SGCM API",
		version = "1.0",
		description = "API do Sistema de Gerenciamento de Clínicas Médicas"
	),
	security = {@SecurityRequirement(name = "jwtAuth")}
)
@SecurityScheme(
	name = "jwtAuth",
	description = "Autenticação JWT",
	scheme = "bearer",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
@EnableCaching
@EnableScheduling
public class SgcmapiApplication {

	private final ConvenioService convenioService;

	public SgcmapiApplication(ConvenioService convenioService) {
		this.convenioService = convenioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SgcmapiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void exemploRestClient() {
		System.out.println("Consultando CEP...");
		var cliente = RestClient.create();
		var resultado = cliente.get()
			.uri("https://brasilapi.com.br/api/cep/v2/69915632")
			.retrieve()
			.body(String.class);
		System.out.println(resultado);
		convenioService.verificarRegistroAns();
	}

}
