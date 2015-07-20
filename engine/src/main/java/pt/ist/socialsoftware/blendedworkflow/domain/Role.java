package pt.ist.socialsoftware.blendedworkflow.domain;

public class Role extends Role_Base {

    public Role(String name, String description, Boolean bwManagerAcess,
            Boolean organizationalManagerAcess, Boolean worklistManagerAcess) {
        setOrganizationalModel(
                BlendedWorkflow.getInstance().getOrganizationalModel());
        setName(name);
        setDescription(description);
        setBwManagerAcess(bwManagerAcess);
        setOrganizationalManagerAcess(organizationalManagerAcess);
        setWorklistManagerAcess(worklistManagerAcess);
    }
}
