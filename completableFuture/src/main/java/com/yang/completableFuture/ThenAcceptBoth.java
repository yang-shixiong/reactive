package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class ThenAcceptBoth {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 200);
        CompletableFuture<Void> future2 = future.thenAcceptBoth(future1, (x, y) -> {
            System.out.println("the x : " + x);
            System.out.println("the y : " + y);
        });
        System.out.println("future: " + future.get());
        System.out.println("future1: " + future1.get());
        System.out.println("future2: " + future2.get());
        /*
            the x : 100
            the y : 200
            future: 100
            future1: 200
            future2: null
         */
    }
}
