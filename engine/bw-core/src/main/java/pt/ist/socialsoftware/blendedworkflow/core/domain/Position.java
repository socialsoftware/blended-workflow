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

    public Position getNearPosition() {
        return new Position(Integer.toString(Integer.parseInt(getX()) + 20), Integer.toString(Integer.parseInt(getY()) + 20));
    }
}
