package com.base.engine.core;

import com.base.engine.audio.AudioEngine;
import com.base.engine.rendering.Window;

public class CoreEngine
{
	private boolean isRunning;
	private Game game;
	private int width;
	private int height;
	private double frameTime;
	private RenderingEngine renderingEngine;
	private AudioEngine audioEngine;

	public CoreEngine(double framerate, Game game)
	{
		this.isRunning = false;
		this.game = game;
		this.frameTime = 1.0 / framerate;
		this.audioEngine = new AudioEngine();

	}

	public void createWindow(int width, int height, String title)
	{
		Window.createWindow(width, height, title);
		this.renderingEngine = new RenderingEngine();
	}

	public void start()
	{
		if(!isRunning)
		{
			run();
		}
	}

	public void stop()
	{
		if(isRunning)
		{
			isRunning = false;
		}
		return;
	}

	private void run()
	{
		isRunning = true;
		int frames = 0;
		long frameConter = 0;
		game.init();
		double lastTime = Time.getTime();
		double unprocessedTime = 0;

		while(isRunning)
		{
			boolean render = false;

			double startTime = Time.getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime;
			frameConter += passedTime;

			while(unprocessedTime > frameTime)
			{
				render = true;

				unprocessedTime -= frameTime;

				if(Window.isCloseRequested())
				{
					stop();
				}

				game.input((float) frameTime);
				Input.update();

				game.update((float) frameTime);

				if(frameConter >= 1.0)
				{
					System.out.println(frames);
					frames = 0;
					frameConter = 0;
				}

			}

			if(render)
			{
				renderingEngine.render(game.getRootObject());
				Window.render();
				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}

		}

		cleanUp();
	}

	private void cleanUp()
	{
		Window.dispose();
		AudioEngine.cleanUp();
	}

}
