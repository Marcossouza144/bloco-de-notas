package com.notepad;

public class NotePad {

	private String nome;

	private String sobrenome;

	private String texto;

	private String id;

	public NotePad() {

	}

	public boolean isNovo () {
		return id == null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NotePad(String nome, String sobrenome, String texto, String id) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.texto = texto;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}