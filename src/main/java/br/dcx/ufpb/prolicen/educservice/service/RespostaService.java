package br.dcx.ufpb.prolicen.educservice.service;

import br.dcx.ufpb.prolicen.educservice.model.Questao;
import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;
import br.ufpb.dcx.prolicen.educservice.dao.RespostaDAO;

public class RespostaService {

	private RespostaDAO respostaDao;

	public RespostaService(EducServiceDaoFactory daoFactory) {
		this.respostaDao = daoFactory.createRespostaDao();
	}

	public void cadastrarRespostaQuestaoMEPorId(String idAluno,
			String idExercicio, String idQuestao, int alternativaCorreta) {

		this.respostaDao.cadastrarRespostaQuestaoMEPorId(idAluno, idExercicio,
				idQuestao, alternativaCorreta);
	}

	public Questao consultarRespostaDeAluno(String idAluno, String idExercicio,
			String idQuestao) {

		return this.respostaDao.consultarRespostaDeAluno(idAluno, idExercicio,
				idQuestao);
	}

}
