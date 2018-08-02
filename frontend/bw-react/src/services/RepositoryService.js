import axios from 'axios';

export class RepositoryService {
    constructor() {
        this.axios = axios.create({
            baseURL: 'http://localhost:8080',
            timeout: 1000,
            headers: {
                'X-Custom-Header': 'BlendedWorkflow'
            }
        });
    }

    // Specifications
    getSpecifications() {
        return this.axios.get("/specs");
    }

    getSpecification(specId) {
        return this.axios.get("/specs/" + specId);
    }

    // Data Model
    getDataModel(specId) {
        return this.axios.get("/specs/" + specId + "/datamodel");
    }

    getEntities(specId) {
        return this.axios.get("/specs/" + specId + "/datamodel/entities");
    }

    getAttributes(specId) {
        return this.axios.get("/specs/" + specId + "/datamodel/attributes");
    }
    getDependencies(specId) {
        return this.axios.get("/specs/" + specId + "/datamodel/dependencies");
    }

    getRules(specId) {
        return this.axios.get("/specs/" + specId + "/datamodel/rules");
    }

    getRelations(specId) {
        return this.axios.get("/specs/" + specId + "/datamodel/relations");
    }

    // Data model instance

    getMandatoryEntityInstance(specId, name) {
        return this.axios.get("/specs/" + specId + "/instances/" + name + "/entities/mandatory")
    }

    getEntityInstanceByExternalId(externalId) {
        return this.axios.get("/entityinstance/" + externalId)
    }

    getEntityInstancesForDependence(entityInstanceExtId, dependenceExtId) {
        return this.axios.get("/entityinstance/" + entityInstanceExtId + "/dependence/"+ dependenceExtId)
    }

    // Instances
    getWorkflowInstances(specId) {
        return this.axios.get("/specs/" + specId + "/instances")
    }

    getWorkflowInstance(specId, name) {
        return this.axios.get("specs/" + specId + "/instances/" + name)
    }

    createWorkflowInstance(specId, name) {
        return this.axios.post("/specs/" + specId + "/instances", {
            "specId": specId,
            "name": name
        });
    }

    deleteWorkflowInstance(specId, name) {
        return this.axios.delete("/specs/" + specId + "/instances/" + name);
    }

    // Activity workitems
    getNextActivityWorkItems(specId, instance) {
        return this.axios.get("/specs/" + specId + "/instances/" + instance + "/activityworkitem/next");
    }

    // Goal workitems
    getNextGoalWorkItems(specId, instance) {
        return this.axios.get("/specs/" + specId + "/instances/" + instance + "/goalworkitem/next");
    }

    // getLogGoalWorkItems : function(spec,instance) {
    // 	var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem/log";
    // 	return $http.get(url);
    // },

    executeWorkItem(spec, instance, workItemName, workItem) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/goalworkitem", {
    		"specId" :  spec,
    		"workflowInstanceName" : instance,
    		"name" : workItemName,
    		"definitionGroupSet" : workItem.definitionGroupSet
        });
    }


}