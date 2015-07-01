package br.dcx.ufpb.prolicen.educservice.model;

import java.util.List;

public interface EducServiceFacade {

	public Aluno criarAluno(String nome, String login, String senha);

	public Aluno pesquisarAluno(String id);

	public void cadastraRespostaVFDeAluno(String idAluno, String idExercicio,
			String idQuestao, List<String> respostas);

	public void cadastrarRespostaQuestaoMEPorId(String idAluno,
			String idExercicio, String idQuestao, int alternativaCorreta);

	public void cadastrarRespostaQuestaoDissertativaPorId(String idAluno,
			String idExercicio, String idQuestao, String solucao);

	public void removerAluno(String idAluno);

	public Questao consultaRespostaDeAluno(String idAluno, String idExercicio,
			String idQuestao);

	// Exercício

	public Exercicio criarExercicio();

	public Exercicio criarExercicio(List<String> palavrasChave);

	public void configuraPalavrasChave(String idExercicio);

	public void apagar(String idExercicio);

	public void atualizar(Exercicio e);

	public Exercicio pesquisarExercicioPorId(String idExercicio);

	// public QuestaoMultiplaEscolha cadastrarQuestaoME(String idExercicio,
	// String enunciado, List<String> alternativas);

	public QuestaoVouF cadastrarQuestaoVouF(String idExercicio,
			List<String> afirmativas);

	public QuestaoDissertativa cadastrarQuestaoDissertativa(String idExercicio,
			String enunciado);

	public Questao pesquisarQuestaoPorId(String idExercicio, String idQuestao);

	public QuestaoMultiplaEscolha pesquisarQuestaoMEPorId(String idExercicio,
			String idQuestao);

	public QuestaoVouF pesquisarQuestaoVouFPorId(String idExercicio,
			String idQuestao);

	public QuestaoDissertativa pesquisarQuestaoDissertativaPorId(
			String idExercicio, String idQuestao);

	public QuestaoMultiplaEscolha cadastrarRespostaQuestaoMEPorId(
			String idExercicio, int alternativaCorreta);

	public QuestaoVouF cadastrarRespostaQuestaoVouFPorId(String idExercicio,
			String idQuestao, List<String> respostas);

	public QuestaoDissertativa cadastrarRespostaQuestaoDissertativaPorId(
			String idExercicio, String idQuestao, String solucao);

	public QuestaoMultiplaEscolha cadastrarQuestaoME(String idExercicio,
			String enunciado, List<String> alternativas, int alternativaCorreta);

	public QuestaoVouF cadastrarQuestaoVouF(String idExercicio,
			List<String> afirmativas, List<String> respostas);

	public QuestaoDissertativa cadastrarQuestaoDissertativa(String idExercicio,
			String enunciado, String solucao);

	public List<Exercicio> pesquisarTodosOsExercicios();

	public List<Exercicio> pesquisarExerciciosSobre(List<String> palavrasChave);

}
