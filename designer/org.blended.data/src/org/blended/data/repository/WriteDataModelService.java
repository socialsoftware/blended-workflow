package org.blended.data.repository;

import java.util.Set;

import org.blended.common.common.Association;
import org.blended.common.common.Attribute;
import org.blended.common.common.AttributeGroup;
import org.blended.common.common.Constraint;
import org.blended.common.common.Entity;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.dto.AttributeDTO;
import org.blended.common.repository.resttemplate.dto.AttributeGroupDTO;
import org.blended.common.repository.resttemplate.dto.DependenceDTO;
import org.blended.common.repository.resttemplate.dto.EntityDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO;
import org.blended.common.repository.resttemplate.dto.RelationDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.common.repository.resttemplate.dto.SpecDTO;
import org.blended.data.data.DataModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteDataModelService {
	final static String SERVER_ADDRESS = "http://localhost:8080/";

	private static Logger log = LoggerFactory.getLogger(WriteDataModelService.class);

	private static WriteDataModelService instance = null;

	public static WriteDataModelService getInstance() {
		if (instance == null) {
			instance = new WriteDataModelService();
		}
		return instance;
	}

	// to be invoked by tests only
	public void deleteSpecification(String specId) {
		log.debug("deleteSpecification {}", specId);
		ci.deleteSpecification(specId);
	}

	private CommonInterface ci = null;

	private WriteDataModelService() {
		ci = CommonInterface.getInstance();
	}

	public BWNotification write(String specId, DataModel eDataModel) {
		log.debug("loadDataModel: {}", specId);

		BWNotification notification = new BWNotification();

		try {
			ci.getSpecBySpecId(specId);
			ci.cleanDataModel(specId);
		} catch (RepositoryException re) {
			log.debug("getSpec: {}", re.getMessage());
			try {
				ci.createSpec(new SpecDTO(specId, eDataModel.getSpecification().getName()));
			} catch (RepositoryException ree) {
				notification.addError(ree.getError());
				log.debug("createSpec: {}", re.getMessage());
				return notification;
			}
		}

		for (Entity eEnt : eDataModel.getEntities()) {
			String entityExtId = null;
			try {
				EntityDTO entityDTO = ci.createEntity(new EntityDTO(specId, eEnt.getName(), eEnt.isExists()));
				entityExtId = entityDTO.getExtId();
				log.debug("createdEntity: {}, {}, {}", entityDTO.getExtId(), entityDTO.getName(),
						entityDTO.getExists());
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				log.debug("createEntity: {}", re.getMessage());
				continue;
			}
			// create entity dependences
			for (String eDep : eEnt.getDependsOn()) {
				try {
					ci.createDependence(new DependenceDTO(specId, eEnt.getName(), eDep));
				} catch (RepositoryException re) {
					notification.addError(re.getError());
					log.debug("Error: {}", re.getMessage());
				}
			}

			for (EObject eObj : eEnt.getAttributes()) {
				if (eObj instanceof Attribute) {
					AttributeDTO attributeDTO;
					Attribute eAtt = (Attribute) eObj;
					try {
						attributeDTO = ci.createAttribute(new AttributeDTO(specId, entityExtId, null, eAtt.getName(),
								eAtt.getType(), eAtt.isMandatory()));
					} catch (RepositoryException re) {
						notification.addError(re.getError());
						log.debug("Error: {}", re.getMessage());
						continue;
					}

					// create attribute dependences
					for (String eDep : eAtt.getDependsOn()) {
						try {
							ci.createDependence(new DependenceDTO(specId,
									attributeDTO.getEntityName() + "." + attributeDTO.getName(), eDep));
						} catch (RepositoryException re) {
							notification.addError(re.getError());
							log.debug("Error: {}", re.getMessage());
						}
					}
				} else if (eObj instanceof AttributeGroup) {
					AttributeGroupDTO groupVO;
					AttributeGroup eAttGroup = (AttributeGroup) eObj;
					try {
						groupVO = ci.createAttributeGroup(new AttributeGroupDTO(specId, entityExtId,
								eAttGroup.getName(), eAttGroup.isMandatory()));
					} catch (RepositoryException re) {
						notification.addError(re.getError());
						log.debug("Error: {}", re.getMessage());
						continue;
					}
					// create group attribute dependences
					for (String eDep : eAttGroup.getDependsOn()) {
						try {
							ci.createDependence(
									new DependenceDTO(specId, groupVO.getEntityName() + "." + groupVO.getName(), eDep));
						} catch (RepositoryException re) {
							notification.addError(re.getError());
							log.debug("Error: {}", re.getMessage());
						}
					}

					for (Attribute eAtt : eAttGroup.getAttributes()) {
						// create groupAttribute's attributes
						try {
							ci.createAttribute(new AttributeDTO(specId, entityExtId, groupVO.getExtId(), eAtt.getName(),
									eAtt.getType(), eAtt.isMandatory()));
						} catch (RepositoryException re) {
							notification.addError(re.getError());
							log.debug("Error: {}", re.getMessage());
						}
					}
				}
			}
		}

		for (Association assoc : eDataModel.getAssociations()) {
			try {
				ci.createRelation(new RelationDTO(specId, assoc.getName(), assoc.getEntity1().getName(),
						assoc.getName1(), assoc.getCardinality1(), assoc.getEntity2().getName(), assoc.getName2(),
						assoc.getCardinality2()));
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				log.debug("Error: {}", re.getMessage());
			}
		}

		Set<DependenceDTO> deps = ci.getDependencies(specId);
		for (DependenceDTO dep : deps) {
			log.debug("dependence extId:{}, path:{}, product:{}", dep.getExtId(), dep.getPath(), dep.getProduct());
			try {
				ci.checkDependence(specId, dep.getExtId());
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				log.debug("Error: {}", re.getMessage());

				ci.deleteDependence(specId, dep.getExtId());
			}
		}

		for (Constraint constraint : eDataModel.getConstraint()) {
			ExpressionDTO expression = ExpressionDTO.buildExpressionDTO(specId, constraint.getConstraint());
			try {
				ci.createRule(new RuleDTO(specId, constraint.getName(), expression));
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				log.debug("Error: {}", re.getMessage());
			}
		}

		ci.printSpecificationModels(specId);

		return notification;
	}

}
