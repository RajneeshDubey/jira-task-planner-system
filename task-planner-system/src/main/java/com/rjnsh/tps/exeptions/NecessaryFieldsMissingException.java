package com.rjnsh.tps.exeptions;

public class NecessaryFieldsMissingException extends RuntimeException {
    public NecessaryFieldsMissingException(){}
    public NecessaryFieldsMissingException(String msg){
        super(msg);
    }

}
