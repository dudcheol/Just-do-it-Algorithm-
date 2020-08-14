package com.test;
// runtime exception
class Car{
    int num;
}

public class ExceptionTest {
    Car c; // null

    public static void main(String[] args) {
        new ExceptionTest().go();
    }

    private void go() {
        int a = 8, b = 0;

        try {
            System.out.println(c.num);
            System.out.println(a / b);
        } catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex){
            c = new Car();
            System.out.println("c.num:" + c.num);
        }

        System.out.println("End...");
    }

}
