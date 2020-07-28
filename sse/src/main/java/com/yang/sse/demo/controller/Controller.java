package com.yang.sse.demo.controller;

import com.yang.sse.demo.entity.Temperature;
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

    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @GetMapping("/temperature-stream")
    public SseEmitter events(HttpServletRequest request){
        SseEmitter sseEmitter = new SseEmitter();
        clients.add(sseEmitter);
        sseEmitter.onTimeout(() -> clients.remove(sseEmitter));
        sseEmitter.onCompletion(() -> clients.remove(sseEmitter));
        return sseEmitter;
    }

    @Async
    @EventListener
    public void handleMessage(Temperature temperature){
        List<SseEmitter> deadEmitters = new ArrayList<>();
        clients.forEach(sseEmitter -> {
            try{
                sseEmitter.send(temperature, MediaType.APPLICATION_JSON);
            }catch (Exception ignore){
                deadEmitters.add(sseEmitter);
            }
        });
        clients.removeAll(deadEmitters);
    }
}
