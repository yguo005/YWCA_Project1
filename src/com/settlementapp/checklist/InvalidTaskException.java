package com.settlementapp.checklist;

// Instead of using generic IllegalArgumentException everywhere, consider a custom unchecked exception
// so CLI can catch validation errors specifically
public class InvalidTaskException extends RuntimeException{
  public InvalidTaskException(String message){
    //super: super(...) calls the parent class RuntimeException's constructor
    super (message);
  }

}
