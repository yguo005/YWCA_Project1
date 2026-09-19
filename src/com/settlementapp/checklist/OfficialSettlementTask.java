package com.settlementapp.checklist;

import java.time.LocalDate;

public class OfficialSettlementTask extends AbstractTask {
  private final String governmentAgency;

  // why complete is not in the constructor parameter?
  // a constructor's job is to establish a valid starting state, not to mirror every field
  // a constructor does not need to match fields 1:1 at all
  public OfficialSettlementTask(String id, String description, String priority, LocalDate deadline, String governmentAgency){
    super(id, description, priority, deadline);
    this.governmentAgency =  governmentAgency;
  }


  public String getGovernmentAgency(){
    return governmentAgency;
  }

  //@Override
  public String getCategoryLabel(){
    return "Government";
  }

}
