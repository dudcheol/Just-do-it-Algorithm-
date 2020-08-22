package javaStudy.io;

import java.io.*;

public class BufferPerformanceTest {
    public static void main(String[] args) {

        File src = new File("c:/Windows/explorer.exe");
        File target = new File("c:/Temp/copied_explorer.exe");

        // Buffered~~ 가 훨씬 빠르다
        try (FileInputStream fi = new FileInputStream(src);
             FileOutputStream fo = new FileOutputStream(target);
             BufferedInputStream bi = new BufferedInputStream(fi);
             BufferedOutputStream bo = new BufferedOutputStream(fo);) {

            copy("노드", fi, fo);
            copy("보조", bi, bo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(String type, InputStream input, OutputStream output) throws IOException {
        long start = System.nanoTime();
        byte[] cart = new byte[1024];
        int read = -1;
        while ((read = input.read(cart)) > 0) {
            output.write(cart, 0, read);
        }
        long end = System.nanoTime();
        System.out.println(type + ", 소요 시간: " + (end - start) + "ns");
    }
}