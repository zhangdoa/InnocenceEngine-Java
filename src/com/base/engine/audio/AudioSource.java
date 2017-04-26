package com.base.engine.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;

import com.base.engine.core.Input;
import com.base.engine.math.Vector3f;

public class AudioSource {
	private IntBuffer buffer;
	private IntBuffer source;

	public float gainModulator;
	public float pitchModulator;
	public int loop;

	public Vector3f sourcePos;
	public Vector3f sourceVel;

	public AudioSource() {
		buffer = BufferUtils.createIntBuffer(1);
		source = BufferUtils.createIntBuffer(1);

		AL10.alGenBuffers(buffer);
		AL10.alGenSources(source);

		gainModulator = 1.0f;
		pitchModulator = 1.0f;
		loop = 1;
		sourcePos = new Vector3f(0.0f, 0.0f, 0.0f);
		sourceVel = new Vector3f(0.0f, 0.0f, 0.0f);

		initSource();

	}

	private void initSource() {
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, gainModulator);
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, pitchModulator);
		AL10.alSource(source.get(0), AL10.AL_POSITION, (FloatBuffer) BufferUtils.createFloatBuffer(3)
				.put(new float[] { sourcePos.getX(), sourcePos.getY(), sourcePos.getZ() }).rewind());
		AL10.alSource(source.get(0), AL10.AL_VELOCITY, (FloatBuffer) BufferUtils.createFloatBuffer(3)
				.put(new float[] { sourceVel.getX(), sourceVel.getY(), sourceVel.getZ() }).rewind());
		AL10.alSourcei(source.get(0), AL10.AL_LOOPING, loop);
	}

	public void play(String fileName) {

		try {
			WaveData waveFile;
			waveFile = WaveData.create(new BufferedInputStream(new FileInputStream("./res/audio/" + fileName)));
			AL10.alBufferData(buffer.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
			waveFile.dispose();
			AL10.alSourcei(source.get(0), AL10.AL_BUFFER, buffer.get(0));
			AL10.alSourcePlay(source.get(0));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		AL10.alSourceStop(source.get(0));
	}

	public void pause() {
		AL10.alSourcePause(source.get(0));
	}

	public void delete() {
		AL10.alDeleteSources(source);
		AL10.alDeleteBuffers(buffer);
	}

	public float getGainModulator() {
		return gainModulator;
	}

	public void setGainModulator(float gainModulator) {
		this.gainModulator = gainModulator;
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, gainModulator);
	}

	public float getPitchModulator() {
		return pitchModulator;
	}

	public void setPitchModulator(float pitchModulator) {
		this.pitchModulator = pitchModulator;
		// FIXME
		// Unaffected
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, pitchModulator);
	}

	public int getLoop() {
		return loop;
	}

	public void setLoop(int loop) {
		this.loop = loop;
		AL10.alSourcei(source.get(0), AL10.AL_LOOPING, loop);
	}

	public Vector3f getSourcePos() {
		return sourcePos;
	}

	public void setSourcePos(Vector3f sourcePos) {
		this.sourcePos = sourcePos;
	}

	public Vector3f getSourceVel() {
		return sourceVel;
	}

	public void setSourceVel(Vector3f sourceVel) {
		this.sourceVel = sourceVel;
	}

	public void input() {
		float pitchmod = 1.0f;
		if (Input.getKey(Input.KEY_UP)) {
			pitchmod += 0.1f;
			this.setPitchModulator(0.5f);
			System.out.println("pitch shift up");
		}
		if (Input.getKey(Input.KEY_DOWN)) {
			pitchmod -= 0.1f;
			this.setPitchModulator(pitchmod);
			System.out.println("pitch shift down");
		}
	}
}
