package it.federicoserini.beziercurvedsurfaces.model;

public class VertexCoordinates extends Coordinates {
    private String vertex;

    public VertexCoordinates(double x, double y, double z, String vertex){
       super(x,y,z);
       this.vertex = vertex;
    }

    public String getVertex() { return vertex; }

    public void setVertex(String vertex) { this.vertex = vertex; }
}
