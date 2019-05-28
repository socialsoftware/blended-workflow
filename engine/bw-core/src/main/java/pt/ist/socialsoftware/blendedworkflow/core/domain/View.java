package pt.ist.socialsoftware.blendedworkflow.core.domain;

public class View extends View_Base {
    public void delete() {
        getPosition().delete();

        deleteDomainObject();
    }
}
