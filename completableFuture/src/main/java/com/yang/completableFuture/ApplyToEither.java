package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class ApplyToEither {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("start sleep");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end sleep");
            return 200;
        });
        CompletableFuture<Integer> future2 = future.applyToEither(future1, (x) -> {
            System.out.println("the result： " + x);
            return 1000;
        });
        System.out.println("future: " + future.get());
        System.out.println("future2: " + future2.get());
        System.out.println("future1: " + future1.get());
        /*
            start sleep
            the result： 100
            future: 100
            future2: 1000
            end sleep
            future1: 200
         */
    }
}
