package com.locadora.model.br;

import java.time.LocalDate;
import java.util.Date;

public class Filme {

	private int id_filme;
	private String nome_filme;
	private String duracao_filme;
	private String genero_filme;
	private String data_filme;
	private String sinopse_filme;
	private String imagem_filme;
	private LocalDate data_modificacao_filme;

	public LocalDate getData_modificacao_filme() {
		return data_modificacao_filme;
	}

	public void setData_modificacao_filme(LocalDate data_modificacao_filme) {
		this.data_modificacao_filme = data_modificacao_filme;
	}

	public String getDuracao_filme() {
		return duracao_filme;
	}

	public void setDuracao_filme(String duracao_filme) {
		this.duracao_filme = duracao_filme;
	}

	public String getData_filme() {
		return data_filme;
	}

	public void setData_filme(String data_filme) {
		this.data_filme = data_filme;
	}

	public int getId_filme() {
		return id_filme;
	}

	public void setId_filme(int id_filme) {
		this.id_filme = id_filme;
	}

	public String getNome_filme() {
		return nome_filme;
	}

	public void setNome_filme(String nome_filme) {
		this.nome_filme = nome_filme;
	}

	public String getGenero_filme() {
		return genero_filme;
	}

	public void setGenero_filme(String genero_filme) {
		this.genero_filme = genero_filme;
	}

	public String getSinopse_filme() {
		return sinopse_filme;
	}

	public void setSinopse_filme(String sinopse_filme) {
		this.sinopse_filme = sinopse_filme;
	}

	public String getImagem_filme() {
		return imagem_filme;
	}

	public void setImagem_filme(String imagem_filme) {
		this.imagem_filme = imagem_filme;
	}

}
