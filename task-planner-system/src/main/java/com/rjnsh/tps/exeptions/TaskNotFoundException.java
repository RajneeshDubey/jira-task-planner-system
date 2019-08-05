package com.rjnsh.tps.exeptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(){}
    public TaskNotFoundException(String msg){
        super(msg);
    }

}

