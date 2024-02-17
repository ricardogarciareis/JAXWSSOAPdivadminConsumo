package com.dzrrcreations.JAXWSSOAPdivadminConsumo.model;

public class Concelho {

	private Long id;
	private String nome;
	private String distrito;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getDistrito() { return distrito; }
	public void setDistrito(String distrito) { this.distrito = distrito; }
	
	@Override
	public String toString() {
		return "Concelho [idConcelho=" + id + ", concelho=" + nome + ", distrito=" + distrito + "]";
	}
	
}
