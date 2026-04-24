package org.example.spring.controller;

import org.example.spring.entity.Task;
import org.example.spring.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;
    private static final Logger logger = LogManager.getLogger(TaskController.class);

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable int id, Model model){
        logger.info("GetByTask: id = {}", id);
        Task task = service.getTaskById(id);
        model.addAttribute("task", task);
        return "task-detail";
    }

    @GetMapping
    public String getTasks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model) {

        model.addAttribute("tasks", service.getTasksList(page, size));
        model.addAttribute("currentPage", page);

        return "task-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task){
        if (task.getId() == 0) {
            logger.info("New task");
            service.createTask(task);
        } else {
            logger.info("updateTask id = {}", task.getId());
            service.updateTask(task);
        }
        return "redirect:/task";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model){
        Task task = service.getTaskById(id);
        model.addAttribute("task", task);
        return "task-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id){
        logger.info("Task {} deleted", id);
        service.deleteTask(id);
        return "redirect:/task";
    }
}
