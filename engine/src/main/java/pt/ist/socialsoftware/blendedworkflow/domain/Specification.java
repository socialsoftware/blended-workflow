package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class Specification extends Specification_Base {

    @Override
    public void setName(String name) {
        checkSpecificationName(name);
        super.setName(name);
    }

    public Specification(String name, String author, String description,
            String version, String UID) throws BWException {
        setName(name);
        setAuthor(author);
        setDescription(description);
        setVersion(version);
        setUID(UID);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        setCreationDate(dateFormat.format(Calendar.getInstance().getTime()));

        setDataModel(new DataModel());
        setTaskModel(new TaskModel());
        setGoalModel(new GoalModel());
        setInstanceCounter(0);
        BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
        blendedWorkflow.addSpecification(this);
    }

    private void checkSpecificationName(String name) {
        if ((name == null) || name.equals(""))
            throw new BWException(
                    BlendedWorkflowError.INVALID_SPECIFICATION_NAME, name);

        checkUniqueSpecificationName(name);
    }

    private void checkUniqueSpecificationName(String name) throws BWException {
        BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
        boolean existsName = blendedWorkflow.getSpecificationSet().stream()
                .anyMatch(spec -> spec.getName().equals(name));
        if (existsName)
            throw new BWException(
                    BlendedWorkflowError.INVALID_SPECIFICATION_NAME, name);

    }

    public int generateInstanceId() {
        setInstanceCounter(getInstanceCounter() + 1);
        return getInstanceCounter();
    }

    public void delete() {
        getDataModel().delete();
        getTaskModel().delete();
        getGoalModel().delete();

        setBlendedWorkflow(null);
        deleteDomainObject();
    }

}