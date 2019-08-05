package com.rjnsh.tps.entity;

import java.io.Serializable;
import java.util.Objects;

public class SubTrack implements Serializable {

    private String story;

    private String title;

    private String status;

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
