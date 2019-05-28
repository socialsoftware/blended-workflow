package pt.ist.socialsoftware.blendedworkflow.core.domain;

public class Position extends Position_Base {
    public Position(String x, String y) {
        setX(x);
        setY(y);
    }

    public void delete() {
        setView(null);

        deleteDomainObject();
    }
}
