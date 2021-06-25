package com.kensbunker.java.concurrency.advanced;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorDemo {

  public static void main(String[] args) {
    Executor executor = Executors.newSingleThreadExecutor();
    Runnable task = () -> System.out.println("I run!");
    executor.execute(task);
  }
}
