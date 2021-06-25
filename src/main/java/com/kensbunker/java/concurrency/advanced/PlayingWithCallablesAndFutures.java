package com.kensbunker.java.concurrency.advanced;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PlayingWithCallablesAndFutures {

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, TimeoutException {
    Callable<String> task = () -> {
//      Thread.sleep(300);
//      return "I am in thread " + Thread.currentThread().getName();
      throw new IllegalStateException("I throw an exception in thread " + Thread.currentThread().getName());
    };

    ExecutorService executor = Executors.newFixedThreadPool(4);

    try {
      for (int i = 0; i < 10; i++) {
        Future<String> future = executor.submit(task);
        System.out.println("I get: " + future.get(100, TimeUnit.MILLISECONDS));
      }
    } finally {
      executor.shutdown();
    }
  }
}