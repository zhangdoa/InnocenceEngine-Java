package com.base.engine.audio;

import java.util.Scanner;

import com.base.engine.core.AudioEngine;
import com.base.engine.math.Vector3f;

public class AudioTest {

	public static void main(String[] args) {
		AudioEngine audioEngine = new AudioEngine();
		AudioSource audioSource = new AudioSource();

		char c = ' ';
		Scanner stdin = new Scanner(System.in);
		while (c != 'q') {
			try {
				System.out.print("Input: ");
				c = stdin.nextLine().charAt(0);
			} catch (Exception ex) {
				c = 'q';
			}

			switch (c) {
			// Pressing 'p' will begin playing the sample.
			case 'p':
				audioSource.play("BMV1007.wav");

				break;

			// Pressing 's' will stop the sample from playing.
			case 's':
				audioSource.stop();
				break;

			// Pressing 'h' will pause the sample.
			case 'h':
				audioSource.pause();
				break;

			case 'a':

				audioSource.setPos(new Vector3f(0.3f, 0.0f, 0.0f));
				break;

			case 'd':

				audioSource.setPos(new Vector3f(-0.3f, 0.0f, 0.0f));
				break;

			}

		}
		audioSource.delete();
		AudioEngine.cleanUp();

	}
}
