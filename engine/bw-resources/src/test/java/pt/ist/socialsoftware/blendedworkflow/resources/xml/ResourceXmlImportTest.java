package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlImportTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ResourceXmlImportTest extends TeardownRollbackTest {
    private static Logger logger = LoggerFactory.getLogger(SpecXmlImportTest.class);

    @Override
    public void populate4Test() throws BWException {

    }

    @Test
    public void importer() throws FileNotFoundException {
        ResourceXmlImport importer = new ResourceXmlImport();

        String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

        File directory = new File(testModelsDirectory);
        String filename = "spec-test-1.xml";
        File file = new File(directory, filename);
        FileInputStream inputStream = new FileInputStream(file);

        importer.importSpecification(inputStream);
    }


    @Test
    public void importFromFile() throws FileNotFoundException {
        BlendedWorkflow.getInstance().getSpecificationSet().stream().forEach(s -> s.delete());

        ResourceXmlImport importer = new ResourceXmlImport();

        String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

        File directory = new File(testModelsDirectory);
        String filename = "DoctorAppointmentTest.xml";
        File file = new File(directory, filename);
        FileInputStream inputStream = new FileInputStream(file);

        importer.importSpecification(inputStream);

        Specification specification = BlendedWorkflow.getInstance().getSpecificationSet().stream().findFirst().get();

        //logger.debug(this.exporter.export(specification));
    }

}