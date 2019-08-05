package com.rjnsh.tps.service;

import com.rjnsh.tps.constants.TaskStatusEnum;
import com.rjnsh.tps.constants.TaskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("taskStatusManager")
public class TaskStateManager {

    @Autowired
    TaskProcessor taskProcessor;

    public String changeStatus(String taskId) {
        if (TaskTypeEnum.STORY.getType().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getType())) {
            return this.storyStatusChange(taskId);
        } else if (TaskTypeEnum.FEATURE.getType().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getType())) {
            return this.featureStatusChange(taskId);
        } else
            return this.bugStatusChange(taskId);
    }


    private String storyStatusChange(String taskId) {

        String status;
        if (TaskStatusEnum.OPEN.getStatus().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getStatus())) {
            status = TaskStatusEnum.IN_PROGRESS.getStatus();
            taskProcessor.tasks.get(taskId).setStatus(status);
        } else if (TaskStatusEnum.IN_PROGRESS.getStatus().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getStatus())) {
            status = TaskStatusEnum.COMPLETED.getStatus();
            taskProcessor.tasks.get(taskId).setStatus(status);
        } else {
            status = TaskStatusEnum.COMPLETED.getStatus();
        }
        return status;
    }

    private String featureStatusChange(String taskId) {
        String status;
        if (TaskStatusEnum.OPEN.getStatus().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getStatus())) {
            status = TaskStatusEnum.IN_PROGRESS.getStatus();
            taskProcessor.tasks.get(taskId).setStatus(status);
        } else if (TaskStatusEnum.IN_PROGRESS.getStatus().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getStatus())) {
            status = TaskStatusEnum.TESTING.getStatus();
            taskProcessor.tasks.get(taskId).setStatus(status);
        } else if (TaskStatusEnum.TESTING.getStatus().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getStatus())) {
            status = TaskStatusEnum.DEPLOYED.getStatus();
            taskProcessor.tasks.get(taskId).setStatus(status);
        } else {
            status = TaskStatusEnum.DEPLOYED.getStatus();
        }
        return status;
    }

    private String bugStatusChange(String taskId) {
        return "TO-DO";
    }
}
