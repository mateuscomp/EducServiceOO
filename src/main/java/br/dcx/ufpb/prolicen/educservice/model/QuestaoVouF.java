package br.dcx.ufpb.prolicen.educservice.model;

import java.util.List;

public class QuestaoVouF extends Questao {

	private List<String> afirmativas;
	/**
	 * Lista com Strings que podem ser "V" ou "F"
	 */
	private List<String> respostas;

	public QuestaoVouF(String id, List<String> afirmativas) {
		super(id);
		this.setAfirmativas(afirmativas);
	}

	@Override
	public String getDescricao() {
		return afirmativas.toString();
	}

	@Override
	public String getSolucao() {
		return respostas.toString();
	}

	public List<String> getAfirmativas() {
		return afirmativas;
	}

	public void setAfirmativas(List<String> afirmativas) {
		this.afirmativas = afirmativas;
	}

	public List<String> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<String> respostas) {
		this.respostas = respostas;
	}
}
