package solutions.vcx.invoices.domain.resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import solutions.vcx.invoices.domain.AbstractIntegrationTests;

import static org.junit.Assert.assertEquals;


public class CompanyControllerTest extends AbstractIntegrationTests {

    /**
     * Configure the web application to receive the rest requests
     */
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllCompanies() throws Exception {
        String uri = "/companies";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Product[] productlist = super.mapFromJson(content, Product[].class);
//        assertTrue(productlist.length > 0);
    }
}
