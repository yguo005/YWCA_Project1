package com.settlementapp.checklist;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
  private List<Task> tasks = new ArrayList<>();

  public void add(Task task) {
    tasks.add(task);
  }

  public List<Task> getAll(){
    // Caller gets their own independent list; mutating it does nothing to internal state of taskRepository.
    return new ArrayList<>(tasks);
  }

  // Why Optional<Task> instead of null?
  // Optional<Task> puts that risk directly in the type signature — anyone calling findById sees
  // Optional<Task> and immediately knows "this might not have a value, I have to handle both cases
  public Optional<Task> findById(String id){
    for (Task t : tasks){
      if (t.getId().equals(id)){
        return Optional.of(t);
      }
    }
    return Optional.empty();
  }

}
