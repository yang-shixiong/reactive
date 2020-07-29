package com.yang.sse.demo.sseEmitter;

import com.yang.sse.demo.entity.Temperature;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscriber;

import java.io.IOException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class RxSseEmitter extends SseEmitter {

    public static final long SSE_SESSION_TIMEOUT = 30 * 60 *100L;

    private final Subscriber<Temperature> subscriber;

    public RxSseEmitter(){
        super(SSE_SESSION_TIMEOUT);

        this.subscriber = new Subscriber<Temperature>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Temperature temperature) {
                // 出现异常取消订阅
                try {
                    RxSseEmitter.this.send(temperature);
                } catch (IOException e) {
                    e.printStackTrace();
                    unsubscribe();
                }
            }
        };

        // 会话完成或者超期取消会话
        onCompletion(subscriber::unsubscribe);
        onTimeout(subscriber::unsubscribe);
    }

    public Subscriber<Temperature> getSubscriber(){
        return subscriber;
    }
}
