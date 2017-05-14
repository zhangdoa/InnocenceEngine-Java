package com.base.engine.math;

import com.base.engine.components.Camera;

public class Transform {
	private Transform parent;
	private Matrix4f parentMatrix;

	private Vector3f pos;
	private Quaternion rot;
	private Vector3f scale;

	private Vector3f oldPos;
	private Quaternion oldRot;
	private Vector3f oldScale;

	public Transform() {

		parentMatrix = new Matrix4f().initIdentity();
		pos = new Vector3f(0, 0, 0);
		rot = new Quaternion(0, 0, 0, 1);
		scale = new Vector3f(1, 1, 1);

	}

	public void update() {

		if (oldPos != null) {
			oldPos.set(pos);
			oldRot.set(rot);
			oldScale.set(scale);
		}

		else {
			oldPos = new Vector3f(0, 0, 0).set(pos).add(1.0f);
			oldRot = new Quaternion(0, 0, 0, 0).set(rot).mul(0.5f);
			oldScale = new Vector3f(0, 0, 0).set(scale).add(1.0f);
		}

	}

	public void rotate(Vector3f axis, float angle) {
		rot = new Quaternion(axis, angle).mul(rot).normalized();
	}

	public boolean hasChanged() {

		if (!pos.equals(oldPos) || !rot.equals(oldRot) || !scale.equals(oldScale)) {
			return true;
		}

		if (parent != null && parent.hasChanged()) {
			return true;
		}

		return false;
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(pos.getX(), pos.getY(), pos.getZ());
		Matrix4f rotaionMartix = rot.toRotationMatrix();
		Matrix4f scaleMartix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

		return getParentMatrix().mul(translationMatrix.mul(rotaionMartix.mul(scaleMartix)));
	}

	private Matrix4f getParentMatrix() {
		if (parent != null && parent.hasChanged())
			parentMatrix = parent.getTransformation();

		return parentMatrix;

	}

	public void setParent(Transform parent) {
		this.parent = parent;
	}

	public Matrix4f getProjectedTransformation(Camera camera) {
		return camera.getViewProjection().mul(getTransformation());
	}

	public Vector3f getTransformedPos() {
		return getParentMatrix().transform(pos);
	}

	public Quaternion getTransformedRot() {

		Quaternion parentRotation = new Quaternion(0, 0, 0, 1);
		if (parent != null)
			parentRotation = parent.getTransformedRot();

		return parentRotation.mul(rot);
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Quaternion getRot() {
		return rot;
	}

	public void setRot(Quaternion rot) {
		this.rot = rot;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

}
