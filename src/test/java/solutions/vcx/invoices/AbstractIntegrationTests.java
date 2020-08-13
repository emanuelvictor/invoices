package solutions.vcx.invoices;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import solutions.vcx.invoices.Application;
import solutions.vcx.invoices.TestApplication;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, Application.class})
public abstract class AbstractIntegrationTests {

    /**
     *
     */
    protected MockMvc mvc;

    /**
     *
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * Configure the web application to receive the rest requests
     */
    protected void setUp() {
        mvc = webAppContextSetup(webApplicationContext).build();
    }

}
