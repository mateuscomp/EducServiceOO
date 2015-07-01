package br.ufpb.dcx.prolicen.educservice.dao;

import br.dcx.ufpb.prolicen.educservice.model.Aluno;

public interface AlunoDAO {

	public Aluno criarAluno(Aluno aluno);

	public Aluno pesquisarAlunoPorId(Long id);
}
