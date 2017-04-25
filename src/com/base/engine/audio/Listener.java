package com.base.engine.audio;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import com.base.engine.math.Vector3f;

public class Listener
{
	public Vector3f listenerPos;
	public Vector3f listenerVel;
	public Vector3f listenerOriAt;
	public Vector3f listenerOriUp;

	public Listener()

	{
		listenerPos = new Vector3f(0.0f, 0.0f, 0.0f);
		listenerVel = new Vector3f(0.0f, 0.0f, 0.0f);
		listenerOriAt = new Vector3f(0.0f, 0.0f, -1.0f);
		listenerOriUp = new Vector3f(0.0f, 1.0f, 0.0f);

		AL10.alListener(AL10.AL_POSITION, (FloatBuffer) BufferUtils.createFloatBuffer(3)
				.put(new float[] { listenerPos.getX(), listenerPos.getY(), listenerPos.getZ() }).rewind());
		AL10.alListener(AL10.AL_POSITION, (FloatBuffer) BufferUtils.createFloatBuffer(3)
				.put(new float[] { listenerVel.getX(), listenerVel.getY(), listenerVel.getZ() }).rewind());

		/**
		 * Orientation of the listener. (first 3 elements are "at", second 3 are
		 * "up")
		 */
		AL10.alListener(AL10.AL_ORIENTATION, (FloatBuffer) BufferUtils
				.createFloatBuffer(6).put(new float[] { listenerOriAt.getX(), listenerOriAt.getY(),
						listenerOriAt.getZ(), listenerOriUp.getX(), listenerOriUp.getY(), listenerOriUp.getZ() })
				.rewind());

	}

}
