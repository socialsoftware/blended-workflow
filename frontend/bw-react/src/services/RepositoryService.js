import axios from 'axios';

export class RepositoryService {
    constructor(loggedUser) {
        var headers = {
            'X-Custom-Header': 'BlendedWorkflow',
        };
        if (loggedUser) {
            headers['Authorization'] = loggedUser.token;
        } 

        this.axios = axios.create({
            baseURL: 'http://localhost:8080',
            timeout: 5000,
            headers: headers,
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
        return this.axios.get("/specs/" + specId + "/instances/" + name + "/entities/mandatory");
    }

    getEntityInstances(specId, name) {
        return this.axios.get("/specs/" + specId + "/instances/" + name+ "/entities");
    }

    getEntityInstanceByExternalId(externalId) {
        return this.axios.get("/entityinstance/" + externalId)
    }

    getEntityInstancesForDependence(entityInstanceExtId, dependenceExtId) {
        return this.axios.get("/entityinstance/" + entityInstanceExtId + "/dependence/"+ dependenceExtId);
    }

    // Goal Model
    getGoalModel(specId) {
        return this.axios.get("/specs/" + specId + "/goalmodel/goals");
    }

    getGoalModelGraphVis(specId) {
        return this.axios.get("/specs/" + specId + "/goalmodel/graph/vis");
    }

    getGoalEntitySuccessConditions(specId, goalName) {
        return this.axios.get("/specs/" + specId + "/goalmodel/goals/" + goalName + "/sucent");
    }

    getGoalAttributeSuccessConditions(specId, goalName) {
        return this.axios.get("/specs/" + specId + "/goalmodel/goals/" + goalName + "/sucatt");
    }

    getGoalRelations(specId, goalName) {
        return this.axios.get("/specs/" + specId + "/goalmodel/goals/" + goalName + "/relations");
    }

    renameGoal(specId, goal, goalName) {
        return this.axios.post("/specs/" + specId + "/goalmodel/goals/" + goal + "/rename/" + goalName);
    }

    mergeGoals(specId, goalOne, goalTwo, newGoalName) {
        return this.axios.post("/specs/" + specId + "/goalmodel/goals/merge", 
            {
                "specId" : specId,
                "nameOne" : goalOne.name,
                "nameTwo" : goalTwo.name,
                "newName" : newGoalName
            });
    }

    splitGoal(specId, goal, sucConditions, relations, newGoalName) {
        return this.axios.post("/specs/" + specId + "/goalmodel/goals/extract", 
            {
                "specId" : specId,
                "sourceGoalName" : goal.name,
                "successConditions" : sucConditions,
                "relations" : relations,
                "newGoalName" : newGoalName
            });
    }

    storeView(specId, goals) {
        return this.axios.put( "/specs/" + specId + "/goalmodel/view", {
            "specId" : specId,
            "goals" : goals,
        } );
    }

    // Instances
    getWorkflowInstances(specId) {
        return this.axios.get("/specs/" + specId + "/instances");
    }

    getWorkflowInstance(specId, name) {
        return this.axios.get("specs/" + specId + "/instances/" + name);
    }

    getInitWorkItem(specId) {
        return this.axios.get("/specs/" + specId + "/instances/init");
    }

    createWorkflowInstance(specId, name, unitOfWork) {
        return this.axios.post("/specs/" + specId + "/instances", {
            "specId": specId,
            "workflowInstanceName": name,
            "unitOfWork": unitOfWork
        });
    }

    deleteWorkflowInstance(specId, name) {
        return this.axios.delete("/specs/" + specId + "/instances/" + name);
    }

    getWorkflowInstanceLog(specId, name) {
        return this.axios.get("/specs/" + specId + "/instances/" + name + "/log");
    }


    // Activity model
    getActivityModel( specId ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities" );
    }

    getActivityGraph( specId ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities/graph" );
    }

    getActivityModelGraphVis(specId) {
        return this.axios.get("/specs/" + specId + "/activitymodel/graph/vis");
    }

    getActivityPreConditions( specId, activityName ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities/" + activityName + "/pre" );
    }

    getActivitySeqConditions( specId, activityName ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities/" + activityName + "/seq" );
    }

    getActivityPostConditions( specId, activityName ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities/" + activityName + "/post" );
    }

    getActivityMulConditions( specId, activityName ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities/" + activityName + "/postmul" );
    }

    getActivityRuleConditions( specId, activityName ) {
        return this.axios.get( "specs/" + specId + "/activitymodel/activities/" + activityName + "/postrule" );
    }

    // Activity workitems
    getNextActivityWorkItems(specId, instance) {
        return this.axios.get("/specs/" + specId + "/instances/" + instance + "/activityworkitem/next");
    }

    executeActivityWorkItem(spec, instance, workItemName, workItem) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/activityworkitem", {
    		"specId" :  spec,
    		"workflowInstanceName" : instance,
    		"name" : workItemName,
            "unitOfWork": workItem.unitOfWork
        });
    }

    executeActivityWorkItemWithResources(spec, instance, workItemName, workItem, entityIsPerson) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/activityworkitem", {
    		"specId" :  spec,
    		"workflowInstanceName" : instance,
    		"name" : workItemName,
            "unitOfWork": workItem.unitOfWork,
            "entityIsPersonDTOSet": entityIsPerson,
        });
    }

    // Goal workitems
    getNextGoalWorkItems(specId, instance) {
        return this.axios.get("/specs/" + specId + "/instances/" + instance + "/goalworkitem/next");
    }

    // getLogGoalWorkItems : function(spec,instance) {
    // 	var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem/log";
    // 	return $http.get(url);
    // },

    executeGoalWorkItem(spec, instance, workItemName, workItem) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/goalworkitem", {
    		"specId" :  spec,
    		"workflowInstanceName" : instance,
    		"name" : workItemName,
            "unitOfWork": workItem.unitOfWork
        });
    }

    executeGoalWorkItemWithResources(spec, instance, workItemName, workItem, entityIsPerson) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/goalworkitem", {
    		"specId" :  spec,
    		"workflowInstanceName" : instance,
    		"name" : workItemName,
            "unitOfWork": workItem.unitOfWork,
            "entityIsPersonDTOSet": entityIsPerson,
        });
    }

    // Authentication
    getUsers() {
        return this.axios.get("/users/list");
    }

    loginUser(username) {
        return this.axios.post("/users/login", {
            username : username,
            password : username
        });
    }

    getDashboard() {
        return this.axios.get("/users/dashboard");
    }

    // Dependency Tree
    checkDependentAttributeInstances(spec, instance, dependencyTree) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/dependencytree/check", dependencyTree);
    }

    defineDependentAttributeInstances(spec, instance, dependencyTree) {
        return this.axios.post("/specs/" + spec + "/instances/"+ instance + "/dependencytree", dependencyTree);
    }

    getDependencyTree(spec, instance, attributeInstance, entityInstanceId) {
        return this.axios.post("/specs/" + spec + "/instances/" + instance + "/dependencytree/get", {
            "attributeInstance": attributeInstance,
            "entityInstanceId": entityInstanceId
        });
    }

    getDependentAttributeInstances(spec, instance, attributeInstance, entityInstanceId) {
        return this.axios.post("/specs/" + spec + "/instances/" + instance + "/dependencytree/get/dependentattributeinstances", {
            "attributeInstance": attributeInstance,
            "entityInstanceId": entityInstanceId
        });
    }

}