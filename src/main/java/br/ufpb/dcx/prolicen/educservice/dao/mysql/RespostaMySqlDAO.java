package br.ufpb.dcx.prolicen.educservice.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.dcx.ufpb.prolicen.educservice.model.Questao;
import br.dcx.ufpb.prolicen.educservice.model.QuestaoMultiplaEscolha;
import br.ufpb.dcx.prolicen.educservice.dao.RespostaDAO;

public class RespostaMySqlDAO extends AbstractMySqlDAO implements RespostaDAO {

	public static final String TABLE_NAME = "resposta";

	private QuestaoMySqlDAO questaoMySqlDAO;

	public RespostaMySqlDAO(MySqlConnector connectionFactory,
			QuestaoMySqlDAO questaoMySqlDAO) {
		super(connectionFactory);
		this.questaoMySqlDAO = questaoMySqlDAO;
	}

	public void cadastrarRespostaQuestaoMEPorId(String idAluno,
			String idExercicio, String idQuestao, int alternativaCorreta) {

		String sqlInsert = "INSERT INTO "
				+ getDatabaseName()
				+ "."
				+ TABLE_NAME
				+ " ( `id_aluno`, `id_exercicio`, `id_questao`, `alternativa_correta`) "
				+ "VALUES (?, ?, ?, ?);";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setLong(1, Long.parseLong(idAluno));
			ps.setLong(2, Long.parseLong(idExercicio));
			ps.setLong(3, Long.parseLong(idQuestao));
			ps.setInt(4, alternativaCorreta);
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Questao consultarRespostaDeAluno(String idAluno, String idExercicio,
			String idQuestao) {

		QuestaoMultiplaEscolha questaoME = questaoMySqlDAO
				.pesquisarQuestaoMultiplaEscolhaById(idExercicio, idQuestao);

		String sql = "SELECT * FROM "
				+ getDatabaseName()
				+ "."
				+ TABLE_NAME
				+ " r WHERE r.id_aluno = ? AND r.id_exercicio = ? AND r.id_questao = ?";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(idAluno));
			ps.setLong(2, Long.parseLong(idExercicio));
			ps.setLong(3, Long.parseLong(idQuestao));
			ps.execute();

			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				questaoME.setAlternativaCorreta(resultSet
						.getInt("alternativa_correta"));
			} else {
				questaoME = null;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questaoME;
	}

}
