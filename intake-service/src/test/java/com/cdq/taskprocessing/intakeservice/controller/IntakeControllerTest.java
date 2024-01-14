package com.cdq.taskprocessing.intakeservice.controller;

import com.cdq.taskprocessing.database.dao.TaskDao;
import com.cdq.taskprocessing.intakeservice.model.CreateTaskRequest;
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

import static org.junit.jupiter.api.Assertions.fail;
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

    @Test
    void shouldReturnCreatedAndNonNullBody_WhenValidRequestMade() throws Exception {

        CreateTaskRequest request = new CreateTaskRequest("testInput", "testPattern");
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
}