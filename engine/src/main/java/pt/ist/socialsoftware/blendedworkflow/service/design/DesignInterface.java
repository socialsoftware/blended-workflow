package pt.ist.socialsoftware.blendedworkflow.service.design;

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
        new CreateSpecification(name).execute();
    }

    public void creatEntity(String specName, String entityName) {
        new CreateEntity(specName, entityName).execute();
    }

}
