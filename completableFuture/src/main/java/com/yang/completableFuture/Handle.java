package com.yang.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class Handle {

    public static int mockTask(){
        System.out.println("start task");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task is end");
        return new Random().nextInt();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Handle::mockTask);
        CompletableFuture<Integer> future1 = future.handleAsync((result, exception) -> {
            System.out.println("the result: " + result);
            System.out.println("the error: " + (exception == null ? "no error" : exception));
            return result * 10;
        });

        System.out.println("the future result: " + future.get() );
        System.out.println("the future1 result: " + future1.get() );
        /*
            start task
            task is end
            the future result: 1544126383
            the result: 1544126383
            the error: no error
            the future1 result: -1738605354
         */
    }
}
