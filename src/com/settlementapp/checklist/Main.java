package com.settlementapp.checklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    // the declared type is Task (the interface) but the actual object is CustomTask
    Task customTask = new CustomTask("T1", "Register kids at school", "Medium",
        LocalDate.of(2026, 9, 18));
    System.out.println(customTask.getId());
    System.out.println(customTask.getDescription());
    System.out.println(customTask.getPriority());
    System.out.println(customTask.getDeadline());

    customTask.markComplete();
    System.out.println("Task complete? " + customTask.isComplete());

    TaskRepository repository = new TaskRepository();

    CustomTask customTask2 = new CustomTask("T2", "Doctor appointment", "High",
        LocalDate.of(2026, 9, 19));
    CustomTask customTask3 = new CustomTask("T3", "Grocery shopping", "Low",
        LocalDate.of(2026, 9, 20));

    repository.add(customTask2);
    repository.add(customTask3);
    System.out.println("Size before: " + repository.getAll().size());

    List<Task> stolen = repository.getAll();
    stolen.clear();

    System.out.println("Size after: " + repository.getAll().size());

    Optional<Task> found1 = repository.findById("T2");
    if (found1.isPresent()) {
      System.out.println("Id exists");
    } else {
      System.out.println("Id not exists");
    }

    Optional<Task> found2 = repository.findById("Not exist");
    if (found2.isPresent()) {
      System.out.println("something went wrong");
    } else {
      System.out.println("Id not exists");
    }

    String priorityFilter = "High";

    for (Task t : repository.getAll()) {
      if (t.getPriority().equalsIgnoreCase(priorityFilter)) {
        System.out.println(t.getId() + " - " + t.getDescription());
      }
    }

    List<TaskFilter> filters1 = new ArrayList<>();
    filters1.add(new PriorityFilter("High"));
    filters1.add(new TimelineFilter(LocalDate.of(2026, 10, 30)));

    for (Task t : repository.getAll()) {
      boolean matchesAll = true;
      for (TaskFilter f : filters1) {
        if (!f.matches(t)) {
          matchesAll = false;
          break;
        }

      }
      if (matchesAll) {
        System.out.println(t.getId() + " - " + t.getDescription());
      }
    }

    // Case 1: empty id
    try {
      Task t = new CustomTask(" ", "School registration", "Medium", LocalDate.of(2026, 9, 15));
      System.out.println("This should not print for bad id test");
    } catch (InvalidTaskException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Case 2: empty description
    try {
      Task t4 = new CustomTask("T4", " ", "Low", LocalDate.of(2026, 10, 15));
      System.out.println("This should not print for bad description test");
    } catch (InvalidTaskException e) {
      System.out.println("Error: " + e.getMessage());
    }

    //Case 3: priority = "urgent"
    try {
      Task t5 = new CustomTask("T5", "Pick up kid", "urgent", LocalDate.of(2026, 9, 19));
      System.out.println("This should not print for bad priority test");
    } catch (InvalidTaskException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Case 4: priority = null
    try {
      Task t6 = new CustomTask("T6", "Pick up kid", null, LocalDate.of(2026, 9, 19));
      System.out.println("This should not print for bad priority test");
    } catch (InvalidTaskException e) {
      System.out.println("Error: " + e.getMessage());
    }

    Task official = new OfficialSettlementTask("T7", "Apply for SIN", "High",
        LocalDate.of(2026, 9, 30), "Service Canada");
    repository.add(official);
    for (Task t : repository.getAll()) {
      System.out.println(t.getId() + " [ " + t.getCategoryLabel() + " ]: " + t.getDescription());
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    while (running) {
      System.out.print("> ");
      String line = scanner.nextLine();

      if (line.equalsIgnoreCase("exit")) {
        running = false;

      } else {
        System.out.println("You type: " + line);
        // .split(...): a String method that breaks a string into an array of smaller strings
        // \\s+: "one or more whitespace characters" (spaces, tabs)
        String[] parts = line.split("\\s+");
        String command = parts[0];

        if (command.equals("complete-task")) {
          // .length: Arrays (String[] parts) have .length as a field, giving you its fixed size
          // .length(): String has .length() as a method
          if (parts.length < 2) {
            System.out.println(
                "Error: complete-task requires a task ID. Usage: complete-task <task-id>");
          } else {
            String taskID = parts[1];
            Optional<Task> found = repository.findById(taskID);

            if (found.isPresent()) {
              Task t = found.get();
              t.markComplete();
              System.out.println("Task " + taskID + " marked completed.");
            } else {
              System.out.println("Error: no task found with ID " + taskID);
            }
          }


        } else if (command.equals("view-checklist")) {
          List<TaskFilter> filters = new ArrayList<>();

          // i starts from 1: parts[0] is the command name itself ("view-checklist")
          // view-checklist --priority=High --timeline=2026-10-30
          for (int i = 1; i < parts.length; i++) {
            String arg = parts[i];
            // --something=value
            String[] flagParts = arg.split("=");
            if (flagParts.length < 2) {
              System.out.println("Error: invalid flag format: " + arg);
              continue; // skip this flag, keep scanning remaining flag
            }

            String flagName = flagParts[0];
            String flagValue = flagParts[1];

            if (flagName.equals("--priority")) {
              filters.add(new PriorityFilter(flagValue));
            } else if (flagName.equals("--timeline")) {
              filters.add(new TimelineFilter(LocalDate.parse(flagValue)));
            }

          }

          for (Task t : repository.getAll()) {
            boolean matchAll = true;
            for (TaskFilter f : filters) {
              if (!f.matches(t)) {
                matchAll = false;
                break;
              }

            }
            if (matchAll) {
              System.out.println(
                  t.getId() + " [ " + t.getCategoryLabel() + "]: " + t.getDescription());
            }
          }



        } else if (command.equals("help")) {
          System.out.println("Available commands:");
          System.out.println(" view-checklist --priority=High|Medium|Low --timeline=YYYY-MM-DD");
          System.out.println(" complete-task taskid");
          System.out.println(" exit");
        } else {
          System.out.println(
              " Unknown command: " + command + ". Type 'help' for a list of commands.");
        }

      }
    }

  }
}







