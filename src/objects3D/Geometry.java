package objects3D;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

public class Geometry {

	
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	float x=0f;
	float y = 0f ;
	float z = 0f;
	float scaleX = 200f;
	float scaleY = 200f;
	float scaleZ = 200f;
	Texture road;
	Texture wood;
	TexCube roadcube;
	TexCube wallcube;
	TexCube woodcube;
	TexCube buildcube;
	TexCube shopBrand;
	TexCube glassCube;
	TexCube projectSign;
	public Geometry(Texture road,Texture wood) {
		roadcube = new TexCube(road);
		woodcube = new TexCube(wood);
		this.road = road;
		this.wood = wood;
	}
	public Geometry(Texture road,Texture wood,Texture cityWall) {
		roadcube = new TexCube(road);
		woodcube = new TexCube(wood);
		this.wallcube = new TexCube(cityWall);
		this.road = road;
		this.wood = wood;
	}
	public Geometry(Texture road,Texture wood,Texture cityWall,Texture building) {
		roadcube = new TexCube(road);
		woodcube = new TexCube(wood);
		this.wallcube = new TexCube(cityWall);
		this.buildcube = new TexCube(building);
		this.road = road;
		this.wood = wood;
	}
	public Geometry(Texture road,Texture wood,Texture cityWall,Texture building,Texture shopBrand) {
		roadcube = new TexCube(road);
		woodcube = new TexCube(wood);
		this.wallcube = new TexCube(cityWall);
		this.buildcube = new TexCube(building);
		this.road = road;
		this.wood = wood;
		this.shopBrand =new TexCube(shopBrand);
		
	}
	public Geometry(Texture road,Texture wood,Texture cityWall,Texture building,Texture shopBrand,Texture glass,Texture projectSign) {
		roadcube = new TexCube(road);
		woodcube = new TexCube(wood);
		this.wallcube = new TexCube(cityWall);
		this.buildcube = new TexCube(building);
		this.road = road;
		this.wood = wood;
		this.shopBrand =new TexCube(shopBrand);
		this.glassCube = new TexCube(glass);
		this.projectSign = new TexCube(projectSign);
		
		
		FloatBuffer lightPos6 = BufferUtils.createFloatBuffer(4);
		lightPos6.put(1000f).put(1000f).put(1000).put(0).flip();
		GL11.glLight(GL11.GL_LIGHT6, GL11.GL_DIFFUSE, Utils.ConvertForGL(objects3D.Color.white));
		GL11.glLight(GL11.GL_LIGHT6, GL11.GL_POSITION, lightPos6); // specify
//		FloatBuffer lightPos7 = BufferUtils.createFloatBuffer(4);
//		lightPos7.put(0f).put(1000f).put(-1000).put(1).flip();
//		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_AMBIENT, Utils.ConvertForGL(objects3D.Color.white));
//		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_POSITION, lightPos7);
		//GL11.glEnable(GL11.GL_LIGHT7);
		//GL11.glEnable(GL11.GL_LIGHT6);
		
	}
	
	
	
	
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawPlane() 
	{	
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);
		
		GL11.glColor3f(0.3f, 1f, 0f);
		
		Cube geo=new Cube();
		
		int x;
		
		
		
	
		
		
		
		
		
		for(int i=-1;i<4;i++) {
		x= 6400*i;
		
	
		
		
		GL11.glPushMatrix();
		GL11.glTranslatef(x+1600, -100, -1600);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		roadcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
		
		
	
		GL11.glTranslatef(x+4800, -100, -1600);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		roadcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		
		//
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x-4800, -100, -1600);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		roadcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);
		GL11.glColor3f(0.7f, 0.7f, 0.7f);
		GL11.glPushMatrix();
//		Cube geo=new Cube();
		
		
		GL11.glTranslatef(x+1600, -100, 1600);
		GL11.glScalef(800, 100, 800);
		GL11.glScalef(2, 1, 2);
		//geo.DrawCube();
		wallcube.DrawTexCube();
		
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	//	
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x-1600, -100, -4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x-4800, -100, -4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x-4800, -100, 1600);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x-4800, -100, 4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x-1600, -100, 4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x+1600, -100, 4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x+4800, -100, 4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
	//	
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x+4800, -100, 1600);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	//
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x+4800, -100, -4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		//
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);

		GL11.glColor3f(0.3f, 1f, 0f);
		GL11.glPushMatrix();
		
		
	
		GL11.glTranslatef(x+1600, -100, -4800);
		GL11.glScalef(800, 100, 800);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(2, 1, 2);
		wallcube.DrawTexCube();
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);
		GL11.glColor3f(0.3f, 0.3f, 0f);
		GL11.glPushMatrix();
		//Cube geo=new Cube();
		
		
		GL11.glTranslatef(x-1600, -100, -1600);
		GL11.glScalef(800, 100, 800);
		GL11.glScalef(2, 1, 2);
		GL11.glRotatef(90, 0, 1, 0);
		roadcube.DrawTexCube();
	
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);
		GL11.glColor3f(0.3f, 0.3f, 0.7f);
		GL11.glPushMatrix();
//		Cube geo=new Cube();
		
		
		GL11.glTranslatef(x-1600, -100, 1600);
		GL11.glScalef(800, 100, 800);
		GL11.glScalef(2, 1, 2);
		//geo.DrawCube();
		wallcube.DrawTexCube();
	
		GL11.glPopMatrix();}
		GL11.glPopAttrib();
	
	}
	
	public void DrawCoord() {
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BITS);
		Cube cube = new Cube();
		GL11.glColor3f(1.0f, 1.0f, 0.0f);
		GL11.glRotatef(0f, 0.0f, 1.0f, 0.0f);
		GL11.glPushMatrix();
		GL11.glScalef(0.005f, 0.005f, 0.005f);
		GL11.glPushMatrix();
		GL11.glTranslatef(x,y,z); 
		GL11.glScalef(5000, 20,  20); 
		GL11.glRotated(0,0,1,0);
		cube.DrawCube();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		 GL11.glTranslatef(x,y,z); 
		GL11.glScalef(20, 5000,  20); 
		GL11.glRotated(0,0,1,0);
		cube.DrawCube();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		 GL11.glTranslatef(x,y,z); 
		GL11.glScalef(20, 20,  5000); 
		GL11.glRotated(0,0,1,0);
		cube.DrawCube();
		GL11.glPopMatrix();
		
		GL11.glColor3f(0, 0, 1);
		GL11.glPushMatrix();
		 GL11.glTranslatef(400,0,0); 
		GL11.glScalef(80, 80, 80 ); 
		GL11.glRotated(0,0,1,0);
		cube.DrawCube();
		GL11.glPopMatrix();
		
		GL11.glColor3f(0, 1, 0);
		GL11.glPushMatrix();
		 GL11.glTranslatef(0,400,0); 
		GL11.glScalef(80, 80, 80 ); 
		GL11.glRotated(0,0,1,0);
		cube.DrawCube();
		GL11.glPopMatrix();
		
		GL11.glColor3f(1, 0, 0);
		GL11.glPushMatrix();
		 GL11.glTranslatef(0,0,400); 
		GL11.glScalef(80, 80, 80 ); 
		GL11.glRotated(0,0,1,0);
		cube.DrawCube();
		GL11.glPopMatrix();
		
		for(x=-50;x<50;x++) {
			GL11.glColor3f(0, 0, 1);
			GL11.glPushMatrix();
			 GL11.glTranslatef(400*x,0,0); 
			GL11.glScalef(10, 100, 10 ); 
			GL11.glRotated(0,0,1,0);
			cube.DrawCube();
			GL11.glPopMatrix();
			
			GL11.glColor3f(0, 1, 0);
			GL11.glPushMatrix();
			 GL11.glTranslatef(0,400*x,0); 
			GL11.glScalef(10, 10, 100 ); 
			GL11.glRotated(0,0,1,0);
			cube.DrawCube();
			GL11.glPopMatrix();
			
			GL11.glColor3f(1, 0, 0);
			GL11.glPushMatrix();
			 GL11.glTranslatef(0,0,400*x); 
			GL11.glScalef(10, 100, 10 ); 
			GL11.glRotated(0,0,1,0);
			cube.DrawCube();
			GL11.glPopMatrix();
			
		}
	
		 // GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_EMISSION, Utils.ConvertForGL(black));
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
	}
	
	public void DrawHouse() {
		
		//right side hole
//		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);
//		GL11.glPushMatrix();
//		Cube c= new Cube();
////		GL11.glRotatef(90f, 0f, 0f, 1f);
//		GL11.glTranslatef(6400f, 1500f, 1700f);
//		GL11.glScalef(200f,600f, 300f);
//		
//		GL11.glPushMatrix();
//		GL11.glTranslatef(0f, 0f, 0f);
//		GL11.glScalef(2f, 1f, 2f);
//	//	Cube c= new Cube();
//		c.DrawCube();
//		GL11.glPopMatrix();
//		GL11.glColor3f(0, 0, 0);
//			GL11.glPushMatrix();
//			GL11.glTranslatef(0f,0f, 0f);
//			GL11.glScalef(0.25f, 3f, 6f);
//			c.DrawCube();
//			//woodcube.DrawTexCube();
//			GL11.glPopMatrix();
//			GL11.glPopMatrix();
//	///....................
//			
//		
//			GL11.glPopAttrib();
	//	...........
//			//side hole 2
//			GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BITS);
//			GL11.glPushMatrix();
//		
////			GL11.glRotatef(90f, 0f, 0f, 1f);
//			GL11.glTranslatef(6400f, 1500f, -4900f);
//			GL11.glScalef(200f,600f, 300f);
//			
////			GL11.glPushMatrix();
////			GL11.glTranslatef(0f, 0f, 0f);
////			GL11.glScalef(2f, 1f, 2f);
////			Cube c= new Cube();
////			c.DrawCube();
////			GL11.glPopMatrix();
//			GL11.glColor3f(0, 0, 0);
//				GL11.glPushMatrix();
//				GL11.glTranslatef(0f,0f, 0f);
//				GL11.glScalef(0.25f, 3f, 6f);
//				c.DrawCube();
//				//woodcube.DrawTexCube();
//				GL11.glPopMatrix();
//				GL11.glPopMatrix();
//		///....................
//				
//			
//				GL11.glPopAttrib();
			//...........
			
			
	//shop
			GL11.glPushMatrix();	{
//			GL11.glRotatef(90f, 0f, 0f, 1f);
			GL11.glTranslatef(0f, 1200f, 3600f);
			GL11.glScalef(400f,400f, 400f);
			
//			GL11.glPushMatrix();
//			GL11.glTranslatef(0f, 0f, 0f);
//			GL11.glScalef(2f, 1f, 2f);
//			Cube c= new Cube();
//			c.DrawCube();
//			GL11.glPopMatrix();
				GL11.glPushMatrix();{
				GL11.glTranslatef(0f,0f, 0f);
				GL11.glScalef(4f, 3f, 2f);
				//c.DrawCube();
				buildcube.DrawTexCube();
				
////
//				
//				GL11.glTexParameteri(
//						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
//						GL11.GL_REPEAT);
//				 Color.white.bind();
//				    road.bind();
//				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
//				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
//				//
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 1.2f, -1.0f);
				GL11.glRotatef(5f, 1.0f,0.0f, 0.0f);
				GL11.glScalef(1.0f, 0.6f, 0.05f);
				GL11.glRotatef(-90f, 0.0f, 0.0f, -1.0f);
				GL11.glRotatef(180f, 0.0f,0.0f, 1.0f);
			
				shopBrand.DrawTexCube();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glTranslatef(-0.6f, 0.0f, -0.1f);
				this.drawWindow();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glTranslatef(0.5f,-0.4f, -1.0f);
				GL11.glScalef(0.2f, 0.6f, 0.02f);
				//c.DrawCube();
				woodcube.DrawTexCube();
				GL11.glPopMatrix();
				}GL11.glPopMatrix();
		
	}GL11.glPopMatrix();
	
				//shop2
				GL11.glPushMatrix();	
//				GL11.glRotatef(90f, 0f, 0f, 1f);
				GL11.glTranslatef(4000f, 1200f, 3600f);
				GL11.glScalef(400f,400f, 400f);
				
//				GL11.glPushMatrix();
//				GL11.glTranslatef(0f, 0f, 0f);
//				GL11.glScalef(2f, 1f, 2f);
//				Cube c= new Cube();
//				c.DrawCube();
//				GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glTranslatef(0f,0f, 0f);
					GL11.glScalef(4f, 3f, 2f);
					//c.DrawCube();
					buildcube.DrawTexCube();
					GL11.glPushMatrix();
					GL11.glTranslatef(0.6f, 0.0f, -0.1f);
					this.drawWindow();
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glTranslatef(-0.6f, 0.0f, -0.1f);
					this.drawWindow();
					GL11.glPopMatrix();
					
					GL11.glPushMatrix();
					GL11.glTranslatef(0.05f,-0.4f, -1.0f);
					GL11.glScalef(0.2f, 0.6f, 0.02f);
					//c.DrawCube();
					woodcube.DrawTexCube();
					GL11.glPopMatrix();
					GL11.glPopMatrix();
					
					
					GL11.glPopMatrix();
					
					
					//shop3
					GL11.glPushMatrix();	
//					GL11.glRotatef(90f, 0f, 0f, 1f);
					GL11.glTranslatef(-4000f, 1300f, 3600f);
					GL11.glScalef(360f,500f, 400f);
					
//					GL11.glPushMatrix();
//					GL11.glTranslatef(0f, 0f, 0f);
//					GL11.glScalef(2f, 1f, 2f);
//					Cube c= new Cube();
//					c.DrawCube();
//					GL11.glPopMatrix();
						GL11.glPushMatrix();
						
						GL11.glTranslatef(0f,0f, 0f);
						GL11.glPushMatrix();
						GL11.glScalef(4f, 3f, 2f);
						//c.DrawCube();
						woodcube.DrawTexCube();
						GL11.glPopMatrix();
						GL11.glPushMatrix();
						GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
						GL11.glTranslatef(-0.6f, 0.0f, -2.0f);
						GL11.glScalef(4f, 3f, 2f);
						this.drawWindow2();
						GL11.glPopMatrix();
						
						GL11.glPushMatrix();
						//GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
						GL11.glTranslatef(1.5f, 0.2f, -0.0f);
						GL11.glScalef(4f, 3f, 2f);
						this.drawWindow2();
						GL11.glPopMatrix();
						GL11.glPushMatrix();
						//GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
						GL11.glTranslatef(-0.3f, 0.2f, -0.0f);
						GL11.glScalef(4f, 3f, 2f);
						this.drawWindow2();
						GL11.glPopMatrix();
						
						
						GL11.glPopMatrix();
						GL11.glPopMatrix();
		
			
			
			
	//............................	
//				GL11.glPushMatrix();
//				
////				GL11.glRotatef(90f, 0f, 0f, 1f);
//				GL11.glTranslatef(-3200f, 600f, 1700f);
//				GL11.glScalef(200f,200f, 250f);
//				
////				GL11.glPushMatrix();
////				GL11.glTranslatef(0f, 0f, 0f);
////				GL11.glScalef(2f, 1f, 2f);
////				Cube c= new Cube();
////				c.DrawCube();
////				GL11.glPopMatrix();
//					GL11.glPushMatrix();
//					GL11.glTranslatef(0f,0f, 0f);
//					GL11.glScalef(0.25f, 3f, 6f);
//					//c.DrawCube();
//					woodcube.DrawTexCube();
//					GL11.glPopMatrix();
//					GL11.glPopMatrix();
//				
				
				
	//.........................
					
					//............................	
//					GL11.glPushMatrix();
//					
////					GL11.glRotatef(90f, 0f, 0f, 1f);
//					GL11.glTranslatef(0f, 600f, 1700f);
//					GL11.glScalef(200f,200f, 250f);
//					
////					GL11.glPushMatrix();
////					GL11.glTranslatef(0f, 0f, 0f);
////					GL11.glScalef(2f, 1f, 2f);
////					Cube c= new Cube();
////					c.DrawCube();
////					GL11.glPopMatrix();
//						GL11.glPushMatrix();
//						GL11.glTranslatef(0f,0f, 0f);
//						GL11.glScalef(0.25f, 3f, 6f);
//						//c.DrawCube();
//						woodcube.DrawTexCube();
//						GL11.glPopMatrix();
//						GL11.glPopMatrix();
//					
//					
					
		//.........................
	
	}
	private void drawWindow() {
		GL11.glPushMatrix();{
			GL11.glTranslatef(0, -0.2f, -1.0f);
			GL11.glRotatef(5f, 1.0f,0.0f, 0.0f);
			GL11.glScalef(0.075f, 0.15f, 0.03f);
			this.glassCube.DrawTexCube();
			GL11.glPushMatrix();{
				GL11.glTranslatef(2.4f, 2.4f, -1.0f);
				
				this.glassCube.DrawTexCube();
			
			}GL11.glPopMatrix();
			GL11.glPushMatrix();{
				GL11.glTranslatef(0f, 2.4f, -1.0f);
				
				this.glassCube.DrawTexCube();
			
			}GL11.glPopMatrix();
			
		GL11.glPushMatrix();{
			GL11.glScalef(2.0f, 0.3f,3.0f);
			GL11.glTranslatef(0.5f, -2.9f, -2.0f);
			
			this.woodcube.DrawTexCube();
		
		}GL11.glPopMatrix();
	GL11.glPushMatrix();{
		GL11.glTranslatef(2.4f, 0.0f, 0.0f);
		
		this.glassCube.DrawTexCube();
	
	}GL11.glPopMatrix();
}GL11.glPopMatrix();
	}
	
	private void drawWindow2() {
		GL11.glPushMatrix();{
			GL11.glTranslatef(0, -0.2f, -1.0f);
			GL11.glRotatef(5f, 1.0f,0.0f, 0.0f);
			GL11.glScalef(0.075f, 0.15f, 0.03f);
			this.glassCube.DrawTexCube();
			GL11.glPushMatrix();{
				GL11.glTranslatef(2.4f, 2.4f, -1.0f);
				
				this.glassCube.DrawTexCube();
			
			}GL11.glPopMatrix();
			GL11.glPushMatrix();{
				GL11.glTranslatef(0f, 2.4f, -1.0f);
				
				this.glassCube.DrawTexCube();
			
			}GL11.glPopMatrix();
			
	
	GL11.glPushMatrix();{
		GL11.glTranslatef(2.4f, 0.0f, 0.0f);
		
		this.glassCube.DrawTexCube();
	
	}GL11.glPopMatrix();
}GL11.glPopMatrix();
	}
	
	public void drawSign() {
		GL11.glPushMatrix();
		GL11.glTranslatef(2000.0f, 800.0f, 600.0f);
		GL11.glPushMatrix();
		GL11.glRotatef(90, 1.f, 0.f, 0.f);
		GL11.glScalef(10f, 10f, 10f);
		Cylinder stick = new Cylinder(wood);

		stick.DrawCylinder(4f, 100f, 32);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		//GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(0.0f, 600.0f, 0.0f);
		GL11.glRotatef(30,0.0f,-1.0f,0.0f);
		GL11.glRotatef(90,1.0f,0.0f,0.0f);
		//GL11.glRotatef(60,0.0f,-1.0f,0.0f);
		GL11.glScalef(10f,700f, 600f);
		
		//GL11.glRotatef(90, 0.0f, 0.0f, -1.0f);
		GL11.glRotatef(90,0.0f,-1.0f,0.0f);
		this.projectSign.DrawTexCube();
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}
	
	
	
}

