package com.settlementapp.checklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    TaskRepository repository = new TaskRepository();
    repository.add(new OfficialSettlementTask("T1", "Apply for SIN", "High", LocalDate.of(2026,9,30), "Service Canada"));
    repository.add(new OfficialSettlementTask("T2", "Apply for Health Card", "High", LocalDate.of(2026,10,1), "Service Ontario"));
    repository.add(new CustomTask("T3", "Register kid at school", "Medium", LocalDate.of(2026,9,15)));


    /////////////////////////////////////////////////////////////////////////////////////////
    System.out.println("=== Settlement Milestone Tracker ===");
    System.out.println("Type 'help' to see available commands, or 'exit' to quit.");
    System.out.println("Available commands:");
    System.out.println(" view-checklist --priority=High|Medium|Low --timeline=YYYY-MM-DD");
    System.out.println(" complete-task taskid");
    System.out.println(" exit");
    System.out.println();


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







