package algorithm.javaStudy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {

    public static void main(String[] args) {
        try {
            // fis : 파일에서 입력을 받는 (읽어오는) 스트림
            FileInputStream fis = new FileInputStream("src/algorithm/javaStudy/io/FileCopy.java");

            // fos : 파일에 출력을 하는 (쓰는) 스트림
            FileOutputStream fos = new FileOutputStream("src/algorithm/javaStudy/io/FileCopy2.java");

            for (int i = 0; (i = fis.read()) != -1; ) {
                System.out.println(i);
                fos.write(i);
            }

            fis.close();
            fos.close();
            System.out.println("file copied...!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
