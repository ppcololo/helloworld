package com.example.helloworld.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class Controller {

    private static Map<String, String> alwaysOk = Collections.singletonMap("status", "OK");
    private final MetricsTotalRequestCounter counterMetric;

    public Controller(MetricsTotalRequestCounter counterMetric) {
        this.counterMetric = counterMetric;
    }

    @RequestMapping("/")
    public String index() {
        counterMetric.increment();
        return "Hello World!";
    }

    @RequestMapping("/liveness")
    public Map<String, String> testLiveness() {
        return alwaysOk;
    }
}