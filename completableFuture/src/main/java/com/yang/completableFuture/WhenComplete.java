package com.yang.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class WhenComplete {

    public static int throwException(){
        System.out.println("start to throw the exception");
        throw new RuntimeException("error");
    }

    public static int mockTask(){
        System.out.println("start task");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("the mock task thread:" + Thread.currentThread().getId());
        System.out.println("task is end");
        return new Random().nextInt();
    }

    public static void sync() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(WhenComplete::mockTask, forkJoinPool);
        CompletableFuture<Integer> completableFuture = future.whenComplete((result, exception) -> {
            System.out.println("the when complete use thread" + Thread.currentThread().getId());
            System.out.println("the result is :" + result);
            System.out.println("teh exception is : {}" + (exception == null ? "no error" : exception));
        });
        System.out.println("the main use thread" + Thread.currentThread().getId());

        System.out.println("the future result is:" + future.get());
        System.out.println("the completableFuture result is:" + completableFuture.get());
        /*
            start task
            the main use thread1
            the mock task thread:13
            task is end
            the when complete use thread13
            the result is :87831413
            the future result is:87831413
            teh exception is : {}no error
            the completableFuture result is:87831413
         */
    }

    public static void async() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(WhenComplete::mockTask, forkJoinPool);
        CompletableFuture<Integer> completableFuture = future.whenCompleteAsync((result, exception) -> {
            System.out.println("the when complete use thread" + Thread.currentThread().getId());
            System.out.println("the result is :" + result);
            System.out.println("teh exception is : {}" + (exception == null ? "no error" : exception));
        });
        System.out.println("the main use thread" + Thread.currentThread().getId());

        System.out.println("the future result is:" + future.get());
        System.out.println("the completableFuture result is:" + completableFuture.get());
        /*
            start task
            the main use thread1
            the mock task thread:13
            task is end
            the when complete use thread14
            the result is :696707836
            the future result is:696707836
            teh exception is : {}no error
            the completableFuture result is:696707836
         */
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
//        sync();
//        async();
//        exception();
//        exception2();
//        exception3();
        exception4();
    }

    public static void exception() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(WhenComplete::throwException);
        CompletableFuture<Integer> completableFuture = future.whenCompleteAsync((result, exception) -> {
            System.out.println("the result is :" + result);
            System.out.println("teh exception is : {}" + (exception == null ? "no error" : exception));
        });
        completableFuture.exceptionally(exception -> {
            System.out.println("cache the exception " + exception);
            return 0;
        });
        System.out.println("the future result is:" + future.get());
        System.out.println("the completableFuture result is:" + completableFuture.get());
        /*
            start to throw the exception
         */
    }

    public static void exception2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(WhenComplete::throwException);
        CompletableFuture<Integer> completableFuture = future.whenCompleteAsync((result, exception) -> {
            System.out.println("the result is :" + result);
            System.out.println("teh exception is : {}" + (exception == null ? "no error" : exception));
        });
        completableFuture.exceptionally(exception -> {
            System.out.println("cache the exception " + exception);
            return 0;
        });
        System.out.println("the completableFuture result is:" + completableFuture.get());
        /*
            start to throw the exception
            the result is :null
            teh exception is : {}java.util.concurrent.CompletionException: java.lang.RuntimeException: error
            cache the exception java.util.concurrent.CompletionException: java.lang.RuntimeException: error
         */
    }

    public static void exception3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(WhenComplete::throwException);
        CompletableFuture<Integer> completableFuture = future.whenCompleteAsync((result, exception) -> {
            System.out.println("the result is :" + result);
            System.out.println("teh exception is : {}" + (exception == null ? "no error" : exception));
        });
        CompletableFuture<Integer> exceptionally = completableFuture.exceptionally(exception -> {
            System.out.println("cache the exception " + exception);
            return 0;
        });
        System.out.println("the completableFuture result is:" + completableFuture.get());
        System.out.println("the exceptionally result is:" + exceptionally.get());
        /*
            start to throw the exception
            the result is :null
            teh exception is : {}java.util.concurrent.CompletionException: java.lang.RuntimeException: error
            cache the exception java.util.concurrent.CompletionException: java.lang.RuntimeException: error
         */
    }

    public static void exception4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(WhenComplete::throwException);
        CompletableFuture<Integer> completableFuture = future.whenCompleteAsync((result, exception) -> {
            System.out.println("the result is :" + result);
            System.out.println("teh exception is : {}" + (exception == null ? "no error" : exception));
        });
        CompletableFuture<Integer> exceptionally = completableFuture.exceptionally(exception -> {
            System.out.println("cache the exception " + exception);
            return 0;
        });
        System.out.println("the exceptionally result is:" + exceptionally.get());
        /*
            start to throw the exception
            the result is :null
            teh exception is : {}java.util.concurrent.CompletionException: java.lang.RuntimeException: error
            cache the exception java.util.concurrent.CompletionException: java.lang.RuntimeException: error
            the exceptionally result is:0
         */
    }
}
