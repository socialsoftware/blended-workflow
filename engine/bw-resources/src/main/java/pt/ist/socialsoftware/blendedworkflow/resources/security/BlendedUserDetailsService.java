package pt.ist.socialsoftware.blendedworkflow.resources.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;

@Service
public class BlendedUserDetailsService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(BlendedUserDetailsService.class);

    @Override
    @Atomic(mode = TxMode.READ)
    public BlendedUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("loadUserByUsername username:{}", username);

        BlendedUserDetails matchingUser = null;

        BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();

        for (User user : blendedWorkflow.getUsersSet()) {

            if (user.getEnabled() && user.getActive() && user.getUsername().equals(username)) {

                matchingUser = new BlendedUserDetails(user, user.getUsername(), user.getPassword());

                return matchingUser;
            }
        }

        if (matchingUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return matchingUser;
    }
}
