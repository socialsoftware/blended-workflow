package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprCommonalityDTO;

public abstract class RALExprCommonality extends RALExprCommonality_Base {
    public enum Amount {
        SOME("SOME"),
        ALL("ALL");

        private final String code;

        private Amount(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }

        public static Amount fromString(String code) throws IllegalArgumentException {
            switch (code) {
                case "SOME":
                    return SOME;
                case "ALL":
                    return ALL;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
    
    public RALExprCommonality() {
        super();
    }

    @Override
    public void delete() {
        setPersonExpr(null);
        super.delete();
    }

    @Override
    public void isMergable(RALExpression expression) {
        getPersonExpr().isMergable(expression);
    }
}
