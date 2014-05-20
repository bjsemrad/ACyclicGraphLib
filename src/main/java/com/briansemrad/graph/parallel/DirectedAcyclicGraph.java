package com.briansemrad.graph.parallel;

import com.briansemrad.graph.parallel.exception.DependencyCycleException;

import java.util.*;
import java.util.Map.Entry;

/**
 * A class to represent a Directed Acyclic Graph this can be used to get a ordered
 * list of items in proper order.
 * @author Brian Semrad
 * @param <T>  
 */
public class DirectedAcyclicGraph<T extends IDirectedAcyclicGraphable> {

    private final Map<NodeIdentifier, Node<T>> nodeMap;
    private final List<Node<T>> sortedElements;

    /**
     * Constructs and initializes the graph
     */
    public DirectedAcyclicGraph() {
        nodeMap = new HashMap<NodeIdentifier, Node<T>>();
        sortedElements = new ArrayList<Node<T>>();
    }

    /**
     * Adds a node to the graph
     * @param n The node to add
     * @throws com.briansemrad.graph.parallel.exception.DependencyCycleException
     */
    public void addNode(Node<T> n) throws DependencyCycleException {
        nodeMap.put(n.getId(), n);
    }
    
    /**
     * Adds the collection of nodes to the graph
     * @param graphItems 
     */
    public void addNodes(List<Node<T>> graphItems) {
        for (Node<T> node : graphItems) {
            nodeMap.put(node.getId(), node);
        }
    }

    /**
     * Builds a topological list of the nodes
     * @return a list of ordered nodes
     * @throws com.briansemrad.graph.parallel.exception.DependencyCycleException
     */
    public List<Node<T>> buildTopologicalList() throws DependencyCycleException {
        Set<Node<T>> noInComingEdgesList = new HashSet<Node<T>>();
        Iterator<Entry<NodeIdentifier, Node<T>>> itNode = nodeMap.entrySet().iterator();
        while (itNode.hasNext()) {
            Entry<NodeIdentifier, Node<T>> nextItem = itNode.next();
            Node<T> node = nextItem.getValue();
            if (node.getInEdges().isEmpty()) {
                noInComingEdgesList.add(node);
            }
        }

        while (!noInComingEdgesList.isEmpty()) {
            Node<T> n = noInComingEdgesList.iterator().next();
            noInComingEdgesList.remove(n);
            sortedElements.add(n);
            for (Iterator<Edge> it = n.getOutEdgesIterator(); it.hasNext();) {
                Edge e = it.next();
                Node<T> m = nodeMap.get(e.getTo());
                m.removeInEdge(e);
                if (m.getInEdges().isEmpty()) {
                    noInComingEdgesList.add(m);
                }
            }
        }

        boolean cycle = false;
        Iterator<Node<T>> sortedNodeIt = sortedElements.iterator();
        while (sortedNodeIt.hasNext()) {
            Node<T> node = sortedNodeIt.next();
            if (!node.getInEdges().isEmpty()) {
                cycle = true;
                break;
            }
        }

        if (cycle) {
            throw new DependencyCycleException("Unabled to build a Topological List, cannot process tasks with a cycle in them.");
        }
        return Collections.unmodifiableList(sortedElements);
    }
}
