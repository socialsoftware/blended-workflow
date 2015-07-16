package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;

public abstract class BWProduct extends BWProduct_Base {

    public BWDependence createDependence(String value) {
        return new BWDependence(this.getEntity().getDataModel(), this, value);
    }

    public abstract BWEntity getEntity();

    public abstract BWProduct getNext(List<String> path, String value);

    public void delete() {
        getDependenceSet().stream().forEach(dep -> dep.delete());

        deleteDomainObject();
    }

}
