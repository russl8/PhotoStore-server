package com.drivec.services;

import com.drivec.models.Task;
import com.drivec.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    //PERFORM CRUD OPERATIONS HERE
    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    public List<Task> findAllTasks() {
        return repository.findAll();

    }

    public Task getTaskById(String id) {
        return repository.findById(id).get();
    }

    public List<Task> getTasksBySeverity(int severity) {
        return repository.findBySeverity(severity);
    }

    public List<Task> getTasksByAssignee(String assignee) {
        return repository.getTasksByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest) {
        //get the existing document from the db
        //pupulate new value from request to existing object/entity/document
        Task existingTask = repository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        return repository.save(existingTask);
    }

    public String deleteTask(String taskId) {
        repository.deleteById(taskId);
        return "Task " + taskId + " deleted from database ";
    }

}
