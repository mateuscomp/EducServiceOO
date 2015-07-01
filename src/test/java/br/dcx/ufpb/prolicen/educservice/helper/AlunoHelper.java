package br.dcx.ufpb.prolicen.educservice.helper;

import junit.framework.Assert;
import br.dcx.ufpb.prolicen.educservice.model.Aluno;
import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacade;

public class AlunoHelper {

	public static Aluno verificarCriacaoDeUmAlunoValido(
			EducServiceFacade facade, String nome, String login, String senha) {

		Aluno aluno = facade.criarAluno(nome, login, senha);

		Aluno alunoPesquisado = facade.pesquisarAluno(aluno.getId());
		Assert.assertNotNull(alunoPesquisado);
		Assert.assertNotNull(alunoPesquisado.getId());

		Assert.assertEquals(nome, alunoPesquisado.getNome());
		Assert.assertEquals(login, alunoPesquisado.getLogin());
		Assert.assertEquals(senha, alunoPesquisado.getSenha());
		
		return aluno;
	}

}
