package it.federicoserini.beziercurvedsurfaces.drawer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import it.federicoserini.beziercurvedsurfaces.model.Surface;
import it.federicoserini.beziercurvedsurfaces.model.VertexCoordinates;

import java.util.Vector;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;

public class LineDrawer extends GLCanvas implements GLEventListener {

    private final Vector<VertexCoordinates> vertexCoordinates;
    private final Surface surface;
    private GLUT glut;
    private GLU glu;
    private GL2 gl;

    public LineDrawer(Vector<VertexCoordinates> vertexCoordinates) {
        this.vertexCoordinates = vertexCoordinates;
        SurfaceDrawer surfaceDrawer = new SurfaceDrawer();
        this.surface = surfaceDrawer.prepareSurface(20, 1,1,null, null, vertexCoordinates);
        this.addGLEventListener(this);
    }

    @Override
    /* TODO replace deprecated api calls (glBegin ..) with a VBO */
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();      // get the OpenGL graphics context
        glut = new GLUT();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix
        this.gl = gl;
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glPointSize(8);
        gl.glColor3f(1,1,0);

        gl.glBegin(GL.GL_POINTS); // drawing vertices points
        for(VertexCoordinates coordinates : vertexCoordinates){
            gl.glVertex3d(coordinates.getX(), coordinates.getY(), coordinates.getZ());
        }
        gl.glEnd();

        for(VertexCoordinates coordinates : vertexCoordinates){ // drawing labels
            gl.glRasterPos3d(coordinates.getX(), coordinates.getY(), coordinates.getZ());
            glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, coordinates.getVertex());
        }

        double[][] Xv = surface.getCoordinates().get(0);
        double[][] Yv = surface.getCoordinates().get(1);
        double[][] Zv = surface.getCoordinates().get(2);


        for (int i = 0; i < 20; i++) {
            gl.glBegin(GL.GL_TRIANGLE_STRIP); // drawing bezier surface
            gl.glColor3f(5, 5, 5);
            for (int j = 0; j <= 20; j++) {
                gl.glVertex3d(Xv[i][j], Yv[i][j], Zv[i][j]);
                gl.glVertex3d(Xv[i+1][j], Yv[i+1][j], Zv[i+1][j]);
            }
            gl.glEnd();
        }


    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        gl.glViewport(0, 0, 1024, 1024);
    }
}
