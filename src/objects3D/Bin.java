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

public class Bin {

	public float x;
	public float z;
	float y;
	Texture t;
	TexCube cube;
	TexSphere sphere;
	Cylinder stick;
	public Bin(float x,float y,float z) {
		this.x=x;
		this.z=z;
		this.y=y; 
		stick=new Cylinder();
		isCollect=false;
		
	}
	
	public boolean isCollect;
	
	
	public void drawLight() {
		if(isCollect) {
			return;
		}
		FloatBuffer lightPos7 = BufferUtils.createFloatBuffer(4);
		lightPos7.put(x-300f).put(2800f).put(z-600f).put(1).flip();
		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_DIFFUSE, Utils.ConvertForGL(objects3D.Color.white));
		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_POSITION, lightPos7);
		GL11.glEnable(GL11.GL_LIGHT7);
		
		
		
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		 GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();{
			GL11.glTranslatef(x, y, z);
			GL11.glPushMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef(90, 1.f, 0.f, 0.f);
			
			GL11.glScalef(10f, 10f, 21f);

			stick.DrawCylinder(15f, 30f, 32);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		
			
	}GL11.glPopMatrix();
			
	
		
		
		GL11.glPopAttrib();
	}
	
}
