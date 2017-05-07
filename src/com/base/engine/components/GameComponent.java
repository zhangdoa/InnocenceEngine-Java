package com.base.engine.components;

import com.base.engine.math.Transform;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public abstract class GameComponent {
	public void input(Transform transform, float delta) {
	};

	public void update(Transform transform, float delta) {
	};

	public void render(Transform transform, Shader shader) {
	};

	public void addToRenderingEngine(RenderingEngine renderingEngine) {
	};
}
