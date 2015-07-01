package br.ufpb.dcx.prolicen.educservice.dao;

public interface EducServiceDaoFactory {

	public AlunoDAO createAlunoDao();

	public ExercicioDAO createExercicioDao();

	public QuestaoDAO createQuestaoDAO();

	public RespostaDAO createRespostaDao();

	public void createEducServiceSchema();

	public void dropDatabaseSchema();

}
