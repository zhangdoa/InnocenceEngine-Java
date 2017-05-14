package com.base.engine.core;

import java.util.ArrayList;

import com.base.engine.components.GameComponent;
import com.base.engine.math.Transform;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class GameObject {
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;
	private boolean printInfo;

	public GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
		printInfo = false;
	}

	public void addChind(GameObject child) {
		children.add(child);
		child.getTransform().setParent(transform);
	}

	public GameObject addComponent(GameComponent component) {
		components.add(component);
		component.setParent(this);

		return this;
	}

	public void input(float delta) {
		transform.update();

		for (GameComponent component : components)
			component.input(delta);

		for (GameObject child : children)
			child.input(delta);
	}

	public void update(float delta) {
		for (GameComponent component : components)
			component.update(delta);

		for (GameObject child : children)
			child.update(delta);

		if (printInfo) {
			printInfo();
		}
	}

	public void render(Shader shader) {
		for (GameComponent component : components)
			component.render(shader);

		for (GameObject child : children)
			child.render(shader);
	}

	public void setPrintInfo(boolean printInfo) {
		this.printInfo = printInfo;
	}

	public void printInfo() {
		for (GameComponent component : components)
			component.printInfo();

		for (GameObject child : children)
			child.printInfo();
	}

	public Transform getTransform() {
		return transform;
	}

	public void addToRenderingEngine(RenderingEngine renderingEngine) {
		for (GameComponent component : components)
			component.addToRenderingEngine(renderingEngine);

		for (GameObject child : children)
			child.addToRenderingEngine(renderingEngine);
	}
}
