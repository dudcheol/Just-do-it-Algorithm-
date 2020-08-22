package com.test;

public class ExceptionTest2 {

    public static void main(String[] args) {
        try {
            Thread.sleep(3000); // static. 객체 생성 안하고 클래스 이름으로 바로 호출 가능
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world...");
    }
}
