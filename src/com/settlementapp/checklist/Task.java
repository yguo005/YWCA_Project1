package com.settlementapp.checklist;

import java.time.LocalDate;

// only public static final constants are allowed as fields
public interface Task {
  String getId ();

  String getDescription();

  String getPriority();


  boolean isComplete();

  void markComplete();

  LocalDate getDeadline();

}
