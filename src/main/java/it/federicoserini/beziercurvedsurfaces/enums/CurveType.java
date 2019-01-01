package it.federicoserini.beziercurvedsurfaces.enums;

public enum CurveType {
    SIMPLE(2), QUADRATIC(3), CUBIC(4), TH_GRADE(5);

    private final int numberOfVertices;

    CurveType(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
    }

    public int getNumberOfVertices(){
        return numberOfVertices;
    }
}
