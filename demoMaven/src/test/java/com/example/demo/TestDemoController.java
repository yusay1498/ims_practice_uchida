package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class TestDemoController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetDemo() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Alice")))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(7)));

    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 | Alice | 7
            """)
    void testWithParamGetTest(
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
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));

    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 | Alice | 7
            """)
    void testWithPathGetTest(
            String name, int age,
            String expectName, int expectAge
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/withPath/" + name + "/" + age)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));
    }

    @Test
    void testPostDemo() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(("Alice")))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(7)));

    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 | Alice | 7
            """)
    void testWithBodyPostTest(
            String name, int age,
            String expectName, int expectAge
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/withBody")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsBytes(Map.ofEntries(
                                        Map.entry("name", name),
                                        Map.entry("age", age)
                                ))
                        )
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice | 7 | Bob | Alice | 7
            """)
    void testPutTest(
            String name, int age, String pathName,
            String expectName, int expectAge
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/" + pathName )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsBytes(Map.ofEntries(
                                        Map.entry("name", name),
                                        Map.entry("age", age)
                                ))
                        )
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is((expectName)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.age", Matchers.is(expectAge)));
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Alice
            """)
    void testDeleteTest(
            String pathName
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/" + pathName )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
}
