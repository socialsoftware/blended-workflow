package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlImport;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.*;

import java.util.List;
import java.util.stream.Collectors;

import static pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlExport.ACTIVITY_MODEL;
import static pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlExport.DATA_MODEL;
import static pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlExport.GOAL_MODEL;

public class ResourceXmlImport extends SpecXmlImport {
    public static final String BOOLEAN_TRUE = "true";
    private static Logger logger = LoggerFactory.getLogger(ResourceXmlImport.class);

    private DesignResourcesInterface resourceDesigner;

    public ResourceXmlImport() {
        resourceDesigner = DesignResourcesInterface.getInstance();
    }

    @Override
    public void importModules(Element specElement, Specification spec) {
        resourceDesigner.createResourceModel(spec.getSpecId());

        importResourceModel(specElement.getChild("resource-model"), spec);
        importResourceRules(specElement.getChild("resource-rules"), spec);
    }

    private void importResourceModel(Element specElement, Specification spec) {
        importCapabilities(specElement, spec);

        importUnits(specElement, spec);

        importRoles(specElement, spec);

        importPositions(specElement, spec);

        importPersons(specElement, spec);

        importIsPersons(specElement, spec);
    }

    private void importIsPersons(Element specElement, Specification spec) {
        specElement.getChild("entity-is-person").getChildren().stream().forEach(entIsPer -> {
            resourceDesigner.relationEntityIsPerson(new ResourceRelationDto(
                    spec.getSpecId(),
                    entIsPer.getName()
            ));
        });
    }

    private void importCapabilities(Element specElement, Specification spec) {
        specElement.getChild("capabilities").getChildren().stream().forEach(capability -> {
            resourceDesigner.createCapability(new CapabilityDto(
                    spec.getSpecId(),
                    capability.getAttribute("name").getValue(),
                    (capability.getAttribute("description") != null) ? capability.getAttribute("description").getValue() : null
            ));
        });
    }

    private void importUnits(Element specElement, Specification spec) {
        specElement.getChild("units").getChildren().stream().forEach(unit -> {
            resourceDesigner.createUnit(new UnitDto(
                    spec.getSpecId(),
                    unit.getAttribute("name").getValue(),
                    (unit.getAttribute("description") != null) ? unit.getAttribute("description").getValue() : null
            ));
        });
    }

    private void importRoles(Element specElement, Specification spec) {
        specElement.getChild("roles").getChildren().stream().forEach(role -> {
            resourceDesigner.createRole(new RoleDto(
                    spec.getSpecId(),
                    role.getAttribute("name").getValue(),
                    (role.getAttribute("description") != null) ? role.getAttribute("description").getValue() : null
            ));
        });
    }

    private void importPositions(Element specElement, Specification spec) {
        specElement.getChild("positions").getChildren().stream().forEach(position -> {
            PositionDto positionDTO = new PositionDto(
                    spec.getSpecId(),
                    position.getAttribute("name").getValue(),
                    position.getAttribute("unit").getValue(),
                    (position.getAttribute("description") != null) ? position.getAttribute("description").getValue() : null
            );

            resourceDesigner.createPosition(positionDTO);
        });

        specElement.getChild("positions").getChildren().stream().forEach(position -> {
            PositionDto positionDTO = new PositionDto(
                    spec.getSpecId(),
                    position.getAttribute("name").getValue(),
                    position.getAttribute("unit").getValue(),
                    (position.getAttribute("description") != null) ? position.getAttribute("description").getValue() : null
            );
            List<String> roles = position.getChild("roles").getChildren().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());
            positionDTO.setRoles(roles);

            List<String> delegatesTo = position.getChild("delegate-to").getChildren().stream()
                    .map(pos -> pos.getName())
                    .collect(Collectors.toList());
            positionDTO.setDelegateToRelations(delegatesTo);

            if (position.getChild("reports-to") != null) {
                positionDTO.setReportsTo(position.getChild("reports-to").getValue());
            }

            resourceDesigner.initPosition(positionDTO);
        });
    }

    private void importPersons(Element specElement, Specification spec) {
        specElement.getChild("persons").getChildren().stream().forEach(person -> {
            PersonDto personDTO = new PersonDto(
                    spec.getSpecId(),
                    person.getAttribute("name").getValue(),
                    (person.getAttribute("description") != null) ? person.getAttribute("description").getValue() : null
            );

            List<String> capabilities = person.getChild("capabilities").getChildren().stream()
                    .map(capability -> capability.getName())
                    .collect(Collectors.toList());
            personDTO.setCapabilities(capabilities);

            List<String> positions = person.getChild("positions").getChildren().stream()
                    .map(position -> position.getName())
                    .collect(Collectors.toList());
            personDTO.setPositions(positions);


            resourceDesigner.createPerson(personDTO);
        });
    }

    private void importResourceRules(Element specElement, Specification spec) {
        importDataModelRules(specElement, spec);

        importGoalRules(specElement, spec);

        importActivityRules(specElement, spec);


    }

    private void importDataModelRules(Element specElement, Specification spec) {
        specElement.getChildren().stream()
                .filter(rr -> rr.getAttribute("model").getValue().equals(DATA_MODEL))
                .forEach(resourceRule -> {
                    resourceDesigner.addResourceRule(new ResourceRuleDTO(
                            spec.getSpecId(),
                            resourceRule.getAttribute("product").getValue(),
                            convertResourceRuleType(resourceRule.getAttribute("type").getValue()),
                            importRALExpressionDTO(resourceRule.getChildren().get(0), spec)
                    ));
                });
    }

    private void importGoalRules(Element specElement, Specification spec) {
        specElement.getChildren().stream()
                .filter(rr -> rr.getAttribute("model").getValue().equals(GOAL_MODEL))
                .forEach(resourceRule -> {
                    ResourceRuleDTO.ResourceRuleTypeDTO type = convertResourceRuleType(resourceRule.getAttribute("type").getValue());
                    String goalName = resourceRule.getAttribute("product").getValue();
                    Goal goal = spec.getGoalModel().getGoal(goalName);
                    switch (type) {
                        case HAS_RESPONSIBLE:
                            goal.setResponsibleFor(resourceDesigner.createRALExpression(spec.getSpecId(), importRALExpressionDTO(resourceRule.getChildren().get(0), spec)));
                            break;
                        case INFORMS:
                            goal.setInforms(resourceDesigner.createRALExpression(spec.getSpecId(), importRALExpressionDTO(resourceRule.getChildren().get(0), spec)));
                            break;
                        default:
                    }
                });
    }

    private void importActivityRules(Element specElement, Specification spec) {
        specElement.getChildren().stream()
                .filter(rr -> rr.getAttribute("model").getValue().equals(ACTIVITY_MODEL))
                .forEach(resourceRule -> {
                    ResourceRuleDTO.ResourceRuleTypeDTO type = convertResourceRuleType(resourceRule.getAttribute("type").getValue());
                    String activityName = resourceRule.getAttribute("product").getValue();
                    Activity activity = spec.getActivityModel().getActivity(activityName);
                    switch (type) {
                        case HAS_RESPONSIBLE:
                            activity.setResponsibleFor(resourceDesigner.createRALExpression(spec.getSpecId(), importRALExpressionDTO(resourceRule.getChildren().get(0), spec)));
                            break;
                        case INFORMS:
                            activity.setInforms(resourceDesigner.createRALExpression(spec.getSpecId(), importRALExpressionDTO(resourceRule.getChildren().get(0), spec)));
                            break;
                        default:
                    }
                });
    }

    private RALExpressionDto importRALExpressionDTO(Element resourceRule, Specification spec) throws RMException {
        RALExpressionDto ralExpressionDTO = null;

        if (resourceRule.getName().equals("IsPersonExpr")) {
            ralExpressionDTO = new RALExprIsPersonDto(resourceRule.getAttribute("person").getValue());

        } else if (resourceRule.getName().equals("IsPersonDataObjectExpr")) {

            ralExpressionDTO = new RALExprIsPersonDataObjectDto(resourceRule.getAttribute("path").getValue());

        } else if (resourceRule.getName().equals("IsPersonInTaskDutyExpr")) {

            ralExpressionDTO = new RALExprIsPersonInTaskDutyDto(
                    ResourceRuleDTO.ResourceRuleTypeDTO.fromAsgmtString(resourceRule.getAttribute("task-duty").getValue()),
                    resourceRule.getAttribute("path").getValue()
            );

        } else if (resourceRule.getName().equals("AnyoneExpr")) {

            ralExpressionDTO = new RALExprAnyoneDto();

        } else if (resourceRule.getName().equals("HasPositionExpr")) {

            ralExpressionDTO = new RALExprHasPositionDto(resourceRule.getAttribute("position").getValue());

        } else if (resourceRule.getName().equals("HasUnitExpr")) {

            ralExpressionDTO = new RALExprHasUnitDto(resourceRule.getAttribute("unit").getValue());

        } else if (resourceRule.getName().equals("HasRoleExpr") ) {

            RALExprHasRoleDto dto = new RALExprHasRoleDto(resourceRule.getAttribute("role").getValue());
            if (resourceRule.getAttribute("unit") != null) {
                dto.setUnit(resourceRule.getAttribute("unit").getValue());
            }
            ralExpressionDTO = dto;

        } else if (resourceRule.getName().equals("ReportsToPersonPositionExpr")) {

            RALExprPersonDto personDTO = (RALExprPersonDto) importRALExpressionDTO(resourceRule.getChildren().get(0), spec);
            ralExpressionDTO = new RALExprReportsToPersonPositionExprDto(
                    personDTO,
                    resourceRule.getAttribute("directly").getValue().equals(BOOLEAN_TRUE)
            );

        } else if (resourceRule.getName().equals("ReportsToPositionExpr")) {

            ralExpressionDTO = new RALExprReportsToPositionExprDto(
                    resourceRule.getAttribute("position").getValue(),
                    resourceRule.getAttribute("directly").getValue().equals(BOOLEAN_TRUE)
            );

        } else if (resourceRule.getName().equals("ReportedByPersonPositionExpr")) {

            RALExprPersonDto personDTO = (RALExprPersonDto) importRALExpressionDTO(resourceRule.getChildren().get(0), spec);
            ralExpressionDTO = new RALExprReportedByPersonPositionExprDto(
                    personDTO,
                    resourceRule.getAttribute("directly").getValue().equals(BOOLEAN_TRUE)
            );

        } else if (resourceRule.getName().equals("ReportedByPositionExpr")) {

            ralExpressionDTO = new RALExprReportedByPositionExprDto(
                    resourceRule.getAttribute("position").getValue(),
                    resourceRule.getAttribute("directly").getValue().equals(BOOLEAN_TRUE)
            );

        } else if (resourceRule.getName().equals("DelegatesToPersonPositionExpr")) {

            RALExprPersonDto personDTO = (RALExprPersonDto) importRALExpressionDTO(resourceRule.getChildren().get(0), spec);
            ralExpressionDTO = new RALExprDelegatesToPersonPositionExprDto(personDTO);

        } else if (resourceRule.getName().equals("DelegatesToPositionExpr")) {

            ralExpressionDTO = new RALExprDelegatesToPositionExprDto(resourceRule.getAttribute("position").getValue());

        } else if (resourceRule.getName().equals("DelegatedByPersonPositionExpr")) {

            RALExprPersonDto personDTO = (RALExprPersonDto) importRALExpressionDTO(resourceRule.getChildren().get(0), spec);
            ralExpressionDTO = new RALExprDelegatedByPersonPositionExprDto(personDTO);

        } else if (resourceRule.getName().equals("DelegatedByPositionExpr")) {

            ralExpressionDTO = new RALExprDelegatedByPositionExprDto(resourceRule.getAttribute("position").getValue());

        } else if (resourceRule.getName().equals("NotExpr") ) {

            ralExpressionDTO = new RALExprNotDto(importRALExpressionDTO(resourceRule.getChildren().get(0), spec));

        } else if (resourceRule.getName().equals("OrExpr")) {
            ralExpressionDTO = new RALExprOrDto(
                    importRALExpressionDTO(resourceRule.getChildren().get(0), spec),
                    importRALExpressionDTO(resourceRule.getChildren().get(1), spec)
            );
        } else if (resourceRule.getName().equals("AndExpr")) {
            ralExpressionDTO = new RALExprAndDto(
                    importRALExpressionDTO(resourceRule.getChildren().get(0), spec),
                    importRALExpressionDTO(resourceRule.getChildren().get(1), spec)
            );
        } else if (resourceRule.getName().equals("HasCapabilityExpr")) {

            ralExpressionDTO = new RALExprHasCapabilityDto(resourceRule.getAttribute("capability").getValue());

        } else if (resourceRule.getName().equals("HistoryExecutingExpr")) {

            ralExpressionDTO = new RALExprHistoryExecutingDto(
                    RALExprHistoryDto.QuantifierDTO.fromString(resourceRule.getAttribute("quantifier").getValue()),
                    resourceRule.getAttribute("data-field").getValue()
            );

        } else if (resourceRule.getName().equals("HistoryInformedExpr")) {

            ralExpressionDTO = new RALExprHistoryInformedDto(
                    RALExprHistoryDto.QuantifierDTO.fromString(resourceRule.getAttribute("quantifier").getValue()),
                    resourceRule.getAttribute("data-field").getValue()
            );

        } else if (resourceRule.getName().equals("SharesPositionExpr")) {

            ralExpressionDTO = new RALExprSharesPositionDto(
                    RALExprCommonalityDto.AmountDTO.fromString(resourceRule.getAttribute("amount").getValue()),
                    importRALExpressionDTO(resourceRule.getChildren().get(0), spec)
            );

        } else if (resourceRule.getName().equals("SharesUnitExpr")) {

            ralExpressionDTO = new RALExprSharesUnitDto(
                    RALExprCommonalityDto.AmountDTO.fromString(resourceRule.getAttribute("amount").getValue()),
                    importRALExpressionDTO(resourceRule.getChildren().get(0), spec)
            );

        } else if (resourceRule.getName().equals("SharesRoleExpr")) {

            RALExprSharesRoleDto dto = new RALExprSharesRoleDto(
                    RALExprCommonalityDto.AmountDTO.fromString(resourceRule.getAttribute("amount").getValue()),
                    importRALExpressionDTO(resourceRule.getChildren().get(0), spec)
            );

            if (resourceRule.getAttribute("unit") != null) {
                dto.setUnit(resourceRule.getAttribute("unit").getValue());
            }

            ralExpressionDTO = dto;

        }

        return ralExpressionDTO;
    }

    private ResourceRuleDTO.ResourceRuleTypeDTO convertResourceRuleType(String type) {
        switch (type) {
            case "responsible-for":
                return ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE;
            case "informs":
                return ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS;
            default:
                throw new RMException(RMErrorType.INVALID_RESOURCE_RULE_TYPE);
        }
    }
}
