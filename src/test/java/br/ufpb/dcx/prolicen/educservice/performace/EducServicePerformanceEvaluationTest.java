package br.ufpb.dcx.prolicen.educservice.performace;

import org.junit.Test;

import br.dcx.ufpb.prolicen.educservice.EducServiceTest;
import br.dcx.ufpb.prolicen.educservice.model.Exercicio;

public class EducServicePerformanceEvaluationTest extends
		EducServiceTest {
	

	/**
	 * Cenário 1: Cadastrar 10.000 alunos e medir o tempo de cada cadastro.
	 * Depois colocar cada tempo desse numa planilha e traçar o gráfico desse
	 * tempo medido ao longo a medida em que aumenta o número de alunos Variável
	 * #tempoCadastroAluno
	 */
//	@Test
//	public void coletarTempoGastoParaCadastrarMuitosAlunos() {
//		int quantidadeDeAlunosACadastrar = 10000;
//
//		for (int i = 0; i < quantidadeDeAlunosACadastrar; i++) {
//			AlunoPerformanceHelper.cadastrarAlunoEExportarTempoGasto(facade, i,
//					"#tempoCadastroAluno");
//		}
//	}

	/**
	 * Cenário 2: Cadastra um exercício e vai aumentando o número de questões
	 * até 10.000 e calcula o tempo gasto para cadastrar cada questão Variável
	 * #tempoCadastroQuestao1Exercicio
	 */
//	@Test
//	public void coletarTempoGastoParaCadastrarUmExercicioComMuitasQuestoes() {
//		Exercicio exercicio = ExercicioPerformanceHelper
//				.criarExercicioComCincoPalavrasChave(facade);
//
//		int quantidadeDeQuestoes = 10000;
//
//		for (int i = 0; i < quantidadeDeQuestoes; i++) {
//			QuestaoMultiplaEscolhaPerformanceHelper
//					.cadastrarQuestaoMultiplaEscolhaEExportarTempoGasto(facade,
//							exercicio, i, "#tempoCadastroQuestao1Exercicio");
//		}
//	}

	/**
	 * Cenário 3: Cadastrar 10.000 exercícios de 10 questões cada e calcular o
	 * tempo de cadastro de cada exercício Variável
	 * #tempoCadastroExercicio10Questoes
	 */
//	@Test
//	public void coletarTempoGastoParaCadastrarMuitosExercicioComDezQuestoes() {
//		int quantidadeDeExercicios = 10000;
//		int quantidadeDeQuestoes = 10;
//
//		for (int i = 0; i < quantidadeDeExercicios; i++) {
//			ExercicioPerformanceHelper
//					.cadastrarExercicioComQuestoesDeMultiplaEscolhaEExportarTempoGasto(
//							facade, quantidadeDeQuestoes,
//							"#tempoCadastroExercicio10Questoes");
//		}
//	}

	/**
	 * Cenário 4: - Depois de cadastrar 10.000 Exercícios e 10.000 alunos, você
	 * vai calcular o tempo para fazer a pesquisa de um aluno e de um exercício
	 * #tempoPesquisaAluno (do 1o. ao último aluno) #tempoPesquisaExercicio (do
	 * 1o. ao último exercício) #tempoPesquisaQuestao (do 1o. ao último
	 * exercício a primeira questão de cada um)
	 */
	@Test
	public void coletarTempoDePesquisaDeExercicioEDeAlunoDepoisDeMuitosExerciciosEMuitosAlunosCadastrados() {
		int qtdDeExerciciosEAlunos = 10000;
		int qtdQuestoesPorExercicio = 10;

		this.cadastrarMuitosAlunosMuitosExerciciosMuitasQuestoes(
				qtdDeExerciciosEAlunos, qtdQuestoesPorExercicio);

		for (int i = 1; i <= qtdDeExerciciosEAlunos; i++) {
			AlunoPerformanceHelper.pesquisarAlunoEExportarTempoGasto(facade,
					String.valueOf(i), "#tempoPesquisaAluno");
		}

		for (int i = 1; i <= qtdDeExerciciosEAlunos; i++) {
			ExercicioPerformanceHelper.pesquisarExercioEExportarTempoGasto(
					facade, String.valueOf(i), "#tempoPesquisaExercicio");
		}

		int idQuestao = 1;
		for (int idExercicio = 1; idExercicio <= qtdDeExerciciosEAlunos; idExercicio++) {
			for (int j = 1; j <= qtdQuestoesPorExercicio; j++) {
				QuestaoMultiplaEscolhaPerformanceHelper
						.pesquisarQuestaoDeMultiplaEscolhaDeExercicioEExportarTempoGasto(
								facade, String.valueOf(idExercicio),
								String.valueOf(idQuestao),
								"#tempoPesquisaQuestao");
				idQuestao++;
			}
		}
	}

	private void cadastrarMuitosAlunosMuitosExerciciosMuitasQuestoes(
			int qtdDeExerciciosEAlunos, int qtdQuestoesPorExercicio) {

		for (int i = 0; i < qtdDeExerciciosEAlunos; i++) {
			AlunoPerformanceHelper.criarAluno(facade, i);
		}

		for (int i = 0; i < qtdDeExerciciosEAlunos; i++) {
			Exercicio exercicio = ExercicioPerformanceHelper
					.criarExercicioComCincoPalavrasChave(facade);

			for (int j = 0; j < qtdQuestoesPorExercicio; j++) {
				QuestaoMultiplaEscolhaPerformanceHelper
						.cadastrarQuestaoMultiplaEscolha(facade, exercicio);
			}
		}
	}
}