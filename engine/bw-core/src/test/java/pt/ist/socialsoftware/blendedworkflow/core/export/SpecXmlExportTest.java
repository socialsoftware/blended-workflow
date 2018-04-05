package pt.ist.socialsoftware.blendedworkflow.core.export;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.controller.ExportController;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlExport;

public class SpecXmlExportTest extends TeardownRollbackTest {
	private static Logger logger = LoggerFactory.getLogger(ExportController.class);

	@Override
	public void populate4Test() throws BWException {
	}

	@Test
	public void export() {
		SpecXmlExport exporter = new SpecXmlExport();
		for (Specification spec : BlendedWorkflow.getInstance().getSpecificationSet()) {
			logger.debug(exporter.export(spec));
		}
	}

}
