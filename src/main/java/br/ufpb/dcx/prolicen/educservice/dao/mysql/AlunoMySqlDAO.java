package br.ufpb.dcx.prolicen.educservice.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.dcx.ufpb.prolicen.educservice.model.Aluno;
import br.ufpb.dcx.prolicen.educservice.dao.AlunoDAO;

public class AlunoMySqlDAO extends AbstractMySqlDAO implements AlunoDAO {

	public static final String TABLE_NAME = "aluno";

	public AlunoMySqlDAO(MySqlConnector connectionFactory) {
		super(connectionFactory);
	}

	public Aluno criarAluno(Aluno aluno) {
		String sqlInsert = "INSERT INTO " + getDatabaseName() + "."
				+ TABLE_NAME + " (nome, login, senha) VALUES (?, ?, ?);";

		try {
			Connection connection = this.createConnection();
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getLogin());
			ps.setString(3, aluno.getSenha());
			ps.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return this.findByMaxId();
	}

	private Aluno findByMaxId() {
		String sql = "SELECT * FROM " + getDatabaseName() + "." + TABLE_NAME
				+ " a WHERE a.id = (SELECT max(e.id) FROM " + getDatabaseName()
				+ "." + TABLE_NAME + " e);";

		Aluno aluno = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				aluno = new Aluno();
				aluno.setId(String.valueOf(resultSet.getLong("id")));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setLogin(resultSet.getString("login"));
				aluno.setSenha(resultSet.getString("senha"));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return aluno;
	}

	public Aluno pesquisarAlunoPorId(Long id) {
		String sql = "SELECT * FROM " + getDatabaseName() + "." + TABLE_NAME
				+ " a WHERE a.id = ?";

		Aluno aluno = null;
		try {
			Connection connection = this.createConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);

			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				aluno = new Aluno();
				aluno.setId(String.valueOf(resultSet.getLong("id")));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setLogin(resultSet.getString("login"));
				aluno.setSenha(resultSet.getString("senha"));
			}
			this.closeConexao();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return aluno;
	}
}
