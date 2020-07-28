package com.yang.sse.demo.service;

import com.yang.sse.demo.entity.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TemperatureSensor {

    @Autowired
    private ApplicationEventPublisher publisher;

    private final Random rnd = new Random();

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

//    public TemperatureSensor(ApplicationEventPublisher publisher) {
//        this.publisher = publisher;
//    }

    @PostConstruct
    public void startProcessing(){
        this.executor.schedule(this::probe, 1, TimeUnit.SECONDS);
    }

    private void probe(){
        double temperature = 16 + rnd.nextGaussian() * 10;

        System.out.println("temperature : " + temperature);
        publisher.publishEvent(new Temperature(temperature));

        executor.schedule(this::probe, rnd.nextInt(5000), TimeUnit.MILLISECONDS);
    }
}
