package com.anemchenko.controllers;


import com.anemchenko.utils.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class StatisticController {
    private final StatisticService statisticService;


    @GetMapping()
    public List<String> getStatistic(){
        return statisticService.getServiceStat();
    }
}
