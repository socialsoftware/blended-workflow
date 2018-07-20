package pt.ist.socialsoftware.blendedworkflow.resources;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractDocExampleTest extends AbstractMvcTest {
    @BeforeClass
    @Atomic(mode = Atomic.TxMode.WRITE)
    public static void importSpec() throws BWException {
        BlendedWorkflow.getInstance().getSpecificationSet().stream()
                .filter(s -> s.getSpecId().equals("DoctorAppointment"))
                .forEach(s -> {
                    if (s.getResourceModel() != null) { s.getResourceModel().delete(); }
                    s.delete();
                });

        ResourceXmlImport importer = new ResourceXmlImport();

        String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

        File directory = new File(testModelsDirectory);
        String filename = "doc-example.xml";
        File file = new File(directory, filename);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RMException(RMErrorType.FILE_ERROR);
        }

        importer.importSpecification(inputStream);

    }

    @AfterClass
    @Atomic(mode = Atomic.TxMode.WRITE)
    public static void deleteSpec() {
        BlendedWorkflow.getInstance().getSpecificationSet().stream()
                .filter(s -> s.getSpecId().equals("DoctorAppointment"))
                .forEach(s -> {
                    if (s.getResourceModel() != null) { s.getResourceModel().delete(); }
                    s.delete();
                });
    }
}
