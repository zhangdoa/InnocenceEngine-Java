package com.base.game;

import com.base.engine.core.CoreEngine;

public class Main {
	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine(60.0, new TestGame());
		engine.createWindow(1024, 768, "Innocence Engine 0.5");
		engine.start();

	}
}
