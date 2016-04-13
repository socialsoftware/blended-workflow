package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;

public abstract class OldWorkItem extends OldWorkItem_Base {

    /**********************************
     * Events
     **********************************/
    public abstract void notifyCheckedIn();

    public abstract void notifyDataChanged();

    /**********************************
     * WorkItemArguments
     **********************************/
    public void createInputWorkItemArguments() {
        for (OldAttributeInstance attributeInstance : getInputAttributeInstancesSet()) {
            OldWorkItemArgument workItemArgument = new OldWorkItemArgument(
                    attributeInstance, attributeInstance.getValue(),
                    attributeInstance.getState());
            addInputWorkItemArguments(workItemArgument);
        }
    }

    public void updateInputWorkItemArguments() {
        for (OldWorkItemArgument workItemArgument : getInputWorkItemArgumentsSet()) {
            OldAttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            workItemArgument.setValue(attributeInstance.getValue());
            workItemArgument.setState(attributeInstance.getState());
        }
    }

    public void createOutputWorkItemArguments() {
        for (OldAttributeInstance attributeInstance : getOutputAttributeInstancesSet()) {
            OldWorkItemArgument workItemArgument = new OldWorkItemArgument(
                    attributeInstance, attributeInstance.getValue(),
                    attributeInstance.getState());
            addOutputWorkItemArguments(workItemArgument);
        }
    }

    public void updateOutputWorkItemArguments() {
        for (OldWorkItemArgument workItemArgument : getOutputWorkItemArgumentsSet()) {
            OldAttributeInstance attributeInstance = workItemArgument
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
        for (OldWorkItemArgument workItemArgument : getInputWorkItemArgumentsSet()) {
            OldAttributeInstance attributeInstance = workItemArgument
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
        for (OldWorkItemArgument workItemArgument : getOutputWorkItemArgumentsSet()) {
            OldAttributeInstance attributeInstance = workItemArgument
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
        for (OldWorkItemArgument workItemArgument : getInputWorkItemArgumentsSet()) {
            OldAttributeInstance attributeInstance = workItemArgument
                    .getAttributeInstance();
            if (attributeInstance.getState() == DataState.UNDEFINED) {
                attributeInstance.setState(DataState.SKIPPED);
                isPreTask = true;
                modified = true;
            }
        }

        // Add ConstrainViolation data
        for (OldWorkItemArgument workItemArgument : getOutputWorkItemArgumentsSet()) {
            OldAttributeInstance attributeInstance = workItemArgument
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
        Set<OldWorkItem> notifyWorkItems = new HashSet<OldWorkItem>();

        if (isPreTask) {
            for (OldAttributeInstance attributeInstance : getInputAttributeInstancesSet()) {
                for (OldWorkItem workItem : attributeInstance
                        .getPreConstraintWorkItemsSet()) {
                    notifyWorkItems.add(workItem);
                }
                for (OldWorkItem workItem : attributeInstance
                        .getContraintViolationWorkItemsSet()) {
                    notifyWorkItems.add(workItem);
                }
            }
        }

        for (OldAttributeInstance attributeInstance : getOutputAttributeInstancesSet()) {
            for (OldWorkItem workItem : attributeInstance
                    .getPreConstraintWorkItemsSet()) {
                notifyWorkItems.add(workItem);
            }
            for (OldWorkItem workItem : attributeInstance
                    .getContraintViolationWorkItemsSet()) {
                notifyWorkItems.add(workItem);
            }
        }

        for (OldWorkItem workItem : notifyWorkItems) {
            workItem.updateInputWorkItemArguments();
            workItem.updateOutputWorkItemArguments();
            workItem.notifyDataChanged();
        }
    }

}