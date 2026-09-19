package com.settlementapp.checklist;

import java.time.LocalDate;

public class OfficialSettlementTask {
  private String id;
  private String description;
  private String priority;
  private boolean complete;
  private LocalDate deadline;

  // why complete is not in the constructor parameter?
  // a constructor's job is to establish a valid starting state, not to mirror every field
  // a constructor does not need to match fields 1:1 at all
  public OfficialSettlementTask(String id, String description, String priority, LocalDate deadline){
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

}
