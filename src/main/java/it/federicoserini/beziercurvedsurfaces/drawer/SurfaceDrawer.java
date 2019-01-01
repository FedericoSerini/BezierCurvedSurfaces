package it.federicoserini.beziercurvedsurfaces.drawer;

import it.federicoserini.beziercurvedsurfaces.model.*;

import java.util.Vector;

public class SurfaceDrawer implements SurfaceDrawerInterface{

    @Override
    public Surface prepareSurface(int precision, double parameterFirstCurve, double parameterSecondCurve, Curve curve1, Curve curve2, Vector<VertexCoordinates> vertices) {
        Surface surface = new Surface();
        surface.setCurve1(curve1);
        surface.setCurve2(curve2);
        surface.setCoordinates(traceSurface(precision, parameterFirstCurve, parameterSecondCurve, vertices));
        return surface;
    }

    private Vector<double[][]> traceSurface(int precision, double parameterFirstCurve, double parameterSecondCurve, Vector<VertexCoordinates> vertices){
        double parameterB = 1 - parameterFirstCurve; // The point of the first curve goes A (parameterFirstCurve) to B (parameterB)
        double parameterD = 1 - parameterSecondCurve; // The point of the second curve goes C (parameterSecondCurve) to D (parameterD)
        Vector<double[][]> surfaceCoords = new Vector<>();
        double Xv[][] = new double[21][21];
        double Yv[][] = new double[21][21];
        double Zv[][] = new double[21][21];

        for (int i = 0; i <= precision; i++) {

            Vector<Double> calculatedBinomialFirstCurve = binomialCalculus(parameterFirstCurve, parameterB, 3);

            for (int j = 0; j <= precision; j++) {
                Vector<Double> calculatedBinomialSecondCurve = binomialCalculus(parameterSecondCurve, parameterD, 3);
                Vector<Double> mulBinomial = binomialMultiplication(calculatedBinomialFirstCurve, calculatedBinomialSecondCurve); // (a+b)^n * (c+d)^n

                for (int k=0; k < vertices.size(); k++) { // DRAWING COORDINATES FOR EACH VERTEX
                    Xv[i][j] = Xv[i][j] + vertices.get(k).getX() * mulBinomial.get(k);
                    Yv[i][j] = Yv[i][j] + vertices.get(k).getY() * mulBinomial.get(k);
                    Zv[i][j] = Zv[i][j] + vertices.get(k).getZ() * mulBinomial.get(k);
                }
                parameterSecondCurve = parameterSecondCurve - (1.0/precision);
                parameterD = 1.0 - parameterSecondCurve;
            }

            parameterFirstCurve = parameterFirstCurve - (1.0/precision);
            parameterB = 1.0 - parameterFirstCurve;

            parameterSecondCurve = 1.0;
            parameterD = 1.0 - parameterSecondCurve;
        }


        surfaceCoords.add(Xv);
        surfaceCoords.add(Yv);
        surfaceCoords.add(Zv);
        return surfaceCoords;
    }

    /**
     * <p> Resolve all binomial of type (a+b) through Pascal Triangle </p>
     * @param parameter a
     * @param parameterB b
     * @param grade grade of the binomial
     * @return A Vector with calculated values of the binomial
     */
    private Vector<Double> binomialCalculus(double parameter, double parameterB, int grade){
        System.out.println("SOLVING "+grade+" GRADE BINOMIAL ..");
        Vector<Double> calculatedBinomial = new Vector<>();
        PascalTriangle pascalTriangle = pascalTriangle(grade);

        Vector<Integer> coefficients = pascalTriangle.getLayers().get(grade);

        for(int i = 0; i < coefficients.size(); i++){ // Calculating values of the binomial
            System.out.println(coefficients.get(i)+"* " +"a^"+(grade-i)+" * b^"+i);
            calculatedBinomial.add(coefficients.get(i) * (Math.pow(parameter, grade - i)) * Math.pow(parameterB, i));
        }

        return calculatedBinomial;
    }

    private Vector<Double> binomialMultiplication(Vector<Double> firstBinomial, Vector<Double> secondBinomial){
        Vector<Double> multBinomial = new Vector<>();

        for (Double firstTerm : firstBinomial) {
            for (Double secondTerm : secondBinomial) {
                multBinomial.add((double) firstTerm * secondTerm);
            }
        }
        return multBinomial;
    }

    /**
     * <p> Build a Pascal Triangle</p>
     * @param grade The grade of the binomial
     * @return A PascalTriangle object with depth (rows) and a vector of vectors of layers (divided by grades)
     */
    private PascalTriangle pascalTriangle(int grade){
        System.out.println("SOLVING PASCAL TRIANGLE");
        PascalTriangle pascalTriangle = new PascalTriangle();
        Vector<Vector<Integer>> layers = new Vector<>();
        pascalTriangle.setDepth(grade);

        int coefficient = 1, i, j;

        for(i=0; i<=pascalTriangle.getDepth(); i++) {
            Vector<Integer> layer = new Vector<>();

            for(j=0; j <= i; j++) {
                if (j==0 || i==0)
                    coefficient = 1;
                else
                    coefficient = coefficient*(i-j+1)/j;

                layer.add(coefficient);
            }
            layers.add(layer);
        }
        System.out.println("PASCAL TRIANGLE RESOLVED");

        pascalTriangle.setLayers(layers);
        return pascalTriangle;
    }
}
