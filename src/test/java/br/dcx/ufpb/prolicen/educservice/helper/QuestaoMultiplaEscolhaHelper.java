package br.dcx.ufpb.prolicen.educservice.helper;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;

import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacade;
import br.dcx.ufpb.prolicen.educservice.model.Exercicio;
import br.dcx.ufpb.prolicen.educservice.model.QuestaoMultiplaEscolha;

public class QuestaoMultiplaEscolhaHelper {

	public static QuestaoMultiplaEscolha verificarCriacaoDeUmQuestaoDeMultiplaEscolhaValida(
			EducServiceFacade facade, String enunciado, Exercicio exercicio,
			int indiceAlternativaCorreta, String... alternativas) {

		List<String> alternativasList = new LinkedList<String>();
		int qtdMaxAlternativas = 5;
		for (int i = 0; i < qtdMaxAlternativas; i++) {
			alternativasList.add(alternativas[i]);
		}

		QuestaoMultiplaEscolha qME = facade.cadastrarQuestaoME(
				exercicio.getId(), enunciado, alternativasList,
				indiceAlternativaCorreta);

		Assert.assertNotNull(qME.getId());
		Assert.assertEquals(enunciado, qME.getEnunciado());
		Assert.assertEquals(indiceAlternativaCorreta,
				qME.getAlternativaCorreta());

		for (int i = 0; i < alternativasList.size(); i++) {
			Assert.assertEquals(alternativasList.get(i), qME.getAlternativas()
					.get(i));
		}
		return qME;
	}
}
