package algorithm.javaStudy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyAutoClosable {

    public static void main(String[] args) {
        /* try with resources(스트림) : 예외 발생 여부와 관계없이 자원 사용 후 반납(close)이 자동으로 이루어짐.

         try(스트림 생성){
            // 스트림 사용하는 문장 ...
            // 사용한 스트림 닫아주는 부분 사라짐 (!!!!)
         } catch(Exception e){
            // 예외처리
         }

         * */
        try (// 새로운 형태의 try 모양
                // fis : 파일에서 입력을 받는 (읽어오는) 스트림
                FileInputStream fis = new FileInputStream("src/algorithm/javaStudy/io/FileCopy.java");
                // fos : 파일에 출력을 하는 (쓰는) 스트림
                FileOutputStream fos = new FileOutputStream("src/algorithm/javaStudy/io/FileCopy2.java");) {

            for (int i = 0; (i = fis.read()) != -1; ) {
                System.out.println(i);
                fos.write(i);
            }
//            fis.close();
//            fos.close();
            System.out.println("file copied...!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
