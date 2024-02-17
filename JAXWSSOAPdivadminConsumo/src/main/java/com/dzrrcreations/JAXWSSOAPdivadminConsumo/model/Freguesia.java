package com.dzrrcreations.JAXWSSOAPdivadminConsumo.model;

public class Freguesia {

	private Long id;
	private String nome;
	private String concelho;
	private String distrito;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getConcelho() { return concelho; }
	public void setConcelho(String concelho) { this.concelho = concelho; }
	public String getDistrito() { return distrito; }
	public void setDistrito(String distrito) { this.distrito = distrito; }
	
	@Override
	public String toString() {
		return "Freguesia [idFreguesia=" + id + ", freguesia=" + nome + ", concelho=" + concelho + ", distrito=" + distrito + "]";
	}

}
