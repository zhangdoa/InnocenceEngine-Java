package com.base.engine.audio;

import java.nio.FloatBuffer;

import com.base.engine.math.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public class Listener {
	public Vector3f pos;
	public Vector3f vel;
	public Vector3f oriAt;
	public Vector3f oriUp;

	public Listener()

	{
		pos = new Vector3f(0.0f, 0.0f, 0.0f);
		vel = new Vector3f(0.0f, 0.0f, 0.0f);
		oriAt = new Vector3f(0.0f, 0.0f, -1.0f);
		oriUp = new Vector3f(0.0f, 1.0f, 0.0f);

		AL10.alListener3f(AL10.AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
		AL10.alListener3f(AL10.AL_VELOCITY, vel.getX(), vel.getY(), vel.getZ());

		/**
		 * Orientation of the listener. (first 3 elements are "at", second 3 are
		 * "up")
		 */
		AL10.alListener(AL10.AL_ORIENTATION, (FloatBuffer) BufferUtils.createFloatBuffer(6)
				.put(new float[] { oriAt.getX(), oriAt.getY(), oriAt.getZ(), oriUp.getX(), oriUp.getY(), oriUp.getZ() })
				.rewind());

	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
		AL10.alListener3f(AL10.AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
	}

	public Vector3f getVel() {
		return vel;
	}

	public void setVel(Vector3f vel) {
		this.vel = vel;
		AL10.alListener3f(AL10.AL_VELOCITY, vel.getX(), vel.getY(), vel.getZ());
	}

	public Vector3f getOriAt() {
		return oriAt;

	}

	public void setOriAt(Vector3f oriAt) {
		this.oriAt = oriAt;
		AL10.alListener(AL10.AL_ORIENTATION, (FloatBuffer) BufferUtils.createFloatBuffer(6)
				.put(new float[] { oriAt.getX(), oriAt.getY(), oriAt.getZ(), oriUp.getX(), oriUp.getY(), oriUp.getZ() })
				.rewind());
	}

	public Vector3f getOriUp() {
		return oriUp;
	}

	public void setOriUp(Vector3f oriUp) {
		this.oriUp = oriUp;
		AL10.alListener(AL10.AL_ORIENTATION, (FloatBuffer) BufferUtils.createFloatBuffer(6)
				.put(new float[] { oriAt.getX(), oriAt.getY(), oriAt.getZ(), oriUp.getX(), oriUp.getY(), oriUp.getZ() })
				.rewind());
	}

}
