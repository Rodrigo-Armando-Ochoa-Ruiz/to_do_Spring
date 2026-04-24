package org.example.spring.service;

import org.example.spring.dao.TaskDAO;
import org.example.spring.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public int createTask(Task task){
        return taskDAO.createTask(task);
    }

    public Task getTaskById(int id){
        return taskDAO.getTaskById(id);
    }

    public List<Task> getTasksList(int page, int size){
        return taskDAO.getTaskList(page, size);
    }

    public Task updateTask(Task task){
        return taskDAO.updateTask(task);
    }

    public void deleteTask(int id){
        taskDAO.deleteTask(id);
    }
}
