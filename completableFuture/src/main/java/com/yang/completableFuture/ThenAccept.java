package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class ThenAccept {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Void> future1 = future.thenAcceptAsync(i -> {
            System.out.println("result: " + i);
        });
        System.out.println("future: " + future.get());
        System.out.println("future1: " + future1.get());
        /*
            result: 1
            future: 1
            future1: null
         */
    }
}
