package br.dcx.ufpb.prolicen.educservice.service;

import java.util.List;

import br.dcx.ufpb.prolicen.educservice.model.Exercicio;
import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;
import br.ufpb.dcx.prolicen.educservice.dao.ExercicioDAO;

public class ExercicioService {

	private ExercicioDAO exercicioDao;

	public ExercicioService(EducServiceDaoFactory daoFactory) {
		this.exercicioDao = daoFactory.createExercicioDao();
	}

	public Exercicio criarExercicio() {
		return exercicioDao.criarExercicio(null);
	}

	public Exercicio criarExercicio(List<String> palavrasChave) {
		return exercicioDao.criarExercicio(palavrasChave);
	}

}
