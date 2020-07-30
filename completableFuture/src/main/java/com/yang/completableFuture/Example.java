package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/30
 */
public class Example {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> base = new CompletableFuture<>();
        CompletableFuture<String> future =
                base.thenApply(
                        s -> {
                            System.out.println("2");
                            return s + " 2";
                        });
        base.thenAccept(s -> System.out.println(s+"a")).thenAccept(aVoid -> System.out.println("b"));
        base.thenAccept(s -> System.out.println(s+"c")).thenAccept(aVoid -> System.out.println("d"));
        base.complete("1");
        System.out.println("base result: {}" + base.get());
        System.out.println("future result: {}" + future.get());
    }
}
