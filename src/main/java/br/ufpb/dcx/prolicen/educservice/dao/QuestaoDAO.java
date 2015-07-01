package br.ufpb.dcx.prolicen.educservice.dao;

import br.dcx.ufpb.prolicen.educservice.model.QuestaoMultiplaEscolha;

public interface QuestaoDAO {

	QuestaoMultiplaEscolha criarQuestaoMultiplaEscolha(
			QuestaoMultiplaEscolha qME, String idExercicio);

	QuestaoMultiplaEscolha pesquisarQuestaoMultiplaEscolhaById(
			String idExercicio, String idQuestao);

}
