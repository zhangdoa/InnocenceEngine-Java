package com.base.engine.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.IntBuffer;

import org.newdawn.slick.openal.WaveData;

import com.base.engine.math.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public class AudioSource {
	private IntBuffer buffer;
	private IntBuffer source;

	private Vector3f pos;
	private Vector3f vel;

	public float gainModulator;
	public float pitchModulator;
	public int loop;

	public AudioSource() {
		buffer = BufferUtils.createIntBuffer(1);
		source = BufferUtils.createIntBuffer(1);

		AL10.alGenBuffers(buffer);
		AL10.alGenSources(source);

		gainModulator = 1.0f;
		pitchModulator = 1.0f;
		loop = 1;
		pos = new Vector3f(0.0f, 0.0f, 0.0f);
		vel = new Vector3f(0.0f, 0.0f, 0.0f);

		update();

	}

	public void update() {
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, gainModulator);
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, pitchModulator);
		AL10.alSource3f(source.get(0), AL10.AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
		AL10.alSource3f(source.get(0), AL10.AL_VELOCITY, vel.getX(), vel.getY(), vel.getZ());
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
	}

	public float getPitchModulator() {
		return pitchModulator;
	}

	public void setPitchModulator(float pitchModulator) {
		this.pitchModulator = pitchModulator;
	}

	public int getLoop() {
		return loop;
	}

	public void setLoop(int loop) {
		this.loop = loop;
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

}
