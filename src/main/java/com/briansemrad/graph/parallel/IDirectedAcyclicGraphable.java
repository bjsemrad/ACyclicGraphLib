package com.briansemrad.graph.parallel;
import java.util.List;
/**
 * An interface used to describe an object which can be sorted via a topological sort.
 * It defines and ID and various methods for getting a list of dependencies from it.
 * @author Brian Semrad
 */
public interface IDirectedAcyclicGraphable {
    
    /**
     * Gets identifier for the node
     * @return 
     */
    public NodeIdentifier getIdentifier();
        
    /**
     * Gets a list of NodeIdentifiers that this item depends on
     * @return 
     */
    public List<NodeIdentifier> getDependencyList();

}
