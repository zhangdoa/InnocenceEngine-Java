package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;

public abstract class Game {
	private GameObject root;

	public void init() {
	}

	public void input(float delta) {
		getRootObject().input(delta);
	}

	public void update(float delta) {
		getRootObject().update(delta);
	}

	public void render(RenderingEngine renderingEngine) {
		renderingEngine.render(root);
	}

	public void addObject(GameObject object) {
		getRootObject().addChind(object);
	}

	private GameObject getRootObject() {
		if (root == null)
			root = new GameObject();
		return root;
	}
}
