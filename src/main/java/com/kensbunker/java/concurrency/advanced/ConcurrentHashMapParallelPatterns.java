package com.kensbunker.java.concurrency.advanced;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapParallelPatterns {

  public static void main(String[] args) {
    ConcurrentHashMap<Actor, Set<Movie>> map = new ConcurrentHashMap<>();

    MovieReader reader = new MovieReader();
    reader.addActorsToMap(map);

    System.out.println("# Actors = " + map.size());

    int maxMoviesForOneActor = map.reduce(10, (actor, movies) -> movies.size(), Integer::max);
    System.out.println("Max movies for one actor = " + maxMoviesForOneActor);

    Actor mostSeenActor = map.search(10, (actor, movies) -> movies.size() == maxMoviesForOneActor ? actor : null);
    System.out.println("Most seen actor = " + mostSeenActor);
  }
}
