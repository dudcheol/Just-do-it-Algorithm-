package javaStudy.thread;

// 2. Runnable Interface를 구현해서 thread 만드는 방법
class Animal {
}

class Lion extends Animal implements Runnable {

    @Override
    public void run() { // thread의 작업 내용
        Thread t = Thread.currentThread(); // 현재 이 문장을 실행하고 있는 thread를 알아내는 메소드
        System.out.println("thread is running..." + t.getName());
    }
}

public class LionTest {

    public static void main(String[] args) {
        Lion l1 = new Lion(); // Thread아님. Runnable 객체(run()메소드를 가지고 있는 객체)
        Thread t1 = new Thread(new Tiger());
        t1.start();

        Lion l2 = new Lion(); // Thread아님. Runnable 객체(run()메소드를 가지고 있는 객체)
        Thread t2 = new Thread(l2);
        t2.start();

        Lion l3 = new Lion(); // Thread아님. Runnable 객체(run()메소드를 가지고 있는 객체)
        Thread t3 = new Thread(l3);
        t3.start();
    }
}
