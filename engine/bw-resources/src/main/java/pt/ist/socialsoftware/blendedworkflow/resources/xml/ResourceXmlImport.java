package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlImport;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;

import java.util.Set;

public class ResourceXmlImport extends SpecXmlImport {
    private pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface workflowDesigner;
    private DesignInterface resourceDesigner;

    @Override
    public void importModules(Document doc) {
        XPathFactory xpfac = XPathFactory.instance();
        XPathExpression<Element> xp = xpfac.compile("//specification", Filters.element());
        for (Element specification : xp.evaluate(doc)) {
            String name = specification.getAttributeValue("name");
            String specId = specification.getAttributeValue("specId");

            Specification spec = new Specification(specId, name);

            importResourceModel(doc, spec.getResourceModel());
            importResourceRules(doc, spec);
        }
    }
}
