package com.test;

import java.io.*;

// checked exception
public class ExceptionTest3 {

    public static void main(String[] args) {
        File file;
        try {
            FileInputStream fis = new FileInputStream("hello.txt"); // 파일 입력 파이프
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println("파일이 없습니다");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
