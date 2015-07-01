package br.ufpb.dcx.prolicen.educservice.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.dcx.ufpb.prolicen.educservice.model.QuestaoMultiplaEscolha;
import br.ufpb.dcx.prolicen.educservice.dao.QuestaoDAO;

public class QuestaoMySqlDAO extends AbstractMySqlDAO implements QuestaoDAO {

	public static final String TABLE_NAME = "questao";
	public static final String QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME = "questao_multipla_escolha";
	public static final String ALTERNATIVA_TABLE_NAME = "alternativa_questao_multipla_escolha";

	public QuestaoMySqlDAO(MySqlConnector connectionFactory) {
		super(connectionFactory);
	}

	public QuestaoMultiplaEscolha criarQuestaoMultiplaEscolha(
			QuestaoMultiplaEscolha qME, String idExercicio) {

		String idQuestao = criarQuestao(qME.getEnunciado(), idExercicio);

		String sqlInsertQuestaoMultiplaEscolha = "INSERT INTO "
				+ getDatabaseName() + "." + QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME
				+ " (id_questao, alternativa_correta) VALUES (?, ?);";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection
					.prepareStatement(sqlInsertQuestaoMultiplaEscolha);
			ps.setLong(1, Long.parseLong(idQuestao));
			ps.setInt(2, qME.getAlternativaCorreta());
			ps.execute();
			connection.close();
			this.cadastrarAlternativas(idQuestao, qME.getAlternativas());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return findQuestaoMultiplaEscolhaByMaxId();
	}

	private QuestaoMultiplaEscolha findQuestaoMultiplaEscolhaByMaxId() {
		String sql = "SELECT * FROM " + getDatabaseName() + "."
				+ QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME + " qme INNER JOIN "
				+ getDatabaseName() + "." + TABLE_NAME
				+ " q ON qme.id_questao = q.id "
				+ "WHERE qme.id_questao = (SELECT max(e.id_questao) FROM "
				+ getDatabaseName() + "." + QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME
				+ " e);";

		QuestaoMultiplaEscolha questaoMultiplaEscolha = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				String id = String.valueOf(resultSet.getLong("id_questao"));
				String enunciado = (resultSet.getString("enunciado"));
				int alternativaCorreta = resultSet
						.getInt("alternativa_correta");
				questaoMultiplaEscolha = new QuestaoMultiplaEscolha(id,
						enunciado, null, alternativaCorreta);
			}
			connection.close();

			if (questaoMultiplaEscolha != null) {
				List<String> alternativas = this
						.findAlternativasByQuestaoMultiplaEscolhaId(questaoMultiplaEscolha
								.getId());
				questaoMultiplaEscolha.setAlternativas(alternativas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return questaoMultiplaEscolha;
	}

	private List<String> findAlternativasByQuestaoMultiplaEscolhaId(
			String id_questao_multipla_escolha) {
		String sql = "SELECT * FROM " + getDatabaseName() + "."
				+ ALTERNATIVA_TABLE_NAME
				+ " a WHERE a.id_questao_multipla_escolha = ?;";

		List<String> alternativas = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(id_questao_multipla_escolha));
			ResultSet resultSet = ps.executeQuery();
			alternativas = new LinkedList<String>();

			while (resultSet.next()) {
				alternativas.add(resultSet.getString("alternativa"));
			}
			connection.close();
		} catch (Exception e) {
			return null;
		}

		return alternativas;
	}

	private void cadastrarAlternativas(String idQuestao,
			List<String> alternativas) throws SQLException {

		String sqlInsertAlternativas = "INSERT INTO "
				+ getDatabaseName()
				+ "."
				+ ALTERNATIVA_TABLE_NAME
				+ " (`alternativa`, id_questao_multipla_escolha) VALUES (?, ?);";

		for (String alternativa : alternativas) {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection
					.prepareStatement(sqlInsertAlternativas);
			ps.setString(1, alternativa);
			ps.setLong(2, Long.parseLong(idQuestao));
			ps.execute();
			connection.close();
		}
	}

	private String criarQuestao(String enunciado, String idExercicio) {
		String sqlInsertQuestao = "INSERT INTO " + getDatabaseName() + "."
				+ TABLE_NAME + "(enunciado, id_exercicio) VALUES (?, ?);";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection
					.prepareStatement(sqlInsertQuestao);

			ps.setString(1, enunciado);
			ps.setLong(2, Long.parseLong(idExercicio));

			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return this.findMaxIdDeUmaQuestao();
	}

	private String findMaxIdDeUmaQuestao() {
		String sql = "SELECT * FROM " + getDatabaseName() + "." + TABLE_NAME
				+ " a WHERE a.id = (SELECT max(e.id) FROM " + getDatabaseName()
				+ "." + TABLE_NAME + " e);";

		String idQuestao = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				idQuestao = String.valueOf(resultSet.getLong("id"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return idQuestao;
	}

	public QuestaoMultiplaEscolha pesquisarQuestaoMultiplaEscolhaById(
			String idExercicio, String idQuestao) {

		String sql = "SELECT * FROM "
				+ getDatabaseName()
				+ "."
				+ QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME
				+ " qme INNER JOIN "
				+ getDatabaseName()
				+ "."
				+ TABLE_NAME
				+ " q ON qme.id_questao = q.id WHERE q.id_exercicio = ? AND qme.id_questao = ?";

		QuestaoMultiplaEscolha questaoMultiplaEscolha = null;
		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(idExercicio));
			ps.setLong(2, Long.parseLong(idQuestao));
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				String id = String.valueOf(resultSet.getLong("id_questao"));
				String enunciado = (resultSet.getString("enunciado"));
				int alternativaCorreta = resultSet
						.getInt("alternativa_correta");
				questaoMultiplaEscolha = new QuestaoMultiplaEscolha(id,
						enunciado, null, alternativaCorreta);
			}
			connection.close();

			if (questaoMultiplaEscolha != null) {
				List<String> alternativas = this
						.findAlternativasByQuestaoMultiplaEscolhaId(questaoMultiplaEscolha
								.getId());
				questaoMultiplaEscolha.setAlternativas(alternativas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return questaoMultiplaEscolha;
	}
}
