package com.briansemrad.graph.parallel;

/**
 *
 * @author Brian Semrad
 */
public class NodeIdentifier {

    private final String id;

    public NodeIdentifier(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof NodeIdentifier) {
            @SuppressWarnings("unchecked")
            NodeIdentifier identifier = (NodeIdentifier) obj;
            isEqual = id.equals(identifier.getIdentifier());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
