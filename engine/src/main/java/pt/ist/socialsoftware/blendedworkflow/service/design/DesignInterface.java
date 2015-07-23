package pt.ist.socialsoftware.blendedworkflow.service.design;

import pt.ist.socialsoftware.blendedworkflow.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecificationDTO;

public class DesignInterface {
    private static DesignInterface instance = null;

    public static DesignInterface getInstance() {
        if (instance == null) {
            instance = new DesignInterface();
        }
        return instance;
    }

    private AtomicDesignInterface adi = null;

    private DesignInterface() {
        adi = AtomicDesignInterface.getInstance();
    }

    public BWNotification createSpecification(SpecificationDTO specDTO) {
        BWNotification notification = new BWNotification();
        try {
            adi.createSpecification(specDTO);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

    public BWNotification createEntity(EntityDTO entDTO) {
        BWNotification notification = new BWNotification();

        try {
            adi.createEntity(entDTO);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

    public BWNotification createAttribute(AttributeDTO attDTO) {
        BWNotification notification = new BWNotification();

        try {
            adi.createAttribute(attDTO);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }
        return notification;
    }

    public BWNotification createRelation(RelationDTO relDTO) {
        BWNotification notification = new BWNotification();

        try {
            adi.createRelation(relDTO);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;

    }

}
