package com.interest.calculator.api.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interest.calculator.api.interest.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthController.class)
class HealthControllerTest extends BaseControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void checkHealth() throws Exception {
        HealthCheck expectedHealthCheckResponse = HealthCheck.builder().name("name").description("desc").status(Status.OK).build();
        when(healthService.dbCheck()).thenReturn(expectedHealthCheckResponse);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/health")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        HealthCheck healthCheckResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), HealthCheck.class);
        assertThat(healthCheckResponse.getName()).isEqualTo(expectedHealthCheckResponse.getName());
        assertThat(healthCheckResponse.getDescription()).isEqualTo(expectedHealthCheckResponse.getDescription());
        assertThat(healthCheckResponse.getStatus()).isEqualTo(expectedHealthCheckResponse.getStatus());
    }
}