package org.blended.condition.repository;

public class ConditionInterface {
    // private static Logger log = LoggerFactory
    // .getLogger(ConditionInterface.class);
    //
    // private static ConditionInterface instance = null;
    //
    // public static ConditionInterface getInstance() {
    // if (instance == null) {
    // instance = new ConditionInterface();
    // }
    // return instance;
    // }
    //
    // // to be invoked by tests only
    // public void deleteSpecification(SpecificationDTO specDTO) {
    // adi.deleteSpecification(specDTO);
    // }
    //
    // private AtomicDesignInterface adi = null;
    //
    // private ConditionInterface() {
    // adi = AtomicDesignInterface.getInstance();
    // }
    //
    // public BWNotification loadConditionModel(String specId,
    // ConditionModel eConditionModel) {
    // BWNotification notification = new BWNotification();
    //
    // Specification eSpec = eConditionModel.getSpecification();
    // log.debug("Specification: {}", eSpec.getName());
    //
    // try {
    // adi.loadConditionSpecification(
    // new SpecificationDTO(specId, eSpec.getName()));
    // } catch (BWException bwe) {
    // notification
    // .addError(new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
    // }
    //
    // for (EObject eObj : eConditionModel.getEntityAchieveConditions()) {
    // if (eObj instanceof EntityAchieveCondition) {
    // EntityAchieveCondition eEac = (EntityAchieveCondition) eObj;
    // log.debug("EntityAchieveCondition Name:{} ", eEac.getName());
    // try {
    // adi.createEntityAchieveCondition(
    // new EntityAchieveConditionDTO(specId,
    // eEac.getName(), false));
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(),
    // bwe.getMessage());
    // }
    //
    // } else if (eObj instanceof EntityAchieveConditionExist) {
    // EntityAchieveConditionExist eEace = (EntityAchieveConditionExist) eObj;
    // log.debug("EntityAchieveConditionExists Name:{} ",
    // eEace.getName());
    // try {
    // adi.createEntityAchieveCondition(
    // new EntityAchieveConditionDTO(specId,
    // eEace.getName(), true));
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(),
    // bwe.getMessage());
    // }
    //
    // }
    // }
    //
    // for (EntityDependenceCondition eEpc : eConditionModel
    // .getEntityDependenceConditions()) {
    // log.debug("EntityDependenceCondition Entity1:{}, Entity2:{}",
    // eEpc.getEntity1(), eEpc.getEntity2());
    // try {
    // adi.createEntityDependenceCondition(new DependenceDTO(specId,
    // eEpc.getEntity1(), eEpc.getEntity2()));
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
    // }
    // }
    //
    // for (EntityInvariantCondition eEic : eConditionModel
    // .getEntityInvariantConditions()) {
    // log.debug("EntityInvariantCondition Name:{}, Cardinality:{}",
    // eEic.getName(), eEic.getCardinality());
    // try {
    // adi.createEntityInvariantCondition(new MulInvariantDTO(specId,
    // eEic.getName(), eEic.getCardinality()));
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
    // }
    // }
    //
    // for (AttributeAchieveCondition eAac : eConditionModel
    // .getAttributeAchieveConditions()) {
    // log.debug("AttributeAchieveCondition Conditions:{} ",
    // eAac.getConditions());
    // boolean mandatory;
    // if (eAac instanceof NotMandatoryAttributeAchieveCondition) {
    // mandatory = false;
    // } else {
    // mandatory = true;
    // }
    // Set<String> paths = new HashSet<String>();
    // for (String path : eAac.getConditions()) {
    // paths.add(path);
    // }
    // try {
    // adi.createAttributeAchieveCondition(
    // new AttributeAchieveConditionDTO(specId, paths,
    // mandatory));
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
    // }
    // }
    //
    // for (AttributeDependenceCondition eApc : eConditionModel
    // .getAttributeDependenceConditions()) {
    // log.debug(
    // "AttributeDependenceCondition Attributes1:{}, Attributes2:{}",
    // eApc.getAttributes1(), eApc.getAttributes2());
    // try {
    // Set<String> sourceAtts = eApc.getAttributes1().stream()
    // .collect(Collectors.toSet());
    // Set<String> targetAtts = eApc.getAttributes2().stream()
    // .collect(Collectors.toSet());
    // adi.createAttributeDependenceCondition(specId, sourceAtts,
    // targetAtts);
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
    // }
    // }
    //
    // for (AttributeInvariantCondition eAic : eConditionModel
    // .getAttributeInvariantConditions()) {
    // log.debug("AttributeInvariantCondition Name:{}", eAic.getName());
    // try {
    // adi.createAttributeInvariantCondition(
    // new RuleDTO(specId, eAic.getName()));
    // } catch (BWException bwe) {
    // notification.addError(
    // new BWError(bwe.getError(), bwe.getMessage()));
    // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
    // }
    // }
    //
    // adi.printSpecificationModels(specId);
    //
    // return notification;
    // }
    //
}
