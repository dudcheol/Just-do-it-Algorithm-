package javaStudy.io;

import java.io.*;

class Card implements Serializable {
    int num;
    String name;

    public Card(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 직렬화 : 객체가 스트림을 통해 이동될 때 이동하려면 직렬화가 되어 있어야 함
// 직렬화란 객체가 스트림을 통해 이동될 때 객체안의 데이터가 연속된 바이트 단위로 변환되어 전송되는 작업
public class CardSave {

    public static void main(String[] args) throws Exception {
        Card mycard = new Card(12345, "billy");

        // Card 객체를 파일에 저장
        FileOutputStream fos = new FileOutputStream("test.dat"); // node stream
        ObjectOutputStream oos = new ObjectOutputStream(fos); // filter stream
        oos.writeObject(mycard);

        oos.close();
        fos.close();

        // Card 객체를 파일에서 불러오기
        FileInputStream fis = new FileInputStream("test.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Card c = (Card)ois.readObject();

        System.out.println(c.getNum());
        System.out.println(c.getName());

        ois.close();
        fos.close();
    }
}
