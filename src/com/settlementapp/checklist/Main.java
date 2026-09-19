package com.settlementapp.checklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    TaskRepository repository = new TaskRepository();

    CustomTask customTask2 = new CustomTask("T2", "Doctor appointment", "High", LocalDate.of(2026,9,19));
    CustomTask customTask3 = new CustomTask("T3", "Grocery shopping", "Low", LocalDate.of(2026,9,20));

    repository.add(customTask2);
    repository.add(customTask3);
    System.out.println("Size before: " + repository.getAll().size());

    List<Task> stolen = repository.getAll();
    stolen.clear();

    System.out.println("Size after: " + repository.getAll().size());


    Optional<Task> found1 = repository.findById("T2");
    if (found1.isPresent()){
      System.out.println("Id exists");
    } else {
      System.out.println("Id not exists");
    }

    Optional<Task> found2 = repository.findById("Not exist");
    if (found2.isPresent()){
      System.out.println("something went wrong");
    } else {
      System.out.println("Id not exists");
    }

    String priorityFilter = "High";

    for (Task t : repository.getAll()){
      if (t.getPriority().equalsIgnoreCase(priorityFilter)){
        System.out.println(t.getId() + " - " + t.getDescription());
      }
    }

    List<TaskFilter> filters = new ArrayList<>();
    filters.add(new PriorityFilter("High"));
    filters.add(new TimelineFilter(LocalDate.of(2026,10,30)));

    for (Task t : repository.getAll()){
      boolean matchesAll = true;
      for (TaskFilter f : filters){
        if (!f.matches(t)){
          matchesAll = false;
          break;
        }

      }
      if (matchesAll){
        System.out.println(t.getId() + " - " + t.getDescription());
      }
    }

    // Case 1: empty id
    try {
      Task t = new CustomTask(" ", "School registration", "Medium", LocalDate.of(2026,9,15));
      System.out.println("This should not print for bad id test");
    } catch (InvalidTaskException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Case 2: empty description
    try{
      Task t4 = new CustomTask("T4", " ", "Low", LocalDate.of(2026,10,15));
      System.out.println("This should not print for bad description test");
    } catch (InvalidTaskException e){
      System.out.println("Error: " + e.getMessage());
    }

    //Case 3: priority = "urgent"
    try {
      Task t5 = new CustomTask("T5", "Pick up kid", "urgent", LocalDate.of(2026,9,19));
      System.out.println("This should not print for bad priority test");
    } catch (InvalidTaskException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Case 4: priority = null
    try {
      Task t6 = new CustomTask("T6", "Pick up kid", null, LocalDate.of(2026,9,19));
      System.out.println("This should not print for bad priority test");
    } catch (InvalidTaskException e){
      System.out.println("Error: " + e.getMessage());
    }

    Task official = new OfficialSettlementTask("T7", "Apply for SIN", "High", LocalDate.of(2026,9,30), "Service Canada");
    repository.add(official);
    for (Task t :repository.getAll()){
      System.out.println(t.getId() + " [ " + t.getCategoryLabel() + " ]: " + t.getDescription() );
    }

  }

}
