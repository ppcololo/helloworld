package com.example.helloworld.helloworld;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
class MetricsTotalRequestCounter {
    private final Counter counter;

    MetricsTotalRequestCounter(MeterRegistry registry) {
        this.counter = Counter.builder("requests_counter")
                .description("Count visits on index page")
                .register(registry);
    }

    void increment() {
        counter.increment();
    }
}