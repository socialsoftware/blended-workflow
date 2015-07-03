package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;

public abstract class WorkItem extends WorkItem_Base {

    /**********************************
     * Events
     **********************************/
    public abstract void notifyCheckedIn();

    public abstract void notifyDataChanged();

    /**********************************
     * WorkItemArguments
     **********************************/
    public void createInputWorkItemArguments() {
        for (AttributeInstance attributeInstance : getInputAttributeInstancesSet()) {
            WorkItemArgument workItemArgument = new WorkItemArgument(
                    attributeInstance, attributeInstance.getValue(),
                    attributeInstance.getState());
            addInputWorkItemArguments(workItemArgument);
        }
    }

    public void updateInputWorkItemArguments() {
        for (WorkItemArgument workItemArgument : getInputWorkItemArgumentsSet()) {
            AttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            workItemArgument.setValue(attributeInstance.getValue());
            workItemArgument.setState(attributeInstance.getState());
        }
    }

    public void createOutputWorkItemArguments() {
        for (AttributeInstance attributeInstance : getOutputAttributeInstancesSet()) {
            WorkItemArgument workItemArgument = new WorkItemArgument(
                    attributeInstance, attributeInstance.getValue(),
                    attributeInstance.getState());
            addOutputWorkItemArguments(workItemArgument);
        }
    }

    public void updateOutputWorkItemArguments() {
        for (WorkItemArgument workItemArgument : getOutputWorkItemArgumentsSet()) {
            AttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            workItemArgument.setValue(attributeInstance.getValue());
            workItemArgument.setState(attributeInstance.getState());
        }
    }

    /**********************************
     * Data
     **********************************/

    /**
     * Commits the new data to the dataModel.
     */
    public void setAttributeValues() {
        Boolean modified = false;
        Boolean isPreTask = false;

        // Add PreConstrain data
        for (WorkItemArgument workItemArgument : getInputWorkItemArgumentsSet()) {
            AttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            if (!attributeInstance.getState().equals(DataState.DEFINED)) {
                workItemArgument.getAttributeInstance()
                        .setValue(workItemArgument.getValue());
                isPreTask = true;
                modified = true;
            } else if (!attributeInstance.getValue()
                    .equals(workItemArgument.getValue())) {
                workItemArgument.getAttributeInstance()
                        .setValue(workItemArgument.getValue());
                isPreTask = true;
                modified = true;
            }
        }

        // Add ConstrainViolation data
        for (WorkItemArgument workItemArgument : getOutputWorkItemArgumentsSet()) {
            AttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            if (!attributeInstance.getState().equals(DataState.DEFINED)) {
                workItemArgument.getAttributeInstance()
                        .setValue(workItemArgument.getValue());
                modified = true;
            } else if (!attributeInstance.getValue()
                    .equals(workItemArgument.getValue())) {
                workItemArgument.getAttributeInstance()
                        .setValue(workItemArgument.getValue());
                modified = true;
            }
        }

        if (modified) {
            notifyWorkItemDataChanged(isPreTask);
        }
    }

    /**
     * Commits the new data as SKIPPED to the dataModel.
     */
    public void setAttributeSkipped() {
        Boolean isPreTask = false;
        Boolean modified = false;

        // Add PreConstrain data
        for (WorkItemArgument workItemArgument : getInputWorkItemArgumentsSet()) {
            AttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            if (attributeInstance.getState() == DataState.UNDEFINED) {
                attributeInstance.setState(DataState.SKIPPED);
                isPreTask = true;
                modified = true;
            }
        }

        // Add ConstrainViolation data
        for (WorkItemArgument workItemArgument : getOutputWorkItemArgumentsSet()) {
            AttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            if (attributeInstance.getState() == DataState.UNDEFINED) {
                attributeInstance.setState(DataState.SKIPPED);
                attributeInstance.setValue("$SKIPPED$");
                modified = true;
            }
        }

        if (modified) {
            notifyWorkItemDataChanged(isPreTask);
        }
    }

    /**
     * Get all affected WorkItems an notifies then to reevaluate their
     * conditions.
     */
    public void notifyWorkItemDataChanged(Boolean isPreTask) {
        Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();

        if (isPreTask) {
            for (AttributeInstance attributeInstance : getInputAttributeInstancesSet()) {
                for (WorkItem workItem : attributeInstance
                        .getPreConstraintWorkItemsSet()) {
                    notifyWorkItems.add(workItem);
                }
                for (WorkItem workItem : attributeInstance
                        .getContraintViolationWorkItemsSet()) {
                    notifyWorkItems.add(workItem);
                }
            }
        }

        for (AttributeInstance attributeInstance : getOutputAttributeInstancesSet()) {
            for (WorkItem workItem : attributeInstance
                    .getPreConstraintWorkItemsSet()) {
                notifyWorkItems.add(workItem);
            }
            for (WorkItem workItem : attributeInstance
                    .getContraintViolationWorkItemsSet()) {
                notifyWorkItems.add(workItem);
            }
        }

        for (WorkItem workItem : notifyWorkItems) {
            workItem.updateInputWorkItemArguments();
            workItem.updateOutputWorkItemArguments();
            workItem.notifyDataChanged();
        }
    }

}