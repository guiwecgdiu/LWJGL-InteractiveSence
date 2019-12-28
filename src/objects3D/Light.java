package objects3D;

import java.nio.FloatBuffer;

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

public class Light {

	float x;
	float z;
	Texture t;
	TexCube cube;
	TexSphere sphere;
	Cylinder stick;
	public Light(float x,float z,Texture t) {
		this.x=x;
		this.z=z;
		this.t=t; 
		cube= new TexCube(t);
		sphere= new TexSphere();
		stick=new Cylinder(t);
		
	}
	
	
	public void drawLight() {
		FloatBuffer lightPos7 = BufferUtils.createFloatBuffer(4);
		lightPos7.put(x-300f).put(2800f).put(z-600f).put(1).flip();
		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_DIFFUSE, Utils.ConvertForGL(objects3D.Color.white));
		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_POSITION, lightPos7);
		GL11.glEnable(GL11.GL_LIGHT7);
		
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		 GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();{
			GL11.glTranslatef(x, 2800.0f, z);
			GL11.glPushMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef(90, 1.f, 0.f, 0.f);
			
			GL11.glScalef(15f, 15f, 32f);

			stick.DrawCylinder(3f, 100f, 32);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			 GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();
			//GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.0f, 200.0f, -300.0f);
			
			GL11.glRotatef(90,1.0f,0.0f,0.0f);
			//GL11.glRotatef(60,0.0f,-1.0f,0.0f);
			GL11.glScalef(80f,400f, 150f);
			//GL11.glRotatef(90, 0.0f, 0.0f, -1.0f);
			this.cube.DrawTexCube();
			GL11.glPopMatrix();
			  GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();
			
			GL11.glTranslatef(0.0f, -100.0f, -600.0f);
			GL11.glRotatef(90,1.0f,0.0f,0.0f);
			//GL11.glRotatef(60,0.0f,-1.0f,0.0f);
			GL11.glScalef(100f,100f, 100f);
			this.sphere.DrawTexSphere(1.0f, 32, 32, t);
			GL11.glPopMatrix();
	}GL11.glPopMatrix();
			
	
		
		
		GL11.glPopAttrib();
	}
	
}
