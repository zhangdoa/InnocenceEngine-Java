package com.base.game;

import com.base.engine.audio.AudioSource;
import com.base.engine.components.Camera;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.MeshRenderer;
import com.base.engine.components.PointLight;
import com.base.engine.components.SpotLight;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.math.Quaternion;
import com.base.engine.math.Vector2f;
import com.base.engine.math.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;
import com.base.engine.rendering.Window;

public class TestGame extends Game {
	private GameObject planeObject1;
	private GameObject planeObject2;
	private GameObject planeObject3;

	private GameObject directionalLightObject;
	private GameObject pointLightObject1;
	private GameObject pointLightObject2;
	private GameObject spotLightObject1;

	private GameObject cameraObject1;

	@Override
	public void init() {
		// Mesh mesh = new Mesh("deer.obj");
		planeObject1 = new GameObject();

		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] {
				new Vertex(new Vector3f(-fieldWidth, 0, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f)) };

		int[] indices = new int[] { 0, 1, 2, 2, 1, 3 };

		Mesh mesh1 = new Mesh(vertices, indices, true);
		Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
		MeshRenderer meshRenderer = new MeshRenderer(mesh1, material);

		planeObject1.addComponent(meshRenderer);
		planeObject1.getTransform().getPos().set(0, -1, 5);

		planeObject2 = new GameObject();
		planeObject3 = new GameObject();
		directionalLightObject = new GameObject();
		pointLightObject1 = new GameObject();
		pointLightObject2 = new GameObject();
		spotLightObject1 = new GameObject();
		cameraObject1 = new GameObject();

		directionalLightObject.addComponent(new DirectionalLight(new Vector3f(0.2f, 1.0f, 0.5f), 0.5f));
		pointLightObject1.addComponent(new PointLight(new Vector3f(0.2f, 0.4f, 0.7f), 3.0f, new Vector3f(0, 0, 1)));
		pointLightObject2.addComponent(new PointLight(new Vector3f(0, 0.5f, 0.7f), 3.0f, new Vector3f(0, 0, 1)));
		spotLightObject1
				.addComponent(new SpotLight(new Vector3f(1.0f, 0.5f, 0.2f), 3.0f, new Vector3f(0, 0, 0.1f), 0.7f));

		cameraObject1.addComponent(new Camera((float) Math.toRadians(70.0f),
				(float) Window.getWidth() / (float) Window.getHeight(), 0.1f, 1000));

		getRootObject().addChind(planeObject1);
		getRootObject().addChind(directionalLightObject);
		getRootObject().addChind(pointLightObject1);
		getRootObject().addChind(pointLightObject2);
		getRootObject().addChind(spotLightObject1);
		getRootObject().addChind(cameraObject1);

		directionalLightObject.getTransform()
				.setRot(new Quaternion(new Vector3f(1.0f, 0, 0), (float) Math.toRadians(-45)));
		pointLightObject1.getTransform().getPos().set(3, 0, 0);
		pointLightObject2.getTransform().getPos().set(16, 0, 0);
		spotLightObject1.getTransform().getPos().set(7, 0, 0);
		spotLightObject1.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));

		AudioSource audioSource = new AudioSource();
		audioSource.play("BMV1007.wav");
	}

}
