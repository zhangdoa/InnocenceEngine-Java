package com.base.engine.rendering;

import com.base.engine.core.Input;
import com.base.engine.core.Time;
import com.base.engine.math.Matrix4f;
import com.base.engine.math.Vector2f;
import com.base.engine.math.Vector3f;

public class Camera
{

	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	private Matrix4f projection;

	public Camera(float fov, float aspectRatio, float zNear, float zFar)
	{
		this.pos = new Vector3f(0, 0, 0);
		this.forward = new Vector3f(0, 0, 1).normalized();
		this.up = new Vector3f(0, 1, 0).normalized();
		this.projection = new Matrix4f().initPerspective(fov, aspectRatio, zNear, zFar);
	}

	public Camera(Vector3f pos, Vector3f forward, Vector3f up)
	{
		this.pos = pos;
		this.forward = forward.normalized();
		this.up = up.normalized();
	}

	public Matrix4f getViewProjection()
	{
		Matrix4f cameraRotation = new Matrix4f().initRotation(forward, up);
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(-pos.getX(), -pos.getY(), -pos.getZ());
		return projection.mul(cameraRotation.mul(cameraTranslation));
	}

	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

	public void input(float delta)
	{
		float sensitivity = 0.1f;
		float movAmt = (float) (10 * delta);
		float rotAmt = (float) (100 * Time.getDelta());

		if(Input.getKey(Input.KEY_ESCAPE))
		{
			Input.SetCursor(true);
			mouseLocked = false;
		}

		if(Input.getKey(Input.KEY_W))
			move(getForward(), movAmt);
		if(Input.getKey(Input.KEY_S))
			move(getForward(), -movAmt);
		if(Input.getKey(Input.KEY_A))
			move(getLeft(), movAmt);
		if(Input.getKey(Input.KEY_D))
			move(getRight(), movAmt);

		if(Input.GetMouseDown(0))

		{
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}

		if(mouseLocked)
		{

			Vector2f deltaPos = Input.GetMousePosition().sub(centerPosition);

			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;

			if(rotY)
				rotateY(deltaPos.getX() * sensitivity);
			if(rotX)
				rotateX(deltaPos.getY() * sensitivity);

			if(rotY || rotX)
				Input.SetMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
		}

		/*
		 * if (Input.getKey(Input.KEY_UP)) rotateX(-rotAmt); if
		 * (Input.getKey(Input.KEY_DOWN)) rotateX(rotAmt); if
		 * (Input.getKey(Input.KEY_LEFT)) rotateY(-rotAmt); if
		 * (Input.getKey(Input.KEY_RIGHT)) rotateY(rotAmt);
		 */

	}

	public void move(Vector3f dir, float amt)
	{
		pos = pos.add(dir.mul(amt));
	}

	public void rotateY(float angle)
	{
		Vector3f Haxis = yAxis.cross(forward).normalized();

		forward.rotate(angle, yAxis).normalized();

		up = forward.cross(Haxis).normalized();

	}

	public void rotateX(float angle)
	{
		Vector3f Haxis = yAxis.cross(forward).normalized();

		forward.rotate(angle, Haxis).normalized();

		up = forward.cross(Haxis).normalized();

	}

	public Vector3f getLeft()
	{
		Vector3f left = forward.cross(up).normalized();
		return left;
	}

	public Vector3f getRight()
	{
		Vector3f right = up.cross(forward).normalized();
		return right;
	}

	public Vector3f getPos()
	{
		return pos;
	}

	public void setPos(Vector3f pos)
	{
		this.pos = pos;
	}

	public Vector3f getForward()
	{
		return forward;
	}

	public void setForward(Vector3f forward)
	{
		this.forward = forward;
	}

	public Vector3f getUp()
	{
		return up;
	}

	public void setUp(Vector3f up)
	{
		this.up = up;
	}

}
