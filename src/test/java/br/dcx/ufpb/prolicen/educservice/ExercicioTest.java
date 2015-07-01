package br.dcx.ufpb.prolicen.educservice;

import junit.framework.Assert;

import org.junit.Test;

import br.dcx.ufpb.prolicen.educservice.helper.ExercicioHelper;
import br.dcx.ufpb.prolicen.educservice.model.Exercicio;

public class ExercicioTest extends EducServiceTest {

	@Test
	public void verificarCadastroDeUmExercicioSemPalavrasChave() {
		Exercicio e = this.facade.criarExercicio();
		Assert.assertNotNull(e.getId());
	}

	@Test
	public void verificarCadastroDeUmExercicioComPalavrasChave() {
		ExercicioHelper.verificarCriacaoDeUmExercicioValido(facade, "extends",
				"herança", "java", "poo", "lcc");
	}
}
