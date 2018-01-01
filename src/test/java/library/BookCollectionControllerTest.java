package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public final class BookCollectionControllerTest {

    private String isbn = "978-0-356-50429-2";
    private String title = "Babylon's Ashes";
    private String author = "james S.A. Corey";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getBooksShouldErrorNotAcceptable() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").accept("application/xml"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void getBooksShouldReturnEmptyListIfNoBooksExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void postBooksShouldErrorUnsupportedMediaType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/xml")
                .accept("text/plain"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void postBooksShouldErrorNotAcceptable() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/json")
                .accept("application/xml"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void postBooksShouldErrorBadRequestOnNonObject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/json")
                .content("[]")
                .accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postBooksShouldErrorBadRequestOnEmptyObject() throws Exception {
        // @todo Finish this after the @todo on the Book class is fixed.
        return;
//        mvc.perform(MockMvcRequestBuilders.post("/books")
//                .contentType("application/json")
//                .content("{}")
//                .accept("text/plain"))
//            .andExpect(status().isBadRequest());
    }

    @Test
    public void postBooksShouldStoreANewBook() throws Exception {
        String expected = String.format("{\"isbn\": \"%s\", \"title\": \"%s\", \"author\": \"%s\"}", isbn, title, author);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/json")
                .content(expected)
                .accept("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void booksCanBePostedAndRetrieved() throws Exception {
        String expected = String.format("{\"isbn\": \"%s\", \"title\": \"%s\", \"author\": \"%s\"}", isbn, title, author);
        mvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/json")
                .content(expected))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/books").accept("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(String.format("[%s]", expected), result.getResponse().getContentAsString(), false);
    }

}