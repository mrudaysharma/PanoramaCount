package com.panono.panorama.controllers;

import com.panono.panorama.entity.Statistic;
import com.panono.panorama.service.StatisticService;
import com.panono.panorama.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 *
 * @author uday
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMVC;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testHandleNoContentException() throws Exception {
        mockMVC.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"count\": 3,\"timestamp\": 12890212}"))
                .andExpect(status().isNoContent())
                .andExpect(content().bytes(new byte[0]));
    }

    @Test
    public void testAcceptValidRequest() throws Exception {

        long unixTime = System.currentTimeMillis();
        mockMVC.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"count\": 2,\"timestamp\": " + unixTime + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().bytes(new byte[0]));

        unixTime = System.currentTimeMillis();
        mockMVC.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"count\": 2,\"timestamp\": " + unixTime + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().bytes(new byte[0]));
        
    }
}
