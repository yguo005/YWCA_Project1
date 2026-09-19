package com.settlementapp.checklist;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args){
    Task task = new Task ("T1", "Apple for SIN", "High", LocalDate.of(2026,9,18));
    System.out.println(task.getId());
    System.out.println(task.getDescription());
    System.out.println(task.getPriority());
    System.out.println(task.getDeadline());

    task.markComplete();
    System.out.println("Task complete? " + task.isComplete());
  }

}
