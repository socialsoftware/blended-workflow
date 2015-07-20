package pt.ist.socialsoftware.blendedworkflow.domain;

public class User extends User_Base {

    public User(String name, String ID, String password, String notes) {
        setOrganizationalModel(
                BlendedWorkflow.getInstance().getOrganizationalModel());
        setName(name);
        setID(ID);
        setPassword(password);
        setNotes(notes);
    }

}
