package com.panono.panorama.controller;

import com.panono.panorama.entity.EntityResponse;
import com.panono.panorama.entity.Statistic;
import com.panono.panorama.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author uday
 */
@Controller
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EntityResponse<Statistic> getTransactions() {
        return new EntityResponse<>(statisticService.getStatistics(), OK);
    }
}