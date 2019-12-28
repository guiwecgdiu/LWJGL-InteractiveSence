package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Vector4f;

public class Sphere {

	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		 
		
		//declare the increments, phi represents slice via latitude, and theta represents segment via longitude
		
				float incretheta = (float) (Math.PI*2/nSlices);
				float increphi = (float) (Math.PI / nSegments);
				
				
				GL11.glBegin(GL11.GL_QUADS);
				//Scan loop
				for(float theta = (float) -Math.PI; theta<Math.PI;theta+=incretheta) 
				{
					for(float phi = (float) -Math.PI/2; phi <=Math.PI/2; phi+=increphi)
					{
						
						
						//draw a piece of quad with moving the phi and theta
						float x = (float) (radius*Math.cos(phi)  *  Math.cos(theta));
						float y = (float) (radius * Math.cos(phi) * Math.sin(theta));
						float xNext1 = (float) (radius*Math.cos(phi)  *  Math.cos(theta+incretheta));
						float yNext1 = (float) ((radius*Math.cos(phi)) * Math.sin(theta+incretheta));
						float xNext2 = (float) (radius*Math.cos(phi+increphi)  *  Math.cos(theta+incretheta));
						float yNext2 = (float) ((radius*Math.cos(phi+increphi)) * Math.sin(theta+incretheta));
						float xNext3 = (float) (radius*Math.cos(phi+increphi)  *  Math.cos(theta));
						float yNext3 = (float) ((radius*Math.cos(phi+increphi)) * Math.sin(theta));
						float z = (float) (radius * Math.sin(phi));
						float zNext = (float) (radius * Math.sin(phi+increphi));
					//for()
						float length =  new Vector4f(x,y,z,0).length();
						
						GL11.glNormal3f(x/length, y/length, z/length);
						
					//inputs four relevant vertices per round when reaching a vertex	
						GL11.glVertex3f(x, y, z);
						GL11.glVertex3f(xNext1, yNext1, z);
						GL11.glVertex3f(xNext2, yNext2, zNext);
						GL11.glVertex3f(xNext3, yNext3, zNext);
						
					}
				}
				GL11.glEnd();
				
	}
}

 