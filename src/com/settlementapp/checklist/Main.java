package com.settlementapp.checklist;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args){
    // the declared type is Task (the interface) but the actual object is CustomTask
    Task customTask = new CustomTask ("T1", "Register kids at school", "Medium", LocalDate.of(2026,9,18));
    System.out.println(customTask.getId());
    System.out.println(customTask.getDescription());
    System.out.println(customTask.getPriority());
    System.out.println(customTask.getDeadline());

    customTask.markComplete();
    System.out.println("Task complete? " + customTask.isComplete());
  }

}
