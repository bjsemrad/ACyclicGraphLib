package com.briansemrad.graph.parallel;

/**
 * A Class to represent the edge between two nodes
 * @author Brian Semrad
 */
public class Edge {

    private final NodeIdentifier from;
    private final NodeIdentifier to;

    public NodeIdentifier getFrom() {
        return from;
    }

    public NodeIdentifier getTo() {
        return to;
    }

    public Edge(NodeIdentifier from, NodeIdentifier to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof Edge) {
            @SuppressWarnings("unchecked")
            Edge e = (Edge) obj;
            isEqual = e.getFrom().equals(from) && e.getTo().equals(to);
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.from.hashCode() + this.to.hashCode();
    }
}