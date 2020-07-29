package com.yang.sse.demo.controller;

import com.yang.sse.demo.entity.Temperature;
import com.yang.sse.demo.service.TemperatureSensor;
import com.yang.sse.demo.sseEmitter.RxSseEmitter;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@CrossOrigin(origins = {"*"})
@RestController
public class Controller {

    private final TemperatureSensor temperatureSensor;

    public Controller(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @GetMapping("/temperature-stream")
    public SseEmitter events(HttpServletRequest request){
        RxSseEmitter sseEmitter = new RxSseEmitter();
        temperatureSensor.temperatureStream().subscribe(sseEmitter.getSubscriber());
        return sseEmitter;
    }
}
