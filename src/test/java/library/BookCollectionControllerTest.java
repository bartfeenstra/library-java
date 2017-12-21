package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class BookCollectionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getBooksShouldNot() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").accept("application/xml"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void getBooksShouldReturnEmptyListIfNoBooksExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

}