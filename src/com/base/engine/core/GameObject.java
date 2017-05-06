package com.base.engine.core;

import java.util.ArrayList;

import com.base.engine.math.Transform;
import com.base.engine.rendering.Shader;

public class GameObject {
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;

	public GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
	}

	public void addChind(GameObject child) {
		children.add(child);
	}

	public void addComponent(GameComponent component) {
		components.add(component);
	}

	public void input(float delta) {
		for (GameComponent component : components)
			component.input(transform, delta);

		for (GameObject child : children)
			child.input(delta);
	}

	public void update(float delta) {
		for (GameComponent component : components)
			component.update(transform, delta);

		for (GameObject child : children)
			child.update(delta);
	}

	public void render(Shader shader) {
		for (GameComponent component : components)
			component.render(transform, shader);

		for (GameObject child : children)
			child.render(shader);
	}

	public Transform getTransform() {
		return transform;
	}
}
