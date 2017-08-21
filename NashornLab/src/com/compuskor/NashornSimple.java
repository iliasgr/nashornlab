package com.compuskor;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class NashornSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");

		try {
			// create a js function and evaluate it
			// the function is now available

			engine.eval("function sum(a,b) { return a+b;} ");
			System.out.println(engine.eval("sum(1,3);"));

			// cast invocable to engine
			Invocable invocable = (Invocable) engine;
			// Use invocable to call the function
			System.out.println(invocable.invokeFunction("sum", 10, 2));

			// Bind a java interface to Javascript implementation
			Adder adder = invocable.getInterface(Adder.class);
			System.out.println(adder.sum(10, 20));

			// read js from a file -eval - use

			engine.eval(new FileReader("src\\greeter.js"));
			System.out.println(invocable.invokeFunction("greet", "ilias"));
			System.out.println(engine.eval("greet('Ilias')"));

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
