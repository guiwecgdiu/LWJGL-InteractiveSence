package objects3D;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Utils;

public class Saler extends MoveHuman {
	int state;
	public boolean isCarry;
	ArrayList<Texture> arrs = new ArrayList<Texture>();
	

	public Saler(Texture metalTex, Texture woodTex, float posx, float y, float posz) throws IOException {
		super(metalTex, woodTex, posx, y, posz);
		// TODO Auto-generated constructor stub
		 Texture cityWall= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/sale.jpg"));
		arrs.add(cityWall);
		isCarry=true;
		
	}
	
	

	public Saler(Texture wheel, Texture wheel2) {
		// TODO Auto-generated constructor stub
		super(wheel, wheel2);
	}



	@Override
	public void DrawHuman(float delta) {
		// TODO Auto-generated method stub
		super.DrawHuman(delta);
	
	}
	
	public void setPosition(float x, float y, float z) {
		super.setPosition(x, y, z);
	}



	@Override
	public void control_head(float delta) {
		// TODO Auto-generated method stub
	
		super.control_head(delta);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		// GL11.glRotatef((float) (Math.cos(delta*360*0.1f)*40), 1.0f, 0.f, 0.0f);	
		 GL11.glRotatef(180.f, 0.0f, 1.f, 0.0f);	
		 GL11.glRotatef(40.f, 0.f, 1.f, 0.0f);	
	        //ystem.out.println(the);
	}



	@Override
	public void control_larm(float delta) {
		// TODO Auto-generated method stub
		super.control_larm(delta);
		if(isCarry) {
		GL11.glRotatef((float) (Math.cos(delta*360*0.1f)*40), 0.0f, 0.f, 1.0f);	
		GL11.glRotatef(80.f, 1.0f,0.f, 0.0f);	
		}
		}



	@Override
	public void control_lforearm(float delta) {
		// TODO Auto-generated method stub
		super.control_lforearm(delta);
	}



	@Override
	public boolean control_hat(float delta, boolean isDraw) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void control_rarm(float delta) {
		// TODO Auto-generated method stub
		super.control_rarm(delta);
	}



	@Override
	public void control_rhand(float delta) {
		// TODO Auto-generated method stub
		super.control_rhand(delta);
//		Cylinder cy= new Cylinder();
//		GL11.glColor3f(blue[0], blue[1], blue[2]);
//         GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
//		GL11.glPushMatrix();{
//			cy.DrawCylinder(1.0f, 4.0f, 32);
//	
//		
//		}GL11.glPopMatrix();
//	}
	}


	@Override
	public void control_lhand(float delta) {
		// TODO Auto-generated method stub
		super.control_lhand(delta);
		if(isCarry) {
		Cylinder cy= new Cylinder(arrs.get(0));
		TexCube texCube= new TexCube(arrs.get(0));
		GL11.glColor3f(blue[0], blue[1], blue[2]);
         GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		GL11.glPushMatrix();{
			GL11.glTranslatef(0.1f, 0.1f, 1.0f);
			GL11.glScalef(0.2f, 0.5f, 0.2f);
			GL11.glRotatef(100, 1.0f, 0.0f, 0.f);
			cy.DrawTexCylinder(1.0f, 10.0f, 32);
			GL11.glTranslatef(0.0f, 0.0f, 10f);
			GL11.glScalef(4f, 1f, 3f);
			texCube.DrawTexCube();
			
		}
		GL11.glPopMatrix();}
	}
	
	public void setCarry(boolean b) {
		this.isCarry=b;
	}



	@Override
	public void control_rforearm(float delta) {
		// TODO Auto-generated method stub
		super.control_rforearm(delta);
	}



	@Override
	public void control_lhip(float delta) {
		// TODO Auto-generated method stub
		super.control_lhip(delta);
	}
	
	


	
	

}
