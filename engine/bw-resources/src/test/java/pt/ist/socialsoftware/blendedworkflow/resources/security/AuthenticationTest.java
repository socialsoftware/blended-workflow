package pt.ist.socialsoftware.blendedworkflow.resources.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractDocExampleTest;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractMvcTest;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractSpecTest1Test;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthenticationTest extends AbstractDocExampleTest {
    private Logger logger = LoggerFactory.getLogger(AuthenticationTest.class);

    @Test
    public void testLogin() throws Exception {
        MvcResult request = login("John", "John")
                .andExpect(status().isOk()).andReturn();

        assertNotNull(request.getResponse().getHeader(HEADER_STRING));
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        login("John", "fail")
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testLoginWrongUsername() throws Exception {
        login("fail", "fail")
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testLoginWithNulls() throws Exception {
        login(null, null)
                .andExpect(status().isUnauthorized());

    }
}
