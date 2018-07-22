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

    // Goal workitems
    getNextGoalWorkItems(specId, instance) {
        return this.axios.get("/specs/" + specId + "/instances/" + instance + "/goalworkitem/next");
    }

    // getLogGoalWorkItems : function(spec,instance) {
    // 	var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem/log";
    // 	return $http.get(url);
    // },
    // executeWorkItem : function(spec, instance,
    // 		workItemName, workItem) {
    // 	var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem";
    // 	return $http.post(url, {
    // 		"specId" :  spec,
    // 		"workflowInstanceName" : instance,
    // 		"name" : workItemName,
    // 		"definitionGroupSet" : workItem.definitionGroupSet
    // 	});
    // }


}