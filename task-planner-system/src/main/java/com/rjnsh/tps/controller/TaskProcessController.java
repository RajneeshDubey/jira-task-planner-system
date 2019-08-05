package com.rjnsh.tps.controller;

import com.rjnsh.tps.entity.Task;
import com.rjnsh.tps.exeptions.NecessaryFieldsMissingException;
import com.rjnsh.tps.exeptions.TaskNotFoundException;
import com.rjnsh.tps.service.TaskProcessor;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/task/", produces = "application/json")
@Api(value = "TaskPlannerSystem", description = "Operation to manage task Planner")
public class TaskProcessController {

    @Autowired
    TaskProcessor taskProcessor;

    Logger log = Logger.getLogger(TaskProcessController.class.getName());

    @PostMapping(value = "createTask")
    @ApiOperation(value = "create Task Operation.")
    public Boolean createTask(final @RequestBody Task task) {
        Boolean createStatus = Boolean.FALSE;
        try {
            createStatus = taskProcessor.createTask(task);
        }
        catch(NecessaryFieldsMissingException e)
        {
            log.warning("Could Not find all the necessary fields.");
        }
        return createStatus;
    }


    @PutMapping(value = "changeStatus/{taskId}")
    @ApiOperation(value = "change Status Operation to update the status of a task.")
    public String changeStatusOfTask(final @PathVariable String taskId) {
        String changedStatus ="";
        try {
            changedStatus = taskProcessor.changeStatusOfTask(taskId);
        }
        catch(TaskNotFoundException e)
        {
            log.warning("Could Not Fetch Task.");

        }
        return changedStatus;
    }


}
