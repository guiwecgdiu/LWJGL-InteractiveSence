package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class Car {
	
	public float speed;
	public boolean isMove;
	public boolean isDraw;
	public float x;
	public float y;
	public float z;
	public Texture wheel;
	float theta;
	
	int energyAmt;
	public TexCube carCube;
	public TexCube signCube;
	public TexCube glassCube;
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	public boolean isFire;
	
	public Car(float sd,float [] pos,Texture wheel,Texture top,Texture bottom,Texture glass) {
		speed=sd;
		x=pos[0];
		y=pos[1];
		z=pos[2];
		isMove=false;
		isDraw = true;
		this.wheel=wheel;
		signCube=new TexCube(top);
		carCube = new TexCube(bottom);
		glassCube = new TexCube(glass);
		theta=0;
		energyAmt = 0;
		isFire=false;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getZ() {
		return z;
	}
	
	public void setMove(boolean b) {
		isMove = b;
	}
	
	public void setDraw(boolean b) {
		isDraw = false;
	}
	
	
	public void addEnergy() {
		this.energyAmt=energyAmt+1;
		if(energyAmt==4) {
			this.isFire=true;
		}
	}
	
	public void drawCar(float delta) {
		
		
		if(isMove) {
		theta++;
		}
		
		if(isDraw==false) {
			return;
		}
		
		Cube cube = new Cube();
		Cylinder cylinder = new Cylinder();
		Cylinder texCylinder =new Cylinder(wheel);
 		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BIAS);
		GL11.glScalef(250f, 300f, 400f);
		GL11.glTranslatef(0f, 0.0f, 0f);
      	 GL11.glColor3f(orange[0], orange[1], orange[2]);
          GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		GL11.glPushMatrix();{
			
			GL11.glPushMatrix();
			//car Body
			
		if(isFire && !isMove) {
			GL11.glTranslatef((float) (Math.cos(100*delta)*0.1f), (float) (0.4f+Math.cos(100*delta)*0.1f), 0.0f);
			GL11.glScalef(4f, 1.0f,1.0f );
		}else {
			GL11.glTranslatef(0.0f, 0.4f, 0.0f);
			GL11.glScalef(4f, 1.0f,1.0f );
		}
			if(energyAmt<4) {
			GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BIAS);
				GL11.glPushMatrix();
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
				  GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				  
			  
				for(int i=0; i<this.energyAmt;i++) {
				
				
				GL11.glPushMatrix();
			
				GL11.glTranslatef(-1.0f ,1.0f+0.5f*i,-1.3f);
				GL11.glScalef(0.15f,0.15f, 0.3f);
				cube.DrawCube();
				GL11.glPopMatrix();
				}
				GL11.glPopMatrix();
			GL11.glPopAttrib();}
			carCube.DrawTexCube();
			GL11.glColor3f(1f,0f, 0f);
			GL11.glTranslatef(0f, 2.0f, 0.0f);
			glassCube.DrawTexCube();
			GL11.glColor3f(1f,0f, 0f);
			GL11.glTranslatef(0f, 2.0f, 0.0f);
			carCube.DrawTexCube();
			
			
			GL11.glPopMatrix();
			
			//wheel1 rightBack
			GL11.glColor3f(Color.cyan[0],Color.cyan[1], Color.cyan[2]);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,   Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();{
				GL11.glTranslatef(-2.4f,-0.3f , -1.0f);
				GL11.glScalef(1.3f,1.3f, -0.5f);
				GL11.glRotatef(theta, 0.f, 0.f, -1.0f);
				texCylinder.DrawTexCylinder(0.6f, 1.0f, 32);
				
			}GL11.glPopMatrix();
			
			//wheel rightForward
			GL11.glColor3f(Color.cyan[0],Color.cyan[1], Color.cyan[2]);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,   Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();{
				GL11.glTranslatef(2.4f,-0.3f , -1.0f);
				GL11.glScalef(1.3f,1.3f, -0.5f);
				GL11.glRotatef(theta, 0.f, 0.f, -1.0f);
				texCylinder.DrawTexCylinder(0.6f, 1.0f, 32);
				GL11.glTranslatef(-2.4f,-0.5f , -4.0f);
			}GL11.glPopMatrix();
			
			//wheel leftForward
			GL11.glColor3f(Color.cyan[0],Color.cyan[1], Color.cyan[2]);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,   Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();{
				GL11.glTranslatef(2.4f,-0.3f , 1.5f);
				GL11.glScalef(1.3f,1.3f, -0.5f);
				GL11.glRotatef(theta, 0.f, 0.f, -1.0f);
				texCylinder.DrawTexCylinder(0.6f, 1.0f, 32);
				GL11.glTranslatef(-2.4f,-0.5f , -4.0f);
			}GL11.glPopMatrix();
			
			//wheel leftForward
			GL11.glColor3f(Color.cyan[0],Color.cyan[1], Color.cyan[2]);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,   Utils.ConvertForGL(Color.white));
			GL11.glPushMatrix();{
				GL11.glTranslatef(-2.4f,-0.3f , 1.5f);
				GL11.glScalef(1.3f,1.3f, -0.5f);
				GL11.glRotatef(theta, 0.f, 0.f, -1.0f);
				texCylinder.DrawTexCylinder(0.6f, 1.0f, 32);
				GL11.glTranslatef(-2.4f,-0.5f , -4.0f);
			}GL11.glPopMatrix();
			
			
		}GL11.glPopMatrix();
		GL11.glPopAttrib();
		
		
		
		//Change the position
//		if(isMove==true) {
//			x+=speed*delta;
//		}
	}
}