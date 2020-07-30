package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class RunAfterBoth {

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
        future.runAfterBoth(future1, () ->{
            System.out.println("over, but no args");
        });
        System.out.println("future: " + future.get());
        System.out.println("future1: " + future1.get());
        /*
            start sleep
            future: 100
            end sleep
            over, but no args
            future1: 200
         */
    }
}
