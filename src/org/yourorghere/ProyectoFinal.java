package org.yourorghere;

/*Se importan las librerias necesarias*/
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.KeyListener;
import java.security.PublicKey;
 
/**
 * 
@EQUIPO 6 
 * ISC
 * @author Aureliano Josafat
 * */

public class ProyectoFinal extends JFrame implements KeyListener{
    

    static GL gl;
    static GLU glu;
   private float rotateX, rotateY;  
    public ProyectoFinal (){
        setTitle("PROYECTO FINAL (TETERA 3D) ");
        setSize(600, 400);
        //Instanciamos la clase Graphic
        GraphicListener listener = new GraphicListener();
        //Creamos el canvas
        GLCanvas canvas = new GLCanvas(new GLCapabilities());
        canvas.addGLEventListener(listener);
        getContentPane().add(canvas);
        //la funcion de animacion
        Animator animator = new Animator(canvas );
        animator.add(canvas);
        animator.start(); 
        addKeyListener(this);
     
        
        rotateX = 20;  
	rotateY = -30;
       
    } 
    
    public static void main (String args[]){
        ProyectoFinal frame = new ProyectoFinal();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

          public GLUT glut = new GLUT();

    
     public class GraphicListener implements GLEventListener{
         
         //Definimos la Traslación,Escalacion y Rotación
          public void display(GLAutoDrawable arg0) {
            
                //definimos los colores de un material
                       float  mat_ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};
                       float mat_diffuse[] = {1.0f, 0.0f, 0.0f, 0.0f};
                       float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};

                   gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);   
                   gl.glLoadIdentity();	
                   
                  //traslacion
                   gl.glTranslatef(0.0f,0.0f,-10.0f);	
               
                  // Rotacion de 20 grados en torno al eje x
                   gl.glRotatef(rotateX, 1.0f, 0.0f, 0.0f);
                  // Rotacion de -30 grados en torno al eje y
                   gl.glRotatef(rotateY, 0.0f, 1.0f, 0.0f);
               
                   
                  // Dibujamos una "Tetera" y le aplico el material
                   gl.glPushMatrix();// copia de la matriz superior
                  
                  // escalamiento
                   gl.glScalef(1.0f, 1.1f, 1.0f );
                               

                //aplico el material
                gl.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient,0);
                gl.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse,0);
                gl.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular,0);
                gl.glMaterialf (GL.GL_FRONT, GL.GL_SHININESS, 50.0f);

                //este objeto esta definido en GLUT
               glut.glutSolidTeapot(2.5); 
               
               //gl.glFlush(); 
                //intercambio los Buffers
                //glutSwapBuffers();             
                           //Se acaba el dibujado de puntos
                         gl.glEnd();

                          gl.glFlush();


        }
      
        //inicializo la fuente de luz y las demas variables(relleno, iluminación y sombreado)
        public void init(GLAutoDrawable arg0) {
            glu = new GLU();
            gl = arg0.getGL();
            gl.glClearColor(0, 0, 0, 0);
         //iluminaciones
            float light_ambient[] = { 0.75f, 0.75f, 0.75f, 1.0f };// luz Ambiente
            float light_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };//luz Difusa
            float light_specular[] = { 10.0f, 1.0f, 1.0f, 1.0f };//luz especular
            float light_position[] = { 0.3f, 0.25f, 1.0f, 0.0f };//luz Posicion

            gl.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient,0);
            gl.glLightfv (GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse,0);  
            gl.glLightfv (GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular,0);
            gl.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, light_position,0);
             
            //la fuente de luz se activa
            gl.glEnable (GL.GL_LIGHTING);
            gl.glEnable (GL.GL_LIGHT0);
            
            //sombreado cara suave
            gl.glShadeModel(GL.GL_SMOOTH);
            //cara plana
            //gl.glShadeModel(GL.GL_FLAT);
            gl.glClearDepth(1.0f);			            
            gl.glEnable(GL.GL_DEPTH_TEST);			    
           gl. glDepthFunc(GL.GL_LEQUAL);
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); //relleno de fondo
        
            
           
            
        }
                //metodo remodelación vacía
		
        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

             if (height <= 0) { 
              height = 1;
        }
                final float h = (float) width / (float) height;
                gl.glViewport(0, 0, width, height);
                gl.glMatrixMode(GL.GL_PROJECTION);
                gl.glLoadIdentity();
                glu.gluPerspective(45.0f, h, 1.0, 20.0);
                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glLoadIdentity();
        
        }
      
            public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
 
        }

       
      }
     
     //////////////Agregamos la funcion del teclado para que tenga animacion en giro en cada direccion
     @Override
     public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();  // 
		if ( key == KeyEvent.VK_LEFT )
			rotateY -= 15;
		else if ( key == KeyEvent.VK_RIGHT )
			rotateY += 15;
		else if ( key == KeyEvent.VK_DOWN)
			rotateX += 15;
		else if ( key == KeyEvent.VK_UP )
			rotateX -= 15;
		else if ( key == KeyEvent.VK_HOME )
			rotateX = rotateY = 0;
		repaint();
	}
//metodo para que funcionen las teclas "w,s,a,d"
	@Override
	public void keyTyped(KeyEvent e) { 
		if( e.getKeyChar()== 'w'|| e.getKeyChar()== 'W'|| e.getExtendedKeyCode()== KeyEvent.VK_UP ){
                  rotateX -= 15;
                 }
               if( e.getKeyChar()== 's'|| e.getKeyChar()== 'S'|| e.getExtendedKeyCode()== KeyEvent.VK_DOWN ){
                rotateX += 15;
                }
               if( e.getKeyChar()== 'a'|| e.getKeyChar()== 'A'|| e.getExtendedKeyCode()== KeyEvent.VK_LEFT){
                 rotateY -= 15;
                }
                if( e.getKeyChar()== 'd'|| e.getKeyChar()== 'D'|| e.getExtendedKeyCode()== KeyEvent.VK_RIGHT ){
                rotateY += 15;
                }
                
                    
	}
            @Override
	public void keyReleased(KeyEvent e) { 
	}
   
  }

