
import java.awt.Font;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.TexCube;
import objects3D.TexSphere;
import objects3D.Text;
import objects3D.Bench;
import objects3D.Bin;
import objects3D.Camera;
import objects3D.Car;
import objects3D.Cube;
import objects3D.Cylinder;
import objects3D.Animal;
import objects3D.Geometry;
import objects3D.Grid;
import objects3D.Human;
import objects3D.Light;
import objects3D.MoveHuman;
import objects3D.Saler;
import objects3D.Sphere; 

//...........
//.......
//This is a car anime, please wait for about 5 second, the car will appear.
//.....
//......

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private  boolean MouseOnepressed = true;
	private boolean  dragMode=false;
	private boolean BadAnimation =false;
	private boolean Earth= false;
	private int windowHeight=1200;
	private int windowWidth=800;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	TrueTypeFont awtFont;
	
	long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	long StartTime; // beginAnimiation 

	Arcball MyArcball= new Arcball();
	
	boolean DRAWGRID =false;
	boolean waitForKeyrelease= true;
	/** Mouse movement */
	int LastMouseX = -1 ;
	int LastMouseY= -1;
	
	 float pullX = 0.0f; // arc ball  X cord. 
	 float pullY = 0.0f; // arc ball  Y cord. 

	 
	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project 
	
	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	
	Camera camera;
	Car car;
	int renderTime;

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process 
	

	public void start() {
		
		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		camera = new Camera();

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public int grabTime;
	
	public void update(int delta) {
		// rotate quad
		//rotation += 0.01f * delta;
		  
		  
		int MouseX= Mouse.getX();
		  int MouseY= Mouse.getY();
		  int WheelPostion = Mouse.getDWheel();
	  
		  
		  boolean  MouseButonPressed= Mouse.isButtonDown(0);
		  
		 
		  
		  if(MouseButonPressed && !MouseOnepressed )
		  {
			  MouseOnepressed =true;
			//  System.out.println("Mouse drag mode");
			  MyArcball.startBall( MouseX, MouseY, 1200, 800);
			  dragMode=true;
				
				
		  }else if( !MouseButonPressed)
		  { 
				// System.out.println("Mouse drag mode end ");
			  MouseOnepressed =false;
			  dragMode=false;
		  }
		  
		  if(dragMode)
		  {
			  MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
		  }
		  
		  if(WheelPostion>0)
		  {
			  OrthoNumber += 100;
			 
		  }
		  
		  if(WheelPostion<0)
		  {
			  OrthoNumber -= 100;
			  if( OrthoNumber<210)
			  {
				  OrthoNumber=210;
			  }
			  
			//  System.out.println("Orth nubmer = " +  OrthoNumber);
			  
		  }
		  
		  /** rest key is R*/
		  if (Keyboard.isKeyDown(Keyboard.KEY_R))
			  MyArcball.reset();
		  
		  /* bad animation can be turn on or off using A key)*/
		  
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			camera.goForwardz(delta*3.0f);
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			camera.goForwardz(-delta*3.0f);

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			//rotation += 0.35f * delta;
			camera.YawCamera(delta*0.035f);
		if (Keyboard.isKeyDown(Keyboard.KEY_E))
			//rotation += 0.35f * delta;
			camera.YawCamera(-delta*0.035f);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			//camera.goForwardz(-delta*0.5f);
			MyHuman.pos_z-=delta;
			camera.goForwardz(-delta*0.4f);
				MyHuman.isMove=true;
		MyHuman.rotate(0);} 
			
			

		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			//camera.goForwardz(delta*0.5f);
			MyHuman.pos_z+=delta;
			MyHuman.rotate(180);
			camera.goForwardz(delta*0.3f);
			MyHuman.isMove=true;
		}
					;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			//camera.goForwardz(-delta*0.5f);
			MyHuman.pos_x+=delta;
			camera.goForwardx(delta*0.5f);
				MyHuman.isMove=true;
			MyHuman.rotate(270);}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)) {
			this.isGrab=true;
			this.grabTime=(int) (this.getTimeInsecond()+2);
			
		}
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_F)) {
			this.isGrab = false;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			//camera.goForwardz(delta*0.5f);
			MyHuman.pos_x-=0.55f*delta;
			camera.goForwardx(-delta*0.3f);
			
				MyHuman.isMove=true;
			MyHuman.rotate(90);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			//camera.goForwardz(delta*0.5f);
			MyHuman.jump();
			
			
		}
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			MyHuman.isJump=false;
		}
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&
				!Keyboard.isKeyDown(Keyboard.KEY_DOWN)&&!Keyboard.isKeyDown(Keyboard.KEY_UP))
			MyHuman.isMove=false;
			//System.out.println(MyHuman.angle);
			
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
				camera.goForwardx(-delta*3.0f);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			camera.goForwardx(delta*3.0f);
		if(Keyboard.isKeyDown(Keyboard.KEY_C))
			camera.PitchCamera(delta*0.035f);
		if(Keyboard.isKeyDown(Keyboard.KEY_V))
			camera.PitchCamera(-delta*0.035f);
		if(Keyboard.isKeyDown(Keyboard.KEY_H))
			System.out.println(camera);
		
		 if(waitForKeyrelease) // check done to see if key is released 
		 {
		if (Keyboard.isKeyDown(Keyboard.KEY_G))
		{
			
			DRAWGRID = !DRAWGRID;
			Keyboard.next();
			if(Keyboard.isKeyDown(Keyboard.KEY_G))
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;
				
			}
		}
		 }
		 
		 /** to check if key is released */
		 if(Keyboard.isKeyDown(Keyboard.KEY_G)==false)
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;
				
			}
		 
		 
		 
			

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
			
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, this.windowWidth,this.windowHeight); 
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();
		
		
		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(-1000f).put(0).flip();
		
		FloatBuffer lightPos5 = BufferUtils.createFloatBuffer(4);
		lightPos5.put(0f).put(0f).put(0.f).put(1000.f).flip();
		
		//GL11.glLight(GL11.GL_LIGHT5, GL11.GL_POSITION, lightPos5);
		
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, Utils.ConvertForGL(white));															// position
																	// of the
																	// light
		 //GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange 

		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); // specify the
															// position
																	// of the
																	// light
		//GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));
		GL11.glEnable(GL11.GL_LIGHT1);	
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
																	// the
																	// position
																	// of the
																	// light
		//GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));
		GL11.glEnable(GL11.GL_LIGHT2);	
		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
																	// the
																	// position
																	// of the
																	// light
	//L11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));
		 GL11.glEnable(GL11.GL_LIGHT3);
		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
											// on
	 	GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		   GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load in texture
          
         initObject();
	}
	
	int stopTime;
	int stopTime2;
	int j;
	int cameraTime;
	int tempCameraTime;
	boolean traceCamera;
	Animal dog;
	MoveHuman MyHuman;
	Saler s;
	ArrayList<MoveHuman> theifs = new ArrayList<MoveHuman>();
	ArrayList<Car> cars = new ArrayList<Car>();
	 Geometry geo;
	 Light l;
	 Bin bin1 ,bin2,bin3,bin4;
	 Bench b;
	 Car c;
	public void initObject() {
	
		renderTime=0;
		stopTime=0;
		stopTime2=0;
		j=0;
		cameraTime=0;
		traceCamera = false;
		geo = new Geometry(this.roadTex,this.woodwall,this.cityWall,this.building,this.shopBrand,this.glass,this.projSign);
		l=new Light(400,800,this.glass);
		bin1=new Bin(-259,2700,3541);
		bin2=new Bin(-6449,400,3481);
		bin3=new Bin(-2567,400,-5477);
		bin4=new Bin(30,400,5201);
		b=new Bench(-3200,-4800,woodwall);
		//theifs.add(new MoveHuman(glass,glass,-6400,0));
//		theifs.add(new MoveHuman(wood,wood,-3000f,700f,-4800));
		//dtheifs.add(new MoveHuman(glass,glass,-6400,400));
		dog=new Animal();
		MyHuman =new MoveHuman(head, body,0f,-2000f);
		float[] carPos = {-0f,400.0f,-800.0f};
		//GL11.glTranslatef(0, 400f, -800f);
		c= new Car(50.0f,carPos,wheel,wheel,wheel,glass);
		
	}
	
	
	boolean sign=false;
	boolean isTraffic=false;
	


	 
	
	

	public void changeOrth() {

		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
		//  GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
		  GL11.glOrtho(-this.windowWidth-this.OrthoNumber ,this.windowWidth/2+this.OrthoNumber , -this.windowHeight/2-this.OrthoNumber*(this.windowHeight/this.windowWidth) , this.windowHeight/2+this.OrthoNumber*(this.windowHeight/this.windowWidth), 100000, 
				  -100000);
		 	GL11.glMatrixMode(GL11.GL_MODELVIEW); 
		 	
		 	FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16); 
		 	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);
		 
		 //	if(MouseOnepressed)
		 //	{
		  
		 	MyArcball.getMatrix(CurrentMatrix); 
		 //	}
		 	
		    GL11.glLoadMatrix(CurrentMatrix);
		 	camera.applyCam();
	}
	
	 float delta;

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load 
	 * 
	 * 
	 */
	 
		public void drawspecHuman(MoveHuman human,float x,float y,float delta,float end) {
			//
			
			if(human!=null) {
			
			human.setAim(x, y);
			
	

			GL11.glPushMatrix();{
				//Human MyHuman = new Human();// change by arno
			//	GL11.glTranslatef(0,100.0f, 0);
				//MyHuman.gesture_Human_stop(delta);
				//MyHuman.gesture_Human_move(delta,MoveHuman.DIR_X,0.0f);
			//	MyHuman.setAim(-4800f, -4800.0f);
				if(end == MoveHuman.STATE_REST) {
					human.gesture_Human_goTo(delta);
				}else {
					human.gesture_Human_goTo_rotate(delta);
				}
				
				}GL11.glPopMatrix();
			}
			

			}
	 
//	public void drawHuman(float delta) {
//		//
//		
//		if(MyHuman!=null) {
//		
//		MyHuman.setAim(-2400, 0);
//		
//		float theta = (float) (delta * 2 * Math.PI);
//		 float thetaDeg = delta * 360; 
//		  float posn_x = (float) Math.cos(theta); // same as your circle code in your notes 
//		  float posn_y  = (float) Math.sin(theta);
//
//		GL11.glPushMatrix();{
//			//Human MyHuman = new Human();// change by arno
//		//	GL11.glTranslatef(0,100.0f, 0);
//			//MyHuman.gesture_Human_stop(delta);
//			//MyHuman.gesture_Human_move(delta,MoveHuman.DIR_X,0.0f);
//		//	MyHuman.setAim(-4800f, -4800.0f);
//			MyHuman.gesture_Human_goTo(delta);
//			}GL11.glPopMatrix();
//		}
//		
//
//		}
	
	public void drawDog() {
		GL11.glPushMatrix();
		GL11.glTranslatef(-2600.0f, 200.0f, 2000.0f);
		GL11.glScalef(
				1100f, 1100f, 1100f);

		 float theta = (float) (delta * 2 * Math.PI);
		 float thetaDeg = delta * 360; 
		  float posn_x = (float) Math.cos(theta); 
		  float posn_y  = (float) Math.sin(theta);
		  float offset = 180;
		  float angle = 360 - (thetaDeg +360)%360 + offset;
		  GL11.glTranslatef(posn_x*0.5f, 0.0f, posn_y*0.5f);
		  GL11.glRotated(angle, 0.0f, 0.5f, 0.0f);
		  GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
		dog.DrawDog(delta, Animal.STATE_DOGGO);
		GL11.glPopMatrix();
	}
	
	
	public boolean isDrive=false;
	
	int moveTime=10;
	public void renderGL() { 
		
		changeOrth();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		 
		 myDelta =   getTime() - StartTime; 
		  delta =((float) myDelta)/10000; 
		  renderTime+=1;   
		  // code to aid in animation 
		 float theta = (float) (delta * 2 * Math.PI);
		 
		  
		  /*
		   * This code draws a grid to help you view the human models movement 
		   *  You may change this code to move the grid around and change its starting angle as you please 
		   */
		  
		
		
		if(DRAWGRID)
		{
			

		GL11.glPushAttrib(GL11.GL_DEPTH_BUFFER_BIT|GL11.GL_COLOR_BUFFER_BIT);
		GL11.glDepthMask(false);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 0); 
		GL11.glScalef(10000f, 10000f, 10000f); 
		TexCube skybox = new TexCube(this.starNightSky);
		TexSphere sky = new TexSphere();
		sky.DrawTexSphere(1f, 32f, 32f, this.starNightSky);
		//skybox.DrawTexCube();
		GL11.glPopAttrib();
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glPushMatrix();
		Grid MyGrid = new Grid();
		GL11.glTranslatef(0, 0, 0); 
		GL11.glScalef(200f, 200f,  200f); 
		
		geo.DrawCoord();
		// MyGrid.DrawGrid();
		
		GL11.glPopMatrix();
		}
		
		
	//	Text.drawString("Hello", 100, 100, 0.01, 100);
		this.drawBin();
		drawBackground();
		GL11.glPushMatrix();{
			GL11.glScalef(0.5f, 0.5f, 0.5f);
		if(c!=null) {
		
			
			
			GL11.glPushMatrix();
			
			if(c.isMove==true) {
			
			c.x=c.x+moveTime*1;
			GL11.glTranslatef(c.x, c.y, c.z);}
			else {
				GL11.glTranslatef(c.x, c.y, c.z);
			}
			
			c.drawCar(delta);
			//c.setMove(true);
			
			GL11.glPopMatrix();}
		this.drawDog();
		GL11.glTranslatef(MyHuman.pos_x,MyHuman.pos_y, MyHuman.pos_z);
		GL11.glScalef(200f, 200f, 200f);
		
		if(MyHuman.isJump) {
			MyHuman.DrawTexHuman(delta, head, body, MyHuman.STATE_SIT);
		}else {
		if(MyHuman.isMove) {
			MyHuman.DrawTexHuman(delta, head, body, MyHuman.STATE_WAVE);
		}
		else {
			
			if(this.getTimeInsecond()<this.grabTime) {
				
				
				MyHuman.DrawTexHuman(delta,head,body,MyHuman.STATE_GRAB);
				
				if(!c.isFire) {
				if(!MyHuman.isCarry&&!MyHuman.isOperate) {
				if(this.getTimeInsecond()==this.grabTime) {
					Bin bin =this.whichBin();
					if(bin!=null) {
						MyHuman.isCarry=true;
						bin.isCollect=true;
					}
				}
				}else {
					if(MyHuman.isOperate && MyHuman.isCarry) {
						c.addEnergy();
						MyHuman.isCarry=false;
					}
					
				}
				}else {
					if(this.getTimeInsecond()==this.grabTime && MyHuman.isOperate) {
						c.isMove=true;
					}
				}
			
				
			}else {
				MyHuman.DrawTexHuman(delta, head, body, MyHuman.STATE_REST);
			}
		}
		}
		

		}GL11.glPopMatrix();
//	
		
		
	

	
	//Gravity
		MyHuman.pos_y-=60;
	//	if(MyHuman.pos_x0)
		if(MyHuman.pos_y<400) {
			MyHuman.pos_y=400;
			isGround=true;
			
		}
		if(MyHuman.pos_z<4500 && MyHuman.pos_z>2600) {
		if(MyHuman.pos_x>-1600 && MyHuman.pos_x<1600 ) {
			
			if(MyHuman.pos_y<2700) {
				MyHuman.pos_y=2700;
				
			}
			
		}
		
		
		
		if(MyHuman.pos_x>-5600 && MyHuman.pos_x<-2600 ) {
			if(MyHuman.pos_y<3100) {
				MyHuman.pos_y=3100;
				isGround=true;
			}	
		}
		
		
		if(MyHuman.pos_x>2400 && MyHuman.pos_x<5500 ) {
			if(MyHuman.pos_y<2700) {
				MyHuman.pos_y=2700;
				isGround=true;
			}
		}
		
		
		
		}
		
		
		if(c!=null && c.x>12800) {
			c=null;
		}
		eventsAtSecond(this.getTimeInsecond());

		if(MyHuman.pos_x>-1600 && MyHuman.pos_x<-1200 && MyHuman.pos_z>-1200 && MyHuman.pos_z<-600  ) {
				MyHuman.isOperate=true;
			
		}else {
			MyHuman.isOperate=false;
		}
		
		if(whichBin()!=null) {
			MyHuman.isBin=true;
		}else {
			MyHuman.isBin=false;
		}
	}
	
	public Bin whichBin() {
		float x=MyHuman.pos_x;
		float y=MyHuman.pos_y;
		float z=MyHuman.pos_z;
		
		if(x-bin1.x<300 && x-bin1.x>-300 && z-bin1.z<200 && z-bin1.z>-200) {
			return bin1;
		}
		if(x-bin3.x<300 && x-bin3.x>-300 && z-bin3.z<200 && z-bin3.z>-200) {
			return bin3;
		}
		if(x-bin2.x<300 && x-bin2.x>-300 && z-bin2.z<200 && z-bin2.z>-200) {
			return bin2;
		}
		
		if(x-bin4.x<300 && x-bin4.x>-300 && z-bin4.z<200 && z-bin4.z>-200) {
			return bin4;
		}
		return null;
	}
	
	public boolean isGrab = false;

	public boolean isGround =false;
	
	public void drawBin() {
		GL11.glPushMatrix();{
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			bin1.drawLight();
			bin2.drawLight();
			bin3.drawLight();
			bin4.drawLight();
			//this.eventsAtSecond(getTimeInsecond());
			}GL11.glPopMatrix();
		
	}
	
	public void drawBackground() {

		GL11.glPushMatrix();{
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		this.eventsAtSecond(getTimeInsecond());
		geo.DrawPlane();
		l.drawLight();
		b.drawBench();
		geo.DrawHouse();
		geo.drawSign();
		//this.eventsAtSecond(getTimeInsecond());
		}GL11.glPopMatrix();
	}
	
	
	public void eventsAtSecond(long seconds) {
		//Timer
		int renderSeconds=0;
		if(startTimeInsecond!=0) {
			renderSeconds=(int)seconds-startTimeInsecond;
			
		//	System.out.println(MyHuman.pos_x + ","+ MyHuman.pos_y + "," + MyHuman.pos_z);
			
			
			
		}else {
			startTimeInsecond = (int) seconds;
		}
		
		//System.out.println(renderSeconds);
		
		
		
	}
	
	
	int startTimeInsecond;
	public long getTimeInsecond() {
			
			long t = (getTime()-this.StartTime)/1000;
			return t;
	}
		  
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	
	//4.Comment: Texture Loading parts.....
	//			Three kinds of texture is inited
	// 			The call of init() is in line 334
	//			End comment of this page
	 
	static Texture texture;
	static Texture metal;
	static Texture wood;
	static Texture roadTex;
	Texture woodwall;
	Texture starNightSky;
	Texture cityWall;
	Texture building;
	Texture wheel;
	Texture glass;
	Texture shopBrand;
	Texture projSign;
	Texture head;
	Texture body;
	/*
	 * Any additional textures for your assignment should be written in here. 
	 * Make a new texture variable for each one so they can be loaded in at the beginning 
	 */
	public void init() throws IOException {
		projSign=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/projectImage2019.png"));
		shopBrand =TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/shopBrand.jpg"));
		wheel =TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/wheel.jpg"));
		glass =TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/glass-texture.jpg"));
		building =TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/building.jpg"));
	  texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
	  metal = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/metal.png"));
	  wood = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/wood2.png"));
	  roadTex =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/roadTex.jpg"));
	  woodwall =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/woodwall.jpg"));
	  starNightSky= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/sky.jpg"));
	  cityWall= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/grayplane.jpg"));
	  head= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/head.jpg"));
	  body= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/body.jpg"));
	  System.out.println("Texture loaded okay ");
	  
	}
}
