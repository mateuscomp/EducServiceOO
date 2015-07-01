package br.dcx.ufpb.prolicen.educservice.model;

import java.util.LinkedList;
import java.util.List;

public class Exercicio {

	private String id;
	private List<Questao> questoes;
	private List<String> palavrasChave;

	public Exercicio(String id) {
		this.id = id;
		setQuestoes(new LinkedList<Questao>());
		this.palavrasChave = new LinkedList<String>();
	}

	public void adicionarQuestao(Questao q) {
		this.questoes.add(q);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<String> getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(List<String> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}
}
