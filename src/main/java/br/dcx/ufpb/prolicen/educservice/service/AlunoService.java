package br.dcx.ufpb.prolicen.educservice.service;

import br.dcx.ufpb.prolicen.educservice.model.Aluno;
import br.ufpb.dcx.prolicen.educservice.dao.AlunoDAO;
import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;

public class AlunoService {

	private AlunoDAO alunoDAO;

	public AlunoService(EducServiceDaoFactory daoFactory) {
		this.alunoDAO = daoFactory.createAlunoDao();
	}

	public Aluno criarAluno(String nome, String login, String senha) {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setLogin(login);
		aluno.setSenha(senha);

		return alunoDAO.criarAluno(aluno);
	}

	public Aluno pesquisarAlunoPorId(String id) {
		Long idLong = Long.parseLong(id);
		return alunoDAO.pesquisarAlunoPorId(idLong);
	}

}
