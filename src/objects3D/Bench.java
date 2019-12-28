package objects3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class Bench {

	float x;
	float z;
	Texture t;
	TexCube cube;
	TexSphere sphere;
	Cylinder stick;
	public Bench(float x,float z,Texture t) {
		this.x=x;
		this.z=z;
		this.t=t; 
		cube= new TexCube(t);
		sphere= new TexSphere();
		stick=new Cylinder(t);
	}
	
	
	public void drawBench() {
		
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(objects3D.Color.white));
			GL11.glPushMatrix();
			GL11.glTranslatef(x, 600.0f, z);
			GL11.glScalef(1.0f, 0.5f, 0.5f);
			GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(objects3D.Color.white));
			GL11.glPushMatrix();
			GL11.glRotatef(90, 1.f, 0.f, 0.f);
			GL11.glScalef(10f, 10f, 10f);
			
			stick.DrawCylinder(4f, 100f, 32);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslatef(1000f, 0f, 0f);
			GL11.glRotatef(90, 1.f, 0f, 0f);
			GL11.glScalef(10f,10f, 10f);
			stick.DrawCylinder(4f, 100f, 32);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glTranslatef(0f, 0f, 600f);
			GL11.glRotatef(90, 1.f, 0f, 0f);
			GL11.glScalef(10f,10f, 10f);
			stick.DrawCylinder(4f, 100f, 32);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslatef(1000f, 0.0f, 600f);
			GL11.glRotatef(90, 1.f, 0f, 0f);
			GL11.glScalef(10f,10f, 10f);
			stick.DrawCylinder(4f, 100f, 32);
			GL11.glPopMatrix();
			
			GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(objects3D.Color.white));
			GL11.glPushMatrix();
			//GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			//GL11.glScalef(600f,900f, 600f);
			GL11.glTranslatef(500.0f, 0.0f, 300.0f);
			GL11.glRotatef(90,.0f,0.0f,1.0f);
			GL11.glScalef(60f,600f, 400f);
			//GL11.glRotatef(90,1.0f,0.0f,0.0f);
			//GL11.glRotatef(60,0.0f,-1.0f,0.0f);
		
			
			//GL11.glRotatef(90, 0.0f, 0.0f, -1.0f);
			GL11.glRotatef(90,0.0f,-1.0f,0.0f);
			this.cube.DrawTexCube();
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			//GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			//GL11.glScalef(600f,900f, 600f);
			GL11.glTranslatef(500.0f, 600.0f, -100.0f);
			//GL11.glRotatef(90,.0f,0.0f,1.0f);
			GL11.glScalef(600f,500f, 60f);
			//GL11.glRotatef(90,1.0f,0.0f,0.0f);
			//GL11.glRotatef(60,0.0f,-1.0f,0.0f);
		
			
			//GL11.glRotatef(90, 0.0f, 0.0f, -1.0f);
			GL11.glRotatef(90,1.0f,0.0f,0.0f);
			this.cube.DrawTexCube();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			
	
		
		
		GL11.glPopAttrib();
	}
	
}
