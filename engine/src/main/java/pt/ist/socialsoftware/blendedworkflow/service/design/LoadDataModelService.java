package pt.ist.socialsoftware.blendedworkflow.service.design;

import org.blended.data.data.DataModel;
import org.eclipse.emf.ecore.EObject;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWService;

public class LoadDataModelService extends BWService {
    private final DataModel dataModel;

    public LoadDataModelService(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    protected void dispatch() throws BWException {
        BlendedWorkflow bw = getBlendedWorkflow().getInstance();

        Specification spec = bw
                .createSpecification(dataModel.getSpecification().getName());

        for (org.blended.data.data.Entity ent : dataModel.getEntities()) {
            Entity newEnt = spec.getDataModel().createEntity(ent.getName());
            for (EObject att : ent.getAttributes()) {
                if (att instanceof org.blended.data.data.Attribute) {
                    org.blended.data.data.Attribute eAtt = (org.blended.data.data.Attribute) att;
                    Attribute newAtt = newEnt.createAttribute(eAtt.getName(),
                            CreateAttributeService
                                    .parseAttributeType(eAtt.getType()));
                }
            }
        }

    }

}
