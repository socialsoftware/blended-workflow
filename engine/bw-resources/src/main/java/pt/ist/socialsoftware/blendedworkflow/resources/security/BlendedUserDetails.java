package pt.ist.socialsoftware.blendedworkflow.resources.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class BlendedUserDetails implements UserDetails {
    private static Logger logger = LoggerFactory.getLogger(BlendedUserDetails.class);

    private static final long serialVersionUID = -6509897037222767090L;

    private final String password;
    private final String username;
    private final String userId;

    public BlendedUserDetails(User user, String username, String password) {
        this.username = username;
        this.password = password;
        this.userId = user.getExternalId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public User getUser() {
        // logger.debug("getUser userId:{}", userId);
        return (User) FenixFramework.getDomainObject(userId);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
