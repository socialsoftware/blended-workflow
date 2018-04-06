package pt.ist.socialsoftware.blendedworkflow.core.xml;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class SpecXmlImportTest extends TeardownRollbackTest {
	private static Logger logger = LoggerFactory.getLogger(SpecXmlImportTest.class);

	List<String> xmlSpecs;
	SpecXmlExport exporter;

	@Override
	public void populate4Test() throws BWException {
		this.exporter = new SpecXmlExport();
		this.xmlSpecs = BlendedWorkflow.getInstance().getSpecificationSet().stream()
				.sorted((s1, s2) -> s1.getSpecId().compareTo(s2.getSpecId())).map(spec -> this.exporter.export(spec))
				.collect(Collectors.toList());
	}

	@Test
	public void importFromString() {
		BlendedWorkflow.getInstance().getSpecificationSet().stream().forEach(s -> s.delete());

		SpecXmlImport importer = new SpecXmlImport();

		this.xmlSpecs.stream().forEach(xml -> logger.debug(xml));

		this.xmlSpecs.stream().forEach(xml -> importer.importSpecification(xml));

		List<String> results = BlendedWorkflow.getInstance().getSpecificationSet().stream()
				.sorted((s1, s2) -> s1.getSpecId().compareTo(s2.getSpecId())).map(spec -> this.exporter.export(spec))
				.collect(Collectors.toList());

		results.stream().forEach(xml -> logger.debug(xml));

		// assertEquals(this.xmlSpecs, results);
	}

}
