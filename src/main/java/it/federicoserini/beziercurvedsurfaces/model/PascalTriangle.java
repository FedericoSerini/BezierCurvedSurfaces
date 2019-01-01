package it.federicoserini.beziercurvedsurfaces.model;

import java.util.Vector;

public class PascalTriangle {
    private int depth;
    private Vector<Vector<Integer>> layers;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Vector<Vector<Integer>> getLayers() {
        return layers;
    }

    public void setLayers(Vector<Vector<Integer>> layers) {
        this.layers = layers;
    }
}
