package com.settlementapp.checklist;

import java.time.LocalDate;

public class TimelineFilter implements TaskFilter {
  private LocalDate timeLine;

  public TimelineFilter (LocalDate timeLine) {
    this.timeLine = timeLine;
  }
@Override
public boolean matches(Task task){
    if (task.getDeadline() == null) {
      return false;
    }
    return task.getDeadline().isBefore(timeLine);
    }
}

