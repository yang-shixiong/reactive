package com.yang.sse.demo.service;

import com.yang.sse.demo.entity.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rx.Observable;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TemperatureSensor {

    private final Random rnd = new Random();

    private final Observable<Temperature> stream = Observable.range(0, Integer.MAX_VALUE)
            .concatMap(ticket -> Observable.just(ticket).delay(rnd.nextInt(5000), TimeUnit.MILLISECONDS).map(ticketValue -> this.probe()))
            .publish()
            .refCount();

    private Temperature probe(){
        return new Temperature(16 + rnd.nextGaussian() *10);
    }

    public Observable<Temperature> temperatureStream() {
        return stream;
    }
}
