package com.kensbunker.java.concurrency.advanced;

public class RunnableDemo {
  public static void main(String[] args) {
    Runnable task = () -> System.out.println("Hello world!");
    Thread t = new Thread(task);
    t.start();
  }
}
