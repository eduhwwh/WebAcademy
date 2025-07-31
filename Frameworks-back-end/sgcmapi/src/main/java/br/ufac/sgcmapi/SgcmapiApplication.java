package br.ufac.sgcmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SgcmapiApplication {

	private ExemploService exemploService;

	public SgcmapiApplication(ExemploService exemploService){
		this.exemploService = exemploService;
	}

	@RequestMapping(value = "/")
	public String exemplo() {
		// return "SGCM";

		return exemploService.exibirMensagem();
	}

	public static void main(String[] args) {
		SpringApplication.run(SgcmapiApplication.class, args);
	}

	@Service
	public static class ExemploService {

		public String exibirMensagem(){

			return "SGCM funcionando";

		}
		
	}

}
