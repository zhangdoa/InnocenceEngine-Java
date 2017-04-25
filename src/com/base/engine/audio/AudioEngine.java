package com.base.engine.audio;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

public class AudioEngine
{
	// lwjgl 3.x

	// ALCdevice device;
	// ALCcontext alContext;

	public AudioEngine()
	{

		// lwjgl 3.x

		// String deviceSpecifier = null;
		// device = alcOpenDevice(deviceSpecifier);
		// alContext = alcCreateContext(device, (IntBuffer) null);
		// ALCCapabilities deviceCaps = AL.createCapabilities(device);
		// alcSetThreadContext(alContext);
		// ALCapabilities contextCaps = AL.createCapabilities(deviceCaps);

		// lwjgl 2.x
		try
		{
			AL.create();
		}
		catch(LWJGLException le)
		{
			le.printStackTrace();
			return;
		}
	}

	public static void cleanUp()
	{
		// lwjgl 3.x

		// alContext = (Long) null;
		// alcSetThreadContext(alContext);
		// alcDestroyContext(alContext);
		// alcCloseDevice(device);

		// lwjgl 2.x
		AL.destroy();
	}
}
