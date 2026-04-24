package org.example.spring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.spring.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public int createTask(Task task) {
        entityManager.persist(task);
        return task.getId();
    }

    public Task getTaskById(int id){
        return entityManager.find(Task.class, id);
    }

    public List<Task> getTaskList(int page, int size){
        return entityManager
                .createQuery("SELECT t FROM Task t", Task.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Transactional
    public Task updateTask(Task task){
        return entityManager.merge(task);
    }

    @Transactional
    public void deleteTask(int id){
        Task task = entityManager.find(Task.class, id);
        if(task != null){
            entityManager.remove(task);
        }
    }
}
