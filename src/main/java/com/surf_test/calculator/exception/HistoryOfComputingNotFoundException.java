package com.surf_test.calculator.exception;

public class HistoryOfComputingNotFoundException extends RuntimeException {
    public HistoryOfComputingNotFoundException(String id){
        super("Could not find History Of Computing " + id);
    }
}
