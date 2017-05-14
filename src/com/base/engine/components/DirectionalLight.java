package com.base.engine.components;

import com.base.engine.math.Vector3f;
import com.base.engine.rendering.ForwardDirectional;

public class DirectionalLight extends BaseLight {

	public DirectionalLight(Vector3f color, float intensity) {
		super(color, intensity);

		setShader(ForwardDirectional.getInstance());
	}

	public Vector3f getDirection() {
		return getTransform().getTransformedRot().getForward();
	}

}
