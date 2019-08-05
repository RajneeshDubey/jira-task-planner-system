package com.rjnsh.tps;

import com.rjnsh.tps.constants.TaskStatusEnum;
import com.rjnsh.tps.constants.TaskTypeEnum;
import com.rjnsh.tps.entity.Sprint;
import com.rjnsh.tps.entity.Story;
import com.rjnsh.tps.entity.SubTrack;
import com.rjnsh.tps.entity.Task;
import com.rjnsh.tps.service.SprintProcessor;
import com.rjnsh.tps.service.TaskProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class TaskPlannerSystemTests {

    @Autowired
    TaskProcessor taskProcessor;

    @Autowired
    SprintProcessor sprintProcessor;

    void setUp() {
        Task t1 = new Task();
        t1.setTaskId("1");
        t1.setTitle("Create a microservice");
        t1.setCreator("Amy");
        t1.setAssignee("Ryan");
        t1.setSprint("1.0");
        t1.setStatus(TaskStatusEnum.OPEN.getStatus());
        t1.setDueDate(new Date());
        t1.setType(TaskTypeEnum.STORY.getType());

        taskProcessor.tasks.put(t1.getTaskId(), t1);


        Story s1 = new Story();
        s1.setTaskId("1");
        s1.setSummary("Story to create a microservice");
        SubTrack subTrack = new SubTrack();
        subTrack.setStory("1");
        subTrack.setTitle("Development");
        subTrack.setStatus(TaskStatusEnum.OPEN.getStatus());
        List<SubTrack> subTrackList = new ArrayList<SubTrack>();
        subTrackList.add(subTrack);
        s1.setSubtracks(new ArrayList<SubTrack>(subTrackList));
        taskProcessor.stories.put(s1.getTaskId(), s1);

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(t1);
        Sprint sprint = new Sprint();
        sprint.setTasks(tasks);
        sprintProcessor.sprints.put("1", sprint);

    }

    @Test
    void testCreateTask() {
        setUp();

        Task t1 = new Task();
        t1.setType(TaskTypeEnum.FEATURE.getType());
        t1.setCreator("Brad");
        t1.setAssignee("Peter");
        t1.setStatus(TaskStatusEnum.OPEN.getStatus());
        t1.setDueDate(new Date());
        t1.setTitle("CreateDashboard");
        t1.setTaskId("5");

        Boolean isCreated = taskProcessor.createTask(t1);

        System.out.println(isCreated);
        System.out.println(t1.getTaskId());

        Assertions.assertTrue(isCreated);

    }

    @Test
    void testCreateTaskException() {
        setUp();

        Task t1 = new Task();
        t1.setType(TaskTypeEnum.FEATURE.getType());
        t1.setAssignee("Peter");
        t1.setStatus(TaskStatusEnum.OPEN.getStatus());
        t1.setDueDate(new Date());
        t1.setTitle("CreateDashboard");

        Boolean isCreated = taskProcessor.createTask(t1);

        Assertions.assertTrue(!isCreated);


    }

    @Test
    public void testCreateSubTrack() {
        setUp();
        SubTrack subTrack = new SubTrack();
        subTrack.setStory("2");
        subTrack.setTitle("Unit Test");
        subTrack.setStatus(TaskStatusEnum.OPEN.getStatus());
        taskProcessor.createSubTrack(subTrack);

        //1 subtask is created in setUp
        Assertions.assertTrue(2 == taskProcessor.stories.get(subTrack.getStory()).getSubtracks().size());

    }

    @Test
    public void testChangeStatusOfaTask() {
        setUp();
        String taskId = "1";
        taskProcessor.changeStatusOfTask(taskId);

        //1 subtask is created in setUp
        //previous status of the story is OPEN, so the updated one should be IN_PROGRESS
        Assertions.assertTrue(TaskStatusEnum.IN_PROGRESS.getStatus().equalsIgnoreCase(taskProcessor.tasks.get(taskId).getStatus()));

    }

    @Test
    public void testCreateSprint() {
        setUp();
        sprintProcessor.createSprint("2");

        //1 sprint is added in setUp
        //adding one more sprint should set the size to 2
        Assertions.assertTrue(2 == sprintProcessor.sprints.size());

    }

    @Test
    public void testDeleteSprint() {
        setUp();
        sprintProcessor.deleteSprint("1");

        //1 sprint is added in setUp
        //deleting one more sprint should set the size to 0
        Assertions.assertTrue(0 == sprintProcessor.sprints.size());

    }


    @Test
    public void testDeleteSprintException() {
        setUp();
        Boolean isDeleted = sprintProcessor.deleteSprint("2");

        //1 sprint is added in setUp
        //deleting one more sprint should set the size to 0
        Assertions.assertTrue(!isDeleted);

    }

    @Test
    public void testDisplayTaskByUserAndTaskType() {
        setUp();
        String result = taskProcessor.displayTaskByUserAndTaskType("Ryan", TaskTypeEnum.STORY.getType());
        System.out.println("###################");
        System.out.println(result);
        System.out.println("###################");

    }


    @Test
    public void testDisplayTaskByUser() {
        setUp();
        Task t1 = new Task();
        t1.setType(TaskTypeEnum.FEATURE.getType());
        t1.setAssignee("Ryan");
        t1.setTaskId("3");
        t1.setStatus(TaskStatusEnum.OPEN.getStatus());
        t1.setDueDate(new Date());
        t1.setTitle("Create Dashboard");

        taskProcessor.tasks.put(t1.getTaskId(), t1);

        String result = taskProcessor.displayTaskByUser("Ryan");
        System.out.println("###################");
        System.out.println(result);
        System.out.println("###################");

    }


}
