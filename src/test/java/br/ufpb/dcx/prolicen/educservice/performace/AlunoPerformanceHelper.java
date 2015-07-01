package br.ufpb.dcx.prolicen.educservice.performace;

import java.util.HashMap;
import java.util.Map;

import br.dcx.ufpb.prolicen.educservice.EducServiceTest;
import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacade;

public class AlunoPerformanceHelper {

	public static void cadastrarAlunoEExportarTempoGasto(
			EducServiceFacade facade, int index, String idTrace) {

		Map<String, String> trace = new HashMap<String, String>();
		trace.put("id", idTrace);

		long tempoInicial = System.currentTimeMillis();
		criarAluno(facade, index);
		long tempoFinal = System.currentTimeMillis() - tempoInicial;

		trace.put("tempoGastoEmMilisegundos", String.valueOf(tempoFinal));
		EducServiceTest.gerarTrace(trace);
	}

	public static void criarAluno(EducServiceFacade facade, int index) {
		facade.criarAluno(("aluno" + index), ("login" + index),
				("senha" + index));
	}

	public static void pesquisarAlunoEExportarTempoGasto(
			EducServiceFacade facade, String id, String idTrace) {
		Map<String, String> trace = new HashMap<String, String>();
		trace.put("id", idTrace);

		long tempoInicial = System.currentTimeMillis();
		facade.pesquisarAluno(id);
		long tempoFinal = System.currentTimeMillis() - tempoInicial;

		trace.put("tempoGastoEmMilisegundos", String.valueOf(tempoFinal));
		EducServiceTest.gerarTrace(trace);
	}
}