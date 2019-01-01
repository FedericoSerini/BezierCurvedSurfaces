package it.federicoserini.beziercurvedsurfaces.model;


import java.util.Vector;

public class Surface {
    private Curve curve1;
    private Curve curve2;
    private Vector<double[][]> coordinates;

    public Surface(){

    }

    public Curve getCurve1() {
        return curve1;
    }

    public void setCurve1(Curve curve1) {
        this.curve1 = curve1;
    }

    public Curve getCurve2() {
        return curve2;
    }

    public void setCurve2(Curve curve2) {
        this.curve2 = curve2;
    }

    public Vector<double[][]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector<double[][]> coordinates) {
        this.coordinates = coordinates;
    }
}
