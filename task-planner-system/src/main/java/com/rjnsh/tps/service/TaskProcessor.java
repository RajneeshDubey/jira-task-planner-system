package com.rjnsh.tps.service;

import com.rjnsh.tps.entity.*;
import com.rjnsh.tps.exeptions.TaskNotFoundException;
import com.rjnsh.tps.exeptions.NecessaryFieldsMissingException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskProcessor")
public interface TaskProcessor {

    //making this tps static to store some dummy tps value
    public static Map<String, Sprint> sprints = new HashMap<>();

    public static Map<String, Task> tasks = new HashMap<String, Task>();

    public static Map<String, Story> stories = new HashMap<String, Story>();

    Boolean createTask(Task task) throws NecessaryFieldsMissingException;

    Boolean createSubTrack(SubTrack subTrack) throws TaskNotFoundException;

    String changeStatusOfTask(String taskId) throws TaskNotFoundException;

    Boolean changeAssignee(String task, String assignee) throws TaskNotFoundException;

    public String displayTaskByUserAndTaskType(String user, String type);

    String displayTaskByUser(String user);

}
