package br.com.geradodor.validador.cpf.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geradodor.validador.cpf.model.Cpf;

@RestController
@RequestMapping("/api")
public class CpfController {


	@GetMapping("/gerador/{n}")
	public Cpf gerador(@PathVariable(value = "n") int n) {
		
		String cpf = Cpf.gerador();

		Cpf cpfGerado = new Cpf(cpf, true);

		return cpfGerado;

	}

}
