package br.ufpb.dcx.prolicen.educservice.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;

public class MySqlDaoFactory implements EducServiceDaoFactory {

	private MySqlConnector connector = new MySqlConnector();

	private AlunoMySqlDAO alunoDao;
	private ExercicioMySqlDAO exercicioDao;
	private QuestaoMySqlDAO questaoDao;
	private RespostaMySqlDAO respostaDao;

	public AlunoMySqlDAO createAlunoDao() {
		if (this.alunoDao == null) {
			this.alunoDao = new AlunoMySqlDAO(connector);
		}
		return alunoDao;
	}

	public ExercicioMySqlDAO createExercicioDao() {
		if (this.exercicioDao == null) {
			this.exercicioDao = new ExercicioMySqlDAO(connector);
		}

		return this.exercicioDao;
	}

	public QuestaoMySqlDAO createQuestaoDAO() {
		if (this.questaoDao == null) {
			this.questaoDao = new QuestaoMySqlDAO(connector);
		}
		return this.questaoDao;
	}

	public RespostaMySqlDAO createRespostaDao() {
		if (this.respostaDao == null) {
			this.respostaDao = new RespostaMySqlDAO(connector,
					createQuestaoDAO());
		}
		return respostaDao;
	}

	public void createEducServiceSchema() {
		String databaseName = connector.getDatabaseName();
		this.connector.setDatabaseName(null);

		String createDatabaseCommand = "CREATE DATABASE " + databaseName + "; ";

		String sqlAlunoTable = "CREATE TABLE " + databaseName + "."
				+ AlunoMySqlDAO.TABLE_NAME + " ("
				+ "id bigint(20) NOT NULL AUTO_INCREMENT, "
				+ " nome VARCHAR(45) NOT NULL, "
				+ "login VARCHAR(45) NOT NULL, "
				+ "senha VARCHAR(45) NULL, PRIMARY KEY (id)); ";

		String sqlExercicioTable = "CREATE TABLE "
				+ databaseName
				+ "."
				+ ExercicioMySqlDAO.TABLE_NAME
				+ " (id BIGINT(20) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id) );";

		String sqlPalavraChaveTable = "CREATE TABLE " + databaseName + "."
				+ ExercicioMySqlDAO.PALAVRAS_CHAVE_TABLE
				+ " (id_exercicio BIGINT(20) NOT NULL, "
				+ " palavra_chave VARCHAR(45) NOT NULL, "
				+ " INDEX FK_id_exercicio_idx (id_exercicio ASC), "
				+ " CONSTRAINT FK_id_exercicio "
				+ " FOREIGN KEY (id_exercicio) " + " REFERENCES "
				+ databaseName + "." + ExercicioMySqlDAO.TABLE_NAME + " (id) "
				+ " ON DELETE NO ACTION " + " ON UPDATE NO ACTION);";

		String sqlQuestaoTable = "CREATE TABLE "
				+ databaseName
				+ "."
				+ QuestaoMySqlDAO.TABLE_NAME
				+ " ("
				+ "id BIGINT(20) NOT NULL AUTO_INCREMENT,"
				// + "  enunciado VARCHAR(45) NOT NULL,"
				+ "  enunciado LONGTEXT,"
				+ "  id_exercicio BIGINT(20) NOT NULL," + "  PRIMARY KEY (id),"
				+ "  INDEX FK_EXERCICIO_ID_EXERCICIO_idx (id_exercicio ASC),"
				+ "  CONSTRAINT FK_EXERCICIO_ID_EXERCICIO"
				+ "    FOREIGN KEY (id_exercicio)" + "    REFERENCES "
				+ databaseName + "." + ExercicioMySqlDAO.TABLE_NAME + " (id)"
				+ " ON DELETE NO ACTION" + " ON UPDATE NO ACTION);";

		String sqlQuestaoMultiplaEscolhaTable = "CREATE TABLE "
				+ databaseName
				+ "."
				+ QuestaoMySqlDAO.QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME
				+ " ("
				+ "id_questao BIGINT(20) NOT NULL, "
				+ "alternativa_correta INT NOT NULL,"
				+ "  INDEX FK_QUESTAO_QUESTAO_MULTIPLA_ESCOLHA_idx (id_questao ASC),"
				+ "  CONSTRAINT FK_QUESTAO_QUESTAO_MULTIPLA_ESCOLHA"
				+ "    FOREIGN KEY (id_questao)" + "    REFERENCES "
				+ databaseName + "." + QuestaoMySqlDAO.TABLE_NAME + " (id)"
				+ "    ON DELETE NO ACTION" + "    ON UPDATE NO ACTION);";

		String sqlAlternativaTable = "CREATE TABLE "
				+ databaseName
				+ "."
				+ QuestaoMySqlDAO.ALTERNATIVA_TABLE_NAME
				+ " ( "
				+ "alternativa VARCHAR(45) NOT NULL,"
				+ "  id_questao_multipla_escolha BIGINT(20) NOT NULL,"
				+ "  INDEX FK_ALTERNATIVA_QUESTAO_MULTIPLA_ESCOLHA_idx (id_questao_multipla_escolha ASC),"
				+ "  CONSTRAINT FK_ALTERNATIVA_QUESTAO_MULTIPLA_ESCOLHA"
				+ "    FOREIGN KEY (id_questao_multipla_escolha)"
				+ "    REFERENCES " + databaseName + "."
				+ QuestaoMySqlDAO.QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME
				+ " (id_questao)" + "    ON DELETE NO ACTION"
				+ "    ON UPDATE NO ACTION);";

		String sqlRespostaTable = "CREATE TABLE " + databaseName + "."
				+ RespostaMySqlDAO.TABLE_NAME + "("
				+ "  id BIGINT(20) NOT NULL AUTO_INCREMENT,"
				+ "  id_aluno BIGINT(20) NOT NULL,"
				+ "  id_exercicio BIGINT(20) NOT NULL,"
				+ "  id_questao BIGINT(20) NOT NULL,"
				+ "  alternativa_correta INT NOT NULL," + "  PRIMARY KEY (id),"
				+ "  INDEX FK_RESPOSTA_ID_ALUNO_idx (id_aluno ASC),"
				+ "  INDEX FK_RESPOSTA_ID_EXERCICIO_idx (id_exercicio ASC),"
				+ "  INDEX FK_RESPOSTA_ID_QUESTAO_idx (id_questao ASC),"
				+ "  CONSTRAINT FK_RESPOSTA_ID_ALUNO"
				+ "    FOREIGN KEY (id_aluno)" + "    REFERENCES "
				+ databaseName + "." + AlunoMySqlDAO.TABLE_NAME + " (id)"
				+ "    ON DELETE NO ACTION" + "    ON UPDATE NO ACTION,"
				+ "  CONSTRAINT FK_RESPOSTA_ID_EXERCICIO"
				+ "    FOREIGN KEY (id_exercicio)" + "    REFERENCES "
				+ databaseName + "." + ExercicioMySqlDAO.TABLE_NAME + " (id)"
				+ "    ON DELETE NO ACTION" + "    ON UPDATE NO ACTION,"
				+ "  CONSTRAINT FK_RESPOSTA_ID_QUESTAO"
				+ "    FOREIGN KEY (id_questao)" + "    REFERENCES "
				+ databaseName + "."
				+ QuestaoMySqlDAO.QUESTAO_MULTIPLA_ESCOLHA_TABLE_NAME
				+ " (id_questao)" + "    ON DELETE NO ACTION"
				+ "    ON UPDATE NO ACTION);";

		Connection connection;
		try {
			connection = this.connector.createConnection();
			PreparedStatement ps = connection
					.prepareStatement(createDatabaseCommand);
			ps.execute();
			this.connector.setDatabaseName(databaseName);

			connection = this.connector.createConnection();
			ps = connection.prepareStatement(sqlAlunoTable);
			ps.execute();
			ps = connection.prepareStatement(sqlExercicioTable);
			ps.execute();
			ps = connection.prepareStatement(sqlPalavraChaveTable);
			ps.execute();
			ps = connection.prepareStatement(sqlQuestaoTable);
			ps.execute();
			ps = connection.prepareStatement(sqlQuestaoMultiplaEscolhaTable);
			ps.execute();
			ps = connection.prepareStatement(sqlAlternativaTable);
			ps.execute();
			ps = connection.prepareStatement(sqlRespostaTable);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dropDatabaseSchema() {
		String sql = "drop schema " + connector.getDatabaseName();

		Connection connection;
		try {
			connection = this.connector.createConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
