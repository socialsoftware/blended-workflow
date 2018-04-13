package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.jdom2.Element;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlExport;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.Set;

public class ResourceXmlExport extends SpecXmlExport {

    public static final String DATA_MODEL = "data-model";
    public static final String RESPONSIBLE_FOR = "responsible-for";
    public static final String INFORMS = "informs";
    public static final String ACTIVITY_MODEL = "activity-model";
    public static final String GOAL_MODEL = "goal-model";

    @Override
    public void exportModules(Specification spec, Element element) {
        exportResourceModel(element, spec.getResourceModel());
        exportResourceRules(element, spec);
    }

    private void exportResourceModel(Element element, ResourceModel resourceModel) {
        Element parent = new Element("resource-model");

        exportCapabilities(parent, resourceModel.getCapabilitySet());
        exportPersons(parent, resourceModel.getPersonSet());
        exportPositions(parent, resourceModel.getPositionSet());
        exportRoles(parent, resourceModel.getRoleSet());
        exportUnits(parent, resourceModel.getUnitSet());
        exportIsPersons(parent, resourceModel.getEntityIsPersonSet());

        element.addContent(parent);
    }

    private void exportCapabilities(Element parent, Set<Capability> capabilitySet) {
        Element capabilitiesElement = new Element("capabilities");

        capabilitySet.stream().forEach(capability -> {
            Element element = new Element("capability");
            element.setAttribute("name", capability.getName());
            if (capability.getDescription() != null) {
                element.setAttribute("description", capability.getDescription());
            }
            capabilitiesElement.addContent(element);
        });

        parent.addContent(capabilitiesElement);
    }

    private void exportPersons(Element parent, Set<Person> personSet) {
        Element personsElement = new Element("persons");

        personSet.stream().forEach(person -> {
            Element element = new Element("person");
            element.setAttribute("name", person.getName());
            if (person.getDescription() != null) {
                element.setAttribute("description", person.getDescription());
            }
            Element positionsElement = new Element("positions");
            person.getPositionSet().stream().forEach(position -> {
                Element ele = new Element(position.getName());
                positionsElement.addContent(ele);
            });
            element.addContent(positionsElement);

            Element capabilitiesElement = new Element("capabilities");
            person.getCapabilitySet().stream().forEach(capability -> {
                Element ele = new Element(capability.getName());
                capabilitiesElement.addContent(ele);
            });
            element.addContent(capabilitiesElement);


            personsElement.addContent(element);
        });

        parent.addContent(personsElement);
    }

    private void exportPositions(Element parent, Set<Position> positionSet) {
        Element positionsElement = new Element("positions");

        positionSet.stream().forEach(position -> {
            Element element = new Element("position");

            element.setAttribute("name", position.getName());
            element.setAttribute("unit", position.getUnit().getName());
            if (position.getDescription() != null) {
                element.setAttribute("description", position.getDescription());
            }
            if (position.getReportsTo() != null) {
                element.setAttribute("reports-to", position.getReportsTo().getName());
            }

            Element rolesElement = new Element("roles");
            position.getRoleSet().stream().forEach(role -> {
                Element ele = new Element(role.getName());
                rolesElement.addContent(ele);
            });
            element.addContent(rolesElement);

            Element delegatesElement = new Element("delegate-to");
            position.getCanDelegateWorkToSet().stream().forEach(pos -> {
                Element ele = new Element(pos.getName());
                delegatesElement.addContent(ele);
            });
            element.addContent(delegatesElement);

            positionsElement.addContent(element);
        });

        parent.addContent(positionsElement);
    }

    private void exportRoles(Element parent, Set<Role> roleSet) {
        Element rolesElement = new Element("roles");

        roleSet.stream().forEach(role -> {
            Element element = new Element("role");
            element.setAttribute("name", role.getName());
            if (role.getDescription() != null) {
                element.setAttribute("description", role.getDescription());
            }
            rolesElement.addContent(element);
        });

        parent.addContent(rolesElement);
    }

    private void exportUnits(Element parent, Set<Unit> unitSet) {
        Element unitsElement = new Element("units");

        unitSet.stream().forEach(unit -> {
            Element element = new Element("unit");
            element.setAttribute("name", unit.getName());
            if (unit.getDescription() != null) {
                element.setAttribute("description", unit.getDescription());
            }
            unitsElement.addContent(element);
        });

        parent.addContent(unitsElement);
    }

    private void exportIsPersons(Element parent, Set<Entity> entityIsPersonSet) {
        Element eipElement = new Element("entity-is-person");

        entityIsPersonSet.stream().forEach(entity -> {
            Element element = new Element(entity.getName());
            eipElement.addContent(element);
        });

        parent.addContent(eipElement);
    }

    private void exportResourceRules(Element parent, Specification spec) {
        Element element = new Element("resource-rules");

        exportDataModelResourceRules(element, spec.getDataModel());
        exportActivityModelResourceRules(element, spec.getActivityModel());
        exportGoalModelResourceRules(element, spec.getGoalModel());

        parent.addContent(element);
    }

    private void exportDataModelResourceRules(Element parent, DataModel dataModel) {
        dataModel.getEntitySet().stream().forEach( entity -> {
            exportResourceRule(parent, DATA_MODEL, RESPONSIBLE_FOR, entity.getFullPath(), exportRALExpression(entity.getResponsibleFor()));
            exportResourceRule(parent, DATA_MODEL, INFORMS, entity.getName(), exportRALExpression(entity.getInforms()));
        });

        dataModel.getAttributeSet().stream().forEach( attribute -> {
            exportResourceRule(parent, DATA_MODEL, RESPONSIBLE_FOR, attribute.getFullPath(), exportRALExpression(attribute.getResponsibleFor()));
            exportResourceRule(parent, DATA_MODEL, INFORMS, attribute.getFullPath(), exportRALExpression(attribute.getInforms()));
        });
    }

    private void exportActivityModelResourceRules(Element parent, ActivityModel activityModel) {
        activityModel.getActivitySet().stream().forEach(activity -> {
            exportResourceRule(parent, ACTIVITY_MODEL, RESPONSIBLE_FOR, activity.getName(), exportRALExpression(activity.getResponsibleFor()));
            exportResourceRule(parent, ACTIVITY_MODEL, INFORMS, activity.getName(), exportRALExpression(activity.getInforms()));
        });
    }

    private void exportGoalModelResourceRules(Element parent, GoalModel goalModel) {
        goalModel.getGoalSet().stream().forEach(goal -> {
            exportResourceRule(parent, GOAL_MODEL, RESPONSIBLE_FOR, goal.getName(), exportRALExpression(goal.getResponsibleFor()));
            exportResourceRule(parent, GOAL_MODEL, INFORMS, goal.getName(), exportRALExpression(goal.getInforms()));
        });
    }

    private void exportResourceRule(Element parent, String model, String type, String target, Element RALExpression) {
        Element element = new Element("resource-rule");
        element.setAttribute("product", target);
        element.setAttribute("model", model);
        element.setAttribute("type", type);

        element.addContent(RALExpression);

        parent.addContent(element);
    }

    private Element exportRALExpression(RALExpression ralExpression) {
        Element expression;

        if (ralExpression instanceof RALExprAnd) {
            expression = new Element("AndExpr");

            Element leftExpr = exportRALExpression(((RALExprAnd) ralExpression).getLeftExpression());
            Element rightExpr = exportRALExpression(((RALExprAnd) ralExpression).getRightExpression());

            expression.addContent(0, leftExpr);
            expression.addContent(1, rightExpr);
        } else if (ralExpression instanceof RALExprAnyone) {
            expression = new Element("AnyoneExpr");
        } else if (ralExpression instanceof RALExprDelegatedByPersonPosition) {
            expression = new Element("DelegatedByPersonPositionExpr");

            Element personExpr = exportRALExpression(((RALExprDelegatedByPersonPosition) ralExpression).getPersonExpr());

            expression.addContent(0, personExpr);
        } else if (ralExpression instanceof RALExprDelegatedByPosition) {
            expression = new Element("DelegatedByPositionExpr");

            expression.setAttribute("position", ((RALExprDelegatedByPosition) ralExpression).getPosition().getName());
        } else if (ralExpression instanceof RALExprDelegatesToPersonPosition) {
            expression = new Element("DelegatesToPersonPositionExpr");

            Element personExpr = exportRALExpression(((RALExprDelegatesToPersonPosition) ralExpression).getPersonExpr());

            expression.addContent(0, personExpr);
        } else if (ralExpression instanceof RALExprDelegatesToPosition) {
            expression = new Element("DelegatesToPositionExpr");

            expression.setAttribute("position", ((RALExprDelegatesToPosition) ralExpression).getPosition().getName());
        } else if (ralExpression instanceof RALExprHasCapability) {
            expression = new Element("HasCapabilityExpr");

            expression.setAttribute("capability", ((RALExprHasCapability) ralExpression).getCapability().getName());
        } else if (ralExpression instanceof RALExprHasPosition) {
            expression = new Element("HasPositionExpr");

            expression.setAttribute("position", ((RALExprHasPosition) ralExpression).getPosition().getName());
        } else if (ralExpression instanceof RALExprHasRole) {
            expression = new Element("HasRoleExpr");

            expression.setAttribute("role", ((RALExprHasRole) ralExpression).getRole().getName());

            if (((RALExprHasRole) ralExpression).getUnit() != null) {
                expression.setAttribute("unit", ((RALExprHasRole) ralExpression).getUnit().getName());
            }
        } else if (ralExpression instanceof RALExprHasUnit) {
            expression = new Element("HasUnitExpr");

            expression.setAttribute("unit", ((RALExprHasUnit) ralExpression).getUnit().getName());
        } else if (ralExpression instanceof RALExprHistoryExecuting) {
            expression = new Element("HistoryExecutingExpr");

            expression.setAttribute("quantifier", ((RALExprHistoryExecuting) ralExpression).getQuantifier().toString());
            expression.setAttribute("data-field", ((RALExprHistoryExecuting) ralExpression).getDataField().getFullPath());
        } else if (ralExpression instanceof RALExprHistoryInformed) {
            expression = new Element("HistoryInformedExpr");

            expression.setAttribute("quantifier", ((RALExprHistoryInformed) ralExpression).getQuantifier().toString());
            expression.setAttribute("data-field", ((RALExprHistoryInformed) ralExpression).getDataField().getFullPath());
        } else if (ralExpression instanceof RALExprIsPerson) {
            expression = new Element("IsPersonExpr");

            expression.setAttribute("person", ((RALExprIsPerson) ralExpression).getPerson().getName());
        } else if (ralExpression instanceof RALExprIsPersonDataObject) {
            expression = new Element("IsPersonDataObjectExpr");

            expression.setAttribute("data-field", ((RALExprIsPersonDataObject) ralExpression).getDataField().getFullPath());
        } else if (ralExpression instanceof RALExprIsPersonInTaskDuty) {
            expression = new Element("IsPersonInTaskDutyExpr");

            expression.setAttribute("task-duty", ((RALExprIsPersonInTaskDuty) ralExpression).getTaskDuty().toString());
            expression.setAttribute("product", ((RALExprIsPersonInTaskDuty) ralExpression).getProduct().getFullPath());
        } else if (ralExpression instanceof RALExprNot) {
            expression = new Element("NotExpr");

            Element notExpr = exportRALExpression(((RALExprNot) ralExpression).getExpression());

            expression.addContent(notExpr);
        } else if (ralExpression instanceof RALExprOr) {
            expression = new Element("OrExpr");

            Element leftExpr = exportRALExpression(((RALExprOr) ralExpression).getLeftExpression());
            Element rightExpr = exportRALExpression(((RALExprOr) ralExpression).getRightExpression());

            expression.addContent(0, leftExpr);
            expression.addContent(1, rightExpr);
        } else if (ralExpression instanceof RALExprReportedByPersonPosition) {
            expression = new Element("ReportedByPersonPositionExpr");

            expression.setAttribute("directly", ((RALExprReportedByPersonPosition) ralExpression).getDirectly() ? "true" : "false");

            expression.addContent(exportRALExpression(((RALExprReportedByPersonPosition) ralExpression).getPersonExpr()));
        } else if (ralExpression instanceof RALExprReportedByPosition) {
            expression = new Element("ReportedByPositionExpr");

            expression.setAttribute("directly", ((RALExprReportedByPosition) ralExpression).getDirectly() ? "true" : "false");
            expression.setAttribute("position", ((RALExprReportedByPosition) ralExpression).getPosition().getName());
        } else if (ralExpression instanceof RALExprReportsToPersonPosition) {
            expression = new Element("ReportsToPersonPositionExpr");

            expression.setAttribute("directly", ((RALExprReportsToPersonPosition) ralExpression).getDirectly() ? "true" : "false");

            expression.addContent(exportRALExpression(((RALExprReportsToPersonPosition) ralExpression).getPersonExpr()));
        } else if (ralExpression instanceof RALExprReportsToPosition) {
            expression = new Element("ReportsToPositionExpr");

            expression.setAttribute("directly", ((RALExprReportsToPosition) ralExpression).getDirectly() ? "true" : "false");
            expression.setAttribute("position", ((RALExprReportsToPosition) ralExpression).getPosition().getName());
        } else if (ralExpression instanceof RALExprSharesPosition) {
            expression = new Element("SharesPositionExpr");

            expression.setAttribute("amount", ((RALExprSharesPosition) ralExpression).getAmount().toString());

            expression.addContent(exportRALExpression(((RALExprSharesPosition) ralExpression).getPersonExpr()));
        } else if (ralExpression instanceof RALExprSharesRole) {
            expression = new Element("SharesRoleExpr");

            expression.setAttribute("amount", ((RALExprSharesRole) ralExpression).getAmount().toString());

            expression.addContent(exportRALExpression(((RALExprSharesRole) ralExpression).getPersonExpr()));

            if (((RALExprSharesRole) ralExpression).getUnit() != null) {
                expression.setAttribute("unit", ((RALExprSharesRole) ralExpression).getUnit().getName());
            }
        } else if (ralExpression instanceof RALExprSharesUnit) {
            expression = new Element("SharesUnitExpr");

            expression.setAttribute("amount", ((RALExprSharesUnit) ralExpression).getAmount().toString());

            expression.addContent(exportRALExpression(((RALExprSharesUnit) ralExpression).getPersonExpr()));
        } else {
            throw new RMException(RMErrorType.EXPORT_INVALID);
        }

        return expression;
    }
}
