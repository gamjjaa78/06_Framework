package edu.kh.project.test;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public int subtract(int a, int b) {
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		if(b==0) {
			//잘못된 전달인자 예외
			throw new IllegalArgumentException("0으로 나눌수없어");
		}
		return a / b;
	}

}
