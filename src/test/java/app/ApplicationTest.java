package app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mmak on 14.07.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, WebSecurityConfig.class, MvcConfig.class})
@WebAppConfiguration
public class ApplicationTest {

    MockMvc mockMvc;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true)
                .apply(springSecurity()).build();
    }


    @Test
    public void shouldGetError403() throws Exception{
        mockMvc.perform(post("/login"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser("user")
    public void shouldPassToResources() throws Exception {
        mockMvc.perform(get("/resources"))
                .andExpect(status().isOk())
                .andExpect(content().string("SampleResources"));
    }

    @Test
    public void shouldNotPassToResources() throws Exception {
        mockMvc.perform(get("/resources"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("SampleResources"));
    }

}