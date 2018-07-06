package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprHasCapability extends RALExprHasCapability_Base implements RALExprDeniable {

    public RALExprHasCapability(ResourceModel resourceModel, Capability capability) {
        setResourceModel(resourceModel);
        setCapability(capability);
    }

    @Override
    public void delete() {
        setCapability(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return getPersonSet().stream()
                .filter(person -> person.getCapabilitySet().contains(getCapability()))
                .collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addCapabilities(new HashSet<>(Arrays.asList(getCapability().getDTO())));
    }

    @Override
    public void isMergable(RALExpression expression) {
        
    }


}
