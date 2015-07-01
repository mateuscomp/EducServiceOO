package br.dcx.ufpb.prolicen.educservice;

import org.junit.Test;

import br.dcx.ufpb.prolicen.educservice.helper.QuestaoMultiplaEscolhaHelper;

public class QuestaoDeMultiplaEscolhaTest extends EducServiceTest{

	@Test
	public void cadastrarUmQuestaoValida() {
		String enunciado = "(POSCOMP 2014) Suponha que o sistema de identificação de funcionários "
				+ "em uma empresa seja composto por um código "
				+ "com quatro dígitos numéricos. Assinale a alternativa "
				+ "que apresenta, corretamente, a quantidade máxima de funcionários que "
				+ "essa empresa pode registrar com esse sistema de identificação, "
				+ "considerando dígitos numéricos distintos.";

		QuestaoMultiplaEscolhaHelper
				.verificarCriacaoDeUmQuestaoDeMultiplaEscolhaValida(facade,
						enunciado, this.facade.criarExercicio(), 1, "03024",
						"5040", "06561", "09000", "10000");
	}
}
