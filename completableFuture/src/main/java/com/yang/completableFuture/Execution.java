package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class Execution {

    static void executeBase() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> base = new CompletableFuture<>();
        CompletableFuture<String> future = base.thenApply(s -> s + "2").thenApply(s -> s + "3");
        base.complete("1");
        System.out.println(future.get());  // 123
    }

    static void executeFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> base = new CompletableFuture<>();
        CompletableFuture<String> future = base.thenApply(s -> s + "2").thenApply(s -> s + "3");
        future.complete("1");
        System.out.println(future.get());  // 1
    }

    static void printBase() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> base = new CompletableFuture<>();
        CompletableFuture<String> future = base.thenApply(s -> s + "2").thenApply(s -> s + "3");
        future.complete("1");
        System.out.println(base.get());  // the thread will be block
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        executeBase();
//        executeFuture();
//        printBase();
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 1);
        System.out.println(integerCompletableFuture.get());
    }

    static void run(){
        CompletableFuture<Object> base = new CompletableFuture<>();

    }
}
