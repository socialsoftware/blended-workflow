package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductDTO;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetOfRequiredResources {
    private Set<PositionDTO> positions;
    private Set<PositionDTO> notPositions;
    private Set<PersonDTO> persons;
    private Set<PersonDTO> notPersons;
    private Set<CapabilityDTO> capabilities;
    private Set<CapabilityDTO> notCapabilities;
    private Set<RoleDTO> roles;
    private Set<RoleDTO> notRoles;
    private Set<UnitDTO> units;
    private Set<UnitDTO> notUnits;
    private Set<String> dataField;
    private Set<String> notDataField;
    private Set<String> taskDuty;
    private Set<String> notTaskDuty;
    private Set<String> history;
    private Set<ProductDTO> workItemProducts;


    public SetOfRequiredResources() {
        this.positions = new HashSet<>();
        this.notPositions = new HashSet<>();
        this.persons = new HashSet<>();
        this.notPersons = new HashSet<>();
        this.capabilities = new HashSet<>();
        this.notCapabilities = new HashSet<>();
        this.roles = new HashSet<>();
        this.notRoles = new HashSet<>();
        this.units = new HashSet<>();
        this.notUnits = new HashSet<>();
        this.dataField = new HashSet<>();
        this.notDataField = new HashSet<>();
        this.taskDuty = new HashSet<>();
        this.notTaskDuty = new HashSet<>();
        this.history = new HashSet<>();
        this.workItemProducts = new HashSet<>();
    }

    public SetOfRequiredResources merge(SetOfRequiredResources set) {
        addPositions(set.getPositions());
        addNotPositions(set.getNotPositions());
        addPersons(set.getPersons());
        addNotPersons(set.getNotPersons());
        addCapabilities(set.getCapabilities());
        addNotCapabilities(set.getNotCapabilities());
        addRoles(set.getRoles());
        addNotRoles(set.getNotRoles());
        addUnits(set.getUnits());
        addNotUnits(set.getNotUnits());
        addDataField(set.getDataField());
        addNotDataField(set.getNotDataField());
        addTaskDuty(set.getTaskDuty());
        addNotTaskDuty(set.getNotTaskDuty());
        addWorkItemProducts(set.getWorkItemProducts());

        return this;
    }

    public Set<PositionDTO> getPositions() {
        return positions;
    }

    public SetOfRequiredResources addPositions(Collection<PositionDTO> positions) {
        this.positions.addAll(positions);

        return this;
    }

    public Set<PositionDTO> getNotPositions() {
        return notPositions;
    }

    public SetOfRequiredResources addNotPositions(Collection<PositionDTO> notPositions) {
        this.notPositions.addAll(notPositions);

        return this;
    }

    public Set<PersonDTO> getPersons() {
        return persons;
    }

    public SetOfRequiredResources addPersons(Collection<PersonDTO> persons) {
        this.persons.addAll(persons);

        return this;
    }

    public Set<PersonDTO> getNotPersons() {
        return notPersons;
    }

    public SetOfRequiredResources addNotPersons(Collection<PersonDTO> notPersons) {
        this.notPersons.addAll(notPersons);

        return this;
    }

    public Set<CapabilityDTO> getCapabilities() {
        return capabilities;
    }

    public SetOfRequiredResources addCapabilities(Collection<CapabilityDTO> capabilities) {
        this.capabilities.addAll(capabilities);

        return this;
    }

    public Set<CapabilityDTO> getNotCapabilities() {
        return notCapabilities;
    }

    public SetOfRequiredResources addNotCapabilities(Collection<CapabilityDTO> notCapabilities) {
        this.notCapabilities.addAll(notCapabilities);

        return this;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public SetOfRequiredResources addRoles(Collection<RoleDTO> roles) {
        this.roles.addAll(roles);

        return this;
    }

    public Set<RoleDTO> getNotRoles() {
        return notRoles;
    }

    public SetOfRequiredResources addNotRoles(Collection<RoleDTO> notRoles) {
        this.notRoles.addAll(notRoles);

        return this;
    }

    public Set<UnitDTO> getUnits() {
        return units;
    }

    public SetOfRequiredResources addUnits(Collection<UnitDTO> unit) {
        this.units.addAll(unit);

        return this;
    }

    public Set<UnitDTO> getNotUnits() {
        return notUnits;
    }

    public SetOfRequiredResources addNotUnits(Collection<UnitDTO> notUnit) {
        this.notUnits.addAll(notUnit);

        return this;
    }

    public Set<String> getDataField() {
        return dataField;
    }

    public SetOfRequiredResources addDataField(Collection<String> dataField) {
        this.dataField.addAll(dataField);

        return this;
    }

    public Set<String> getNotDataField() {
        return notDataField;
    }

    public SetOfRequiredResources addNotDataField(Collection<String> notDataField) {
        this.notDataField.addAll(notDataField);

        return this;
    }

    public Set<String> getTaskDuty() {
        return taskDuty;
    }

    public SetOfRequiredResources addTaskDuty(Collection<String> taskDuty) {
        this.taskDuty.addAll(taskDuty);

        return this;
    }

    public Set<String> getNotTaskDuty() {
        return notTaskDuty;
    }

    public SetOfRequiredResources addNotTaskDuty(Collection<String> notTaskDuty) {
        this.notTaskDuty.addAll(notTaskDuty);

        return this;
    }

    public Set<String> getHistory() {
        return history;
    }

    public SetOfRequiredResources addHistory(Collection<String> history) {
        this.history.addAll(history);

        return this;
    }

    public Set<ProductDTO> getWorkItemProducts() {
        return workItemProducts;
    }

    public SetOfRequiredResources addWorkItemProducts(Collection<ProductDTO> workItemProducts) {
        this.workItemProducts.addAll(workItemProducts);

        return this;
    }

    public void setPositions(Set<PositionDTO> positions) {
        this.positions = positions;
    }

    public void setNotPositions(Set<PositionDTO> notPositions) {
        this.notPositions = notPositions;
    }

    public void setPersons(Set<PersonDTO> persons) {
        this.persons = persons;
    }

    public void setNotPersons(Set<PersonDTO> notPersons) {
        this.notPersons = notPersons;
    }

    public void setCapabilities(Set<CapabilityDTO> capabilities) {
        this.capabilities = capabilities;
    }

    public void setNotCapabilities(Set<CapabilityDTO> notCapabilities) {
        this.notCapabilities = notCapabilities;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public void setNotRoles(Set<RoleDTO> notRoles) {
        this.notRoles = notRoles;
    }

    public void setUnits(Set<UnitDTO> units) {
        this.units = units;
    }

    public void setNotUnits(Set<UnitDTO> notUnits) {
        this.notUnits = notUnits;
    }

    public void setDataField(Set<String> dataField) {
        this.dataField = dataField;
    }

    public void setNotDataField(Set<String> notDataField) {
        this.notDataField = notDataField;
    }

    public void setWorkItemProducts(Set<ProductDTO> workItemProducts) {
        this.workItemProducts = workItemProducts;
    }

    public void setTaskDuty(Set<String> taskDuty) {
        this.taskDuty = taskDuty;
    }

    public void setNotTaskDuty(Set<String> notTaskDuty) {
        this.notTaskDuty = notTaskDuty;
    }

    public void setHistory(Set<String> history) {
        this.history = history;
    }
}
