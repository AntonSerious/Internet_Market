package com.anemchenko.Controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/mainPage")
    public String mainPage (){
        return "index.html";
    }
    @HystrixCommand(fallbackMethod = "demoFallback")
    @GetMapping("/products")
    public String products (){
        String result = restTemplate.getForObject("http://productmicroservice/products", String.class);
        return result;
    }

    @HystrixCommand(fallbackMethod = "demoFallback")
    @GetMapping("/greeting")
    public String greeting(){
        String result = restTemplate.getForObject("http://productmicroservice/products/greeting", String.class);
        return result;
    }
    public String demoFallback() {
        return "zero";
    }

}
