package javaStudy.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 * 1. ServerSocket 생성
 * 2. ServerSocket.accept(); 클라이언트가 접속해 오기를 기다렸다가 받음
 * 3. Socket으로부터 입출력 스트림 얻기
 * 4. 스트림으로 메시지 전송
 * 5. Socket 닫기
 */
public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server;
        Socket socket;

        OutputStream out;
        DataOutputStream dos; // 가지고 있는 메소드 중에 utf-8 처리해주는 애가 있음
        InputStream in;
        DataInputStream dis;
        String message = "hello, net world!"; // 클라이언트로 보낼 메시지

        server = new ServerSocket(9870);
        System.out.println("클라이언트를 기다리는 중...");

        socket = server.accept();
        System.out.println("클라이언트 접속!!!");

        out = socket.getOutputStream(); // block
        dos = new DataOutputStream(out); // filter
        in = socket.getInputStream();
        dis = new DataInputStream(in);

        dos.writeUTF(message); // client한테 출력됨
        message = dis.readUTF();
        System.out.println(message);

        dos.close();
        out.close();
        dis.close();
        in.close();

        socket.close();
        server.close();
    }
}
