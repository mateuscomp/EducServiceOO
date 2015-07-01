package br.dcx.ufpb.prolicen.educservice.model;

public abstract class Questao {
	private String id;
	protected String enunciado;

	public Questao(String id) {
		this.setId(id);
	}

	public abstract String getDescricao();

	public abstract String getSolucao();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
}