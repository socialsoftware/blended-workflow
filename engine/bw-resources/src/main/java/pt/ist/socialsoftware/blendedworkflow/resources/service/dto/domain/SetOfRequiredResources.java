package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductDTO;

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
    private Set<UnitDTO> unit;
    private Set<UnitDTO> notUnit;
    private Set<ProductDTO> dataField;
    private Set<ProductDTO> notDataField;
    private Set<ProductDTO> workItemProducts;

    public SetOfRequiredResources() {

    }

    public Set<PositionDTO> getPositions() {
        return positions;
    }

    public SetOfRequiredResources setPositions(Set<PositionDTO> positions) {
        this.positions = positions;

        return this;
    }

    public Set<PositionDTO> getNotPositions() {
        return notPositions;
    }

    public SetOfRequiredResources setNotPositions(Set<PositionDTO> notPositions) {
        this.notPositions = notPositions;

        return this;
    }

    public Set<PersonDTO> getPersons() {
        return persons;
    }

    public SetOfRequiredResources setPersons(Set<PersonDTO> persons) {
        this.persons = persons;

        return this;
    }

    public Set<PersonDTO> getNotPersons() {
        return notPersons;
    }

    public SetOfRequiredResources setNotPersons(Set<PersonDTO> notPersons) {
        this.notPersons = notPersons;

        return this;
    }

    public Set<CapabilityDTO> getCapabilities() {
        return capabilities;
    }

    public SetOfRequiredResources setCapabilities(Set<CapabilityDTO> capabilities) {
        this.capabilities = capabilities;

        return this;
    }

    public Set<CapabilityDTO> getNotCapabilities() {
        return notCapabilities;
    }

    public SetOfRequiredResources setNotCapabilities(Set<CapabilityDTO> notCapabilities) {
        this.notCapabilities = notCapabilities;

        return this;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public SetOfRequiredResources setRoles(Set<RoleDTO> roles) {
        this.roles = roles;

        return this;
    }

    public Set<RoleDTO> getNotRoles() {
        return notRoles;
    }

    public SetOfRequiredResources setNotRoles(Set<RoleDTO> notRoles) {
        this.notRoles = notRoles;

        return this;
    }

    public Set<UnitDTO> getUnit() {
        return unit;
    }

    public SetOfRequiredResources setUnit(Set<UnitDTO> unit) {
        this.unit = unit;

        return this;
    }

    public Set<UnitDTO> getNotUnit() {
        return notUnit;
    }

    public SetOfRequiredResources setNotUnit(Set<UnitDTO> notUnit) {
        this.notUnit = notUnit;

        return this;
    }

    public Set<ProductDTO> getDataField() {
        return dataField;
    }

    public SetOfRequiredResources setDataField(Set<ProductDTO> dataField) {
        this.dataField = dataField;

        return this;
    }

    public Set<ProductDTO> getNotDataField() {
        return notDataField;
    }

    public SetOfRequiredResources setNotDataField(Set<ProductDTO> notDataField) {
        this.notDataField = notDataField;

        return this;
    }

    public Set<ProductDTO> getWorkItemProducts() {
        return workItemProducts;
    }

    public SetOfRequiredResources setWorkItemProducts(Set<ProductDTO> workItemProducts) {
        this.workItemProducts = workItemProducts;

        return this;
    }


}
