package br.ufac.sgcmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@RestController
@OpenAPIDefinition(
	info = @Info(
		title = "SGCM API",
		version = "1.0",
		description = "API do Sistema de Gereciamento de Clínicas Médicas"
	)
)
public class SgcmapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgcmapiApplication.class, args);
	}

}
