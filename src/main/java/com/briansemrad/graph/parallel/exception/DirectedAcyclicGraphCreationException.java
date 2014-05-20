package com.briansemrad.graph.parallel.exception;


/**
 * TODO
 * @author Brian Semrad
 */
public class DirectedAcyclicGraphCreationException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs an instance of <code>DirectedAcyclicGraphCreationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DirectedAcyclicGraphCreationException(String msg) {
        super(msg);
    }
    
    public DirectedAcyclicGraphCreationException(String msg, Throwable underlyingError) {
        super(msg, underlyingError);
    }
}
