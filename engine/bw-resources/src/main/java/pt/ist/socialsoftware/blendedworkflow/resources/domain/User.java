package pt.ist.socialsoftware.blendedworkflow.resources.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.resources.ResourcesApplication;
import pt.ist.socialsoftware.blendedworkflow.resources.security.BlendedUserDetails;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.security.Principal;
import java.util.Optional;

public class User extends User_Base {
    private static Logger logger = LoggerFactory.getLogger(User.class);

    private static PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);

    public User(BlendedWorkflow blendedWorkflow, String username, String password, Person person) {
        getWorkItemSet().stream().forEach(workItem -> removeWorkItem(workItem));
        setEnabled(true);
        setActive(true);
        setBlendedworkflow(blendedWorkflow);
        setUsername(username);
        addPerson(person);
        setPassword(bCryptPasswordEncoder.encode(password));
    }

    public static Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Optional<User> user = getUserByUsername(authentication.getName());

            return user;
        }
        return Optional.empty();
    }

    public static Optional<User> getUserByUsername(String username) {
        return BlendedWorkflow.getInstance().getUsersSet().stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public Person getPerson(Specification spec) {
        return getPersonSet().stream()
                .filter(person -> person.getResourceModel().getSpec().equals(spec))
                .findFirst()
                .orElseThrow(() -> new RMException(RMErrorType.USER_DOES_NOT_HAVE_PERSON_IN_SPEC));
    }

    public void delete() {
        getPersonSet().stream().forEach(person -> removePerson(person));
        setBlendedworkflow(null);
        deleteDomainObject();
    }

    public void cleanPerson(Person person) {
        removePerson(person);
        if (getPersonSet().size() == 0) {
            this.delete();
        }
    }

    @Override
    public void setUsername(String username) {
        checkUniqueUsername(username);
        super.setUsername(username);
    }

    private void checkUniqueUsername(String username) {
        if (getBlendedworkflow().getUsersSet().stream().filter(u -> u.getUsername() != null && u.getUsername().equals(username))
                .findFirst().isPresent()) {
            throw new RMException(RMErrorType.INVALID_USER_NAME);
        }

    }

    /*@Atomic(mode = TxMode.WRITE)
    public RegistrationToken createRegistrationToken(String token) {
        return new RegistrationToken(token, this);
    }

    public void enableUnconfirmedUser() {
        setEnabled(true);
        if (getToken() != null) {
            getToken().remove();
        }
    }*/

    public void switchActive() {
        if (getActive()) {
            setActive(false);
        } else {
            setActive(true);
        }
    }
}