package pt.ist.socialsoftware.blendedworkflow.service.design;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;

public class DesignInterface {
    public static DesignInterface instance = null;

    public static DesignInterface getInstance() {
        if (instance == null) {
            instance = new DesignInterface();
        }
        return instance;
    }

    private DesignInterface() {
        // Bootstrap.init();
    }

    public void createSpecification(String name) {
        new CreateSpecificationService(name).execute();
    }

    public void createEntity(String specName, String entityName) {
        new CreateEntityService(specName, entityName).execute();
    }

    public void createAttribute(String specName, String entityName,
            String attributeName, AttributeType type) {
        new CreateAttributeService(specName, entityName, attributeName, type)
                .execute();
    }

}
