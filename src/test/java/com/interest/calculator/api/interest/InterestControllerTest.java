package com.interest.calculator.api.interest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interest.calculator.api.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InterestController.class)
class InterestControllerTest extends BaseControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void calculateInterest() throws Exception {
        Date expectedDate = new Date();
        Payment expectedPayment = Payment.builder().paymentNumber(1).amount(10D).date(expectedDate).build();
        when(interestService.calculateInterest(any(CreditRequest.class)))
                .thenReturn(List.of(expectedPayment));
        CreditRequest creditRequest = CreditRequest.builder().amount(1000D).terms(5).rate(10D).build();
        String requestInJson = mapper.writeValueAsString(creditRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/interest")
                .content(requestInJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
         List<Payment> payments = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(payments.size()).isEqualTo(1);
        assertThat(payments.get(0).getPaymentNumber()).isEqualTo(expectedPayment.getPaymentNumber());
        assertThat(payments.get(0).getAmount()).isEqualTo(expectedPayment.getAmount());
        assertThat(payments.get(0).getDate()).isEqualTo(expectedPayment.getDate());
    }

    @Test
    public void calculateInterestBadRequest() throws Exception {
        when(interestService.calculateInterest(any(CreditRequest.class)))
                .thenThrow(new BadRequestException("code", "msg"));
        CreditRequest creditRequest = CreditRequest.builder().amount(1000D).terms(5).rate(10D).build();
        String requestInJson = mapper.writeValueAsString(creditRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/interest")
                        .content(requestInJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void calculateInterestMethodArgumentExceptionBadRequest() throws Exception {
        CreditRequest creditRequest = CreditRequest.builder().amount(1000D).rate(10D).build();
        when(interestService.calculateInterest(any(CreditRequest.class)))
                .thenReturn(List.of(Payment.builder().build()));
        String requestInJson = mapper.writeValueAsString(creditRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/interest")
                        .content(requestInJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }
}