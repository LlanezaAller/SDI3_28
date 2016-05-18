package com.sdi.actions.impl;

import com.sdi.actions.Action;

public class ExitAction implements Action{

	@Override
	public void execute() {
		System.out.println("Adios!");
		System.exit(0);
	}
	@Override
	public String describe() {
		return "Salir";
	}
}
