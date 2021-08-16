package br.com.geradodor.validador.cpf.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geradodor.validador.cpf.model.Cpf;

@RestController
@RequestMapping("/api")
public class CpfController {


	@GetMapping("/gerador")
	public Cpf gerador() {
		
		String cpf = Cpf.gerador();

		Cpf cpfGerado = new Cpf(cpf, true);

		return cpfGerado;

	}

}
