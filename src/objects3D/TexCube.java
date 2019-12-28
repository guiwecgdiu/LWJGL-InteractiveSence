package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {
	Texture texture;
	
	public TexCube(Texture myTexture) {
		texture = myTexture;
	}
	

	// Implement using notes  and looking at TexSphere
	public void DrawTexCube() 
 {

	 
		Point4f vertices[] = { 	new Point4f(-1.0f, -1.0f, -1.0f,0.0f),
				new Point4f(-1.0f, -1.0f, 1.0f, 0.0f), 	
				new Point4f(-1.0f, 1.0f, 1.0f,0.0f),
				new Point4f(-1.0f, 1.0f, -1.0f, 0.0f),
				new Point4f(1.0f, -1.0f, -1.0f,0.0f),
				new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, -1.0f,0.0f) };

//2*faces dtriangles
int faces [] [] = {{0,1,2,3},
			
			{0,4,5,1},
			
			{6,2,1,5},
			
			{7,3,2,6},
			
			{4,0,3,7},
			
			{4,7,6,5},			
};


this.initTexture();

GL11.glBegin(GL11.GL_QUADS);
for(int face=0; face < 6; face++) 
{

//test code here
//int i = 2;
//if(face == i || face == i++) {
//
//continue;
//}
Vector4f v = vertices[faces[face][0]].MinusPoint(vertices[faces[face][1]]);
Vector4f w = vertices[faces[face][0]].MinusPoint(vertices[faces[face][2]]);
//calculate norm
Vector4f n = v.cross(w);

//inputs vertices
GL11.glNormal3f(n.x/n.length(), n.y/n.length(), n.z/n.length());
GL11.glTexCoord2d(1.0f,1.0f);
GL11.glVertex3f(vertices[faces[face][0]].x,vertices[faces[face][0]].y, vertices[faces[face][0]].z);
GL11.glTexCoord2d(1.0f,0.0f);
GL11.glVertex3f(vertices[faces[face][1]].x,vertices[faces[face][1]].y, vertices[faces[face][1]].z);
GL11.glTexCoord2d(0.0f,0.0f);
GL11.glVertex3f(vertices[faces[face][2]].x,vertices[faces[face][2]].y, vertices[faces[face][2]].z);
GL11.glTexCoord2d(0.0f,1.0f);
GL11.glVertex3f(vertices[faces[face][3]].x,vertices[faces[face][3]].y, vertices[faces[face][3]].z);

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
 
	/*
	 
	 
}

	*/
	 