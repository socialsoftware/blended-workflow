package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        getActivityInformsSet().stream().forEach(p -> removeActivityInforms(p));
        getActivityResponsibleForSet().stream().forEach(p -> removeActivityResponsibleFor(p));
        getGoalInformsSet().stream().forEach(p -> removeGoalInforms(p));
        getGoalResponsibleForSet().stream().forEach(p -> removeGoalResponsibleFor(p));
        setRalExprNot(null);
        setRalExprAndLeft(null);
        setRalExprAndRight(null);
        setRalExprOrLeft(null);
        setRalExprOrRight(null);
        setProductResponsibleFor(null);
        setProductInforms(null);
        setResourceModel(null);
        deleteDomainObject();
    }

    public abstract List<Person> getEligibleResources(WorkflowInstance history);

    public boolean hasEligiblePerson(Person person, WorkflowInstance history) {
        return getEligibleResources(history).contains(person);
    }

    public abstract SetOfRequiredResources getSetOfRequiredResources();

    public boolean isConsistent() {
        SetOfRequiredResources set = getSetOfRequiredResources();

        // Check of negation falacy
        if (set.getPositions().stream().anyMatch(dto -> set.getNotPositions().contains(dto))){
            return false;
        }

        if (set.getPersons().stream().anyMatch(dto -> set.getNotPersons().contains(dto))){
            return false;
        }

        if (set.getCapabilities().stream().anyMatch(dto -> set.getNotCapabilities().contains(dto))){
            return false;
        }

        if (set.getRoles().stream().anyMatch(dto -> set.getNotRoles().contains(dto))){
            return false;
        }

        if (set.getUnits().stream().anyMatch(dto -> set.getNotUnits().contains(dto))){
            return false;
        }

        if (set.getDataField().stream().anyMatch(dto -> set.getNotDataField().contains(dto))){
            return false;
        }

        if (set.getTaskDuty().stream().anyMatch(dto -> set.getNotTaskDuty().contains(dto))){
            return false;
        }

        // check for incoherence with assigned fields
        if (set.getWorkItemProducts().stream()
                .anyMatch(dto -> set.getNotDataField().contains(dto) ||
                                 set.getNotTaskDuty().contains(dto)))
        {
            return false;
        }

        return true;
    }

    public abstract void isMergable(RALExpression expression);

    public Set<Person> getPersonSet() {
        return getResourceModel().getPersonSet();
    }
}
