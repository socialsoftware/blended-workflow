package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlImport;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;

import java.util.Set;

public class ResourceXmlImport extends SpecXmlImport {
    private static Logger logger = LoggerFactory.getLogger(ResourceXmlImport.class);

    private DesignInterface resourceDesigner;

    public ResourceXmlImport() {
        resourceDesigner = DesignInterface.getInstance();
    }

    @Override
    public void importModules(Element specElement, Specification spec) {
        importResourceModel(specElement, spec);
        // importResourceRules(specElement, spec);
    }

    private void importResourceModel(Element specElement, Specification spec) {
        specElement.getChild("resource-model").getChild("capabilities").getChildren().stream().forEach(c -> logger.debug(c.toString()));
    }
}
