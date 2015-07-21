package org.blended.data.repinterface;

import org.blended.data.data.DataModel;

import pt.ist.socialsoftware.blendedworkflow.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;

public class DataInterface {
    private static DataInterface instance = null;

    public static DataInterface getInstance() {
        if (instance == null) {
            instance = new DataInterface();
        }
        return instance;
    }

    private AtomicDataInterface adi = null;

    private DataInterface() {
        adi = AtomicDataInterface.getInstance();
    }

    public BWNotification createSpecification(String specId, String name) {
        BWNotification notification = new BWNotification();
        try {
            adi.createSpecification(specId, name);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

    public BWNotification createEntity(String specId, String entityName,
            Boolean exists) {
        BWNotification notification = new BWNotification();

        try {
            adi.createEntity(specId, entityName, exists);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

    public BWNotification createAttribute(String specId, String entityName,
            String attributeName, String attributeType) {
        BWNotification notification = new BWNotification();

        try {
            adi.createAttribute(specId, entityName, attributeName,
                    attributeType);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }
        return notification;
    }

    public BWNotification createRelation(String specId, String entityOneName,
            String roleNameOne, String cardinalityOne, String entityTwoName,
            String roleNameTwo, String cardinalityTwo) {
        BWNotification notification = new BWNotification();

        try {
            adi.createRelation(specId, entityOneName, roleNameOne,
                    cardinalityOne, entityTwoName, roleNameTwo, cardinalityTwo);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;

    }

    public BWNotification loadDataModel(String specId, DataModel eDataModel) {
        BWNotification notification = new BWNotification();

        try {
            adi.loadDataModel(specId, eDataModel);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

}
