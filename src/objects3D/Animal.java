package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Utils;

public class Animal {
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
		static float pink[] = {  1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
		static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	//	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
		Sphere sphere;
		Cylinder cylinder;
		TexSphere chest;
		Cube cube;
	 	TexSphere head;
	 	public static final float STATE_DOGRUN = 0;
		public static final float STATE_DOGGO = 1;
	 	public Animal() {
	 		sphere= new Sphere();
			cylinder= new Cylinder();
			chest = new TexSphere();
		 	head = new TexSphere();
		 	cube=new Cube();
		 	
	 	}
	public void DrawDog(float delta,float state) {
		float la=0f;
		float ra=0f;
		float ll=0f;
		float rl=0f;
	  float theta = (float) (delta * 2 * Math.PI);
	  float LimbRotation  = (float) Math.cos(theta*10)*45;
	  if(state==this.STATE_DOGRUN) { 
//1.Comment: Nothing different, just see above in parallel
		  la=LimbRotation/2;
			ra=LimbRotation/2;
			ll=-LimbRotation/2;
			rl=-LimbRotation/2;
	
	}
	  if(state==this.STATE_DOGGO) { 
			la=LimbRotation/(2.5f);
			ra=-LimbRotation/(2.5f);
			ll=-LimbRotation/(2.5f);
			rl=LimbRotation/(2.5f);
//1.Comment: Nothing different, just see above in parallel
		 
	}
		// TODO Auto-generated method stub
		 GL11.glColor3f(pink[0], pink[1], pink[2]);
         GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
          GL11.glPushMatrix(); {
              GL11.glTranslatef(0.0f,0.0f, 0.0f);
              cylinder.DrawCylinder(0.1f,0.15f,32);
              //Æ¨¹É
              GL11.glColor3f(pink[0], pink[1], pink[2]);
              GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
               GL11.glPushMatrix(); {
                   GL11.glTranslatef(0.0f,0.0f, 0.0f);
                   sphere.DrawSphere(0.1f, 32, 32);
                   //Î²°Í
                   GL11.glColor3f(pink[0], pink[1], pink[2]);
                   GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f, -0.1f);
                       // cylinder.DrawCylinder(0.1f,0.15f,32);
                        sphere.DrawSphere(0.035f,16,16);
                    }GL11.glPopMatrix();
                    //ºóÍÈ1
                    GL11.glColor3f(pink[0], pink[1], pink[2]);
                    GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                     GL11.glPushMatrix(); {
                    	 GL11.glRotatef(90, 1, 0, 0);
                    	 
                    	 GL11.glRotatef(la, 1, 0, 0);
                    	 
                         GL11.glTranslatef(-0.06f,0f, 0.04f);
                         
                         cylinder.DrawCylinder(0.03f,0.15f,32);
                         //feet1
                         GL11.glColor3f(pink[0], pink[1], pink[2]);
                         GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                          GL11.glPushMatrix(); {
                        	  GL11.glTranslatef(0f,0f,0.15f);
                              sphere.DrawSphere(0.035f,16,16);
                         
                          }GL11.glPopMatrix();
                     }GL11.glPopMatrix();
                   //ºóÍÈ2
                     GL11.glColor3f(pink[0], pink[1], pink[2]);
                     GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                      GL11.glPushMatrix(); {
                    	  GL11.glRotatef(90, 1, 0, 0);
                          GL11.glTranslatef(0.06f,0f, 0.04f);
                          GL11.glRotatef(ra, 1, 0, 0);
                          cylinder.DrawCylinder(0.03f,0.15f,32);
                          //feet2
                          GL11.glColor3f(pink[0], pink[1], pink[2]);
                          GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                           GL11.glPushMatrix(); {
                        	   GL11.glTranslatef(0f,0f,0.15f);
                               sphere.DrawSphere(0.035f, 16, 16);
                          
                           }GL11.glPopMatrix();
                      }GL11.glPopMatrix();
               }GL11.glPopMatrix();
              
               GL11.glColor3f(pink[0], pink[1], pink[2]);
               GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f, 0.15f);
                    sphere.DrawSphere(0.1f, 32, 32);
                    //²±×Ó
                    GL11.glColor3f(pink[0], pink[1], pink[2]);
                    GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                     GL11.glPushMatrix(); {
                         GL11.glTranslatef(0.0f,0.0f, 0.02f);
                         GL11.glRotatef(-30, 1, 0, 0);
                         cylinder.DrawCylinder(0.03f, 0.11f, 32);
                         //head
                         GL11.glColor3f(pink[0], pink[1], pink[2]);
                         GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                          GL11.glPushMatrix(); {
                              GL11.glTranslatef(0f,0.01f, 0.05f);
//                              GL11.glRotatef(-30, 1, 0, 0);
                              sphere.DrawSphere(0.07f, 32, 32);
                            // cylinder.DrawCylinder(0.1f,0.2f,32);
                              //×ì
                              GL11.glColor3f(pink[0], pink[1], pink[2]);
                              GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                               GL11.glPushMatrix(); {
//                                   GL11.glTranslatef(0f,-0.04f, 0.03f);
//                                   sphere.DrawSphere(0.03f,16,16);
//                               //    cylinder.DrawCylinder(0.1f,0.15f,32);
//                                   
                                   
                               }GL11.glPopMatrix();
                              //¶ú¶ä
                               GL11.glColor3f(pink[0], pink[1], pink[2]);
                               GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                                GL11.glPushMatrix(); {
                                	  GL11.glTranslatef(0.035f,0.04f, 0.05f);
                                      sphere.DrawSphere(0.03f, 32, 32);
                                    
                                    
                                }GL11.glPopMatrix();
                                //¶ú¶ä2
                                GL11.glColor3f(pink[0], pink[1], pink[2]);
                                GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                                 GL11.glPushMatrix(); {
                                     GL11.glTranslatef(-0.035f,0.04f, 0.05f);
                                     sphere.DrawSphere(0.03f, 32, 32);
                                     
                                     
                                     
                                 }GL11.glPopMatrix();
                                 //ÑÛ¾¦1
                                 GL11.glColor3f(white[0], white[1], white[2]);
                                 GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
                                  GL11.glPushMatrix(); {
                                      GL11.glTranslatef(0.035f,0.02f, 0.06f);
                                      sphere.DrawSphere(0.02f, 32, 32);
                                      
                                      
                                      
                                  }GL11.glPopMatrix();
                                  //ÑÛ¾¦2
                                  GL11.glColor3f(white[0], white[1], white[2]);
                                  GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
                                   GL11.glPushMatrix(); {
                                       GL11.glTranslatef(-0.035f,0.02f, 0.06f);
                                       sphere.DrawSphere(0.02f, 32, 32);
                                       
                                       
                                       
                                   }GL11.glPopMatrix();
                                   //ÑÛÇò
                                   GL11.glColor3f(black[0], black[1], black[2]);
                                   GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
                                    GL11.glPushMatrix(); {
                                        GL11.glTranslatef(0.035f,0.02f, 0.08f);
                                        sphere.DrawSphere(0.01f, 32, 32);
                                        
                                        
                                        
                                    }GL11.glPopMatrix();
                                    //ÑÛÇò2
                                    GL11.glColor3f(black[0], black[1], black[2]);
                                    GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
                                     GL11.glPushMatrix(); {
                                         GL11.glTranslatef(-0.035f,0.02f, 0.08f);
                                         sphere.DrawSphere(0.01f, 32, 32);
                                         
                                         
                                         
                                     }GL11.glPopMatrix();
                          }GL11.glPopMatrix();
                         
                         
                     }GL11.glPopMatrix();
                   //ºóÍÈ1
                     GL11.glColor3f(pink[0], pink[1], pink[2]);
                     GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                      GL11.glPushMatrix(); {
                     	 GL11.glRotatef(90, 1, 0, 0);
                     	 GL11.glRotatef(ll, 1, 0, 0);
                          GL11.glTranslatef(-0.06f,0f, 0.04f);
                          
                          cylinder.DrawCylinder(0.03f,0.15f,32);
                          //feet1
                          GL11.glColor3f(pink[0], pink[1], pink[2]);
                          GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                           GL11.glPushMatrix(); {
                         	  GL11.glTranslatef(0f,0f,0.15f);
                               sphere.DrawSphere(0.035f, 32, 32);
                          
                           }GL11.glPopMatrix();
                      }GL11.glPopMatrix();
                    //ºóÍÈ2
                      GL11.glColor3f(pink[0], pink[1], pink[2]);
                      GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                       GL11.glPushMatrix(); {
                     	  GL11.glRotatef(90, 1, 0, 0);
                     	 GL11.glRotatef(rl, 1, 0, 0);
                           GL11.glTranslatef(0.06f,0f, 0.04f);
                           cylinder.DrawCylinder(0.03f,0.15f,32);
                           //feet2
                           GL11.glColor3f(pink[0], pink[1], pink[2]);
                           GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
                            GL11.glPushMatrix(); {
                         	   GL11.glTranslatef(0f,0f,0.15f);
                                sphere.DrawSphere(0.035f, 32, 32);
                           
                            }GL11.glPopMatrix();
                       }GL11.glPopMatrix();
                    
                }GL11.glPopMatrix();
              
          } GL11.glPopMatrix();
          
	}

}
