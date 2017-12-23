package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class SwaggerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void swaggerSpec() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v2/api-docs").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", startsWith("application/json")));
    }

    @Test
    public void swaggerUi() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/swagger-ui.html").accept("text/html"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", startsWith("text/html")));
    }

}