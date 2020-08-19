package algorithm.javaStudy.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Client
 * 1. Socket(ip address, port no) 생성 --> server에서 accept() 받을 수 있음
 * 2. Socket 으로부터 입출력 스트림 얻어냄
 * 3. 스트림으로 메시지 전송
 * 4. 스트림/소켓 닫기
 */
public class SimpleClient {

    public static void main(String[] args) throws Exception {
        Socket socket;
        InputStream in;
        DataInputStream dis;
        OutputStream out;
        DataOutputStream dos;
        String message = "똑바로 살아!!!";

        // 127.0.0.1 : loopback address
        socket = new Socket("127.00.0.1", 9870);
//        socket = new Socket("localhost", 9870);

        in = socket.getInputStream();
        dis = new DataInputStream(in);
        out = socket.getOutputStream(); // block
        dos = new DataOutputStream(out); // filter

//        message = dis.readUTF();
        System.out.println(dis.readUTF());
        dos.writeUTF(message);

        dis.close();
        in.close();
        dos.close();
        out.close();
        socket.close();
    }
}
