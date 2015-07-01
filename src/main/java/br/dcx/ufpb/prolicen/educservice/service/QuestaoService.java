package br.dcx.ufpb.prolicen.educservice.service;

import java.util.List;

import br.dcx.ufpb.prolicen.educservice.model.QuestaoMultiplaEscolha;
import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;
import br.ufpb.dcx.prolicen.educservice.dao.QuestaoDAO;

public class QuestaoService {

	private QuestaoDAO questaoDao;

	public QuestaoService(EducServiceDaoFactory daoFactory) {
		this.questaoDao = daoFactory.createQuestaoDAO();
	}

	public QuestaoMultiplaEscolha cadastrarQuestaoME(String idExercicio,
			String enunciado, List<String> alternativas, int alternativaCorreta) {

		QuestaoMultiplaEscolha qME = new QuestaoMultiplaEscolha(null,
				enunciado, alternativas, alternativaCorreta);

		return questaoDao.criarQuestaoMultiplaEscolha(qME, idExercicio);
	}

	public QuestaoMultiplaEscolha pesquisarQuestaoMEPorId(String idExercicio,
			String idQuestao) {

		return questaoDao.pesquisarQuestaoMultiplaEscolhaById(idExercicio, idQuestao);
	}

}
