package com.settlementapp.checklist;

// it needs to remember which priority it's filtering for, so it needs a field and a constructor
public class PriorityFilter implements TaskFilter {
  private String priority;

  public PriorityFilter(String priority) {
    this.priority =  priority;
  }
  @Override
  public boolean matches(Task task){
    return task.getPriority().equalsIgnoreCase(priority);

    }
  }



