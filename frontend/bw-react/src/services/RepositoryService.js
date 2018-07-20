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

}