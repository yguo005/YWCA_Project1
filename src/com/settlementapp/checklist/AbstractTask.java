package com.settlementapp.checklist;

import java.time.LocalDate;

// why abstract class?
// if straight to a concrete class like CustomTask implements Task, have to write bodies for all six methods right there.
// Then when build OfficialSettlementTask implements Task next, you'd copy-paste those same five identical method bodies again
// The abstract class exists specifically to hold the shared bodies once.
public abstract class AbstractTask implements Task {
  private String id;
  private String description;
  private String priority;
  private boolean complete;
  private LocalDate deadline;

  // why complete is not in the constructor parameter?
  // a constructor's job is to establish a valid starting state, not to mirror every field
  // a constructor does not need to match fields 1:1 at all
  public AbstractTask(String id, String description, String priority, LocalDate deadline){
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
  public abstract String getCategoryLabel();

}
