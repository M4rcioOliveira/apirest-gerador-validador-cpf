package br.com.geradodor.validador.cpf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cpf implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;
	private Boolean situacao;

	public Cpf(String cpf, Boolean situacao) {
		super();
		this.cpf = cpf;
		this.situacao = situacao;
	}

	public String getCpf() {
		return cpf;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	/*
	 * É tipico em algumas linguagens ao adicionar um um valor númerico que inicia
	 * com 0 a esquerda a linguagem desconsiderar,então esse método identifica
	 * quando falta o/os 0 a esquerda e valida fazendo o complemento
	 */
	public static List<Character> verifica(String cpf) {

		List<Character> list = new ArrayList<Character>();

		if (cpf.length() < 11) {
			int a = 11 - cpf.length();
			for (int i = 0; i < a; i++) {
				list.add(i, '0');
			}

			for (int j = 0; j < cpf.length(); j++) {

				list.add(a++, cpf.charAt(j));

			}

		} else {
			for (int i = 0; i < cpf.length(); i++) {
				list.add(i, cpf.charAt(i));
			}
		}

		return list;

	}

	//OBS: Esse método aceita o cpf somente sem maskara!
	//No end points é retornado o cpf com maskara.
	//Faça a validação no front para que o método receba somente os números.
	public static boolean validador(String cpf) {

		if (cpf.equals("00000000000") | cpf.equals("11111111111") | cpf.equals("22222222222")
				| cpf.equals("33333333333") | cpf.equals("44444444444") | cpf.equals("55555555555")
				| cpf.equals("66666666666") | cpf.equals("77777777777") | cpf.equals("88888888888")
				| cpf.equals("99999999999")) {

			return false;
		} else {

			List<Integer> listInt = new ArrayList<Integer>();

			for (Character character : verifica(cpf)) {
				listInt.add(Character.getNumericValue(character));
			}

			int acumulador1 = 0;
			int d1 = 10; // decremento

			for (int i = 0; i < 9; i++) {
				acumulador1 = (listInt.get(i) * d1--) + acumulador1;
			}

			int primeiroDigito = 11 - (acumulador1 % 11);

			if (primeiroDigito >= 10) {
				primeiroDigito = 0;
			}

			int acumulador2 = 0;
			int d2 = 11; // decremento

			for (int i = 0; i < 10; i++) {
				acumulador2 = (listInt.get(i) * d2--) + acumulador2;
			}

			int segundoDigito = 11 - (acumulador2 % 11);

			if (segundoDigito >= 10) {
				segundoDigito = 0;
			}

			if (listInt.get(9) == primeiroDigito & listInt.get(10) == segundoDigito) {
				return true;
			} else {
				return false;
			}

		}

	}

	public static String gerador() {

		List<Integer> listInt = new ArrayList<Integer>();

		Random random = new Random();

		for (int i = 0; i < 9; i++) {

			int nAleatorio = random.nextInt(9);

			listInt.add(nAleatorio);

		}
		int acumulador1 = 0;
		int d1 = 10; // decremento

		for (int i = 0; i < 9; i++) {
			acumulador1 = (listInt.get(i) * d1--) + acumulador1;
		}

		int primeiroDigito = 11 - (acumulador1 % 11);

		if (primeiroDigito >= 10) {
			primeiroDigito = 0;
		}

		listInt.add(9, primeiroDigito);

		int acumulador2 = 0;
		int d2 = 11; // decremento

		for (int i = 0; i < 10; i++) {
			acumulador2 = (listInt.get(i) * d2--) + acumulador2;
		}

		int segundoDigito = 11 - (acumulador2 % 11);

		if (segundoDigito >= 10) {
			segundoDigito = 0;
		}

		listInt.add(10, segundoDigito);

		String cpfGerado = "";

		for (Integer i : listInt) {
			cpfGerado = cpfGerado + Character.forDigit(i, 10);
		}

		return cpfGerado;
	}

}
