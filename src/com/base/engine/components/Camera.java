package com.base.engine.components;

import com.base.engine.core.Input;
import com.base.engine.math.Matrix4f;
import com.base.engine.math.Vector2f;
import com.base.engine.math.Vector3f;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

public class Camera extends GameComponent {

	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

	private Matrix4f projection;

	public Camera(float fov, float aspectRatio, float zNear, float zFar) {
		this.projection = new Matrix4f().initPerspective(fov, aspectRatio, zNear, zFar);
	}

	public Matrix4f getViewProjection() {
		Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
		Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);

		Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(),
				cameraPos.getZ());
		return projection.mul(cameraRotation.mul(cameraTranslation));
	}

	@Override
	public void addToRenderingEngine(RenderingEngine renderingEngine) {
		renderingEngine.addCamera(this);
	}

	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

	@Override
	public void input(float delta) {
		float sensitivity = 0.1f;
		float movAmt = 10 * delta;

		if (Input.getKey(Input.KEY_ESCAPE)) {
			Input.SetCursor(true);
			mouseLocked = false;
		}

		if (Input.getKey(Input.KEY_W))
			move(getTransform().getRot().getForward(), movAmt);
		if (Input.getKey(Input.KEY_S))
			move(getTransform().getRot().getForward(), -movAmt);
		if (Input.getKey(Input.KEY_A))
			move(getTransform().getRot().getLeft(), movAmt);
		if (Input.getKey(Input.KEY_D))
			move(getTransform().getRot().getRight(), movAmt);

		if (Input.GetMouseDown(0))

		{
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}

		if (mouseLocked) {

			Vector2f deltaPos = Input.GetMousePosition().sub(centerPosition);

			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;

			if (rotY)

				getTransform().rotate(yAxis, (float) Math.toRadians(deltaPos.getX() * sensitivity));

			if (rotX)

				getTransform().rotate(getTransform().getRot().getRight(),
						(float) Math.toRadians(-deltaPos.getY() * sensitivity));

			if (rotY || rotX)
				Input.SetMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
		}

	}

	@Override
	public void printInfo() {
		System.out.println(getTransform().getTransformedRot().getRight().getX() + ", "
				+ getTransform().getTransformedRot().getRight().getY() + ", "
				+ getTransform().getTransformedRot().getRight().getZ());
	}

	public void move(Vector3f dir, float amt) {
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}

}
