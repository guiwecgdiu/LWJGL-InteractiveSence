package objects3D;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Utils;

public class TexSphere {
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	static Texture texture;
	
	
	public TexSphere() {

	}
	
	
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawTexSphere(float radius,float nSlices,float nSegments,Texture myTexture) {
		float x,y,z;
		float s,t; // texture coordinates
		
		   float inctheta = (float) ((2.0f*Math.PI )/ nSlices );
		    float incphi   = (float) (Math.PI/ nSegments );
		    
		  texture=myTexture;  
		  initTexture();
		    
		    GL11.glBegin(GL11.GL_QUADS);
		    for(float theta=(float) -Math.PI; theta<Math.PI; theta+=inctheta)
		    {
		        for(float phi=(float) -(Math.PI/2.0f); phi<(Math.PI/2.0f); phi+=incphi)
		        {
		            x = (float) (Math.cos(phi)* Math.cos(theta)*radius);
		            y =  (float) (Math.cos(phi)* Math.sin(theta)*radius);
		            z =  (float) (Math.sin(phi)*radius);
		            
		            t =   (float) (phi/(float)Math.PI) +0.5f; 
		            s =    (float) (theta/Math.PI*2.0f) +0.5f ; 
		            
		            //GL11.glTexCoord2f(s,t);  // should be here but seems to be a bug in LWJGL 
		            GL11.glNormal3f(x,y,z); 
		            GL11.glVertex3f(x,y,z);  
			           
		            x = (float) (Math.cos(phi)*Math.cos(theta+inctheta)*radius);
		            y = (float) (Math.cos(phi)*Math.sin(theta+inctheta)*radius);
		            z = (float) (Math.sin(phi)*radius);
		            t = (float) (((float)phi/(float)Math.PI)+0.5f); 
		            s = (float) ((((float)theta+inctheta)/((float)Math.PI*2.0f)))+0.5f; 
		           
		            GL11.glTexCoord2f(s,t);
		            
		         GL11.glNormal3f(x,y,z); //Mistake in previous version fixed ( abey 11/1/2018) 
		            GL11.glVertex3f(x,y,z);  // Top Right corner

		            x = (float) (Math.cos(phi+incphi)*Math.cos(theta+inctheta)*radius);
		            y = (float) (Math.cos(phi+incphi)*Math.sin(theta+inctheta)*radius);
		            z = (float) (Math.sin(phi+incphi)*radius);
		            t = (float) ((((float)phi+incphi)/(float)Math.PI)+0.5f);
		            s = (float) ((((float)theta+inctheta)/((float)Math.PI*2.0f))+0.5f);
		         
		            GL11.glTexCoord2f(s,t);
		            GL11.glNormal3f(x,y,z);
		            GL11.glVertex3f(x,y,z); 

		            x = (float) (Math.cos(phi+incphi)*Math.cos(theta)*radius);
		            y = (float) (Math.cos(phi+incphi)*Math.sin(theta)*radius);
		            z = (float) (Math.sin(phi+incphi)*radius);
		            t = (float) ((((float)phi+incphi)/(float)Math.PI)+0.5f);
		            s = (float) (((float)theta/((float)Math.PI*2.0f))+0.5f);
		            
		            GL11.glTexCoord2f(s,t);
		            GL11.glNormal3f(x,y,z);
		            GL11.glVertex3f(x,y,z);  
		        }
		    }
		    GL11.glEnd();
		    GL11.glDisable(GL11.GL_TEXTURE_2D); 
	}
	
	public void DrawSphere(float radius,float nSlices,float nSegments,Texture myTexture) {
		float x,y,z;
		float s,t; // texture coordinates
		
		   float inctheta = (float) ((2.0f*Math.PI )/ nSlices );
		    float incphi   = (float) (Math.PI/ nSegments );
		    
		  texture=myTexture;  
		 
		    
		    GL11.glBegin(GL11.GL_QUADS);
		    for(float theta=(float) -Math.PI; theta<Math.PI; theta+=inctheta)
		    {
		    	
		        for(float phi=(float) -(Math.PI/2.0f); phi<(Math.PI/2.0f); phi+=incphi)
		        {
		            x = (float) (Math.cos(phi)* Math.cos(theta)*radius);
		            y =  (float) (Math.cos(phi)* Math.sin(theta)*radius);
		            z =  (float) (Math.sin(phi)*radius);
		            
		            t =   (float) (phi/(float)Math.PI) +0.5f; 
		            s =    (float) (theta/Math.PI*2.0f) +0.5f ; 
		            
		            //GL11.glTexCoord2f(s,t);  // should be here but seems to be a bug in LWJGL 
		            GL11.glNormal3f(x,y,z); 
		            GL11.glVertex3f(x,y,z);  
			           
		            x = (float) (Math.cos(phi)*Math.cos(theta+inctheta)*radius);
		            y = (float) (Math.cos(phi)*Math.sin(theta+inctheta)*radius);
		            z = (float) (Math.sin(phi)*radius);
		            t = (float) (((float)phi/(float)Math.PI)+0.5f); 
		            s = (float) ((((float)theta+inctheta)/((float)Math.PI*2.0f)))+0.5f; 
		           
		            GL11.glTexCoord2f(s,t);
		            
		         GL11.glNormal3f(x,y,z); //Mistake in previous version fixed ( abey 11/1/2018) 
		            GL11.glVertex3f(x,y,z);  // Top Right corner

		            x = (float) (Math.cos(phi+incphi)*Math.cos(theta+inctheta)*radius);
		            y = (float) (Math.cos(phi+incphi)*Math.sin(theta+inctheta)*radius);
		            z = (float) (Math.sin(phi+incphi)*radius);
		            t = (float) ((((float)phi+incphi)/(float)Math.PI)+0.5f);
		            s = (float) ((((float)theta+inctheta)/((float)Math.PI*2.0f))+0.5f);
		         
		            GL11.glTexCoord2f(s,t);
		            GL11.glNormal3f(x,y,z);
		            GL11.glVertex4f(x,y,z,0); 

		            x = (float) (Math.cos(phi+incphi)*Math.cos(theta)*radius);
		            y = (float) (Math.cos(phi+incphi)*Math.sin(theta)*radius);
		            z = (float) (Math.sin(phi+incphi)*radius);
		            t = (float) ((((float)phi+incphi)/(float)Math.PI)+0.5f);
		            s = (float) (((float)theta/((float)Math.PI*2.0f))+0.5f);
		            
		            GL11.glTexCoord2f(s,t);
		            GL11.glNormal3f(x,y,z);
		            GL11.glVertex3f(x,y,z);  
		        }
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
		    texture.bind();
		    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	      
	}
	

	
}
 