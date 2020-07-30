package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class Base {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // just get the result
        CompletableFuture<String> future = CompletableFuture.completedFuture("completed");
        System.out.println(future.get());  //completed

        // init complete and get
        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        System.out.println("start the thread to complete ie");
        System.out.println(Thread.currentThread());
        new Thread(() -> {
            System.out.println("will finish in 1s");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread());
            System.out.println("finished");
            objectCompletableFuture.complete("finish");
        }).start();
        System.out.println("start to get the result");
        System.out.println(objectCompletableFuture.get());
        /*
        result
            completed
            start the thread to complete ie
            Thread[main,5,main]
            start to get the result
            will finish in 1s
            Thread[Thread-0,5,main]
            finished
            finish
         */

        // exception
        CompletableFuture<Object> base = new CompletableFuture<>();
        base.completeExceptionally(new RuntimeException("error"));
        System.out.println(base.get());
        /*
            Exception in thread "main" java.util.concurrent.ExecutionException: java.lang.RuntimeException: error
                at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:395)
                at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1999)
                at com.yang.completableFuture.CompletableFutureDemo.main(CompletableFutureDemo.java:51)
            Caused by: java.lang.RuntimeException: error
                at com.yang.completableFuture.CompletableFutureDemo.main(CompletableFutureDemo.java:50)
         */
    }
}
