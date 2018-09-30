package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pt.ist.socialsoftware.blendedworkflow.resources.security.BlendedUserDetails;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.UserDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.req.JwtAuthenticationResponseDto;
import pt.ist.socialsoftware.blendedworkflow.resources.utils.ResourcesFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;

import static java.util.stream.Collectors.joining;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.EXPIRATION_TIME;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.SECRET;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private static Logger log = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private ResourcesFactory factory;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<UserDto[]> listUsers() {

        DesignResourcesInterface designer = this.factory.createDesignInterface();

        UserDto[] users = designer.getUsers().stream()
                .toArray(size -> new UserDto[size]);

        log.debug("listUsers:" + Arrays.stream(users).map(userDto -> userDto.print()).collect(joining("\n")));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = Jwts.builder()
                .setSubject(loginRequest.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        return ResponseEntity.ok(new JwtAuthenticationResponseDto(token));
    }

}
