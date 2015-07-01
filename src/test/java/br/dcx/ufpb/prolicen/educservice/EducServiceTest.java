package br.dcx.ufpb.prolicen.educservice;

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
	public void init(){
		this.daoFactory = new MySqlDaoFactory();
		this.daoFactory.createEducServiceSchema();
		
		this.facade = new EducServiceFacadeImpl(daoFactory);
	}
	
	@After
	public void finish() {
		daoFactory.dropDatabaseSchema();
	}
}
