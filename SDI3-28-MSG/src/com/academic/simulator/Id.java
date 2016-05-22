package com.academic.simulator;

public class Id {
	private static int value = 0;
	public static int next(){
		return value++;
	}
}
