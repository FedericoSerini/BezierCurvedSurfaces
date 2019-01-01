package it.federicoserini.beziercurvedsurfaces.drawer;

import it.federicoserini.beziercurvedsurfaces.model.Curve;
import it.federicoserini.beziercurvedsurfaces.model.Surface;
import it.federicoserini.beziercurvedsurfaces.model.VertexCoordinates;

import java.util.Vector;

public interface SurfaceDrawerInterface {
    Surface prepareSurface(int precision, double parameterFirstCurve, double parameterSecondCurve, Curve curve1, Curve curve2,  Vector<VertexCoordinates> vertices);
}
