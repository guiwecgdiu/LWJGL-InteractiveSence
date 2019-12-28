package objects3D;

import org.lwjgl.util.glu.GLU;

import GraphicsObjects.Vector4f;

public class Camera {

	float pos_x,pos_y,pos_z;
	float lookat_x,lookat_y,lookat_z;
	double [] direction_cam;
	Vector4f dir;
	Vector4f right;
	Vector4f up; 

	
	//double[] vecDir = {Math.cos(pitch)*Math.cos(yaw),Math.sin(pitch),Math.cos(pitch)*Math.sin(yaw)};
	float pitch;
	float yaw;
	float sight;
	float speed;
	
	public String toString() {
		return "Position is"+"("+pos_x+","+pos_y+","+pos_z+")"+"-Look at"+"("+
	lookat_x+","+lookat_y+","+lookat_z+"-"+"Euler Angle"+"(p"+pitch+",y"+yaw+")";
	}
	
	public Camera()  
	{
		speed = 1;
		sight =10000;
		
		dir = new Vector4f();
		right = new Vector4f();
		up =  new Vector4f();
				
		//pos_x=-2000;
		pos_x=-700;
		pos_y = 2000;
		//pos_y = 100;
		//pos_z = -3000;
		pos_z =-1600;
		sight = 5;
		
		pitch = 40.0f;
		yaw = 240.0f;
		
		update();
		
	}
	
	public void updateCam() {
		
		update();
		
	}
	
	
	void update() {
		
		changeDir();
		lookat_x = (float) (pos_x + sight*dir.x);
		lookat_y = (float) (pos_y + sight*dir.y);
		lookat_z = (float) (pos_z + sight*dir.z);
		
	}
	
	public Vector4f changeDir() {
		dir.x = (float) (Math.cos(Math.toRadians(pitch))*Math.cos(Math.toRadians(yaw)));
		dir.y =(float) Math.sin(Math.toRadians(pitch));
		dir.z = (float) (Math.cos(Math.toRadians(pitch))*Math.sin(Math.toRadians(yaw)));
		
		right.x = dir.z;
		right.z = -dir.x;
		
		
		
		up=dir.cross(right);
		return dir;
		}
	
	public void YawCamera(float delta)  // yaw方法实现
	{
	   yaw += delta*3;
	   update();
	}
	
	public void Rise(float delta)
	{
		pos_y+=delta*speed;
		update();
	}
	
	public void PitchCamera(float delta)  // yaw方法实现
	{
	   pitch += delta*3;
	   update();
	   
	   if(pitch>89) {
		   pitch = 89;
	   }
	   if(pitch<-89) {
		   pitch=-89;
	   }
	}
	
	public void goForwardx(float delta) {
		
		//pos_x+=dir.x*speed*delta;pos_y+=dir.y*speed*delta;pos_z+=dir.z*speed*delta;
		pos_x+=delta*speed;
		update();
	}
	
	public void goForwardz(float delta) {
		
		//pos_x+=dir.x*speed*delta;pos_y+=dir.y*speed*delta;pos_z+=dir.z*speed*delta;
		pos_z+=delta*speed;
		update();
	}
	public void setSpeed(float speed) {
		this.speed=speed;
	}
	
	public void applyCam() {
		
		GLU.gluLookAt(pos_x, pos_y, pos_z, lookat_x, lookat_y, lookat_z, up.x,up.y,up.z);
//		System.out.println
//		("Current pos is:("+pos_x+","+pos_y+","+pos_z+") \n" 
//		+"The centre is:("+lookat_x+","+ lookat_y+","+lookat_z
//		+"\nUp("+up.x+","+up.y+","+up.z+")"
//		+"\nRight("+right.x+","+right.y+","+right.z+")");
	}
	
	public void setX(float x,float y ,float z) {
		this.pos_x=x;
		this.pos_y=y;
		this.pos_z=z;
	}
}
	


