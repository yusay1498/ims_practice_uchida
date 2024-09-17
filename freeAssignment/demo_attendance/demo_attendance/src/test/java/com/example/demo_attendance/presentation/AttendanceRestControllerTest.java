package com.example.demo_attendance.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AttendanceRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllAttendance() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/attendances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                );
    }

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-4f7d-8b95-5a5d6d881bb5', 'emp101', '2024-08-30 09:00:00', '2024-08-30 17:00:00');
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            a5e5e9d4-40d7-4f7d-8b95-5a5d6d881bb5 | a5e5e9d4-40d7-4f7d-8b95-5a5d6d881bb5
            """)
    void testGetAttendanceById(
            String id,
            String expectId
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/attendances/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.id", Matchers.is((expectId)))
                );
    }

    @Test
    void testGetAttendanceById_withNonexistentId() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/attendances/{id}", "nonexistent-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-4f7d-8b95-5a5d6d881b75', 'emp102', '2024-08-30 09:00:00', '2024-08-30 17:00:00');
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            emp102 | 2024-08-30 | emp102 | 2024-08-30
            """)
    void testGetAttendanceByEmployeeIdAndDate(
            String employeeId, String date,
            String expectEmployeeId, String expectDate
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/attendances/searchAttendance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .queryParam("employeeId", employeeId)
                        .queryParam("date", date)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.employeeId", Matchers.is((expectEmployeeId)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.date", Matchers.is(expectDate)));

    }

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-4f7d-1111-5a5d6d881b75', 'emp111', '2024-08-30 09:00:00', '2024-08-30 17:00:00');
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            emp111 | emp111 | 2024-08 | 8.0
            """)
    void testGetMonthlyAttendanceSummary(
            String employeeId,
            String expectEmployeeId,
            String expectYearMonth,
            double expectTotalHours
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/attendances/monthlySummary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .queryParam("employeeId", employeeId)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].employeeId", Matchers.is((expectEmployeeId)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].yearMonth", Matchers.is((expectYearMonth)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].totalHours", Matchers.is((expectTotalHours)))
                );
    }

    @Test
    void testGetMonthlyAttendanceSummary_withEmptyData() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/attendances/monthlySummary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .queryParam("employeeId", "nonexistent-employee-id")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }


    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            emp103 | 2024-08-30T09:00:00 | 2024-08-30T17:00:00 | emp103 | 2024-08-30T09:00:00 | 2024-08-30T17:00:00
            """)
    void testPostAttendance(
            String employeeId, String beginWork, String finishWork
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/attendances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsString(Map.ofEntries(
                                        Map.entry("employeeId", employeeId),
                                        Map.entry("beginWork", beginWork),
                                        Map.entry("finishWork", finishWork)
                                ))
                        )
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated()
                );
        // postのレスポンスにはAttendanceを返すように設定していないのでJsonPathが反応しなかった
        // そのためisCreatedで正しく作成されたかだけ確認
    }

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('c5e5e9d4-40d7-4f7d-8b95-5a5d6d881b75', 'emp104', '2024-08-30 09:00:00', null);
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            c5e5e9d4-40d7-4f7d-8b95-5a5d6d881b75 | 2024-08-30T17:00:00 | c5e5e9d4-40d7-4f7d-8b95-5a5d6d881b75
            """)
    void testPutTest(
            String id, String finishWork, String pathId
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/attendances/" + pathId )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsBytes(Map.ofEntries(
                                        Map.entry("id", id),
                                        Map.entry("finishWork", finishWork)
                                ))
                        )
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                );
        // POSTのとき同様ResponseEntityでステータスしか返していないのでJsonPathは通らない
    }

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('c5e5e9d4-40d7-4f7d-8b95-5a6d6d881b75', 'emp105', '2124-08-30 09:00:00', null);
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            c5e5e9d4-40d7-4f7d-8b95-5a6d6d881b75
            """)
    void testDeleteTest(
            String pathName
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/attendances/" + pathName )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }
}
