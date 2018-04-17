package pt.ist.socialsoftware.blendedworkflow.resources.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.resources.security.BlendedUserDetails;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

public class User extends User_Base {
    private static Logger logger = LoggerFactory.getLogger(User.class);

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    public User(BlendedWorkflow blendedWorkflow, String username, String password, Person person) {
        setEnabled(false);
        setActive(true);
        setBlendedworkflow(blendedWorkflow);
        setUsername(username);
        setPassword(passwordEncoder.encode(password));
    }

    public static User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            BlendedUserDetails userDetails = null;
            Object principal = authentication.getPrincipal();
            if (principal instanceof BlendedUserDetails) {
                userDetails = (BlendedUserDetails) principal;
                return userDetails.getUser();
            }
        }
        return null;
    }

    public void remove() {
        setBlendedworkflow(null);
        deleteDomainObject();
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