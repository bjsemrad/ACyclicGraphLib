package com.briansemrad.graph.parallel.exception;


/**
 * TODO
 * @author Brian Semrad
 */
public class DependencyCycleException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public DependencyCycleException(String message){
        super(message);
    }
    
}
