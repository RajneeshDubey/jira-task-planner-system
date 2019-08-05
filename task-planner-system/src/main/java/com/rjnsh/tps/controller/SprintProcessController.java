package com.rjnsh.tps.controller;

import com.rjnsh.tps.entity.Sprint;
import com.rjnsh.tps.entity.Task;
import com.rjnsh.tps.exeptions.NecessaryFieldsMissingException;
import com.rjnsh.tps.exeptions.SprintNotFoundException;
import com.rjnsh.tps.exeptions.TaskNotFoundException;
import com.rjnsh.tps.service.SprintProcessor;
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
@RequestMapping(value = "/api/sprint/", produces = "application/json")
@Api(value = "Spring Planner", description = "Operation to manage sprint Planner")
public class SprintProcessController {

    @Autowired
    SprintProcessor sprintProcessor;

    Logger log = Logger.getLogger(SprintProcessController.class.getName());

    @PostMapping(value = "createSprint")
    @ApiOperation(value = "create Sprint Operation")
    public Boolean createTask(final @RequestBody String sprintId) {
        Boolean createStatus = Boolean.FALSE;
        try {
            createStatus = sprintProcessor.createSprint(sprintId);
        }
        catch(NecessaryFieldsMissingException e)
        {
            log.warning("Could Not find all the necessary fields.");
        }
        return createStatus;
    }


    @DeleteMapping(value = "deleteSprint/{sprintId}")
    @ApiOperation(value = "Delete operation for a sprint.")
    public Boolean deleteSprint(final @PathVariable String sprintId) {
        Boolean isDeleted = Boolean.FALSE;
        try {
            isDeleted = sprintProcessor.deleteSprint(sprintId);
        }
        catch(SprintNotFoundException e)
        {
            log.warning("Could Not Fetch Sprint.");

        }
        return isDeleted;
    }




}
