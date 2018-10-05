package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ActivityWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDto;

import java.util.Set;

public class DashboardDto {
    private Set<ActivityWorkItemDto> activityWorkItems;
    private Set<GoalWorkItemDto> goalWorkItems;

    public DashboardDto() {
    }

    public DashboardDto(Set<ActivityWorkItemDto> activityWorkItems, Set<GoalWorkItemDto> goalWorkItems) {
        this.activityWorkItems = activityWorkItems;
        this.goalWorkItems = goalWorkItems;
    }

    public Set<ActivityWorkItemDto> getActivityWorkItems() {
        return activityWorkItems;
    }

    public void setActivityWorkItems(Set<ActivityWorkItemDto> activityWorkItems) {
        this.activityWorkItems = activityWorkItems;
    }

    public Set<GoalWorkItemDto> getGoalWorkItems() {
        return goalWorkItems;
    }

    public void setGoalWorkItems(Set<GoalWorkItemDto> goalWorkItems) {
        this.goalWorkItems = goalWorkItems;
    }
}
