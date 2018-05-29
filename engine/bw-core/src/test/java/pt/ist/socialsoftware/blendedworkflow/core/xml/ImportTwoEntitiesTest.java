package pt.ist.socialsoftware.blendedworkflow.core.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.core.WriteOnReadError;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;

public class ImportTwoEntitiesTest {
	private static Logger logger = LoggerFactory.getLogger(ImportTwoEntitiesTest.class);

	SpecXmlExport exporter = new SpecXmlExport();

	@Test
	public void importTwoEntitiesTestFromFile()
			throws FileNotFoundException, WriteOnReadError, NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		FenixFramework.getTransactionManager().begin(false);

		BlendedWorkflow.getInstance().getSpecificationSet().stream()
				.filter(s -> s.getSpecId().equals("TwoEntitiesTest")).forEach(s -> s.delete());

		SpecXmlImport importer = new SpecXmlImport();

		String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

		File directory = new File(testModelsDirectory);
		String filename = "TwoEntitiesTest.xml";
		File file = new File(directory, filename);
		FileInputStream inputStream = new FileInputStream(file);

		importer.importSpecification(inputStream);

		BlendedWorkflow.getInstance().getSpecificationSet().stream()
				.filter(s -> s.getSpecId().equals("TwoEntitiesTest"))
				.forEach(s -> logger.debug(this.exporter.export(s)));

		FenixFramework.getTransactionManager().commit();

		FenixFramework.getTransactionManager().begin(false);

		BlendedWorkflow.getInstance().getSpecificationSet().stream()
				.filter(s -> s.getSpecId().equals("TwoEntitiesTest"))
				.forEach(s -> logger.debug(this.exporter.export(s)));

		FenixFramework.getTransactionManager().commit();
	}

}
