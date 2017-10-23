package com.panono.panorama.controllers;
import com.panono.panorama.entity.Statistic;
import com.panono.panorama.entity.Transaction;
import com.panono.panorama.service.StatisticService;
import com.panono.panorama.service.TransactionService;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 *
 * @author uday
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private StatisticService statisticService;
    
    @MockBean
    private TransactionService transactionService;

    @Test
    public void testReturnNullStatistics() throws Exception {
        when(statisticService.getStatistics()).thenReturn(new Statistic(null, null,null,null,(long) 0));
        Double nullValue = null;
        mockMVC.perform(get("/statistics").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("count", is(0)))
                .andExpect(jsonPath("sum", is(nullValue)))
                .andExpect(jsonPath("avg", is(nullValue)))
                .andExpect(jsonPath("max", is(nullValue)))
                .andExpect(jsonPath("min", is(nullValue)));
    }
    
    @Test
    public void testReturnSampleStatistics() throws Exception {
        long unixTime = System.currentTimeMillis();
         mockMVC.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"count\":2,\"timestamp\": "+unixTime+"}"))
                .andExpect(status().isCreated())
                .andExpect(content().bytes(new byte[0]));
         
            unixTime = System.currentTimeMillis();
        mockMVC.perform(post("/transactions")
                .contentType("application/json")
                .content("{\"count\": 2,\"timestamp\": " + unixTime + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().bytes(new byte[0]));

        verify(transactionService).create(new Transaction((double)2,unixTime));
        
        when(statisticService.getStatistics()).thenReturn(new Statistic(3.0, 1.0, 2.0, 1.0,(long) 2));

        mockMVC.perform(get("/statistics").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("count", is(2)))
                .andExpect(jsonPath("sum", is(3.0)))
                .andExpect(jsonPath("avg", is(1.0)))
                .andExpect(jsonPath("max", is(2.0)))
                .andExpect(jsonPath("min", is(1.0)));
    }


}
