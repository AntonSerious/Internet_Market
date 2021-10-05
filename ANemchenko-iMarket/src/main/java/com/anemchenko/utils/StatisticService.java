package com.anemchenko.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final HashMap<String, Long> statistic;
    public void addNewStat(String serviceName, Long duration){
         statistic.putIfAbsent(serviceName, duration );
    }

    public List<String> getServiceStat(){
        ArrayList<String> list = new ArrayList<>();
        return statistic.entrySet()
                .stream()
                .map(x -> "Service: " + x.getKey() + ", duration = " + x.getValue() + " ms")
                .collect(Collectors.toList());
    }
}
