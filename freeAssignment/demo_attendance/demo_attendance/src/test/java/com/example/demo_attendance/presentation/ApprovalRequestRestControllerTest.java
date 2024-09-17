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
public class ApprovalRequestRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetApprovalRequests() throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/approvalRequests")
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
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-9999-1e2f3g4h5i6j', 'emp500', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            1a2b3c4d-5e6f-7a8b-9999-1e2f3g4h5i6j | 1a2b3c4d-5e6f-7a8b-9999-1e2f3g4h5i6j
            """)
    void testGetApprovalRequestById(
            String id,
            String expectId
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/approvalRequests/{id}", id)
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

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('2a2b3c4d-5e6f-7a8b-9999-1e2f3g4h5i6j', 'emp501', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            emp501 | 2024-05 | emp501 | 2024-05
            """)
    void testGetApprovalRequestByEmployeeIdAndYearMonth(
            String employeeId, String yearMonth,
            String expectEmployeeId, String expectYearMonth
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/approvalRequests/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .queryParam("employeeId", employeeId)
                        .queryParam("yearMonth", yearMonth)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.employeeId", Matchers.is((expectEmployeeId)))
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.yearMonth", Matchers.is(expectYearMonth)));
    }

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-1111-1111-5a5d6d881b75', 'emp502', '2024-08-30 09:00:00', '2024-08-30 17:00:00');
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            emp502 | 2024-08
            """)
    void testPostApprovalRequest(
            String employeeId, String yearMonth
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/approvalRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsString(Map.ofEntries(
                                        Map.entry("employeeId", employeeId),
                                        Map.entry("yearMonth", yearMonth)
                                ))
                        )
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated()
                );
    }

    @ParameterizedTest
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('2a2b3c4d-5e6f-9999-9999-1e2f3g4h5i6j', 'emp503', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            2a2b3c4d-5e6f-9999-9999-1e2f3g4h5i6j | APPROVED | emp000 | 2a2b3c4d-5e6f-9999-9999-1e2f3g4h5i6j
            """)
    void testPutApprovedTest(
            String id, String status, String approvedBy, String pathId
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/approvalRequests/" + pathId )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsBytes(Map.ofEntries(
                                        Map.entry("id", id),
                                        Map.entry("status", status),
                                        Map.entry("approvedBy", approvedBy)
                                ))
                        )
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
            VALUES ('a5e5e9d4-40d1-1111-1111-5a5d6d881b75', 'emp504', '2024-05-30 09:00:00', '2024-05-30 17:00:00');

            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('2a2b3c4d-9999-9999-9999-1e2f3g4h5i6j', 'emp504', '2024-05-01', 9000.0, 'REJECTED', '2024-05-31 08:00:00', '2024-05-31 18:00:00', 'emp000');
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            2a2b3c4d-9999-9999-9999-1e2f3g4h5i6j | 2024-05 | 2a2b3c4d-9999-9999-9999-1e2f3g4h5i6j
            """)
    void testPutRejectedTest(
            String id, String yearMonth, String pathId
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/approvalRequests/" + pathId )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(
                                objectMapper.writeValueAsBytes(Map.ofEntries(
                                        Map.entry("id", id),
                                        Map.entry("yearMonth", yearMonth)
                                ))
                        )
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
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('2a2b3c4d-9999-9989-9999-1e2f3g4h5i6j', 'emp505', '2024-05-01', 9000.0, 'REJECTED', '2024-05-31 08:00:00', '2024-05-31 18:00:00', 'emp000');
            """
    })
    @CsvSource(delimiterString = "|", textBlock = """
            2a2b3c4d-9999-9989-9999-1e2f3g4h5i6j
            """)
    void testDeleteTest(
            String pathName
    ) throws Throwable {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/approvalRequests/" + pathName )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(MockMvcResultHandlers
                        .print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }
}
