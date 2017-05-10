package com.base.game;

import com.base.engine.audio.AudioSource;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.MeshRenderer;
import com.base.engine.components.PointLight;
import com.base.engine.components.SpotLight;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.math.Vector2f;
import com.base.engine.math.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;

public class TestGame extends Game {
	private GameObject planeObject;

	private GameObject directionalLightObject;
	private GameObject pointLightObject1;
	private GameObject pointLightObject2;
	private GameObject spotLightObject1;

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

		directionalLightObject = new GameObject();
		pointLightObject1 = new GameObject();
		pointLightObject2 = new GameObject();
		spotLightObject1 = new GameObject();

		directionalLightObject.addComponent(
				new DirectionalLight(new Vector3f(0.2f, 0.3f, 0.1f), 1.0f, new Vector3f(1.0f, 0.5f, 0.5f)));
		pointLightObject1.addComponent(new PointLight(new Vector3f(1.0f, 0.5f, 0), 0.8f, new Vector3f(0, 0, 1)));
		pointLightObject2.addComponent(new PointLight(new Vector3f(0, 0.5f, 1.0f), 0.8f, new Vector3f(0, 0, 1)));
		spotLightObject1.addComponent(new SpotLight(new Vector3f(0, 1.0f, 1.0f), 0.8f, new Vector3f(0, 0, 0.1f),
				new Vector3f(1, 0, 0), 0.7f));

		spotLightObject1.getTransform().setPos(5, 0, 5);

		getRootObject().addChind(planeObject);
		getRootObject().addChind(directionalLightObject);
		getRootObject().addChind(pointLightObject1);
		getRootObject().addChind(pointLightObject2);
		getRootObject().addChind(spotLightObject1);

		AudioSource audioSource = new AudioSource();
		audioSource.play("BMV1007.wav");
	}

}
