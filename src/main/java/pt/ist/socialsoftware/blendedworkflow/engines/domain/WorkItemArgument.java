package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class WorkItemArgument extends WorkItemArgument_Base {
    
    public WorkItemArgument(AttributeInstance attributeInstance, String value) {
    	setAttributeInstance(attributeInstance);
    	setValue(value);
    }
    
}
