package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class ThenRun {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Void> future1 = future.thenRun(() -> {
            System.out.println("run able");
        });
        System.out.println("future: " + future.get());
        System.out.println("future1: " + future1.get());
        /*
            run able
            future: 100
            future1: null
         */
    }
}
