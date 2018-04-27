package pt.ist.socialsoftware.blendedworkflow.resources;

import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractSpecTest1Test extends AbstractMvcTest {
    @Override
    public void populate4Test() throws BWException {
        BlendedWorkflow.getInstance().getSpecificationSet().stream()
                .filter(s -> s.getSpecId().equals("spec-test-1"))
                .forEach(s -> {
                    if (s.getResourceModel() != null) { s.getResourceModel().delete(); }
                    s.delete();
                });

        ResourceXmlImport importer = new ResourceXmlImport();

        String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

        File directory = new File(testModelsDirectory);
        String filename = "spec-test-1.xml";
        File file = new File(directory, filename);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RMException(RMErrorType.FILE_ERROR);
        }

        importer.importSpecification(inputStream);
    }
}
