package br.ufpb.dcx.prolicen.educservice.performace;

import junit.framework.Assert;
import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacade;
import br.dcx.ufpb.prolicen.educservice.model.QuestaoMultiplaEscolha;

public class RespostaDeQuestaoPerformanceHelper {

	public static QuestaoMultiplaEscolha verificarCriacaoDeUmaRespostaCorretaParaUmaQuestaoDeMultiplaEscolhaValida(
			EducServiceFacade facade, String idAluno, String idExercicio,
			String idQuestao, int indiceAlternativaDaResposta) {

		QuestaoMultiplaEscolha questaoRespondida = null;
		try {
			QuestaoMultiplaEscolha questao = (QuestaoMultiplaEscolha) facade
					.pesquisarQuestaoMEPorId(idExercicio, idQuestao);

			facade.cadastrarRespostaQuestaoMEPorId(idAluno, idExercicio,
					questao.getId(), indiceAlternativaDaResposta);

			questaoRespondida = (QuestaoMultiplaEscolha) facade
					.consultaRespostaDeAluno(idAluno, idExercicio,
							questao.getId());

			for (int i = 0; i < questao.getAlternativas().size(); i++) {
				Assert.assertEquals(questao.getAlternativas().get(i),
						questaoRespondida.getAlternativas().get(i));
			}

			Assert.assertEquals(indiceAlternativaDaResposta,
					questaoRespondida.getAlternativaCorreta());

		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
		return questaoRespondida;
	}
}
