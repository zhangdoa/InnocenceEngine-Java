package com.base.engine.audio;

import java.nio.FloatBuffer;

import com.base.engine.math.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public class Listener {
	private Vector3f pos;
	private Vector3f vel;
	private Vector3f oriForward;
	private Vector3f oriUp;

	public Listener()

	{
		pos = new Vector3f(0.0f, 0.0f, 0.0f);
		vel = new Vector3f(0.0f, 0.0f, 0.0f);
		oriForward = new Vector3f(0.0f, 0.0f, -1.0f);
		oriUp = new Vector3f(0.0f, 1.0f, 0.0f);
		update();

	}

	public void update() {
		AL10.alListener3f(AL10.AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
		AL10.alListener3f(AL10.AL_VELOCITY, vel.getX(), vel.getY(), vel.getZ());
		AL10.alListener(AL10.AL_ORIENTATION,
				(FloatBuffer) BufferUtils.createFloatBuffer(6).put(new float[] { oriForward.getX(), oriForward.getY(),
						oriForward.getZ(), oriUp.getX(), oriUp.getY(), oriUp.getZ() }).rewind());
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getVel() {
		return vel;
	}

	public void setVel(Vector3f vel) {
		this.vel = vel;
	}

	public Vector3f getOriForward() {
		return oriForward;

	}

	public void setOriForward(Vector3f oriForward) {
		this.oriForward = oriForward;
	}

	public Vector3f getOriUp() {
		return oriUp;
	}

	public void setOriUp(Vector3f oriUp) {
		this.oriUp = oriUp;
	}

}
