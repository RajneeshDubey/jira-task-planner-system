package com.rjnsh.tps.service;

import com.rjnsh.tps.constants.TaskStatusEnum;
import com.rjnsh.tps.controller.SprintProcessController;
import com.rjnsh.tps.entity.*;
import com.rjnsh.tps.exeptions.NecessaryFieldsMissingException;
import com.rjnsh.tps.exeptions.TaskNotFoundException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("taskProcessorImpl")
public class TaskProcessorImpl implements TaskProcessor {

    @Autowired
    TaskStateManager taskStateManager;

    Logger log = Logger.getLogger(TaskProcessorImpl.class.getName());

    @Override
    public Boolean createTask(Task task) throws NecessaryFieldsMissingException {
        Boolean isCreated = Boolean.FALSE;
        try {
            if (null != task.getTitle() && null != task.getCreator() && null != task.getStatus() && null != task.getType() && null != task.getDueDate()) {
                tasks.put(task.getTaskId(), task);
                isCreated = Boolean.TRUE;
            }
        } catch (NecessaryFieldsMissingException e) {
            log.warning("Necessary fields are missing");
            isCreated = Boolean.FALSE;
        }
        return isCreated;
    }

    @Override
    public Boolean createSubTrack(SubTrack subTrack) throws TaskNotFoundException {
        Boolean isCreated = Boolean.FALSE;
        try {
            if (null != tasks.get(subTrack.getStory())) {
                Task task = tasks.get(subTrack.getStory());
                Story story = stories.get(task.getTaskId());
                story.getSubtracks().add(subTrack);
            }
            isCreated = Boolean.TRUE;
        } catch (TaskNotFoundException e) {
            log.warning("Story not found.");
            isCreated = Boolean.FALSE;
        }
        return isCreated;
    }

    @Override
    public String changeStatusOfTask(String taskId) throws TaskNotFoundException {

        String status = "";
        if (null != tasks.get(taskId)) {
            status = taskStateManager.changeStatus(taskId);
        }

        return status;
    }

    @Override
    public Boolean changeAssignee(String task, String assignee) throws TaskNotFoundException {
        return null;
    }

    @Override
    public String displayTaskByUserAndTaskType(String user, String type) {

        List<String> taskList = new ArrayList<String>();

        for (Map.Entry<String, Task> taskMap : this.tasks.entrySet()) {
            if (type.equalsIgnoreCase(taskMap.getValue().getType()) && user.equalsIgnoreCase(taskMap.getValue().getAssignee())) {
                taskList.add(taskMap.getValue().getTitle() + "\n" + "Sprint => " + taskMap.getValue().getSprint());
            }
        }
        String titles = "";
        for (String taskTitle : taskList) {
            titles += "Title => " + taskTitle + "\n";
        }

        return "User =>" + user + " : \n" + "Task Type => " + type + " \n" + titles;
    }

    @Override
    public String displayTaskByUser(String user) {

        List<String> taskList = new ArrayList<String>();
        Map<String, List<String>> tasksResult = new HashMap<>();

        for (Map.Entry<String, Task> taskMap : this.tasks.entrySet()) {
            if (user.equalsIgnoreCase(taskMap.getValue().getAssignee())) {
                if (tasksResult.containsKey(taskMap.getValue().getType())) {
                    tasksResult.get(taskMap.getValue().getType()).add(taskMap.getValue().getTitle() + "\n" + "Sprint => " + taskMap.getValue().getSprint());
                } else {
                    List<String> tsks = new ArrayList<>();
                    tsks.add("Title =>" + taskMap.getValue().getTitle() + "\n" + "Sprint => " + taskMap.getValue().getSprint()+"\n");
                    tasksResult.put(taskMap.getValue().getType(), tsks);
                }
            }
        }
        String titles = "";
        for (Map.Entry<String, List<String>> res : tasksResult.entrySet()) {
            titles += "TaskType => " + res.getKey() + "\n";
            for (String tsks : res.getValue()) {
                titles += tsks;
            }
        }

        return "User =>" + user + " : " + "\n" + titles;
    }
}
