package com.settlementapp.checklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// why abstract class?
// if straight to a concrete class like CustomTask implements Task, have to write bodies for all six methods right there.
// Then when build OfficialSettlementTask implements Task next, you'd copy-paste those same five identical method bodies again
// The abstract class exists specifically to hold the shared bodies once.
public abstract class AbstractTask implements Task {
  private final String id;
  private final String description;
  private String priority;
  private boolean complete;
  private final LocalDate deadline;

  // why complete is not in the constructor parameter?
  // a constructor's job is to establish a valid starting state, not to mirror every field
  // a constructor does not need to match fields 1:1 at all
  public AbstractTask(String id, String description, String priority, LocalDate deadline){
    // Validation inside AbstractTask's constructor
    // 1. Main calls new CustomTask(...).
    //2. CustomTask's constructor calls super(...), which runs AbstractTask's constructor.
    //3. AbstractTask's constructor runs its validation if checks. One of them evaluates to true.
    //4. AbstractTask executes throw new InvalidTaskException(...) — this is the one and only place the exception is created.
    //5. Once thrown, the exception doesn't just vanish or get "handled" automatically
    // — it propagates upward through every method call currently in progress,
    // unwinding the call stack: out of AbstractTask's constructor, out of CustomTask's constructor
    // (since super(...) threw, CustomTask's constructor can't finish either), and back to wherever Main called new CustomTask(...).
    //6. In Main, that call site happens to be wrapped in a try { ... } catch (InvalidTaskException e) { ... } block.
    // The catch block is what finally stops the propagation and lets you print e.getMessage()


    // .isBlank() (checks null-or-whitespace-only in one call) rather than .strip().isEmpty()
    if (id == null || id.isBlank()){
      throw new InvalidTaskException("task ID cannot be empty");
    }
    if (description == null || description.isBlank()){
      throw new InvalidTaskException("Description is empty");
    }

    // List.of: immutable objects
    // not planning to add or remove valid priorities at runtime — it's a fixed, permanent set of three values
    List<String> validPriorities = List.of("High", "Medium", "Low");
    if (priority == null || !validPriorities.contains(priority)){
      throw new InvalidTaskException("Invalid priority");
    }


    this.id = id;
    this.description = description;
    this.priority = priority;
    this.complete = false;
    this.deadline = deadline;
  }

  public String getId (){
    return id;

  }

  public String getDescription(){
    return description;
  }

  public String getPriority(){
    return priority;
  }

  public boolean isComplete(){
    return complete;
  }

  public void markComplete(){
    this.complete = true;
  }

  public LocalDate getDeadline(){
    return deadline;
  }

  // why abstract method here?
  // to force every subclass to answer a specific question in its own way
  // if CustomTask extends AbstractTask doesn't provide a body for getCategoryLabel(), it won't compile
  // "Government Requirement" for official tasks, "Personal Goal" for custom ones),
  // When a behavior must vary but you can't write a meaningful shared implementation,
  // that's exactly when a method becomes abstract instead of concrete.
  //public abstract String getCategoryLabel();

}
