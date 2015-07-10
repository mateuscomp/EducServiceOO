package br.ufpb.dcx.prolicen.educservice.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.dcx.ufpb.prolicen.educservice.model.Exercicio;
import br.ufpb.dcx.prolicen.educservice.dao.ExercicioDAO;

public class ExercicioMySqlDAO extends AbstractMySqlDAO implements ExercicioDAO {

	public static final String TABLE_NAME = "exercicio";
	public static final String PALAVRAS_CHAVE_TABLE = "exercicio_palavrachave";

	public ExercicioMySqlDAO(MySqlConnector connector) {
		super(connector);
	}

	public Exercicio criarExercicio(List<String> palavrasChave) {
		String sqlInsert = "INSERT INTO " + getDatabaseName() + "."
				+ TABLE_NAME + " VALUES ();";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		Exercicio exercicioPesquisado = this.findByMaxId();

		if (palavrasChave != null && !palavrasChave.isEmpty()) {
			for (String palavraChave : palavrasChave) {
				this.insertPalavrasChave(exercicioPesquisado.getId(),
						palavraChave);
			}
			exercicioPesquisado.setPalavrasChave(palavrasChave);
		}

		return exercicioPesquisado;
	}

	private void insertPalavrasChave(String id, String palavraChave) {
		String sqlInsert = "INSERT INTO " + getDatabaseName() + "."
				+ PALAVRAS_CHAVE_TABLE
				+ " (`id_exercicio`, `palavra_chave`) VALUES (?, ?);";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setLong(1, Long.parseLong(id));
			ps.setString(2, palavraChave);
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Exercicio findByMaxId() {
		String sql = "SELECT * FROM " + getDatabaseName() + "." + TABLE_NAME
				+ " a WHERE a.id = (SELECT max(e.id) FROM " + getDatabaseName()
				+ "." + TABLE_NAME + " e);";

		Exercicio exercicio = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				String idExercicio = String.valueOf(resultSet.getLong("id"));
				exercicio = new Exercicio(idExercicio);
			}
			connection.close();
			if (exercicio != null) {
				exercicio
						.setPalavrasChave(findPalavrasChaveByExercicioId(String
								.valueOf(exercicio.getId())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return exercicio;
	}

	private List<String> findPalavrasChaveByExercicioId(String idExercicio) {
		String sql = "SELECT * FROM " + getDatabaseName() + "."
				+ PALAVRAS_CHAVE_TABLE + " p WHERE p.palavra_chave = ?";

		List<String> palavrasChave = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(idExercicio));
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				if (palavrasChave == null) {
					palavrasChave = new LinkedList<String>();
				}
				palavrasChave.add(resultSet.getString("palavra_chave"));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return palavrasChave;
	}

	public Exercicio pesquisarExercicioPorId(String idExercicio) {
		String sql = "SELECT * FROM " + getDatabaseName() + "." + TABLE_NAME
				+ " a WHERE a.id = ?";

		Exercicio exercicio = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(idExercicio));

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				exercicio = new Exercicio(idExercicio);
				exercicio.setId(String.valueOf(resultSet.getLong("id")));
				exercicio
						.setPalavrasChave(findPalavrasChaveByExercicioId(String
								.valueOf(exercicio.getId())));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return exercicio;
	}
}
