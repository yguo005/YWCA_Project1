package com.settlementapp.checklist;
import java.time.LocalDate;

public class CustomTask extends AbstractTask {

  public CustomTask(String id, String description, String priority, LocalDate deadline) {
    // super: because AbstractTask's fields are private, so CustomTask can't set them directly.
    // It has to delegate via super(...)
    super(id, description, priority, deadline);
  }

  //@Override
  public String getCategoryLabel() {
    return "Personal Goal";

  }
}



