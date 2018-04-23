package pt.ist.socialsoftware.blendedworkflow.resources.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractSpecTest1Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorizationTest extends AbstractSpecTest1Test {
    private Logger logger = LoggerFactory.getLogger(AuthorizationTest.class);

    @Test
    public void testUserCanAccessHome() throws Exception {
        String token = extractToken(login("Guilherme", "Guilherme").andReturn());

        mockMvc.perform(get("/").header(HEADER_STRING, constructToken(token))).andExpect(status().isOk());
    }

    @Test
    public void testNotLoggedInCannotAccessHome() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isUnauthorized());
    }

}
