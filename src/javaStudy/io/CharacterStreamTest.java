package javaStudy.io;

import java.io.*;

public class CharacterStreamTest {

    public static void main(String[] args) throws Exception {
        // InputStreamReader : InputStream 타입의 스트림을 -> Reader 타입으로 변경시켜 줌.
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

        File f = File.createTempFile("sample", ".java");
        FileWriter fw = new FileWriter(f); // fw : node stream(도착점에 직접 연결되어 있음)
        BufferedWriter bw = new BufferedWriter(fw); // * process stream(=filter stream) : 노드스트림이 없으면 안됨! 먼저 노드스트림을 만들고 필터스트림을 만들 때 사용해야함.
        bw.write("빨리");
        bw.write("놀러가고");
        bw.write("싶다");

        bw.close();
        fw.close();

        //////////////////////////////////////////
        FileReader fr = new FileReader(f); // node stream
        BufferedReader br = new BufferedReader(fr); // filter stream

        for (String str; (str = br.readLine()) != null; ) {
            System.out.println(str);
        }

        br.close();
        fr.close();

    }
}
