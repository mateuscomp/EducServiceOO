package br.dcx.ufpb.prolicen.educservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacade;
import br.dcx.ufpb.prolicen.educservice.model.EducServiceFacadeImpl;
import br.ufpb.dcx.prolicen.educservice.dao.EducServiceDaoFactory;
import br.ufpb.dcx.prolicen.educservice.dao.mysql.MySqlDaoFactory;

public class EducServiceTest {

	private EducServiceDaoFactory daoFactory;
	protected EducServiceFacade facade;

	@Before
	public void init() {
		this.daoFactory = new MySqlDaoFactory();
		this.daoFactory.createEducServiceSchema();

		this.facade = new EducServiceFacadeImpl(daoFactory);
	}

	@After
	public void finish() {
		daoFactory.dropDatabaseSchema();
	}

	public static void gerarTrace(Map<String, String> propertyValueMap) {
		String json = "{";

		boolean ehAPrimeiraProperiedade = true;
		for (String property : propertyValueMap.keySet()) {
			if (!ehAPrimeiraProperiedade) {
				json += ",";
			}

			json += "\"" + property + "\" : \""
					+ propertyValueMap.get(property) + "\"";
			ehAPrimeiraProperiedade = false;
		}
		json += "}";

		outputTrace(json);
	}

	private static void outputTrace(String texto) {
		// String userHome = System.getProperty("user.home");
		// String separator = System.getProperty("file.separator");

		String nameOfFile = "educServiceOO_PerformaceEvaluationEduServiceTest.log";

		// File file = new File(userHome + separator + nameOfFile);
		File file = new File(nameOfFile);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true), "UTF-8"));
			bw.append(texto + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}