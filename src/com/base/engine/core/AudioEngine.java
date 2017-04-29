package com.base.engine.core;

import com.base.engine.audio.AudioSource;
import com.base.engine.audio.Listener;
import com.base.engine.math.Vector3f;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

public class AudioEngine {
	// lwjgl 3.x

	// ALCdevice device;
	// ALCcontext alContext;

	public Listener listener;

	public AudioSource audioSource;

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
			return;
		}
		Listener listener = new Listener();
		listener.setPos(new Vector3f(0.0f, 0.0f, 0.0f));
		AudioSource audioSource = new AudioSource();
		audioSource.play("BMV1007.wav");
		audioSource.setPos(new Vector3f(0.3f, 0.3f, 0.0f));
	}

	public void update() {
		// TODO:audio source pos update

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
