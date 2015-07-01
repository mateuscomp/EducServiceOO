package br.dcx.ufpb.prolicen.educservice.model;

import java.util.List;

import br.dcx.ufpb.prolicen.educservice.service.AlunoService;
import br.dcx.ufpb.prolicen.educservice.service.ExercicioService;
import br.dcx.ufpb.prolicen.educservice.service.QuestaoService;
import br.dcx.ufpb.prolicen.educservice.service.RespostaService;
import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;

public class EducServiceFacadeImpl implements EducServiceFacade {

	private AlunoService alunoService;
	private ExercicioService exercicioService;
	private QuestaoService questaoService;
	private RespostaService respostaService;
	
	public EducServiceFacadeImpl(EducServiceDaoFactory daoFactory){
		this.alunoService = new AlunoService(daoFactory);
		this.exercicioService = new ExercicioService(daoFactory);
		this.questaoService = new QuestaoService(daoFactory);
		this.respostaService = new RespostaService(daoFactory);
	}

	public Aluno criarAluno(String nome, String login, String senha) {
		return this.alunoService.criarAluno(nome, login, senha);
	}

	public Aluno pesquisarAluno(String id) {
		return this.alunoService.pesquisarAlunoPorId(id);
	}

	public void cadastraRespostaVFDeAluno(String idAluno, String idExercicio,
			String idQuestao, List<String> respostas) {
		// TODO Auto-generated method stub

	}

	public void cadastrarRespostaQuestaoMEPorId(String idAluno,
			String idExercicio, String idQuestao, int alternativaCorreta) {
			
		this.respostaService.cadastrarRespostaQuestaoMEPorId(idAluno, idExercicio, idQuestao, alternativaCorreta);
	}

	public void cadastrarRespostaQuestaoDissertativaPorId(String idAluno,
			String idExercicio, String idQuestao, String solucao) {
		// TODO Auto-generated method stub

	}

	public void removerAluno(String idAluno) {
		// TODO Auto-generated method stub

	}

	public Questao consultaRespostaDeAluno(String idAluno, String idExercicio,
			String idQuestao) {

		return this.respostaService.consultarRespostaDeAluno(idAluno, idExercicio, idQuestao);
	}

	public Exercicio criarExercicio() {
		return this.exercicioService.criarExercicio();
	}

	public Exercicio criarExercicio(List<String> palavrasChave) {
		return this.exercicioService.criarExercicio(palavrasChave);
	}

	public void configuraPalavrasChave(String idExercicio) {
		// TODO Auto-generated method stub

	}

	public void apagar(String idExercicio) {
		// TODO Auto-generated method stub

	}

	public void atualizar(Exercicio e) {
		// TODO Auto-generated method stub

	}

	public Exercicio pesquisarExercicioPorId(String idExercicio) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoVouF cadastrarQuestaoVouF(String idExercicio,
			List<String> afirmativas) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoDissertativa cadastrarQuestaoDissertativa(String idExercicio,
			String enunciado) {
		// TODO Auto-generated method stub
		return null;
	}

	public Questao pesquisarQuestaoPorId(String idExercicio, String idQuestao) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoMultiplaEscolha pesquisarQuestaoMEPorId(String idExercicio,
			String idQuestao) {
		return this.questaoService.pesquisarQuestaoMEPorId(idExercicio, idQuestao);
	}

	public QuestaoVouF pesquisarQuestaoVouFPorId(String idExercicio,
			String idQuestao) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoDissertativa pesquisarQuestaoDissertativaPorId(
			String idExercicio, String idQuestao) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoMultiplaEscolha cadastrarRespostaQuestaoMEPorId(
			String idExercicio, int alternativaCorreta) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoVouF cadastrarRespostaQuestaoVouFPorId(String idExercicio,
			String idQuestao, List<String> respostas) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoDissertativa cadastrarRespostaQuestaoDissertativaPorId(
			String idExercicio, String idQuestao, String solucao) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoMultiplaEscolha cadastrarQuestaoME(String idExercicio,
			String enunciado, List<String> alternativas, int alternativaCorreta) {
		return questaoService.cadastrarQuestaoME(idExercicio, enunciado, alternativas, alternativaCorreta);
	}

	public QuestaoVouF cadastrarQuestaoVouF(String idExercicio,
			List<String> afirmativas, List<String> respostas) {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoDissertativa cadastrarQuestaoDissertativa(String idExercicio,
			String enunciado, String solucao) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Exercicio> pesquisarTodosOsExercicios() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Exercicio> pesquisarExerciciosSobre(List<String> palavrasChave) {
		// TODO Auto-generated method stub
		return null;
	}

}
