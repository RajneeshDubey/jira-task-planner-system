package com.rjnsh.tps.entity;

import java.io.Serializable;
import java.util.List;

public class Sprint implements Serializable {

    private String sprintId;

    private List<Task> tasks;

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
