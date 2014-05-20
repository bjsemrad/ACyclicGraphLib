package com.briansemrad.graph.parallel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This is a Node object inside of a graph that contains and id, in and out edges, a processing state, 
 * and some T data (aka task) being ordered.
 * @author Brian Semrad
 * @param <T> 
 */
public class Node<T extends IDirectedAcyclicGraphable> {

    private final NodeIdentifier id;
    private final Set<Edge> inEdges;
    private final Set<Edge> outEdges;
    private NodeState state;
    private final T data;

    public Node(final NodeIdentifier id, final T data) {
        this.id = id;
        inEdges = new HashSet<Edge>();
        outEdges = new HashSet<Edge>();
        this.data = data;
        state = NodeState.NOT_PROCESSED;
    }

    public T getData() {
        return data;
    }

    public Set<Edge> getInEdges() {
        return Collections.unmodifiableSet(inEdges);
    }

    public void removeInEdge(final Edge e) {
        inEdges.remove(e);
    }

    public Iterator<Edge> getInEdgesIterator() {
        return inEdges.iterator();
    }

    public Set<Edge> getOutEdges() {
        return Collections.unmodifiableSet(outEdges);
    }

    public void removeOutEdge(final Edge e) {
        outEdges.remove(e);
    }

    public Iterator<Edge> getOutEdgesIterator() {
        return outEdges.iterator();
    }

    public Node<T> addEdge(final Node<T> node) {
        final Edge e = new Edge(getId(), node.getId());
        addOutEdge(e);
        node.addInEdge(e);
        return this;
    }

    public NodeIdentifier getId() {
        return id;
    }

    public void addOutEdge(final Edge e) {
        outEdges.add(e);
    }

    public void addInEdge(final Edge e) {
        inEdges.add(e);
    }

    public void addOutEdges(final Set<Edge> e) {
        outEdges.addAll(e);
    }

    public void addInEdges(final Set<Edge> e) {
        inEdges.addAll(e);
    }

    @Override
    public String toString() {
        return id.getIdentifier();
    }

    public void markInProcess() {
        synchronized (id) {
            state = NodeState.IN_PROCESS;
        }
    }

    public void markCompleted() {
        synchronized (id) {
            state = NodeState.COMPLETED;
        }
    }

    public void markFailed() {
        synchronized (id) {
            state = NodeState.FAILED;
        }
    }

    public boolean isInCompletedState() {
        return (state == NodeState.COMPLETED || state == NodeState.FAILED);
    }

    public boolean isInProcessState() {
        return (state == NodeState.IN_PROCESS);
    }

    public NodeState getState() {
        return state;
    }

    public void setState(final NodeState state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node<T> other = (Node<T>) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
