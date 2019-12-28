package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	Texture wheel;
	public Cylinder() { 
	}
	public Cylinder(Texture tex) { 
		wheel = tex;
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
		 //Start with triangle mode
		
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		for(int i=0; i < nSegments; i++)
		{
			//Compute current angle and next angle...unit float
			//...
			//angle is equal to 2PI/segments x i
			float angle = (float) (Math.PI * 2.0 * i)/nSegments;
			float nextAngle = (float) (Math.PI * 2.0 * (i+1))/nSegments;
			
			
			//Compute 4 vertices of a single vertical rect face...unit float
			//...
			// [x1,y1,height/2] [x2,y2,height/2] [x1,y1,-height/2] [x2,y2,-height/2]
			//x1 = radius x sin(angle) y1 = radius x cos(angle)
			float x1 = radius * (float)Math.sin(angle); float y1 = radius *(float) Math.cos(angle);
			float x2 = radius * (float)Math.sin(nextAngle); float y2 = radius *(float) Math.cos(nextAngle);
			
//			
//			//Calculate the norm of the face 
//			//...
//			Vector4f v = new Point4f(x1,y1,height/2,0.0f).MinusPoint(new Point4f(x2, y2, height/2, 0.0f));
//			Vector4f w = new Point4f(x1,y1,-height/2,0.0f).MinusPoint(new Point4f(x2, y2, height/2, 0.0f));
//			Vector4f n = v.cross(w);
			
			
			//Smoother one...each point has a norm vector from the origin point of the top/bottom to itself
			//...
			
			//a top triangle here
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, height);
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, 0);
			
			//a bottom triangle here
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, 0);
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, 0);
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height);
			
			//...
			//end of the side 
			
			//draw the top and bottom
			Vector4f v = new Vector4f(x1,y1,0.0f,0.0f);
			Vector4f w = new Vector4f(x2,y2,0.0f,0.0f);
			Vector4f n = v.cross(w);
			
			//top
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(0.0f, 0.0f, height/2);
			GL11.glVertex3f(x1, y1, height);
			GL11.glVertex3f(x2, y2, height);
			
			//bottom
			GL11.glNormal3f(-n.x, -n.y, -n.z);
			GL11.glVertex3f(0.0f, 0.0f, 0);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glVertex3f(x2, y2, 0);
			
		}
		
		GL11.glEnd();
	}
	
	
	public void DrawTexCylinder(float radius, float height, int nSegments ) 
	{
		 //Start with triangle mode
		this.initTexture();
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		for(int i=0; i < nSegments; i++)
		{
			//Compute current angle and next angle...unit float
			//...
			//angle is equal to 2PI/segments x i
			float angle = (float) (Math.PI * 2.0 * i)/nSegments;
			float nextAngle = (float) (Math.PI * 2.0 * (i+1))/nSegments;
			
			float texCoordx = i/nSegments;
			float nextTexCoordx = (i+1)/nSegments;
		
	 
			
			
			//Compute 4 vertices of a single vertical rect face...unit float
			//...
			// [x1,y1,height/2] [x2,y2,height/2] [x1,y1,-height/2] [x2,y2,-height/2]
			//x1 = radius x sin(angle) y1 = radius x cos(angle)
			float x1 = radius * (float)Math.sin(angle); float y1 = radius *(float) Math.cos(angle);
			float x2 = radius * (float)Math.sin(nextAngle); float y2 = radius *(float) Math.cos(nextAngle);
			
//			
//			//Calculate the norm of the face 
//			//...
//			Vector4f v = new Point4f(x1,y1,height/2,0.0f).MinusPoint(new Point4f(x2, y2, height/2, 0.0f));
//			Vector4f w = new Point4f(x1,y1,-height/2,0.0f).MinusPoint(new Point4f(x2, y2, height/2, 0.0f));
//			Vector4f n = v.cross(w);
			float t =   (float) (angle/(float)Math.PI); 
			float next_t =   (float) (nextAngle/(float)Math.PI); 
			
			//Smoother one...each point has a norm vector from the origin point of the top/bottom to itself
			//...
			
			//a top triangle here
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glTexCoord2d(t,1.0f); GL11.glVertex3f(x1, y1, height);
			GL11.glNormal3f(x2, y2, 0.0f);GL11.glTexCoord2d(next_t,1.0f); GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1, 0.0f);GL11.glTexCoord2d(t,0.0f); GL11.glVertex3f(x1, y1, 0);
			
			//a bottom triangle here
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glTexCoord2d(t,0.0f); GL11.glVertex3f(x1, y1, 0);
			GL11.glNormal3f(x2, y2, 0.0f);GL11.glTexCoord2d(next_t,0.0f); GL11.glVertex3f(x2, y2, 0);
			GL11.glNormal3f(x2, y2, 0.0f);GL11.glTexCoord2d(next_t,1.0f);  GL11.glVertex3f(x2, y2, height);
			
			//...
			//end of the side 
			
			//draw the top and bottom
			Vector4f v = new Vector4f(x1,y1,0.0f,0.0f);
			Vector4f w = new Vector4f(x2,y2,0.0f,0.0f);
			Vector4f n = v.cross(w);
			
			//top
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(0.0f, 0.0f, height/2);
			GL11.glVertex3f(x1, y1, height);
			GL11.glVertex3f(x2, y2, height);
			
			//bottom
			GL11.glNormal3f(-n.x, -n.y, -n.z);
			GL11.glVertex3f(0.0f, 0.0f, 0);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glVertex3f(x2, y2, 0);
			
		}
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	void initTexture() {
		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
				GL11.GL_REPEAT);
//	  
		 Color.white.bind();
		    wheel.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	      
	}
}
