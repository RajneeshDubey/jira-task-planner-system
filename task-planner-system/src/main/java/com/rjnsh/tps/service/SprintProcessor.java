package com.rjnsh.tps.service;

import com.rjnsh.tps.entity.Sprint;
import com.rjnsh.tps.entity.Task;
import com.rjnsh.tps.exeptions.SprintNotFoundException;
import com.rjnsh.tps.exeptions.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sprintProcessor")
public interface SprintProcessor {

    // storing data store in static variable
    public static Map<String, Sprint> sprints = new HashMap<String, Sprint>();

    public Boolean createSprint(String sprintId);

    public Boolean deleteSprint(String sprintId) throws SprintNotFoundException;

    public Boolean addTask(String sprintId, String taskId) throws SprintNotFoundException, TaskNotFoundException;

    public Boolean removeTask(String sprintId, String taskId) throws SprintNotFoundException, TaskNotFoundException;

    public String displaySprintSnapshot(String sprintId) throws SprintNotFoundException;
}
