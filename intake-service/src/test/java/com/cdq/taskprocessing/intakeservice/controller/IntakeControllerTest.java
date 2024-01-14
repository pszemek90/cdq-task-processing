package com.cdq.taskprocessing.intakeservice.controller;

import com.cdq.taskprocessing.database.dao.TaskDao;
import com.cdq.taskprocessing.intakeservice.model.CreateTaskRequest;
import com.cdq.taskprocessing.intakeservice.model.Error;
import com.cdq.taskprocessing.intakeservice.model.ErrorResponse;
import com.cdq.taskprocessing.intakeservice.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBeans({@MockBean(TaskDao.class)})
@WebMvcTest(IntakeController.class)
@EmbeddedKafka // to mock kafka dependencies
class IntakeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskService taskService;

    @Test
    void shouldReturnCreatedAndNonNullBody_WhenValidRequestMade() throws Exception {

        CreateTaskRequest request = new CreateTaskRequest("ABCABC", "ABC");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/tasks/create")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    void shouldReturnOkResponse_WhenGetAllTasksCalled() throws Exception{
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequest_WhenInvalidRequestMade() throws Exception {
        CreateTaskRequest request = new CreateTaskRequest(null, "testPattern");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/tasks/create")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnErrorResponse_WhenGenericExceptionIsThrown() throws Exception{
        when(taskService.createTask(any(), any())).thenThrow(new RuntimeException("Test exception"));
        //when
        CreateTaskRequest request = new CreateTaskRequest("ABCABC", "ABC");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(post("/tasks/create")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse = mapper.readValue(response, ErrorResponse.class);
        Error error = errorResponse.errors().get(0);
        assertNull(error.field());
        assertNotNull(error.message());
    }
}