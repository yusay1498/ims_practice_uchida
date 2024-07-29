package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

//    @Autowired
//    WebApplicationContext context;
//
//    @BeforeEach
//    void initMockMvc() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    @Test
    void testGetDemo() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Alice"))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(7)));

    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7
            """)
    void testGetDemo(
            String name, int age
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((name))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(age)));

    }

    @Test
    void testGetDemoWithParam() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/withParam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .queryParam("name", "Bob")
                        .queryParam("age", "10")
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Bob"))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(10)));

    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 |Alice | 7
            """)
    void testGetDemoWithParam(
            String name, int age,
            String expectName, int expectAge
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/withParam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .queryParam("name", name)
                        .queryParam("age", String.format("%s", age))
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));
    }

    @Test
    void testGetDemoWithPathVar() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/withPathVar/Carol/12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Carol"))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(12)));

    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 |Alice | 7
            """)
    void testGetDemoWithPathVar(
            String name, int age,
            String expectName, int expectAge
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/withPathVar/" + name + "/" + age)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));

    }

    @Test
    void testPostDemo() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Alice"))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(7)));

    }

    @Test
    void testPostDemoWithBody() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/withBody")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsString(Map.ofEntries(
                                        Map.entry("name", "Alice"),
                                        Map.entry("age", 7)
                                ))
//                                "{ \"name\": \"Alice\", \"age\": 7  }"
                        )
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Alice"))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(7)));

    }

    @DisplayName("test")
    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 | Alice | 7
            """)
    void testPostDemoWithBody(
            String name, int age,
            String expectName, int expectAge
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                                .post("/withBody")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(
                                        objectMapper.writeValueAsString(Map.ofEntries(
                                                Map.entry("name", name),
                                                Map.entry("age", age)
                                        ))
                                )
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));

    }

    @Test
    void testPutDemo() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/Bob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsString(Map.ofEntries(
                                        Map.entry("name", "Alice"),
                                        Map.entry("age", 7)
                                ))
                        )
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Alice"))))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(7)));

    }

    @Test
    void testDeleteDemo() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/Alice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());

    }
}
