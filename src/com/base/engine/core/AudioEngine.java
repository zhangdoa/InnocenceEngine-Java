package com.base.engine.core;

import com.base.engine.audio.Listener;
import com.base.engine.math.Vector3f;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

public class AudioEngine {
	// lwjgl 3.x

	// ALCdevice device;
	// ALCcontext alContext;

	private Listener listener;

	public AudioEngine() {

		// lwjgl 3.x

		// String deviceSpecifier = null;
		// device = alcOpenDevice(deviceSpecifier);
		// alContext = alcCreateContext(device, (IntBuffer) null);
		// ALCCapabilities deviceCaps = AL.createCapabilities(device);
		// alcSetThreadContext(alContext);
		// ALCapabilities contextCaps = AL.createCapabilities(deviceCaps);

		// lwjgl 2.x
		try {
			AL.create();

		} catch (LWJGLException le) {
			le.printStackTrace();

		}
		listener = new Listener();

	}

	public void update(Vector3f listenerPos, Vector3f listenerOriForward, Vector3f listenerOriUp) {
		listener.setPos(listenerPos);
		listener.setOriForward(listenerOriForward);
		listener.setOriUp(listenerOriUp);
		listener.update();

	}

	public static void cleanUp() {
		// lwjgl 3.x

		// alContext = (Long) null;
		// alcSetThreadContext(alContext);
		// alcDestroyContext(alContext);
		// alcCloseDevice(device);

		// lwjgl 2.x
		AL.destroy();
	}

	public void input(float delta) {

	}
}
