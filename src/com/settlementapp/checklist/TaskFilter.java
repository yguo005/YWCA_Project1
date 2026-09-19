package com.settlementapp.checklist;

// Why make the filter interface?
// what must every filter be able to do, regardless of what it filters on
public interface TaskFilter {
  boolean matches(Task task);
}
