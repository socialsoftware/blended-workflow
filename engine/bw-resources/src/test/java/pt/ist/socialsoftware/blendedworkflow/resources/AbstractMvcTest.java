package pt.ist.socialsoftware.blendedworkflow.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.UserDto;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.TOKEN_PREFIX;

@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public abstract class AbstractMvcTest extends TeardownRollbackTest {
    protected MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void populate4Test() {
        mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    protected String json(Object o) throws IOException {
        return mapper.writeValueAsString(o);
    }

    protected ResultActions login(String username, String password) throws Exception {
        final UserDto auth = new UserDto();
        auth.setUsername(username);
        auth.setPassword(password);
        return mockMvc.perform(
                post("/login")
                        .content(json(auth))
                        .contentType(MediaType.APPLICATION_JSON));
    }

    protected String extractToken(MvcResult result) {
        return result.getResponse().getHeader(HEADER_STRING).split("Bearer ")[1];
    }

    protected String extractToken(String username, String password) throws Exception {
        return extractToken(login(username, password).andReturn());
    }

    protected String constructToken(String token) {
        return TOKEN_PREFIX + token;
    }

}