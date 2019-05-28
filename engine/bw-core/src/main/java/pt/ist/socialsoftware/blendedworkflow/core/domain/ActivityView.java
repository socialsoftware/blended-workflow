package pt.ist.socialsoftware.blendedworkflow.core.domain;

public class ActivityView extends ActivityView_Base {
    public ActivityView(Activity activity, Position position) {
        setActivity(activity);
        setPosition(position);
    }

    public void delete() {
        setActivity(null);

        super.delete();
    }
}
