package objects3D;

import java.io.IOException;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

public class Human {

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	
	static Texture head;
	static Texture body;
	public static final float STATE_REST = 0;
	public static final float STATE_WAVE = 1;
	public static final float STATE_SIT = 2;
	public static final float STATE_FREE = 3;
	public static final float STATE_GRAB = 4;
	private double random;
	
	public Human() {
		random =Math.random();
	

	}
	

	
	
	//0.Comment: This function draw a pure human without texture
	//            This class is only be responsible to the posture and animation of human
	//            but the position and move is excluded
	//				An additional hat was drawn near the head
	
	// Implement using notes  in Animation lecture  
	public void DrawHuman(float delta) 
 {
		
		
	//1.Comment: Theta is the angle (amount of change with time) for the orbital revolution 	
	//			LimbRotation is the angle variable for general limb rotation.
	//          Left and Right Rotation represents angle for the sub limb like forearm and lowleg
	//...
		 float theta = (float) (delta * 2 * Math.PI);
		  float LimbRotation;
		  float RightLimbRotation=0.0f;
		  float LeftLimbRotation=0.0f;
	//...
		  //lefhand righhand legright legleft
		  
		 //2.Comment: General rotation is in 45 amplitude 
		 //       : To make the move smoother, when the arm reach the vertical position, keep stretching
		 //        : In a bad animation mode, try to make the posture stable
		  
		
			 
			 LimbRotation = (float) Math.cos(theta*10)*45;
		//  LimbRotation =45;
			 //...
			 if(LimbRotation>0) {
			 RightLimbRotation = LimbRotation*3;
			 LeftLimbRotation = 0;
			 }else {
				 LeftLimbRotation = LimbRotation*3;	 
				 RightLimbRotation = 0;
			 }
			 //...
	
		 
		 
		 
		 //3.Comment: Generate the drawer utility class
		  
		 //..
		Sphere sphere= new Sphere();
		Cylinder cylinder= new Cylinder();
		//...
 
 
		 GL11.glPushMatrix(); 
		 
		 {	
			 
			  GL11.glTranslatef(0.0f,0.5f,0.0f);  
			  sphere.DrawSphere(0.5f, 32, 32); 

		        //  chest
			 GL11.glColor3f(green[0], green[1], green[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
			 GL11.glPushMatrix(); {
		            GL11.glTranslatef(0.0f,0.5f,0.0f);
		            
		            sphere.DrawSphere(0.5f, 32, 32); 


		            // neck
		       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(0.0f,0.0f, 0.0f);
		              //  GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
		                //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f); 
		                cylinder.DrawCylinder(0.15f,0.7f,32);


		                // head
		           	 GL11.glColor3f(red[0], red[1], red[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,1.0f);
		                  
		                    sphere.DrawSphere(0.5f, 32, 32);  
		                    

		                    
		                    
		                    // hat
		             
				           	 GL11.glColor3f(black[0], black[1], black[2]);
				               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
				                GL11.glPushMatrix(); {
				                    GL11.glTranslatef(0.0f,0.0f,0.2f);
				                    cylinder.DrawCylinder(0.5f, 1.5f, 32);
				                    cylinder.DrawCylinder(1.0f, 0.2f, 32);
				                    
				                }GL11.glPopMatrix();
				                
		                GL11.glPopMatrix();
		                } GL11.glPopMatrix();

		               //4.Comment: The arms of avatar is set to tilt.
		                //          Some as both shoulder though
		                
		                // left shoulder
		           	 GL11.glColor3f(blue[0],blue[1], blue[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.5f,0.4f,0.0f);
		                   //..
		                    GL11.glRotatef(27.5f,0.0f,0.0f,1.0f);  
		                    //...
		                    sphere.DrawSphere(0.25f, 32, 32); 
		                    

		                    // left arm
		               	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.0f);
		                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
		                        
		                        
		                        GL11.glRotatef(LimbRotation,-1.0f,0.0f,0.0f); 
		                        cylinder.DrawCylinder(0.15f,0.7f,32);


		                        // left elbow
		                   	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.75f);
		                            sphere.DrawSphere(0.2f, 32, 32); 
		                            
		                            //left forearm
		                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.0f);
		                                  GL11.glRotatef(LeftLimbRotation,-1.0f,0.0f,0.0f); 
		                                cylinder.DrawCylinder(0.1f,0.7f,32);

		                                // left hand
		                           	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                                GL11.glPushMatrix(); {
		                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                    sphere.DrawSphere(0.2f, 32, 32); 
		                                    


		                                } GL11.glPopMatrix();
		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix ();
		                } GL11.glPopMatrix ();
		                // to chest

		                // right shoulder
			           	 GL11.glColor3f(blue[0],blue[1], blue[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(-0.5f,0.4f,0.0f);
			                    GL11.glRotatef(27.5f,0.0f,0.0f,-1.0f); 
			                    sphere.DrawSphere(0.25f, 32, 32); 
			              

		                    // right arm
			                    GL11.glColor3f(orange[0], orange[1], orange[2]);
				                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                   GL11.glPushMatrix();{
				                	   GL11.glTranslatef(0.0f,0.0f,0.0f);
				                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
				                        
				                        
				                        GL11.glRotatef(LimbRotation,1.0f,0.0f,0.0f); 
				                     //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);  
				                        cylinder.DrawCylinder(0.15f,0.7f,32);
				                  
		                        // right elbow
				                        GL11.glColor3f(blue[0], blue[1], blue[2]);
					                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
					                        GL11.glPushMatrix(); {
					                            GL11.glTranslatef(0.0f,0.0f,0.75f);
					                          // GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
					                            sphere.DrawSphere(0.2f, 32, 32); 
		                            //right forearm
					                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
					                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
					                            GL11.glPushMatrix(); {
					                                GL11.glTranslatef(0.0f,0.0f,0.0f);
					                                              // GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
					                                GL11.glRotatef(RightLimbRotation,1.0f,0.0f,0.0f); 
					                                cylinder.DrawCylinder(0.1f,0.7f,32);
		                                // right hand
					                              	 GL11.glColor3f(blue[0], blue[1], blue[2]);
						                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
						                                GL11.glPushMatrix(); {
						                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
						                                    sphere.DrawSphere(0.2f, 32, 32);
						                                } GL11.glPopMatrix();
					                            }GL11.glPopMatrix();
					                        }GL11.glPopMatrix();
		                          }GL11.glPopMatrix();
			                }GL11.glPopMatrix();    
		                //chest


		            } GL11.glPopMatrix();


		            // pelvis

		            
		            // left hip
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
		               
		                sphere.DrawSphere(0.25f, 32, 32); 


		                // left high leg
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);
		                    //GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                   
		                    
		                    GL11.glRotatef((-LimbRotation/2)+90,1.0f,0.0f,0.0f); 
		                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
		                    cylinder.DrawCylinder(0.15f,0.7f,32);


		                    // left knee
		               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.75f);
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                        sphere.DrawSphere(0.25f, 32, 32); 

		                        //left low leg
		                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.0f);
		                            GL11.glRotatef(LeftLimbRotation/6,1.0f,0.0f,0.0f);
		                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                            cylinder.DrawCylinder(0.15f,0.7f,32);

		                            // left foot
		                       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                sphere.DrawSphere(0.3f, 32, 32);  

		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix();
		                } GL11.glPopMatrix();
		            } GL11.glPopMatrix();
		            // pelvis


		            // right hip
			       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			            GL11.glPushMatrix(); {
			                GL11.glTranslatef(0.5f,-0.2f,0.0f);
			               
			                sphere.DrawSphere(0.25f, 32, 32); 


			                // right high leg
			           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(0.0f,0.0f,0.0f);
			                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
			                
			                    
			                    GL11.glRotatef((LimbRotation/2)+90,1.0f,0.0f,0.0f); 
			                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
			                    cylinder.DrawCylinder(0.15f,0.7f,32);


			                    // right knee
			               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                    GL11.glPushMatrix(); {
			                        GL11.glTranslatef(0.0f,0.0f,0.75f);
			                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                        sphere.DrawSphere(0.25f, 32, 32); 

			                        //right low leg
			                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
			                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                        GL11.glPushMatrix(); {
			                            GL11.glTranslatef(0.0f,0.0f,0.0f);
			                            GL11.glRotatef(RightLimbRotation/6,-1.0f,0.0f,0.0f);
			                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                            cylinder.DrawCylinder(0.15f,0.7f,32);

			                            // right foot
			                       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                            GL11.glPushMatrix(); {
			                                GL11.glTranslatef(0.0f,0.0f,0.75f);
			                                sphere.DrawSphere(0.3f, 32, 32);  

			                            } GL11.glPopMatrix();
			                        } GL11.glPopMatrix();
			                    } GL11.glPopMatrix();
			                } GL11.glPopMatrix();
			            } GL11.glPopMatrix();
		        
		        } GL11.glPopMatrix();
		         
		    }
		 
		 
	}

	
	
	
	
	boolean isFiring=false;
	
	//0.Comment: This function draw a pure human with texture
	//			Same as above, but textures of head and body are inserted
	public void DrawTexHuman(float delta,Texture head, Texture body,float state) 
 { 
		 float lhLeg = 0f;
			float rhLeg=0f;
			float lForem=0f;
			float rForem=0f;
			float rArm=0f;
			float lArm = 0f;
			float llLeg=0f;
			float rlLeg=0f;
			float bind=0f;
			float leftOffset =27.5f;
			float rightOffset =27.5f;
		
		
		
		 float theta = (float) (delta * 2 * Math.PI);
		  float LimbRotation=0.0f;
		  float RightLimbRotation=0.0f;
		  float LeftLimbRotation=0.0f;
		  
		  
		  
		  
		  
		  if(state==this.STATE_WAVE) { 
		
	//1.Comment: Nothing different, just see above in parallel
			  this.isFiring=false;
			 LimbRotation = (float) Math.cos(theta*10)*45;
			 if(LimbRotation>0) {
			 RightLimbRotation = LimbRotation*3;
			 LeftLimbRotation = 0;
			 }else {
				 LeftLimbRotation = LimbRotation*3;	 
				 RightLimbRotation = 0;
			 }
			 
			 
			 	lhLeg=  (-LimbRotation/2)+90;
				rhLeg = (LimbRotation/2)+90;
				lForem=-LeftLimbRotation;
				rForem = RightLimbRotation;
				rArm=LimbRotation;
				lArm =-LimbRotation;
				llLeg = LeftLimbRotation/6;
				rlLeg =-RightLimbRotation/6;
			 
		}
		  
		  if(state==this.STATE_REST) { 
				
	//1.Comment: Nothing different, just see above in parallel
			 
			  	this.isFiring=false;
			 	lhLeg=  (-LimbRotation/2)+90;
				rhLeg = (LimbRotation/2)+90;
				lForem=-LeftLimbRotation;
				rForem = RightLimbRotation;
				rArm=LimbRotation;
				lArm =-LimbRotation;
				llLeg = LeftLimbRotation/6;
				rlLeg =-RightLimbRotation/6;
			  
			 
		}
		  if(state == this.STATE_SIT) {
			  	this.isFiring=true;
			 	lhLeg=  (-LimbRotation/2)+90;
					rhLeg = (LimbRotation/2)+90;
					lForem=-LeftLimbRotation+50;
					rForem = RightLimbRotation+50;
					rArm=LimbRotation;
					lArm =-LimbRotation;
					llLeg = LeftLimbRotation/6-50;
					rlLeg =-RightLimbRotation/6-50;
					
			  
		  }
		  if(state == this.STATE_FREE) {
			  LimbRotation=40.0f;
			  RightLimbRotation=40.0f;
			  LeftLimbRotation=40.0f;
			 	lhLeg=  0;
				rhLeg = 0;
				lForem=0;
				rForem = 0;
				rArm=LimbRotation;
				lArm =LimbRotation;
				llLeg = 90;
				rlLeg =90;
			  
		  }
		  
		  
		
		  if(state == this.STATE_GRAB) {
				 LimbRotation = (float) Math.cos(theta*10)*45;
				 if(LimbRotation>0) {
				 RightLimbRotation = LimbRotation*3;
				 LeftLimbRotation = 0;
				 }else {
					 LeftLimbRotation = LimbRotation*3;	 
					 RightLimbRotation = 0;
					 
				 }
				
		
				bind = -LimbRotation/4+10;
				lhLeg= 90;
				rhLeg = 90;
				lForem=-LimbRotation+30;
				rForem =0;
				rArm=0;
				lArm =LimbRotation+90;
				llLeg = 0;
				rlLeg = 0;
//				
				
			  
		  }
		  
	
		  
	
		
			 
			 
			 
	
		  
	//2.Comment: A texture sphere generator is created
	//			Texture sphere provides texture mapping comparing to pure sphere
	//			by useing texcoord2f(s,t)...
		
		 
		 //...
		 TexSphere sphere = new TexSphere();
		 Cylinder cylinder = new Cylinder();
		//...
	
		 //3.Comment: Sphere().draw is replace by TexSphere().draw in below
		 //            which is declared by DrawTexSphere(r,slice,segment,texture)
		 //				The forth argument will be assigned either head texture or body texture
 
		 GL11.glPushMatrix(); 
		 
		 {	
			 
			  GL11.glTranslatef(0.0f,0.5f,0.0f);  //face dir changing code
			  sphere.DrawTexSphere(0.5f, 32, 32,body); 
			  GL11.glRotatef(-bind, 1, 0, 0);
		        //  chest
			 GL11.glColor3f(green[0], green[1], green[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
			 GL11.glPushMatrix(); {
		            GL11.glTranslatef(0.0f,0.5f,0.0f);
		           
		            
		            //..
		            sphere.DrawTexSphere(0.5f, 32, 32,body); 
		            //...End Comment of this page from here

		            // neck
		       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(0.0f,0.0f, 0.0f);
		                GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
		                //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f); 
		                cylinder.DrawCylinder(0.15f,0.7f,32);


		                // head
		           	 GL11.glColor3f(red[0], red[1], red[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		                GL11.glPushMatrix(); {
		                	
		                    GL11.glTranslatef(0.0f,0.0f,1.0f);
		                    GL11.glRotated(-90.0f, 0.0f, 0.0f, 1.0f);
		                    GL11.glRotated(180,0.0,1.0,0.0f);
		                    control_head(delta);
		                    sphere.DrawTexSphere(0.5f, 32, 32,head);  
		                    

		                    
		                    
		            
		                GL11.glPopMatrix();
		                } GL11.glPopMatrix();


		                // left shoulder
		           	 GL11.glColor3f(blue[0],blue[1], blue[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.5f,0.4f,0.0f);
		                    GL11.glRotatef(leftOffset,0.0f,0.0f,1.0f);  
		                    sphere.DrawTexSphere(0.25f, 32, 32,body); 
		                    

		                    // left arm
		               	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.0f);
		                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
		                        
		                        control_larm(delta);
		                        GL11.glRotatef(lArm,1.0f,0.0f,0.0f); //xxx
		                       // GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);  
		                        cylinder.DrawCylinder(0.15f,0.7f,32);


		                        // left elbow
		                   	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.75f);
		                            //GL11.glRotatef(27, 0.0f, 0.0f, 1.0f);
		                            sphere.DrawTexSphere(0.2f, 32, 32,body); 
		                            
		                            //left forearm
		                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.0f);
		                                control_lforearm(delta);
		                                  GL11.glRotatef(lForem,1.0f,0.0f,0.0f); //xxx
		                                cylinder.DrawCylinder(0.1f,0.7f,32);

		                                // left hand
		                           	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                                
		                               GL11.glPushMatrix(); {
		                                	 control_lhand(delta);
		                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                    
		                                    sphere.DrawTexSphere(0.2f, 32, 32,body); 
		                                    


		                                } GL11.glPopMatrix();
		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix ();
		                } GL11.glPopMatrix ();
		                // to chest

		                // right shoulder
			           	 GL11.glColor3f(blue[0],blue[1], blue[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(-0.5f,0.4f,0.0f);
			                    GL11.glRotatef(rightOffset,0.0f,0.0f,-1.0f); 
			                    sphere.DrawTexSphere(0.25f, 32, 32,body); 
			              

		                    // right arm
			                    GL11.glColor3f(orange[0], orange[1], orange[2]);
				                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                   GL11.glPushMatrix();{
				                	   GL11.glTranslatef(0.0f,0.0f,0.0f);
				                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
				                        
				                        control_rarm(delta);
				                        GL11.glRotatef(rArm,1.0f,0.0f,0.0f); 
				                     //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);  
				                        cylinder.DrawCylinder(0.15f,0.7f,32);
				                  
		                        // right elbow
				                        GL11.glColor3f(blue[0], blue[1], blue[2]);
					                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
					                        GL11.glPushMatrix(); {
					                            GL11.glTranslatef(0.0f,0.0f,0.75f);
					                          // GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
					                            sphere.DrawTexSphere(0.2f, 32, 32,body); 
		                            //right forearm
					                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
					                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
					                            GL11.glPushMatrix(); {
					                                GL11.glTranslatef(0.0f,0.0f,0.0f);
					                                              // GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
					                                control_rforearm(delta);
					                                GL11.glRotatef(rForem,1.0f,0.0f,0.0f); 
					                                cylinder.DrawCylinder(0.1f,0.7f,32);
		                                // right hand
					                              	 GL11.glColor3f(blue[0], blue[1], blue[2]);
						                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
						                                GL11.glPushMatrix(); {
						                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
						                                    control_rhand(delta);
						                                    sphere.DrawTexSphere(0.2f, 32, 32,body);
						                                } GL11.glPopMatrix();
					                            }GL11.glPopMatrix();
					                        }GL11.glPopMatrix();
		                          }GL11.glPopMatrix();
			                }GL11.glPopMatrix();    
		                //chest


		            } GL11.glPopMatrix();

		            GL11.glRotatef(-bind, -1, 0, 0);
		            // pelvis
		          

		            
// left hip
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
		               
		                control_lhip(delta);
		                sphere.DrawTexSphere(0.25f, 32, 32,body); 


		                // left high leg
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);
		                    //GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                   
		                    control_lhleg(delta);
		                    GL11.glRotatef(lhLeg,1.0f,0.0f,0.0f); 
		                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
		                    cylinder.DrawCylinder(0.15f,0.7f,32);


		                    // left knee
		               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); {
		                    
		                        GL11.glTranslatef(0.0f,0.0f,0.75f);
		                 	   control_lknee(delta);    
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                       
		                        sphere.DrawTexSphere(0.25f, 32, 32,body); 

		                        //left low leg
		                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); {
		                        	 
		                        	GL11.glTranslatef(0.0f,0.0f,0.0f);
		                            GL11.glRotatef(llLeg,1.0f,0.0f,0.0f);
		                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                            control_lleg(delta);
		                            cylinder.DrawCylinder(0.15f,0.7f,32);

		                            // left foot
		                       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                sphere.DrawTexSphere(0.3f, 32, 32,body);  
		                                if(this.isFiring) {
		                                	GL11.glTranslatef(0.0f,0.0f,0.0f);
			                                cylinder.DrawCylinder(0.15f, 0.7f, 32);
		                                }
		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix();
		                } GL11.glPopMatrix();
		            } GL11.glPopMatrix();
		            // pelvis


		            // right hip
			       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			            GL11.glPushMatrix(); {
			            	  
			                GL11.glTranslatef(0.5f,-0.2f,0.0f);
			                control_rhip(delta);
			                sphere.DrawTexSphere(0.25f, 32, 32,body); 


			                // right high leg
			           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                GL11.glPushMatrix(); {
			                	
			                    GL11.glTranslatef(0.0f,0.0f,0.0f);
			                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
			                
			                    control_rhleg(delta);
			                    GL11.glRotatef(rhLeg,1.0f,0.0f,0.0f); 
			                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
			                    cylinder.DrawCylinder(0.15f,0.7f,32);


			                    // right knee
			               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                    GL11.glPushMatrix(); {
			                        GL11.glTranslatef(0.0f,0.0f,0.75f);
			                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                        control_rknee(delta);
			                        sphere.DrawTexSphere(0.25f, 32, 32,body); 

			                        //right low leg
			                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
			                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                        GL11.glPushMatrix(); {
			                            GL11.glTranslatef(0.0f,0.0f,0.0f);
			                            control_rlleg(delta);
			                            GL11.glRotatef(rlLeg,1.0f,0.0f,0.0f);
			                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                            cylinder.DrawCylinder(0.15f,0.7f,32);

			                            // right foot
			                       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                            GL11.glPushMatrix(); {
			                                GL11.glTranslatef(0.0f,0.0f,0.75f);
			                                sphere.DrawTexSphere(0.3f, 32, 32,body);  
			                                if(this.isFiring) {
			                                	GL11.glTranslatef(0.0f,0.0f,0.0f);
				                                cylinder.DrawCylinder(0.15f, 0.7f, 32);
			                                }

			                            } GL11.glPopMatrix();
			                        } GL11.glPopMatrix();
			                    } GL11.glPopMatrix();
			                } GL11.glPopMatrix();
			            } GL11.glPopMatrix();
		        
		        } GL11.glPopMatrix();
		         
		    }
	}




	public void control_lhand(float delta) {
		// TODO Auto-generated method stub
		
	}




	public void control_rhand(float delta) {
		// TODO Auto-generated method stub
	
	}




	public boolean control_hat(float delta, boolean isDraw) {
		// TODO Auto-generated method stub
		return isDraw;
	}




	public void control_head(float delta) {
		// TODO Auto-generated method stub
		
	}




	public void control_larm(float delta) {
		// TODO Auto-generated method stub
		
	}




	public void control_lforearm(float delta) {
		// TODO Auto-generated method stub
		
	}




	public void control_rarm(float delta) {
		// TODO Auto-generated method stub
		
	}




	public void control_rforearm(float delta) {
		// TODO Auto-generated method stub
		
	}




	public void control_lhip(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_lhleg(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_rknee(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_rhip(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_lknee(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_rlleg(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_rhleg(float delta) {
		// TODO Auto-generated method stub
		
	}




	private void control_lleg(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	
}
 
	
	 