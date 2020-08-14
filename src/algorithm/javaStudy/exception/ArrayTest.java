package com.test;

public class ArrayTest {
    public static void main(String[] args) {
        String[] msg = {"spring", "summer", "fall", "winter"};

        for(int i=0;i<=msg.length;i++){
            try{
                System.out.println(msg[i]);
            }catch (ArrayIndexOutOfBoundsException e){
                // 예외가 발생했을 때 처리할 내용
                System.out.println("OOPS, SORRY!");
                System.out.println(e.getMessage()); // 발생한 예외 관련 메시지 리턴
            }finally {
                System.out.println("finally...");
            }
        }

        System.out.println("done...");
    }
}
