package br.com.geradodor.validador.cpf.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.geradodor.validador.cpf.model.Cpf;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")//não usar em apis privadas
public class CpfController {

	@GetMapping("/validador")
	@ResponseBody
	public ResponseEntity<?> validador(@RequestParam(name = "cpf") String cpf) {

		Cpf cpfTrue = new Cpf(cpf, true);
		Cpf cpfFalse = new Cpf(cpf, false);

		boolean validar = Cpf.validador(cpf);

		if (validar == false) {
			return new ResponseEntity<Cpf>(cpfFalse, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<Cpf>(cpfTrue, HttpStatus.OK);
		}

	}

	@GetMapping("/gerador")
	@ResponseBody
	public ResponseEntity<?> gerador() {
		
		String cpf = Cpf.gerador();

		Cpf cpfGerado = new Cpf(cpf, true);

		return new ResponseEntity<Cpf>(cpfGerado, HttpStatus.OK);

	}

}
