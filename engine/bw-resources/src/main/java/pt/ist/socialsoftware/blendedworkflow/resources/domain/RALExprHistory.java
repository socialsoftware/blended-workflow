package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

public abstract class RALExprHistory extends RALExprHistory_Base {
    public enum Quantifier {
        LEAST("LEAST"),
        MOST("MOST");

        private final String code;

        private Quantifier(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }

        public static Quantifier fromString(String code) throws IllegalArgumentException {
            switch (code) {
                case "LEAST":
                    return Quantifier.LEAST;
                case "MOST":
                    return Quantifier.MOST;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void delete() {
        setQuantifier(null);
        setDataField(null);
        super.delete();
    }

    @Override
    public void isMergable(RALExpression expression) {
        throw new RMException(RMErrorType.INVALID_MERGE, "The RALExpression is not mergable.");
    }
}
