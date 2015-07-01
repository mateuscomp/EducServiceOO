package br.dcx.ufpb.prolicen.educservice.model;

import java.util.List;

public class QuestaoMultiplaEscolha extends Questao {
	private List<String> alternativas;
	private int alternativaCorreta;

	public QuestaoMultiplaEscolha(String id, String enunciado,
			List<String> alternativas, int alternativaCorreta) {
		super(id);
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.alternativaCorreta = alternativaCorreta;
	}

	public List<String> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<String> alternativas) {
		this.alternativas = alternativas;
	}

	public int getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(int alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	@Override
	public String getDescricao() {
		return enunciado + alternativas.toString();
	}

	@Override
	public String getSolucao() {
		return alternativas.get(alternativaCorreta);
	}

}
