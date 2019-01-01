package it.federicoserini.beziercurvedsurfaces;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import it.federicoserini.beziercurvedsurfaces.drawer.LineDrawer;
import it.federicoserini.beziercurvedsurfaces.model.VertexCoordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String TITLE = "Bezier Surfaces";
            JFrame frame = new JFrame();

            FPSAnimator animator;

            Vector<VertexCoordinates> vertexCoordinates = new Vector<>();

            VertexCoordinates vertexA = new VertexCoordinates(rescale(-2), rescale(2), 1.0, "A");
            VertexCoordinates vertexB = new VertexCoordinates(rescale(-1), rescale(3), -1.0, "B");
            VertexCoordinates vertexC = new VertexCoordinates(rescale(1), rescale(3), 1.0, "C");
            VertexCoordinates vertexD = new VertexCoordinates(rescale(2), rescale(2), -1.0, "D");
            VertexCoordinates vertexE = new VertexCoordinates(rescale(-1.5), rescale(1), 1.0, "E");
            VertexCoordinates vertexF = new VertexCoordinates(rescale(-0.5), rescale(1.5), -1.0, "F");
            VertexCoordinates vertexG = new VertexCoordinates(rescale(1.5), rescale(1.5), 1.0, "G");
            VertexCoordinates vertexH = new VertexCoordinates(rescale(2.5), rescale(1), -1.0, "H");
            VertexCoordinates vertexI = new VertexCoordinates(rescale(-2.5), rescale(-1.0), 1.0, "I");
            VertexCoordinates vertexJ = new VertexCoordinates(rescale(-1.5), rescale(-0.5), -1.0, "J");
            VertexCoordinates vertexK = new VertexCoordinates(rescale(0.5), rescale(-0.5), 1.0, "K");
            VertexCoordinates vertexL = new VertexCoordinates(rescale(1.5), rescale(-1), -1.0, "L");
            VertexCoordinates vertexM = new VertexCoordinates(rescale(-2), rescale(-2), 1.0, "M");
            VertexCoordinates vertexN = new VertexCoordinates(rescale(-1), rescale(-1), -1.0, "N");
            VertexCoordinates vertexO = new VertexCoordinates(rescale(1), rescale(-1), 1.0, "O");
            VertexCoordinates vertexP = new VertexCoordinates(rescale(2), rescale(-2), -1.0, "P");

            vertexCoordinates.add(vertexA);
            vertexCoordinates.add(vertexB);
            vertexCoordinates.add(vertexC);
            vertexCoordinates.add(vertexD);
            vertexCoordinates.add(vertexE);
            vertexCoordinates.add(vertexF);
            vertexCoordinates.add(vertexG);
            vertexCoordinates.add(vertexH);
            vertexCoordinates.add(vertexI);
            vertexCoordinates.add(vertexJ);
            vertexCoordinates.add(vertexK);
            vertexCoordinates.add(vertexL);
            vertexCoordinates.add(vertexM);
            vertexCoordinates.add(vertexN);
            vertexCoordinates.add(vertexO);
            vertexCoordinates.add(vertexP);

            GLCanvas canvas =  new GLCanvas();
            canvas.setPreferredSize(new Dimension(1024, 1024));
            animator = new FPSAnimator(canvas, 60, true);

            canvas.addGLEventListener(new LineDrawer(vertexCoordinates));
            frame.getContentPane().add(canvas);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Use a dedicate thread to run the stop() to ensure that the
                    // animator stops before program exits.
                    new Thread(() -> {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }).start();
                }
            });

            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            animator.start();
        });

    }

    private static double rescale(double valueToScale){
        return (0 + (valueToScale - 0) * ((0.0 - 0.1) / (1)));
    }
}
