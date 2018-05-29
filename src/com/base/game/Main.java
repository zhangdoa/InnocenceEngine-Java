package com.base.game;

import com.base.engine.core.CoreEngine;

public class Main {
	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine(60.0, new TestGame());
		engine.createWindow(1280, 720, "Innocence Engine 0.0.1 - Java");
		engine.start();
	}
}
