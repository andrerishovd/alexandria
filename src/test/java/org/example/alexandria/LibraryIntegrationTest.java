package org.example.alexandria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.alexandria.modules.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.class) // This is optional, adds the @Order()
@ActiveProfiles("test")
public class LibraryIntegrationTest {

    // For jococo testing add <plugin> to pom.xml, all test files must end in "...Test"
    // Use command ???

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    void shouldReturnListOfBooks() throws Exception {
        this.mockMvc.perform(get("/api/books/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(10))); //A reference to the size of the document?
    }

    @Test
    @Order(2)
    void shouldReturnListOfAvailableBooks() throws Exception {
        this.mockMvc.perform(get("/api/books/available"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    void shouldReturnBookById() throws Exception {
        this.mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookId").value(1));
    }

    @Test
    @Order(4)
    void shouldCreateNewBook() throws Exception {
        Book testBook = new Book(
                101,
                "TestingBook",
                new Author(
                    "John Testing",
                    100
                ),
                "Testing Publishing",
                new Status(true),
                new Location('a',1)
        );

        this.mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(5)
    void ShouldEditExistingBook() throws Exception {
        BookUpdate bookUpdate = new BookUpdate(1, "title", "Test Title");

        this.mockMvc.perform(patch("/api/books/patch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookUpdate)))
                .andExpect(jsonPath("$.title").value(bookUpdate.getUpdate()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void ShouldDeleteExistingBook() throws Exception {

        this.mockMvc.perform(delete("/api/books/delete/2"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    void ShouldNotDeleteNonExistingBook() throws Exception {

        this.mockMvc.perform(delete("/api/books/delete/3"))
                .andExpect(status().isOk());
        this.mockMvc.perform(delete("/api/books/delete/3"))
                .andExpect(status().isNotFound());
    }
}
