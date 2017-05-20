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

	private GameObject directionalLightObject;
	private GameObject pointLightObject1;
	private GameObject pointLightObject2;
	private GameObject spotLightObject1;

	private GameObject cameraObject1;

	@Override
	public void init() {

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

		Material matertial1 = new Material();// new Material(new
												// Texture("test.png"), new
												// Vector3f(1, 1, 1), 1, 8);

		matertial1.addTexture("diffuse", new Texture("test.png"));
		matertial1.addFloat("specularIntensity", 1.0f);
		matertial1.addFloat("specularPower", 8.0f);

		MeshRenderer meshRenderer1 = new MeshRenderer(mesh1, matertial1);

		planeObject1.addComponent(meshRenderer1);
		planeObject1.getTransform().getPos().set(0, -1, 5);

		planeObject2 = new GameObject();

		Mesh mesh2 = new Mesh("deer.obj");

		Material matertial2 = new Material();

		matertial2.addTexture("diffuse", new Texture("test.png"));
		matertial2.addFloat("specularIntensity", 1.0f);
		matertial2.addFloat("specularPower", 8.0f);

		MeshRenderer meshRenderer2 = new MeshRenderer(mesh2, matertial2);

		planeObject2.addComponent(meshRenderer2);
		planeObject2.getTransform().getPos().set(7, -1, 15);
		planeObject2.getTransform().getScale().set(0.01f, 0.01f, 0.01f);

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

		addObject(planeObject1);
		addObject(planeObject2);
		addObject(directionalLightObject);
		addObject(pointLightObject1);
		addObject(pointLightObject2);
		addObject(spotLightObject1);
		addObject(cameraObject1);

		directionalLightObject.getTransform()
				.setRot(new Quaternion(new Vector3f(1.0f, 0, 0), (float) Math.toRadians(-45)));
		pointLightObject1.getTransform().getPos().set(3, 0, 15);
		pointLightObject2.getTransform().getPos().set(16, 0, 15);
		spotLightObject1.getTransform().getPos().set(7, 0, 15);
		spotLightObject1.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));

		AudioSource audioSource = new AudioSource();
		audioSource.play("BMV1007.wav");
	}

}
