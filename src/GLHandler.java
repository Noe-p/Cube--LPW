import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class GLHandler implements GLEventListener {
	
	private GLU glu;
	private float angle, angle2; 
	private ArrayList<Shape3D> items3D;
	
	
	public GLHandler()
	{
		this.glu = new GLU();
		this.angle = 0;
		this.angle2 = 0; 
		this.items3D = new ArrayList<Shape3D>();
		createCube(5);
	}
	
	public void createCube(int nbCube) {
		float y = -14f;
		for(float i=nbCube; i>0; i--) {
			this.items3D.add(new Cube3D(-1f, y, -50f, i));
			y = y + i + (i - 1);
		}
	}
	
	public void display(GLAutoDrawable draw) {
		// TODO Auto-generated method stub
		GL2 gl = draw.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		for(int i=0; i<this.items3D.size(); i++) {
			if (i%2 == 0) this.items3D.get(i).display(gl, this.angle); 
			else this.items3D.get(i).display(gl, this.angle2);
		}
		
		this.angle += 0.1;
		this.angle2 -= 0.1;
		
	}

	// LORSQUE L'ON QUITTE 
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	
	// INITIALISATION
	public void init(GLAutoDrawable draw ){
		// TODO Auto-generated method stub
		GL2 gl = draw.getGL().getGL2();
		gl.glClearColor(0,0,0,0);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
	}
	

	public void reshape(GLAutoDrawable draw, int x, int y, int width, int height) {
		GL2 gl = draw.getGL().getGL2();
		float aspect = (float) width / height; 
		gl.glViewport(0,0,width,height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(45.0, aspect, 0.1, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
