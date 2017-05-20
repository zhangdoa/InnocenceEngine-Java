package com.base.engine.rendering;

import java.util.HashMap;

import com.base.engine.math.Vector3f;

public class Material {
	private HashMap<String, Texture> textureHashMap;
	private HashMap<String, Vector3f> vector3fHashMap;
	private HashMap<String, Float> floatHashMap;

	public Material() {
		textureHashMap = new HashMap<String, Texture>();
		vector3fHashMap = new HashMap<String, Vector3f>();
		floatHashMap = new HashMap<String, Float>();
	}

	public void addTexture(String name, Texture texture) {
		textureHashMap.put(name, texture);
	}

	public Texture getTexture(String name) {
		Texture result = textureHashMap.get(name);
		if (result != null)
			return result;
		return new Texture("test.png");
	}

	public void addVector3f(String name, Vector3f vector3f) {
		vector3fHashMap.put(name, vector3f);
	}

	public Vector3f getVector3f(String name) {
		Vector3f result = vector3fHashMap.get(name);
		if (result != null)
			return result;
		return new Vector3f(0.0f, 0.0f, 0.0f);

	}

	public void addFloat(String name, Float floatValue) {
		floatHashMap.put(name, floatValue);
	}

	public Float getFloat(String name) {
		Float result = floatHashMap.get(name);
		if (result != null)
			return result;
		return 0.0f;
	}
}
