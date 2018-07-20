package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetOfRequiredResources {
    private Set<PositionDto> positions;
    private Set<PositionDto> notPositions;
    private Set<PersonDto> persons;
    private Set<PersonDto> notPersons;
    private Set<CapabilityDto> capabilities;
    private Set<CapabilityDto> notCapabilities;
    private Set<RoleDto> roles;
    private Set<RoleDto> notRoles;
    private Set<UnitDto> units;
    private Set<UnitDto> notUnits;
    private Set<String> dataField;
    private Set<String> notDataField;
    private Set<String> taskDuty;
    private Set<String> notTaskDuty;
    private Set<String> history;
    private Set<String> workItemProducts;


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

    public Set<PositionDto> getPositions() {
        return positions;
    }

    public SetOfRequiredResources addPositions(Collection<PositionDto> positions) {
        this.positions.addAll(positions);

        return this;
    }

    public Set<PositionDto> getNotPositions() {
        return notPositions;
    }

    public SetOfRequiredResources addNotPositions(Collection<PositionDto> notPositions) {
        this.notPositions.addAll(notPositions);

        return this;
    }

    public Set<PersonDto> getPersons() {
        return persons;
    }

    public SetOfRequiredResources addPersons(Collection<PersonDto> persons) {
        this.persons.addAll(persons);

        return this;
    }

    public Set<PersonDto> getNotPersons() {
        return notPersons;
    }

    public SetOfRequiredResources addNotPersons(Collection<PersonDto> notPersons) {
        this.notPersons.addAll(notPersons);

        return this;
    }

    public Set<CapabilityDto> getCapabilities() {
        return capabilities;
    }

    public SetOfRequiredResources addCapabilities(Collection<CapabilityDto> capabilities) {
        this.capabilities.addAll(capabilities);

        return this;
    }

    public Set<CapabilityDto> getNotCapabilities() {
        return notCapabilities;
    }

    public SetOfRequiredResources addNotCapabilities(Collection<CapabilityDto> notCapabilities) {
        this.notCapabilities.addAll(notCapabilities);

        return this;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public SetOfRequiredResources addRoles(Collection<RoleDto> roles) {
        this.roles.addAll(roles);

        return this;
    }

    public Set<RoleDto> getNotRoles() {
        return notRoles;
    }

    public SetOfRequiredResources addNotRoles(Collection<RoleDto> notRoles) {
        this.notRoles.addAll(notRoles);

        return this;
    }

    public Set<UnitDto> getUnits() {
        return units;
    }

    public SetOfRequiredResources addUnits(Collection<UnitDto> unit) {
        this.units.addAll(unit);

        return this;
    }

    public Set<UnitDto> getNotUnits() {
        return notUnits;
    }

    public SetOfRequiredResources addNotUnits(Collection<UnitDto> notUnit) {
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

    public Set<String> getWorkItemProducts() {
        return workItemProducts;
    }

    public SetOfRequiredResources addWorkItemProducts(Collection<String> workItemProducts) {
        this.workItemProducts.addAll(workItemProducts);

        return this;
    }

    public void setPositions(Set<PositionDto> positions) {
        this.positions = positions;
    }

    public void setNotPositions(Set<PositionDto> notPositions) {
        this.notPositions = notPositions;
    }

    public void setPersons(Set<PersonDto> persons) {
        this.persons = persons;
    }

    public void setNotPersons(Set<PersonDto> notPersons) {
        this.notPersons = notPersons;
    }

    public void setCapabilities(Set<CapabilityDto> capabilities) {
        this.capabilities = capabilities;
    }

    public void setNotCapabilities(Set<CapabilityDto> notCapabilities) {
        this.notCapabilities = notCapabilities;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public void setNotRoles(Set<RoleDto> notRoles) {
        this.notRoles = notRoles;
    }

    public void setUnits(Set<UnitDto> units) {
        this.units = units;
    }

    public void setNotUnits(Set<UnitDto> notUnits) {
        this.notUnits = notUnits;
    }

    public void setDataField(Set<String> dataField) {
        this.dataField = dataField;
    }

    public void setNotDataField(Set<String> notDataField) {
        this.notDataField = notDataField;
    }

    public void setWorkItemProducts(Set<String> workItemProducts) {
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
