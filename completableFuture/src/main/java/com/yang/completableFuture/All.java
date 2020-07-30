package com.yang.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Description:
 *
 * @author mark
 * Date 2020/7/29
 */
public class All {

    public static void all(){
        Random random = new Random();

        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("all product detail");
            return "all product detail\n";
        }, forkJoinPool);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("all seller info");
            return "all seller info\n";
        }, forkJoinPool);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("all stock");
            return "all stock\n";
        }, forkJoinPool);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("all order");
            return "all order\n";
        }, forkJoinPool);

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
        allFuture.join();
        System.out.println("all use time:" + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        all();
        any();
        /*
            all seller info
            all product detail
            all order
            all stock
            all use time:1790
            any order
            any: any order
            any use time:1194
         */
    }

    public static void any(){
        Random random = new Random();

        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("any product detail");
            return "any product detail\n";
        }, forkJoinPool);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("any seller info");
            return "any seller info\n";
        }, forkJoinPool);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("any stock");
            return "any stock\n";
        }, forkJoinPool);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("any order");
            return "any order\n";
        }, forkJoinPool);
        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(futureA, futureB, futureC, futureD);
        System.out.println("any: " + anyFuture.join());
        System.out.println("any use time:" + (System.currentTimeMillis() - start));
    }
}
