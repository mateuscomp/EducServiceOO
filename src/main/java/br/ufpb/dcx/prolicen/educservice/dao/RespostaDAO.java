package br.ufpb.dcx.prolicen.educservice.dao;

import br.dcx.ufpb.prolicen.educservice.model.Questao;

public interface RespostaDAO {

	void cadastrarRespostaQuestaoMEPorId(String idAluno, String idExercicio,
			String idQuestao, int alternativaCorreta);

	Questao consultarRespostaDeAluno(String idAluno, String idExercicio,
			String idQuestao);

}