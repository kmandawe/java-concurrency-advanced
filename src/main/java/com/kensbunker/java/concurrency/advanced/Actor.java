package com.kensbunker.java.concurrency.advanced;

import java.util.Objects;

public class Actor {

  private String lastName, firstName;

  public Actor(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public String lastName() {
    return this.lastName;
  }

  public String firstName() {
    return this.firstName;
  }

  @Override
  public String toString() {
    return "Actor{" +
        "lastName='" + lastName + '\'' +
        ", firstName='" + firstName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Actor actor = (Actor) o;

    if (!Objects.equals(lastName, actor.lastName)) {
      return false;
    }
    return Objects.equals(firstName, actor.firstName);
  }

  @Override
  public int hashCode() {
    int result = lastName != null ? lastName.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    return result;
  }
}
