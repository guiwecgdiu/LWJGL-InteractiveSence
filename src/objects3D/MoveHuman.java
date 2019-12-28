package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import objects3D.Human;

public class MoveHuman extends Human {

	public float pos_y = 300f;
	Texture metal, wood;
	public float pos_x = 900f, pos_z = 600f;
	public boolean isMove;
	boolean endGoto;
	float speed;
	public final static float DIR_X = 1;
	public final static float DIR_Z = 0;
	public int humanTimer = 0;
	public boolean hasAim;
	public boolean isCarry;
	public boolean isOperate=false;
	public boolean isBin=false;


	
	@Override
	public void control_head(float delta) {
		// TODO Auto-generated method stub
		if(isOperate) {
		GL11.glPushMatrix();
		Cube cube= new Cube();
		GL11.glScalef(0.3f,0.3f, 0.3f);
		GL11.glTranslatef(0.0f,0.0f,-6.0f);
		cube.DrawCube();
		GL11.glPopMatrix();
		}
		if(isBin) {
			GL11.glPushMatrix();
			Sphere s= new Sphere();
			GL11.glScalef(0.3f,0.3f, 0.3f);
			GL11.glTranslatef(0.0f,0.0f,-6.0f);
			s.DrawSphere(1.0f, 32, 32);
			GL11.glPopMatrix();
			}
		
	}
	float aim_x;
	float aim_z;

	public MoveHuman(Texture metalTex, Texture woodTex) {
		super();
		isMove=false;
		metal = metalTex;
		wood = woodTex;
		isMove = false;
		speed = 1.0f;
		aim_x = pos_x;
		aim_z = pos_z;
		isCarry=false;

	}

	public MoveHuman(Texture metalTex, Texture woodTex, float posx, float posz) {
		super();
		metal = metalTex;
		wood = woodTex;
		isMove = false;
		speed = 1.0f;
		this.pos_x = posx;
		this.pos_z = posz;
		aim_x = pos_x;
		aim_z = pos_z;
		isCarry=false;
	}

	public MoveHuman(Texture metalTex, Texture woodTex, float posx, float y, float posz) {
		super();
		metal = metalTex;
		wood = woodTex;
		isMove = false;
		speed = 1.0f;
		this.pos_x = posx;
		this.pos_z = posz;
		pos_y = y;
		aim_x = pos_x;
		aim_z = pos_z;
		isCarry=false;
	}	

	public float getZ() {
		return pos_z;
	}
	public void setPosition(float x, float y, float z) {
		this.pos_x=x;
		this.pos_y=y;
		this.pos_z=z;
	}
	private void applyTransf() {
		GL11.glTranslatef(pos_x, pos_y, pos_z);
		GL11.glScalef(200f, 200f, 200f);
		aim_x = pos_x;
		aim_z = pos_z;
	}

	public void state_jump(float delta) {
		float height = (float) Math.cos(0.5 * delta * 360) * 100f;
		pos_y += height;
		if (pos_y < 300) {
			pos_y = 300;
		}

	}
	
	

	public void gesture_Human_rotate(float delta) {

		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta);
		float posn_y = (float) Math.sin(theta);

		GL11.glPushMatrix();
		{

			// GL11.glTranslatef(900f, 300f,600f);
			applyTransf();

			float offset = 180;
			float angle = 360 - (thetaDeg + 360) % 360 + offset;
			GL11.glTranslatef(posn_x * 1.0f, 0.0f, posn_y * 1.0f);
			GL11.glRotated(angle, 0.0f, 0.5f, 0.0f);
			// ...

			DrawTexHuman(delta, metal, wood, super.STATE_WAVE);
			// ....
		}
		GL11.glPopMatrix();

	}

	public void gesture_Human_stop(float delta) {
		this.hasAim=false;

		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta);
		float posn_y = (float) Math.sin(theta);

		GL11.glPushMatrix();
		{

			// GL11.glTranslatef(900f, 300f,600f);
			applyTransf();

			DrawTexHuman(delta, metal, wood, super.STATE_REST);
			// ....
		}
		GL11.glPopMatrix();

	}
	
	

	public void gesture_Human_free(float delta) {

		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta);
		float posn_y = (float) Math.sin(theta);

		GL11.glPushMatrix();
		{

			// GL11.glTranslatef(900f, 300f,600f);
			applyTransf();

			DrawTexHuman(delta, metal, wood, super.STATE_FREE);
			// ....
		}
		GL11.glPopMatrix();

	}

	@Override
	public void DrawHuman(float delta) {
		// TODO Auto-generated method stub
			if(isMove) {
				this.DrawTexHuman(delta, head, body, this.STATE_WAVE);}
			else {
				this.DrawTexHuman(delta,head,body,this.STATE_REST);
			}
	}
	public float angle=180;
	public void rotate(float a) {
		angle=a;
	}
	
	public boolean isJump =false;
	public void jump() {
		this.pos_y+=100;
		this.isJump=true;

	}
	
	@Override
	public void DrawTexHuman(float delta, Texture head, Texture body, float state) {
		// TODO Auto-generated method stub
		GL11.glPushMatrix();
		GL11.glRotatef(angle, 0, 1, 0);
		super.DrawTexHuman(delta, head, body, state);
		GL11.glPopMatrix();
	}
	
	
	@Override
	public void control_lhand(float delta) {
		// TODO Auto-generated method stub
		if(isCarry) {
			GL11.glPushMatrix();
			Cube cube= new Cube();
			GL11.glScalef(0.3f,0.3f, 0.3f);
			GL11.glTranslatef(0.0f,0.0f,4.0f);
			cube.DrawCube();
			GL11.glPopMatrix();
		}
	}
	
	public void gesture_Human_sit(float delta) {

		GL11.glPushMatrix();
		{

			// GL11.glTranslatef(900f, 300f,600f);
			applyTransf();

			DrawTexHuman(delta, metal, wood, super.STATE_SIT);
			// ....
		}
		GL11.glPopMatrix();

	}

	public void gesture_Human_move(float delta, float Dir, float value) {

		float sign = 0;
		if (Dir == this.DIR_X) {
			sign = addX(speed * value);
			pos_x += value;
		} else {
			sign = this.addZ(speed * value);
		}
		GL11.glPushMatrix();
		{

			// GL11.glTranslatef(900f, 300f,600f);
			applyTransf();

			GL11.glRotated(-90.0f * sign, 0.0f, 0.5f, 0.0f);
			// ...

			DrawTexHuman(delta, metal, wood, super.STATE_WAVE);
			// ....
		}
		GL11.glPopMatrix();
		// System.out.println("Pos x-z:"+pos_x+","+pos_z);
	}

	private float addX(float value) {
		pos_x += value;
		if (value > 0) {
			return 1.0f;
		} else {
			return -1.0f;
		}
	}

	private float addZ(float value) {
		pos_z += value;
		if (value > 0) {
			return -2.0f;
		} else {
			return 4.0f;
		}
	}

	public void setAim(float aim_x, float aim_z) {
		this.hasAim=true;
		this.aim_x = aim_x;
		this.aim_z = aim_z;
		
	}

	public void gesture_Human_goTo(float delta) {
		if (!isMove) {
			// drawGL11.glPushMatrix();{

			// GL11.glTranslatef(900f, 300f,600f);
			this.gesture_Human_stop(delta);
			this.humanTimer++;
			if (this.humanTimer > 100) {
				this.isMove = true;
			}
		} else {
			float disX = aim_x - pos_x;
			float disZ = aim_z - pos_z;
			// System.out.println("Current distance is: "+disX+" "+disZ);
			if (disX > -40f && disX < 40f) {
				if (disZ > -40f && disZ < 40f) {
					// this.isMove=false;
					this.gesture_Human_stop(delta);
					// this.gesture_Human_rotate(delta);

				} else {
					float sign;
					if (disZ > 0) {
						sign = 1.0f;
					} else {
						sign = -1.0f;
					}

					this.gesture_Human_move(delta, this.DIR_Z, sign * 10);

				}

			} else {
				float sign;
				if (disX > 0) {
					sign = 1.0f;
				} else {
					sign = -1.0f;
				}

				this.gesture_Human_move(delta, this.DIR_X, sign * 5);

			}

		}
	}
	
	
	



	public void gesture_Human_goTo_rotate(float delta) {
		if (!isMove) {
			// drawGL11.glPushMatrix();{

			// GL11.glTranslatef(900f, 300f,600f);
			this.gesture_Human_stop(delta);
			this.humanTimer++;
			if (this.humanTimer > 100) {
				this.isMove = true;
			}
		} else {
			float disX = aim_x - pos_x;
			float disZ = aim_z - pos_z;
			// System.out.println("Current distance is: "+disX+" "+disZ);
			if (disX > -40f && disX < 40f) {
				if (disZ > -40f && disZ < 40f) {
					// this.isMove=false;
					this.gesture_Human_rotate(delta);
					;
					// this.gesture_Human_rotate(delta);

				} else {
					float sign;
					if (disZ > 0) {
						sign = 1.0f;
					} else {
						sign = -1.0f;
					}

					this.gesture_Human_move(delta, this.DIR_Z, sign * 10);

				}

			} else {
				float sign;
				if (disX > 0) {
					sign = 1.0f;
				} else {
					sign = -1.0f;
				}

				this.gesture_Human_move(delta, this.DIR_X, sign * 5);

			}

		}
	}
}
