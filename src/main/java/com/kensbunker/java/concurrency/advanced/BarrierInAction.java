package com.kensbunker.java.concurrency.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BarrierInAction {

  public static void main(String[] args) {
    class Friend implements Callable<String> {

      private CyclicBarrier barrier;

      public Friend(CyclicBarrier barrier) {
        this.barrier = barrier;
      }

      @Override
      public String call() throws Exception {
        try {
          Random random = new Random();
          Thread.sleep(random.nextInt(5) * 1000 + 100);
          System.out.println("I just arrived, waiting for the others...");

          barrier.await();
          System.out.println("Let's go to the cinema!");
          return "ok";
        } catch (InterruptedException e) {
          System.out.println("Interrupted");
        }
        return "nok";
      }
    }

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Barrier opening"));
    List<Future<String>> futures = new ArrayList<>();

    try {
      for (int i = 0; i < 4; i++) {
        Friend friend = new Friend(barrier);
        futures.add(executorService.submit(friend));
      }

      futures.forEach(
          future -> {
            try {
              future.get(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException e) {
              System.out.println(e.getMessage());
            } catch (TimeoutException e) {
              System.out.println("Timed out");
              future.cancel(true);
            }
          });
    } finally {
      executorService.shutdown();
    }
  }
}
