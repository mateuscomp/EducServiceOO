package br.ufpb.dcx.prolicen.educservice.dao;

import java.util.List;

import br.dcx.ufpb.prolicen.educservice.model.Exercicio;

public interface ExercicioDAO {

	Exercicio criarExercicio(List<String> palavrasChave);

	Exercicio pesquisarExercicioPorId(String idExercicio);
}