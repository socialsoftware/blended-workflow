package pt.ist.socialsoftware.blendedworkflow.core.domain;

public class GoalView extends GoalView_Base {
    public GoalView(Goal goal, Position position) {
        setGoal(goal);
        setPosition(position);
    }

    public void delete() {
        setGoal(null);

        super.delete();
    }
}
