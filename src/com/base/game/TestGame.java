package com.base.game;

import com.base.engine.audio.AudioSource;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.math.Vector2f;
import com.base.engine.math.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.BaseLight;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.PointLight;
import com.base.engine.rendering.SpotLight;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;

public class TestGame extends Game {
	private GameObject planeObject;

	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1.0f, 0.5f, 0), 0.8f), new Attenuation(0, 0, 1),
			new Vector3f(-2, 0, 5f), 5.0f);
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0, 0.5f, 1.0f), 0.8f), new Attenuation(0, 0, 1),
			new Vector3f(2, 0, 7f), 5.0f);

	SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(0, 1.0f, 1.0f), 0.8f),
			new Attenuation(0, 0, 0.1f), new Vector3f(-2, 0, 5f), 30.0f), new Vector3f(1, 1, 1), 0.7f);

	@Override
	public void init() {
		// Mesh mesh = new Mesh("deer.obj");
		planeObject = new GameObject();

		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] {
				new Vertex(new Vector3f(-fieldWidth, 0, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f)) };

		int[] indices = new int[] { 0, 1, 2, 2, 1, 3 };
		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().setPos(0, -1, 5);
		getRootObject().addChind(planeObject);

		AudioSource audioSource = new AudioSource();
		audioSource.play("BMV1007.wav");
		// Transform.setCamera(camera);
		// PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));
		// PhongShader.setDirectionalLight(
		// new DirectionalLight(new BaseLight(new Vector3f(1.0f, 1.0f, 1.0f),
		// 0.05f), new Vector3f(1, 1, 1)));
		// PhongShader.setPointLight(new PointLight[] { pLight1, pLight2 });
		// PhongShader.setSpotLight(new SpotLight[] { sLight1 });
	}

	// public void input()
	// {
	//
	// camera.input();
	// root.input();
	// }

	// float temp = 0.0f;

	// public void update()
	// {
	// root.update();
	// temp += Time.getDelta();
	// double sinTemp = Math.sin(temp);
	// double cosTemp = Math.cos(temp);
	// transform.setRotation(0, sinTemp * 180, 0);
	// transform.setScale(1.0f, 1.0f, 1.0f);
	// transform.setScale(0.002f, 0.002f, 0.002f);

	// pLight1.setPosition(new Vector3f(3, 0, 8.0f * (float) (sinTemp + 1.0
	// / 2.0) + 10));
	// pLight2.setPosition(new Vector3f(7, 0, 8.0f * (float) (cosTemp + 1.0
	// / 2.0) + 10));
	//
	// sLight1.getPointLight().setPosition(camera.getPos());
	// sLight1.setDirection(camera.getForward());
	//
	// }
	//
	// public void render()
	// {
	// root.render();
	// }

}
