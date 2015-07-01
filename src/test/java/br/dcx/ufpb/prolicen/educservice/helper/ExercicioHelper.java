package br.dcx.ufpb.prolicen.educservice.helper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacade;
import br.dcx.ufpb.prolicen.educservice.model.Exercicio;

public class ExercicioHelper {

	public static Exercicio verificarCriacaoDeUmExercicioValido(
			EducServiceFacade facade, String... palavrasChave) {

		List<String> palavrasChaveList = new ArrayList<String>();
		for (int i = 0; i < palavrasChave.length; i++) {
			palavrasChaveList.add(palavrasChave[i]);
		}

		Exercicio exercicio = facade.criarExercicio(palavrasChaveList);

		Assert.assertNotNull(exercicio.getId());
		for (int i = 0; i < palavrasChave.length; i++) {
			Assert.assertEquals(palavrasChaveList.get(i), exercicio
					.getPalavrasChave().get(i));
		}
		return exercicio;
	}
}
