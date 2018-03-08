package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.ResourceRuleDTO;

import java.util.List;

public abstract class RALExpression extends RALExpression_Base {
    private static Logger logger = LoggerFactory.getLogger(RALExpression.class);

    public enum TaskDutyType {
        RESPONSIBLE_FOR("RESPONSIBLE FOR"),
        INFORMED_ABOUT("INFORMED ABOUT");

        private final String code;

        private TaskDutyType(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }

        public static TaskDutyType fromResourceRuleString(String code) throws IllegalArgumentException {
            switch (code) {
                case "has responsible":
                    return RESPONSIBLE_FOR;
                case "informs":
                    return INFORMED_ABOUT;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public static TaskDutyType fromString(String code) throws IllegalArgumentException {
            switch (code) {
                case "RESPONSIBLE FOR":
                    return RESPONSIBLE_FOR;
                case "INFORMED ABOUT":
                    return INFORMED_ABOUT;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public RALExpression() {

    }

    public void delete() {
        setRalExprNot(null);
        setRalExprAndLeft(null);
        setRalExprAndRight(null);
        setProductResponsibleFor(null);
        setProductInforms(null);
        setResourceModel(null);
        deleteDomainObject();
    }

    public abstract List<Person> getEligibleResources();
}
