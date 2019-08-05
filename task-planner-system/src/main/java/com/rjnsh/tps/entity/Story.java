package com.rjnsh.tps.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Story implements Serializable {

    private String taskId;

    private String sprint;

    private String summary;

    private List<SubTrack> subtracks;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<SubTrack> getSubtracks() {
        return subtracks;
    }

    public void setSubtracks(List<SubTrack> subtracks) {
        this.subtracks = subtracks;
    }

}
