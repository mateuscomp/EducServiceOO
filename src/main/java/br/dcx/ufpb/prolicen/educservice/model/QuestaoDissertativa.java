package br.dcx.ufpb.prolicen.educservice.model;

public class QuestaoDissertativa extends Questao {

//	private String enunciado;
	private String solucao;

	public QuestaoDissertativa(String id, String enunciado) {
		super(id);
		this.enunciado = enunciado;
	}

	@Override
	public String getDescricao() {
		return enunciado;
	}

	@Override
	public String getSolucao() {
		return solucao;
	}

//	public String getEnunciado() {
//		return enunciado;
//	}
//
//	public void setEnunciado(String enunciado) {
//		this.enunciado = enunciado;
//	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

}
