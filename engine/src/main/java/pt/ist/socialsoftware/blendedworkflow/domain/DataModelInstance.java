package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;

public class DataModelInstance extends DataModelInstance_Base {

    // Search GlobalVariables
    private EntityInstance foundEntityInstance = null;
    private ArrayList<EntityInstance> foundEntityInstancePath = new ArrayList<EntityInstance>();
    private final ArrayList<EntityInstance> visitedEntityInstanceNodes = new ArrayList<EntityInstance>();

    private Entity foundEntity = null;
    private ArrayList<Entity> foundEntityPath = new ArrayList<Entity>();
    private ArrayList<RelationBW> foundRelationPath = new ArrayList<RelationBW>();
    private final ArrayList<Entity> visitedEntityNodes = new ArrayList<Entity>();

    /**********************
     * Getters and Setters
     **********************/
    public EntityInstance getFoundEntityInstance() {
        return this.foundEntityInstance;
    }

    public ArrayList<EntityInstance> getFoundEntityInstancePath() {
        return this.foundEntityInstancePath;
    }

    public ArrayList<EntityInstance> getVisitedEntityInstanceNodes() {
        return this.visitedEntityInstanceNodes;
    }

    public void clearSearchEntityInstanceVariables() {
        this.foundEntityInstance = null;
        this.foundEntityInstancePath.clear();
        this.visitedEntityInstanceNodes.clear();
    }

    public Entity getFoundEntity() {
        return foundEntity;
    }

    public ArrayList<Entity> getFoundEntityPath() {
        return foundEntityPath;
    }

    public ArrayList<RelationBW> getFoundRelationPath() {
        return foundRelationPath;
    }

    public ArrayList<Entity> getVisitedEntityNodes() {
        return visitedEntityNodes;
    }

    public void clearSearchEntityVariables() {
        this.foundEntity = null;
        this.foundEntityPath.clear();
        this.visitedEntityNodes.clear();
    }

    /********************
     * Search Algorithms
     ********************/
    public void searchEntity(Entity initNode, Entity targetNode, RelationBW edge,
            ArrayList<Entity> currentEntityPath,
            ArrayList<RelationBW> currentRelationPath) {
        visitedEntityNodes.add(initNode);

        if (foundEntityInstance == null) {
            currentEntityPath.add(initNode);
            if (edge != null) {
                currentRelationPath.add(edge);
            }
        }

        if (initNode.equals(targetNode)) {
            foundEntity = initNode;
            this.foundEntityPath = currentEntityPath;
            this.foundRelationPath = currentRelationPath;
        } else {
            for (RelationBW relation : initNode.getRelationsSet()) {
                Entity one = relation.getEntityOne();
                Entity two = relation.getEntityTwo();

                Entity newInitNode = null;
                if (initNode.equals(one)) {
                    newInitNode = two;
                } else {
                    newInitNode = one;
                }

                if (!visitedEntityNodes.contains(newInitNode)) {
                    searchEntity(newInitNode, targetNode, relation,
                            currentEntityPath, currentRelationPath);
                }
            }

            if (foundEntity == null) {
                currentEntityPath.subList(currentEntityPath.size() - 1,
                        currentEntityPath.size()).clear();
                if (!currentRelationPath.isEmpty()) {
                    currentRelationPath.subList(currentRelationPath.size() - 1,
                            currentRelationPath.size()).clear();
                }
            }
        }
    }

    public void searchEntityInstance(EntityInstance initNode,
            EntityInstance targetNode, ArrayList<EntityInstance> currentPath) {
        visitedEntityInstanceNodes.add(initNode);

        if (foundEntityInstance == null) {
            currentPath.add(initNode);
        }

        if (initNode.equals(targetNode)) {
            foundEntityInstance = initNode;
            this.foundEntityInstancePath = currentPath;

        } else {
            for (RelationInstance relationInstance : initNode
                    .getEntityInstanceOneRelationInstancesSet()) {
                EntityInstance two = relationInstance.getEntityInstanceTwo();
                if (!visitedEntityInstanceNodes.contains(two)) {
                    searchEntityInstance(two, targetNode, currentPath);
                }
            }

            for (RelationInstance relationInstance : initNode
                    .getEntityInstanceTwoRelationInstancesSet()) {
                EntityInstance one = relationInstance.getEntityInstanceOne();
                if (!visitedEntityInstanceNodes.contains(one)) {
                    searchEntityInstance(one, targetNode, currentPath);
                }
            }

            if (foundEntityInstance == null) {
                currentPath.subList(currentPath.size() - 1, currentPath.size())
                        .clear();
            }
        }
    }

    public EntityInstance getEntityInstance(EntityInstance startEntityInstance,
            EntityInstance targetEntityInstance) {
        searchEntityInstance(startEntityInstance, targetEntityInstance,
                new ArrayList<EntityInstance>());
        EntityInstance result = getFoundEntityInstance();
        clearSearchEntityInstanceVariables();
        return result;
    }

    public Entity getEntity(Entity startEntity, Entity targetEntity) {
        searchEntity(startEntity, targetEntity, null, new ArrayList<Entity>(),
                new ArrayList<RelationBW>());
        Entity result = getFoundEntity();
        clearSearchEntityVariables();
        return result;
    }

    public ArrayList<RelationBW> getRelations(Entity startEntity,
            Entity targetEntity) {
        searchEntity(startEntity, targetEntity, null, new ArrayList<Entity>(),
                new ArrayList<RelationBW>());
        ArrayList<RelationBW> result = getFoundRelationPath();
        clearSearchEntityVariables();
        return result;
    }

    // FIXME: Merge with RelationInstance construtor?
    public void createRelationInstance(BWInstance bwInstance, EntityInstance e1,
            EntityInstance e2) {
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

        Entity entity1 = e1.getEntity();
        Entity entity2 = e2.getEntity();
        boolean exists = false;

        // Check if relation instance already exists
        for (RelationBW relation : dataModelInstance.getRelationsSet()) {
            for (RelationInstance relationInstance : relation
                    .getRelationInstancesSet()) {
                EntityInstance one = relationInstance.getEntityInstanceOne();
                EntityInstance two = relationInstance.getEntityInstanceTwo();
                if (one.equals(e1) && two.equals(e2)
                        || one.equals(e2) && two.equals(e1)) {
                    exists = true;
                    break;
                }
            }
        }

        // Create only if no previous RelationInstance exists
        if (!exists) {
            for (RelationBW relation : dataModelInstance.getRelationsSet()) {
                Entity one = relation.getEntityOne();
                Entity two = relation.getEntityTwo();
                if ((one.equals(entity1) && two.equals(entity2))) {
                    new RelationInstance(relation, e1, e2,
                            e1.getNewRelationInstanceID());
                } else if (one.equals(entity2) && two.equals(entity1)) {
                    new RelationInstance(relation, e2, e1,
                            e2.getNewRelationInstanceID());
                }
            }
        }
    }

}