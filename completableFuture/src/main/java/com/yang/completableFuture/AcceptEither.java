package com.yang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class AcceptEither {

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
        future.acceptEither(future1, (x) ->{
            System.out.println("the result： " + x);
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
