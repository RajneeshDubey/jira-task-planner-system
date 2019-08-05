package com.rjnsh.tps.exeptions;

public class SprintNotFoundException extends RuntimeException  {

    public SprintNotFoundException(){}
    public SprintNotFoundException(String msg){
        super(msg);
    }
}
