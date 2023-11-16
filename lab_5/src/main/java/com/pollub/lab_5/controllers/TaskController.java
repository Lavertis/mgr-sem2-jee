package com.pollub.lab_5.controllers;

import com.pollub.lab_5.entities.Task;
import com.pollub.lab_5.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    public TaskRepository taskRepository;

    @GetMapping("seed")
    @ResponseBody
    public String seedTasks() {
        Task task;
        double cost = 1000;
        boolean isDone = false;
        for (int i = 1; i <= 10; i++) {
            task = new Task();
            task.setName("Task " + i);
            task.setDescription("Description of actions to be performed in the task " + i);
            task.setCost(cost);
            task.setIsDone(isDone);
            isDone = !isDone;
            cost += 200.50;
            taskRepository.save(task);
        }
        return "Tasks have been seeded";
    }

    @GetMapping()
    @ResponseBody
    public String getTasks() {
        StringBuilder response = new StringBuilder();
        taskRepository.findAll().forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("isDone/{isDone}")
    @ResponseBody
    public String getTasksByIsDone(@PathVariable boolean isDone) {
        StringBuilder response = new StringBuilder();
        taskRepository.findByIsDone(isDone).forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("cost/lessThan/{cost}")
    @ResponseBody
    public String getTasksByCostLessThan(@PathVariable double cost) {
        StringBuilder response = new StringBuilder();
        taskRepository.findByCostLessThan(cost).forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("cost/between/{cost1}/{cost2}")
    @ResponseBody
    public String getTasksByCostBetween(@PathVariable double cost1, @PathVariable double cost2) {
        StringBuilder response = new StringBuilder();
        taskRepository.findByCostBetween(cost1, cost2).forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Deleted Task with id = " + id;
    }
}
