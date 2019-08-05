package com.rjnsh.tps.service;

import com.rjnsh.tps.entity.Sprint;
import com.rjnsh.tps.entity.Task;
import com.rjnsh.tps.exeptions.SprintNotFoundException;
import com.rjnsh.tps.exeptions.TaskNotFoundException;
import org.springframework.stereotype.Component;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

@Component("sprintProcessorImpl")
public class SprintProcessorImpl implements SprintProcessor {

    @Override
    public Boolean createSprint(String sprintId) {

        Sprint sprint = new Sprint();
        sprint.setSprintId(sprintId);

        List<Task> tasks = new ArrayList<Task>();
        sprint.setTasks(tasks);
        //sprint created with empty task list
        sprints.put(sprintId, sprint);
        return true;
    }

    @Override
    public Boolean deleteSprint(String sprintId) throws SprintNotFoundException {
        if (null == sprints.get(sprintId))
            return false;
        try {
            sprints.remove(sprintId);
        } catch (SprintNotFoundException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addTask(String sprintId, String taskId) throws SprintNotFoundException, TaskNotFoundException {
        return null;
    }

    @Override
    public Boolean removeTask(String sprintId, String taskId) throws SprintNotFoundException, TaskNotFoundException {
        return null;
    }

    @Override
    public String displaySprintSnapshot(String sprintId) throws SprintNotFoundException {
        return null;
    }
}
