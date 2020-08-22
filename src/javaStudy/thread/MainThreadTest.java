package javaStudy.thread;

public class MainThreadTest implements Runnable {

    MainThreadTest() {
        // thread 생성 후 start
        Thread t = new Thread(this); // Runnable 객체 ?
        t.start(); // run() 으로 감
        t.setPriority(Thread.MAX_PRIORITY);
        try {
            t.join(); // t가 작업을 마칠 때 까지 다른 thread가 기다려줌
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() { // thread의 작업 내용을 가진 메소드
        System.out.println("run by thread...");
    }

    public static void main(String[] args) {
        MainThreadTest m = new MainThreadTest();
        m.go();
    }

    private void go() {
        System.out.println("gogogo...!");
    }
}
