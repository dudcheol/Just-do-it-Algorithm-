package javaStudy.study._0721;

// 파일 하나에 public 클래스는 하나뿐이어야 한다.
// 또한 해당 클래스는 파일명과 동일해야 한다.
// 한 파일에 클래스는 여러개가 있을 수 있다.
public class First {
    // main이 없으면 실행할 수 없다.
    // 이클립스에서는 파일명과 같은 클래스 내부의 main문을 찾아 먼저 실행하도록 되어있다.
}

class Second {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}

class Third {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}