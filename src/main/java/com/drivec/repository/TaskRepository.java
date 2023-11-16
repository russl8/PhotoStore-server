package com.drivec.repository;

import com.drivec.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

//Example Repo. DELETE LATER
public interface TaskRepository extends MongoRepository<Task,String> {
    List<Task> findBySeverity(int severity);
    @Query("{assignee: ?0 }") //what does this do??????????
    List <Task> getTasksByAssignee(String assignee);
}
