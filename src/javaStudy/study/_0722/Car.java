package javaStudy.study._0722;

public class Car {
    // data == field == property : field는 기본값을 가짐. 전역 변수
    int num; // 기본값 0
    String model; // 참조형 필드는 기본값으로 null을 가짐
    String owner;

    // 생성자
    // 클래스 안에 생성자가 없으면 컴파일러가 자동으로 생성자를 제공해 줌 -> 디폴트생성자
    Car() {
        System.out.println("Car 생성");
    }

    void run() {
        System.out.println("run...");
    }

    void stop() {
        System.out.println("stop...");
    }

    void info() {
        System.out.println("num:" + num);
        System.out.println("model:" + model);
        System.out.println("owner:" + owner);
    }

    public static void hiCar(){
        System.out.println("Car!!");
    }

    // 현재 Car에는 main이 없어 자체로 실행할 수 없게 됨.

}
