package com.lambda;
/* 람다식 : 함수형 언어에 존재하던 기능(자바스크립트)
 * 함수가 데이터 타입중에 하나.
 * int a = 9;
 * a = void go(){} << 함수자체가 저장가능
 * String go(int x){}
 * 함수를 변수에 저장할 수도 있고, 파라메터로 이용할 수도 있고, 리턴타입으로도 사용 가능 
 * 람다식 형태 >>
 * (파라메터, , , ,) -> (실행문)
 * 
 */

@FunctionalInterface
interface Work {
	int job(int a, int b);
}

class MyWork implements Work {

	@Override
	public int job(int a, int b) {
		System.out.println("MyWork");
		return a + b;
	}

}

class Person {
	public void test(Work w) {
		int num = w.job(3, 5);
		System.out.println(num);
	}
}

public class LambdaTest {

	public static void main(String[] args) {
		Person tom = new Person();

//		tom.test(new MyWork()); // 자식 클래스 사용
		tom.test(new Work() { // 무명 클래스 사용

			@Override
			public int job(int a, int b) {
				System.out.println("tom");
				return a * b;
			}

		});

		// with lambda
		tom.test((a, b) -> {
			return a - b;
		});

		tom.test((a, b) -> {
			return 2 * a + 2 * b;
		});
	}

}
